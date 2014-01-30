var Explore = window.Explore = {};

// -- Explore Log -- //
Explore.log = function(message, tag){
	if(console && console.log != undefined){
		tag = tag || "[EXPLORE]";
		console.log(tag);
		console.log(message);
	}
}

var initializerList = [];

Explore.showError = function(errorText){
	Explore.log(errorText);
}

Explore.captureClick = function(e){
	var target = e.currentTarget;
	if(target.dataset.local){
		e.preventDefault();
		e.stopPropagation();
		Explore.NavigationManager.handleLinkClick(target.href);
	}
}

Explore.addInitializer = function(initializer){
	initializerList.push(initializer);
}

Explore.addInitializer(function(){
	var container = $("#menuOverlay");
	var button = $("#buttonMenu");

	$(document).mouseup(function (e){
   		if (!button.is(e.target) && !container.is(e.target) && container.hasClass("open")){
        	button.removeClass("open");
			container.removeClass("open");
    	}
	});

	$("#buttonMenu").click(function(e){
		e.preventDefault();
		$("#buttonMenu").toggleClass("open");
		$("#menuOverlay").toggleClass("open");
	});
});

$(function __INITIALIZER__(){
	for(var i = 0; i < initializerList.length; i++){
		initializerList[i].call(window);
	}

	$("a").click(Explore.captureClick);
});