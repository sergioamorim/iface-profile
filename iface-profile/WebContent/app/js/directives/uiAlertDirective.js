angular.module("listaTelefonica").directive("uiAlert", function (){
	return {
		templateUrl: "app/view/alert.html", 
		replace: true, 
		restrict: "AE",
		scope:{
			title: "@title"
		},
		transclude: true
	};
});