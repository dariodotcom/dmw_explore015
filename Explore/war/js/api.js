var Api = Explore.Api = {};

var endpointUrl = "/api";

function wrapCallback(callback){
	return function(data){
		if(data.type == undefined){
			var errorJson = data.responseJSON;
			if(errorJson.type == "ERROR"){
				callback.call(null, errorJson.details);
				return;
			}
			
			Explore.log("Received error response " + data.responseText);
			return;
		}

		callback.call(null, data.details);
	}
}

var ApiClient = {
	get: function _get(url, onSuccess, onError){
		$.getJSON(endpointUrl + url)
			.done(wrapCallback(onSuccess))
			.fail(wrapCallback(onError));
	},
	post: function _post(url, data, onSuccess, onError){
		var json = JSON.stringify(data);
		Explore.log(json);
		$.ajax(endpointUrl + url, {
			type: "POST",
			contentType: "application/json",
			data: json})
				.done(wrapCallback(onSuccess))
				.fail(wrapCallback(onError));
	}
}

Api.User = {
	getCurrent: function _getCurrent(callback){
		ApiClient.get("/user/current", callback);
	},
	login: function _login(loginData, onSuccess, onError){
		ApiClient.post("/user/login", loginData, onSuccess, onError);
	},
	logout: function _logout(onSuccess, onError){
		ApiClient.get("/user/logout", onSuccess, onError);
	},
	register: function _register(registrationData, onSuccess, onError){
		ApiClient.post("/user/register", registrationData, onSuccess, onError);
	}
}

Api.Exhibition = function(id){
	this.context = "/exhibition/id/" + id;
}

Api.Exhibition.search = function _search(term, onSuccess, onError){
	ApiClient.get("/exhibition/search/" + term, onSuccess, onError);
}

Api.Exhibition.top = function _search(onSuccess, onError){
	ApiClient.get("/exhibition/top", onSuccess, onError);
}

Api.Exhibition.prototype = {
	getDetails: function getDetail(onSuccess, onError){
		ApiClient.get(this.context, onSuccess, onError);
	},
	getReviews: function getReviews(onSuccess, onError){
		ApiClient.get(this.context + "/reviews", onSuccess, onError);
	},
	review: function review(reviewData, onSuccess, onError){
		ApiClient.post(this.context + "/review", reviewData, onSuccess, onError);
	},
	checkIn: function checkin(onSuccess, onError){
		ApiClient.get(this.context + "/checkin", onSuccess, onError);
	}
}

Api.Review = function(id){
	this.context = "/review/id/" + id;
}

Api.Review.prototype = {
	getDetails: function getDetail(onSuccess, onError){
		ApiClient.get(this.context, onSuccess, onError);
	},
	appreciate: function appreciate(positive, onSuccess, onError){
		var url = this.context + "/appreciate/" + (positive ? "up" : "down");
		ApiClient.get(url, onSuccess, onError);
	}
}