angular.module("iFace").controller("profileCtrl", function($scope, $location, $cookies, profileAPI, friendshipAPI) {
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