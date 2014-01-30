function NavigationManager(){
	$(window).on("popstate", this.onPopState.bind(this));
	$(document).ready(this.onStart.bind(this));
}

NavigationManager.prototype = {
	navigateTo: function _navigateTo(path){
		try{
			this.parseRequest(path);
		}catch(e){
			Explore.log("Invalid path " + path);
			return;
		}

		var state = {path: path};
		history.pushState(state, "", path);

	},

	parseRequest: function _parseRequest(path){
		var parts = path.split("/");
		Explore.log(parts);
		switch(parts[1]){
			case "":
				Explore.ViewManager.showView("home");
				break;
			case "exhibition":
				this.handleExhibitionRequest(parts.slice(2));
				break;
			case "auth":
				this.handleAutenticationRequest(parts[2]);
				break;
			case "search":
				Explore.ViewManager.showView("search");
				break;
			case "top":
				Explore.ViewManager.showChartView();
				break;
			case "profile":
				Explore.ViewManager.showProfile();
				break;
		}
	},

	onPopState: function _onPopState(e){
		this.parseRequest(e.originalEvent.state.path);
	},

	onStart: function _onStart(){
		this.parseRequest(location.pathname);
	},

	handleLinkClick: function _handleLinkClick(href){
		var host = location.protocol + "//" + location.host;
		var path = href.replace(host, "");

		this.navigateTo(path);
	},

	handleAutenticationRequest: function _handleAutenticationRequest(request){
		switch(request){
		case "login":
			Explore.ViewManager.showView("login");
			break;
		case "register":
			Explore.ViewManager.showView("registration");
			break;
		case "logout":
			Api.User.logout();
			Explore.ViewManager.showView("home");
			break;
		default:
			throw new Error();
		}
	},

	handleExhibitionRequest: function _handleExhibitionRequest(request){
		switch(request[0]){
		case "id":
			Explore.ViewManager.showExhibition(request[1]);
			break;
		case "review":
			var parts = request[1].split(";");
			Explore.ViewManager.showReviewForm(parts[0], decodeURI(parts[1]));
			break;
		}

		Explore.log(request);
	}
}

Explore.NavigationManager = new NavigationManager();