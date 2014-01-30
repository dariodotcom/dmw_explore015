var Api = Explore.Api = {};

var ApiClient = function(){
	this.endpointUrl = "/api";
	this.overlay = $(".waitingOverlay");
	this.overlay.hide();
}

ApiClient.prototype = {
	wrapCallback: function _wrapCallback(callback){
		return function(data){
			if(data.type == undefined){
				var errorJson = data.responseJSON;
				if(errorJson.type == "ERROR"){
					callback.call(null, errorJson.details);
					this.getOverlay().hide();
					return;
				}
			
				Explore.log("Received error response " + data.responseText);
				this.getOverlay().hide();
				return;
			}

			callback.call(null, data.details);
			this.getOverlay().hide();
		}.bind(this);
	},
	getOverlay: function _getOverlay(){
		if(this.overlay.length == 0){
			this.overlay = $(".waitingOverlay");
		}

		return this.overlay;
	},
	get: function _get(url, onSuccess, onError){
		this.getOverlay().show();
		$.getJSON(this.endpointUrl + url)
			.done(this.wrapCallback(onSuccess))
			.fail(this.wrapCallback(onError));
	},
	post: function _post(url, data, onSuccess, onError){
		this.getOverlay().show();
		var json = JSON.stringify(data);
		Explore.log(json);
		$.ajax(this.endpointUrl + url, {
			type: "POST",
			contentType: "application/json",
			data: json})
				.done(this.wrapCallback(onSuccess))
				.fail(this.wrapCallback(onError));
	}
}

var apiClient = new ApiClient();

Api.User = {
	getCurrent: function _getCurrent(callback){
		apiClient.get("/user/current", callback);
	},
	login: function _login(loginData, onSuccess, onError){
		apiClient.post("/user/login", loginData, onSuccess, onError);
	},
	logout: function _logout(onSuccess, onError){
		apiClient.get("/user/logout", onSuccess, onError);
	},
	register: function _register(registrationData, onSuccess, onError){
		apiClient.post("/user/register", registrationData, onSuccess, onError);
	}
}

Api.Exhibition = function(id){
	this.context = "/exhibition/id/" + id;
}

Api.Exhibition.search = function _search(term, onSuccess, onError){
	apiClient.get("/exhibition/search/" + term, onSuccess, onError);
}

Api.Exhibition.top = function _search(onSuccess, onError){
	apiClient.get("/exhibition/top", onSuccess, onError);
}

Api.Exhibition.prototype = {
	getDetails: function getDetail(onSuccess, onError){
		apiClient.get(this.context, onSuccess, onError);
	},
	getReviews: function getReviews(onSuccess, onError){
		apiClient.get(this.context + "/reviews", onSuccess, onError);
	},
	review: function review(reviewData, onSuccess, onError){
		apiClient.post(this.context + "/review", reviewData, onSuccess, onError);
	},
	checkIn: function checkin(onSuccess, onError){
		apiClient.get(this.context + "/checkin", onSuccess, onError);
	}
}

Api.Review = function(id){
	this.context = "/review/id/" + id;
}

Api.Review.prototype = {
	getDetails: function getDetail(onSuccess, onError){
		apiClient.get(this.context, onSuccess, onError);
	},
	appreciate: function appreciate(positive, onSuccess, onError){
		var url = this.context + "/appreciate/" + (positive ? "up" : "down");
		apiClient.get(url, onSuccess, onError);
	}
}