angular.module("iFace").controller("profileCtrl", function($scope, $location, $cookies, profileAPI) {
	var currentUser = $cookies.getObject("user");
	if(currentUser != undefined){
		profileAPI.getByUserId(currentUser.id).success(function(data){
			console.log(data);
			$scope.userProfile = data;
			if(data.length == 0){
				$location.path("/new_profile");
				
			}
		});
	}else{
		$location.path("/signup");
	}
	
	$scope.openEditProfile = function(){
		$location.path("/edit_profile");
	}
});