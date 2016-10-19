angular.module("iFace").controller("profileCtrl", function($scope, $location, $routeParams, $cookies, profileAPI, friendshipAPI) {
	var currentUser = $cookies.getObject("user");
	
	if(currentUser != undefined){
		var id = currentUser.id
		if($routeParams.id != undefined)
			id = $routeParams.id
		profileAPI.getByUserId(id).success(function(data){
			console.log(data);
			$scope.userProfile = data;
			if($routeParams.id != undefined)
				$('#amigos').val(data.name+" "+data.lastname);
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
	
	$scope.$watch('amigoProcurado', function(scope){
		if($scope.amigoProcurado != undefined){
			var idUser = $scope.amigoProcurado.description.id;
			$location.path("/index/"+idUser);
		}
	   }, true);
	
});