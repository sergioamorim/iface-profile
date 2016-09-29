angular.module("iFace").controller("profileCtrl", function($scope, $location, profileAPI) {
	$scope.app = "Profile";
	
	profileAPI.getByUserId(1).success(function(data){
		$scope.userProfile = data;
		if(!data.user.haveProfile)
			$location.path("/new_profile");
	});
});