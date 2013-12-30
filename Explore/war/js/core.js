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

Explore.addInitializer = function(initializer){
	initializerList.push(initializer);
}

$(function __INITIALIZER__(){
	for(var i = 0; i < initializerList.length; i++){
		initializerList[i].call(window);
	}

	$("a").click(function(e){
		var target = e.currentTarget;
		if(target.dataset.local){
			e.preventDefault();
			Explore.log("click to " + target.href + " intercepted");

		}
	});

})