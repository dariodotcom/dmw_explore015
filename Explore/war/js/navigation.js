function NavigationManager(){

}

NavigationManager.prototype = {
	navigateTo: function _navigateTo(){
		
	},
	handleLinkClick: function _handleLinkClick(href){
		Explore.log("click to " + href,"Navigation Manager");
	}
}

Explore.NavigationManager = new NavigationManager();