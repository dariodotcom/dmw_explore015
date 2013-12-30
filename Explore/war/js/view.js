var ViewManager = function _viewManager() {
	this.views["login"] = $("#loginView");
	this.views["registration"] = $("#registrationView");
	this.views["reviewCreation"] = $("#reviewCreationView");
	this.views["search"] = $("#searchView");
	this.views["exhibition"] = $("#exhibitionView");
	this.views["login"] = $("#loginView");

}

ViewManager.prototype = {
	views: {},
	currentView: null,
	showView: function _showView(viewName){
		var newView = this.views[viewName];

		if(newView == undefined){
			return;
		}

		if(this.currentView != null){
			this.currentView.removeClass("current");
		}

		newView.addClass("current");
		this.currentView = newView;
	}
};

// LoginView
var LoginView = {

	handleLoginError: function _handleLoginError(e){
		var msg = "Login details incorrect.";
		Explore.showError(msg);
	},

	handleLoginSuccess: function _handleLoginSuccess(e){
		Explore.log(e);
	},

	init: function _init(){
		var form = $("#loginForm");
		form.submit(function(e){
			e.preventDefault();

			var loginData = {
				username: $("#username").val(),
				password: $("#password").val()
			}

			if(loginData.username == "" || loginData.password == ""){
				Explore.showError("Please fill all forms");
				return;
			}

			Explore.Api.User.login(
				loginData,
				LoginView.handleLoginSuccess,
				LoginView.handleLoginError);
		});
	}
}

var RegistrationView = {

	handleRegistrationError: function _handleRegistrationError(e){
		var msg;

		switch(e.type){
			case "DUPLICATE_ENTITY":
				msg = "Username not available";
				break;
			default:
				msg = "Error " + e.type + " occurred";
		}

		Explore.showError(msg);
	},

	handleRegistrationSuccess: function _handleRegistrationSuccess(e){
		Explore.log(e);
	},

	init: function _init(){
		var form = $("#registrationForm");
		form.submit(function(e){
			e.preventDefault();

			var registrationData = {
				username: form.find("input[name=username]").val(),
				name: form.find("input[name=name]").val(),
				surname: form.find("input[name=surname]").val(),
				password: form.find("input[name=password]").val()
			}

			Explore.log(registrationData);

			if(registrationData.password != form.find("input[name=confirmPassword]").val()){
				Explore.showError("Passwords do not match");
				return;
			}

			Explore.Api.User.register(
				registrationData,
				RegistrationView.handleRegistrationSuccess,
				RegistrationView.handleRegistrationError);
		});
	}
}

var ExhibitionView = function(){
	this.image = $("#exhibitionImage");
	this.name = $("#exhibitionImage .exhibitionName");
	this.description = $("#description .text");
	this.checkInButton = $("#checkinBtn");
	this.reviewButton = $("#reviewBtn");
	this.reviewContainer = $("#reviewContainer");
	this.stars = new Stars($("#description .stars"), false, true);
}

ExhibitionView.prototype = {
	handleCheckin: function _handleCheckIn(id){
		new Api.Exhibition(id).checkIn(
			this.updateButtonVisibility.bind(this),
			function(data){
				Explore.showError(data.type);
			}

		);
	},

	handleReview: function _handleReview(id){
		Explore.log("review in exhibition " + id);
	},

	handleLoadError: function _handleLoadError(data){
		Explore.log(data);
	},

	handleLoadSuccess: function _handleLoadSuccess(data){
		Explore.log(data);

		this.name.html(data.name);
		this.description.html(data.description);
		this.stars.setValue(data.grade);
		this.stars.setCount(data.reviewCount);

		this.updateButtonVisibility(data);
	},

	updateButtonVisibility: function _updateButtonVisibility(data){
		if(!data.checkinable){
			this.checkInButton.click(null);
			this.checkInButton.hide();
		} else {
			this.checkInButton.click(this.handleCheckin.bind(this, data.id));
		}

		if(!data.reviewable){
			this.reviewButton.removeAttr("href");
			this.reviewButton.hide();
		} else {
			this.reviewButton.attr("href", "/exhibition/" + data.id + "/review");
		}
	},

	wrapCallback: function _wrapCallback(handler, callback){
		var self = this;
		return function(data){
			handler.call(self, data);
			if(callback){
				callback.call(null);
			}
		}
	},

	handleReviewLoadError: function _handleReviewLoadError(data){
		Explore.log(data);
	},

	createAppreciationHandler: function _createAppreciationHandler(id, button, positive){
		var self = this;
		return function(event){
			event.preventDefault();
			Explore.log("appreciating review " + id + ", positive? " + positive);
		}
	},

	handleReviewLoadSuccess: function _handleReviewLoadSuccess(data){
		var reviews = data.reviews;
		var reviewContainer = $("#reviewContainer");

		if(reviews.length == 0){
			Explore.log("no reviews to show");
			return;
		}

		for(var i = 0; i<reviews.length; i++){
			reviewContainer.append(new Review(reviews[i]).getHtmlElem());
		}
	},

	showExhibition: function _showExhibition(id, onSuccess, onError){
		var api = new Api.Exhibition(id);
		api.getDetails(
			this.wrapCallback(this.handleLoadSuccess, onSuccess),
			this.wrapCallback(this.handleLoadError, onError)
		);

		api.getReviews(
			this.handleReviewLoadSuccess.bind(this),
			this.handleReviewLoadError.bind(this)
		);
	}
}


// Tools
var Stars = function(jElem, interactive, withCount){
	this.interactive = interactive;
	this.stars = [];

	if(interactive){
		jElem.addClass("interactive");
	}

	for(var i=0; i<5; i++){
		var star = $("<span class=\"star\">&nbsp;</span>");
		this.stars.push(star);
		star.appendTo(jElem);
	}

	if(withCount){
		var count = this.count = $("<span class=\"reviewCount\"></span>");
		count.appendTo(jElem);
	}
}

Stars.prototype = {
	setValue: function(value){
		for(var i = 0; i < 5; i++){
			if(value < i){
				return;
			}

			if(value >= i + 1){
				this.stars[i].removeClass("half");
				this.stars[i].addClass("full");
				continue;
			}

			if(value >= i + 0.5){
				this.stars[i].addClass("half");
				this.stars[i].removeClass("full");
				return;
			}
		}
	},
	setCount: function(num){
		this.count.html(num + " review" + (num > 1 ? "s" : ""));
	}
}

var Review = function(reviewData){
	this.review = reviewData;
	var elem = this.htmlElem = this.model.clone();

	elem.find(".author").html(reviewData.author);
	elem.find(".text").html(reviewData.text);

	this.updateAppreciationStatus();

	new Stars(elem.find(".stars"), false, false).setValue(reviewData.mark);

	var likeButton = elem.find(".appreciation.like");
	var dislikeButton = elem.find(".appreciation.dislike");

	if(reviewData.appreciable){
		elem.addClass("appreciable");  
		likeButton.click(this.onLikeListener.bind(this));
		dislikeButton.click(this.onDislikeListener.bind(this));
	}
}

Review.prototype = {
	model: $("<div class=\"review\"></div>")
			.append($("<div class=\"header\"><span class=\"author\"></span><div class=\"stars\"></div></div>"))
			.append($("<p class=\"text\"></p>"))
			.append($("<div class=\"appreciationWrapper\"><span class=\"userAppreciation\"></span><a class=\"appreciation dislike\"></a><a class=\"appreciation like\"></a></div>")),

	getHtmlElem: function _getHthmlElem(){
		return this.htmlElem;
	},

	updateAppreciationStatus: function _updateAppreciationStatus(){
		var likeButton = this.htmlElem.find(".appreciation.like");
		var dislikeButton = this.htmlElem.find(".appreciation.dislike");

		likeButton.html(this.review.positiveAppreciations);
		dislikeButton.html(this.review.negativeAppreciations);

		if(!this.review.userAppreciation){
			return;
		}

		var userAppreciation = this.htmlElem.find(".userAppreciation");

		switch(this.review.userAppreciation){
			case "POSITIVE":
				userAppreciation.removeClass("dislike");
				userAppreciation.addClass("like");
				userAppreciation.html("Liked");
			break;
			case "NEGATIVE":
				userAppreciation.removeClass("like");
				userAppreciation.addClass("dislike");
				userAppreciation.html("Disliked");
			break;
		}
	},

	onLikeListener: function _onLikeListener(e){
		Explore.log("Like " + this.review.id);
		var self = this;
		new Api.Review(this.review.id).appreciate(true,
			function(data){
				self.review = data;
				self.updateAppreciationStatus();
			}, function(data){
				Explore.showError(data);
			});
	},

	onDislikeListener: function _onDislikeListener(e){
		Explore.log("Dislike " + this.review.id);
		var self = this;
		new Api.Review(this.review.id).appreciate(false,
			function(data){
				self.review = data;
				self.updateAppreciationStatus();
			}, function(data){
				Explore.showError(data);
			});
	}
}

Explore.addInitializer(function(){
	Explore.ViewManager = new ViewManager();
	LoginView.init();
	RegistrationView.init();

	var exhibitionView = new ExhibitionView();
	ViewManager.prototype.showExhibition = function(id){
		exhibitionView.showExhibition(id);
		this.showView("exhibition");
	}

	Explore.ViewManager.showView("search");
});
