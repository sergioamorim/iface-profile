angular.module("iFace").controller("profileCtrl", function($scope, $location, $cookies, profileAPI) {
	var currentUser = $cookies.getObject("user");
	profileAPI.getByUserId(currentUser.id).success(function(data){
		console.log(data);
		$scope.userProfile = data;
		//if(!data.user.userProfile)
			//$location.path("/new_profile");
	});
	
	$scope.openEditProfile = function(){
		$location.path("/edit_profile")
	}
});