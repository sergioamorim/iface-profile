angular.module("iFace").controller("profileCtrl", function($scope, $location, $routeParams, $cookies, profileAPI, friendshipAPI) {
	var currentUser = $cookies.getObject("user");
	if(currentUser != undefined){
		var id = currentUser.id
		if($routeParams.id != undefined){
			id = $routeParams.id
		}
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
	$scope.isMyPerfil  = function(){
		return ($routeParams.id != undefined && $routeParams.id != currentUser.id)
	}	
	
	$scope.openEditProfile = function(){
		$location.path("/edit_profile");
	}
	$scope.requestfriendship = function(){
		profileAPI.getByUserId(currentUser.id).success(function(data){
			var user_x = data.user;
			profileAPI.getByUserId($routeParams.id).success(function(data){
				var user_y = data.user;
				friendshipAPI.requestfriendship(user_x,user_y).success(function(data){					
					
				});
			});
		});
	}
	$scope.$watch('amigoProcurado', function(scope){
		if($scope.amigoProcurado != undefined){
			var idUser = $scope.amigoProcurado.description.id;
			$location.path("/index/"+idUser);
		}
	   }, true);
	
});