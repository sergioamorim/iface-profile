angular.module("iFace").controller("profileCtrl", function($scope, $location, profileAPI) {
	profileAPI.getByUserId(1).success(function(data){
		console.log(data);
		$scope.userProfile = data;
		//if(!data.user.userProfile)
			//$location.path("/new_profile");
	});
});