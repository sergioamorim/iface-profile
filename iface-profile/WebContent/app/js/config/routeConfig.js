angular.module("iFace").config(function($routeProvider){
	$routeProvider.when("/signup", {
		templateUrl: "app/view/new_user.html",
		controller: "newUserCtrl"
	});
	$routeProvider.when("/index", {
		templateUrl: "app/view/profile.html",
		controller: "profileCtrl"
	});
	$routeProvider.when("/new_profile", {
		templateUrl: "app/view/new_profile.html",
		controller: "newProfileCtrl"
	});
	$routeProvider.when("/edit_profile", {
		templateUrl: "app/view/edit_profile.html",
		controller: "editProfileCtrl"
	});
});