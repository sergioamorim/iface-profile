angular.module("iFace").controller("profileCtrl", function($scope, $location, $cookies, profileAPI) {
	var currentUser = $cookies.getObject("user");
	alert(currentUser.id);
	profileAPI.getByUserId(1).success(function(data){
		console.log(data);
		$scope.userProfile = data;
		//if(!data.user.userProfile)
			//$location.path("/new_profile");
	});
});