angular.module("iFace").controller("profileCtrl", function($scope, $location, $routeParams, $cookies, profileAPI, friendshipAPI) {
	var currentUser = $cookies.getObject("user");
	
	if(currentUser != undefined){
		var id = currentUser.id
		if($routeParams.id != undefined)
			id = $routeParams.id
		profileAPI.getByUserId(id).success(function(data){
			//console.log(data);
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
	//so para teste em requestfriendship
	/*profileAPI.getByUserId(2).success(function(data){
		$scope.userprofile2=data;
	});*/
	
	//trocar id
	friendshipAPI.findFriendshipRequests(1).success(function(data){
		$scope.friendshipRequests=data;
		
	});
	
	
	$scope.openEditProfile = function(){
		$location.path("/edit_profile");
	}
	
	$scope.requestfriendship = function(){
		friendshipAPI.requestfriendship($scope.userProfile.user, $scope.userprofile2.user).success(function(data){
			
		});
	}
	$scope.acceptingfriendship = function(id_friendship){
		friendshipAPI.acceptingfriendship(id_friendship).success(function(data){
			
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