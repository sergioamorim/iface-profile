angular.module("iFace").controller("profileCtrl", function($scope, profileAPI) {
	$scope.app = "Profile";
	
	profileAPI.getByUserId(1).success(function(data){
		$scope.userProfile = data;
	});
});