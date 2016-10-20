angular.module("iFace").controller("profileCtrl", function($scope, $location, $routeParams, $cookies, profileAPI, friendshipAPI) {
	$scope.friends=[];
	$scope.tab = 0;
	var currentUser = $cookies.getObject("user");
	$scope.currentUser = $cookies.getObject("user");
	
	
	friendshipAPI.findFriends(currentUser.id).success(function(data){
		console.log(data);
		$scope.friends = data;
	});
	
	

	if(currentUser != undefined){
		var id = currentUser.id
		if($routeParams.id != undefined){
			id = $routeParams.id
			
		}
		profileAPI.getByUserId(id).success(function(data){
			$scope.userProfile = data;
			
			if($scope.isMyPerfil()){
				friendshipAPI.hasFriendship(currentUser.id,$routeParams.id).success(function(data){
					$scope.friendship=data;
					if($scope.friendship==""){
						$scope.buttonFriendship = "Adicionar aos amigos";
					}
					else{
						var statusFriendship = $scope.friendship.approved;
						if(statusFriendship)
							$scope.buttonFriendship = "Amigo";
						else{
							var idUser_x = $scope.friendship.user_x.id;
							if(idUser_x==currentUser.id){
								$scope.buttonFriendship = "Cancelar solicitação";
							}
							else{
								$scope.buttonFriendship = "Aceita Solicitação";
							}
						}
					}
				});
			}
			
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
		if($scope.buttonFriendship == "Adicionar aos amigos"){
			profileAPI.getByUserId(currentUser.id).success(function(data){
				var user_x = data.user;
				profileAPI.getByUserId($routeParams.id).success(function(data){
					var user_y = data.user;
					friendshipAPI.requestfriendship(user_x,user_y).success(function(data){					
					
					});
				});
			});
		}
		else if($scope.buttonFriendship == "Aceita Solicitação"){
			$scope.acceptingfriendship($scope.friendship.id);
		}
		else{
			$scope.refusefriendship($scope.friendship.id);
		}
	}
	$scope.acceptingfriendship = function(id_friendship){
		friendshipAPI.acceptingfriendship(id_friendship).success(function(data){
			
		});
	}
	$scope.refusefriendship = function(id_friendship){
		friendshipAPI.refusefriendship(id_friendship).success(function(data){
			
		});
	}
	$scope.getFriendshipRequests = function(){
		friendshipAPI.findFriendshipRequests(currentUser.id).success(function(data){
			$scope.findFriendshipRequests=data;
			console.log($scope.findFriendshipRequests);
			
		});
	}
	
	$scope.$watch('amigoProcurado', function(scope){
		if($scope.amigoProcurado != undefined){
			var idUser = $scope.amigoProcurado.description.id;
			$location.path("/index/"+idUser);
		}
	   }, true);
	
});