var ViewManager = function _viewManager() {
	this.views["login"] = $("#loginView");
	this.views["registration"] = $("#registrationView");
	this.views["reviewCreation"] = $("#reviewCreationView");
	this.views["search"] = $("#searchView");
	this.views["exhibition"] = $("#exhibitionView");
	this.views["login"] = $("#loginView");
	this.views["home"] = $("#homeView");
	this.views["chart"] = $("#chartView");
	this.views["profile"] = $("#profileView");

	this.exhibitionView = new ExhibitionView();
	this.reviewView = new ReviewView();
	this.chartView = new ChartView();
	this.profileView = new ProfileView();

	LoginView.init();
	RegistrationView.init();
	new SearchView();
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
	},
	showReviewForm: function _showReviewForm(exhibitionId, exhibitionName){
		this.reviewView.setReviewExhibition(exhibitionId, exhibitionName);
		this.showView("reviewCreation");
	},
	showExhibition: function _showExhibition(id){
		this.exhibitionView.showExhibition(id);
		this.showView("exhibition");
	},
	showChartView: function _showChartView(){
		this.chartView.update();
		this.showView("chart");
	},
	showProfile: function _showProfile(){
		this.profileView.update();
		this.showView("profile");
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
		Explore.NavigationManager.navigateTo("/profile");
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
		Explore.ViewManager.showView("home");
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
			this.reviewButton.attr("href", "/exhibition/review/" + data.id + ";" + data.name);
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
		reviewContainer.empty();

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

var SearchView = function(){
	this.emptyMessage = $("#searchView .messageContainer");
	this.resultContainer = $("#searchResult");
	this.searchTerm = $("#searchTerm");

	this.emptyMessage.hide();
	$("#searchForm").submit(this.performSearch.bind(this));
}

SearchView.prototype = {
	performSearch: function _performSearch(e){
		e.preventDefault();
		var term = this.searchTerm.val();

		if(term.length == 0){
			return;
		}

		Api.Exhibition.search(term,
			this.onSearchResults.bind(this),
			this.onSearchError.bind(this));
	},

	onSearchResults: function _onSearchResults(data){
		var results = data.results;

		if(results.length == 0){
			this.resultContainer.hide();
			this.emptyMessage.show();
			return;
		}

		this.emptyMessage.hide();
		this.resultContainer.empty();
		
		for(var i = 0; i < results.length; i++){
			var result = new SearchResult(results[i]);
			this.resultContainer.append(result.getHtml());
		}

		this.resultContainer.show();
		Explore.log(data.results);
	},

	onSearchError: function _onSearchError(data){
		Explore.showError(data);
	}
}

var ChartView = function(){
	this.emptyMessage = $("#chartView .messageContainer");
	this.resultContainer = $("#chartResult");
	this.emptyMessage.hide();
}

ChartView.prototype = {
	update: function _update(){
		Api.Exhibition.top(
			SearchView.prototype.onSearchResults.bind(this),
			SearchView.prototype.onSearchError.bind(this)
		)
	}

}

SearchResult = function(data){
	var htmlElem = this.htmlElem = this.model.clone();
	htmlElem.find(".name").html(data.name);
	new Stars(htmlElem.find(".stars"), false, false).setValue(data.grade);
	var link = htmlElem.find(".entry");
	link.attr("href","/exhibition/id/" + data.id);
	link.click(Explore.captureClick);
}

SearchResult.prototype = {
	model: $("<div class=\"entryContainer searchResultItem\"></div>")
		.append(
			$("<a class=\"entry\" data-local=\"true\"></a>")
				.append($("<span class=\"thumbnail\"></span>"))
				.append($("<span class=\"description\"></span>")
					.append($("<span class=\"name\"></span>"))
					.append($("<span class=\"stars\"></span>")))
				.append($("<span class=\"arrow\"></span>"))),
	getHtml: function _getHtml(){
		return this.htmlElem;
	}				
}

ReviewView = function(){
	this.id="";
	this.exhibitionName = $("#reviewCreationView .exhibitionName");
	this.stars = new Stars($("#reviewCreationView .stars"), true, false);
	this.commentField = $("#comment");
	this.tagController = new TagController($("#tagInput"), $("#tagwrapper .container"));

	$("#reviewForm").submit(this.handleFormSubmit.bind(this));
}

ReviewView.prototype = {
	setReviewExhibition: function _setReviewExhibition(id, name){
		this.id = id;
		this.exhibitionName.html(name);
	},

	handleFormSubmit: function _handleFormSubmit(e){
		e.preventDefault();

		if(this.id==""){
			return;
		}

		var reviewData =  {
			grade: this.stars.getValue(),
			text: this.commentField.val(),
			tags: this.tagController.getTags()
		}

		new Api.Exhibition(this.id).review(reviewData,
			this.handleReviewSuccess.bind(this),
			this.handleReviewError.bind(this));
	},

	handleReviewError: function _handleReviewError(data){
		Explore.showError(data);
	},

	handleReviewSuccess: function _handleReviewSuccess(){
		Explore.NavigationManager.navigateTo("/exhibition/id/" + this.id);
	}
}

var TagController = function(input, tagContainer){
	this.tags = [];
	this.elems = [];
	this.input = input;
	this.tagContainer = tagContainer;

	input.keydown(this.handleKeyDown.bind(this));
}

TagController.prototype = {
	tagModel: $("<span class=\"tag\"></span>"),
	getTags: function _getTags(){
		return this.tags;
	},
	handleKeyDown: function _handleKeyDown(e){
		switch(e.which){
		case 13: //Enter
			e.preventDefault();
			if(this.tags.length < 3){
				var tag = this.input.val();
				this.input.val("");

				if(!/^#/i.test(tag)){
					tag = "#" + tag;
				}

				this.tags.push(tag);

				var elem = this.tagModel.clone();
				elem.html(tag);
				this.tagContainer.append(elem);
				this.elems.push(elem);
			}

			break;

		case 8: //Backspace
			if(this.input.val().length > 0 || this.tags.length == 0){
				return;
			}

			e.preventDefault();
			this.elems.pop().remove();
			this.input.val(this.tags.pop());
			
			break;

		case 32: // Space
			e.preventDefault();
			break;
		default:
			if(this.tags.length == 3){
				e.preventDefault();
			}
		}
	}
}

var ProfileView = function(){
	var view = $("#profileView");
	this.loggedPanel = view.find(".loggedPanel");
	this.unloggedPanel = view.find(".unloggedPanel");
	this.name = view.find(".name");
	this.surname = view.find(".surname");
	this.username = view.find(".username");
}

ProfileView.prototype = {
	update: function(){
		Api.User.getCurrent(this.onDataReceived.bind(this));
	},
	onDataReceived: function _onDataReceived(data){
		Explore.log(data);
		if(data.username){
			this.unloggedPanel.hide();
			this.name.html(data.name);
			this.surname.html(data.surname);
			this.username.html(data.username);
		} else {
			this.loggedPanel.hide();
		}
		Explore.log(data);
	}
}


// Tools
var Stars = function(jElem, interactive, withCount){
	this.interactive = interactive;
	this.stars = [];
	this.value = 0;

	if(interactive){
		jElem.addClass("interactive");
	}

	for(var i=0; i<5; i++){
		var star = $("<span class=\"star\">&nbsp;</span>");
		this.stars.push(star);
		star.appendTo(jElem);
		if(interactive){
			star.click(this.setValue.bind(this, i+1));
		}
	}

	if(withCount){
		var count = this.count = $("<span class=\"reviewCount\"></span>");
		count.appendTo(jElem);
	}
}

Stars.prototype = {
	setValue: function(value){
		this.value = value;
		for(var i = 0; i < 5; i++){
			if(i + 1 <= value){
				this.stars[i].removeClass("half");
				this.stars[i].addClass("full");
			} else if(i + 0.5 <= value){
				this.stars[i].addClass("half");
				this.stars[i].removeClass("full");
			}else{
				this.stars[i].removeClass("half");
				this.stars[i].removeClass("full");
			}
		}
	},
	getValue: function _getValue(){
		return this.value;
	},
	setCount: function(num){
		this.count.html(num + " review" + (num > 1 ? "s" : ""));
	}
}

Explore.addInitializer(function(){
	Explore.ViewManager = new ViewManager();
});
