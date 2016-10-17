angular.module("iFace").controller("profileCtrl", function($scope, $location, $cookies, profileAPI, friendshipAPI) {
	var currentUser = $cookies.getObject("user");
	profileAPI.getByUserId(currentUser.id).success(function(data){
		console.log(data);
		$scope.userProfile = data;
		console.log($scope.userProfile.user);
		//if(!data.user.userProfile)
			//$location.path("/new_profile");
	});
	
	$scope.openEditProfile = function(){
		$location.path("/edit_profile");
	}
	
	$scope.requestfriendship = function(user_y){
		friendshipAPI.requestfriendship($scope.userProfile.user, user_y).success(function(data){
			
		});
	}
	$scope.acceptingfriendship = function(friendship){
		friendshipAPI.acceptingfriendship(friendship).success(function(data){
			
		});
	}
	$scope.refusefriendship = function(id){
		friendshipAPI.refusefriendship(id).success(function(data){
			
		});
	}
});