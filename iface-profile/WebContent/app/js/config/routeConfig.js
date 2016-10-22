angular.module("iFace").config(function($routeProvider){
	$routeProvider.when("/signup", {
		templateUrl: "app/view/new_user.html",
		controller: "newUserCtrl"
	});
	$routeProvider.when("/index", {
		templateUrl: "app/view/profile.html",
		controller: "profileCtrl"
	});
	$routeProvider.when("/index/:id", {
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
	$routeProvider.when("/log", {
		templateUrl: "app/view/log.html",
		controller: "logCtrl"
	});
	
	var $cookies;
	angular.injector(['ngCookies']).invoke(['$cookies', function(_$cookies_) {
		$cookies = _$cookies_;
		var user = {};
		user.id = 2;
		$cookies.putObject("user", user);
	}]);
});