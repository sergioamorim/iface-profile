angular.module("iFace").controller("logCtrl", function($scope, $location, $cookies, logAPI) {
	var currentUser = $cookies.getObject("user");
	logAPI.getByUserId(currentUser.id).success(function(data){
		$scope.userLogs = data;
	});
});