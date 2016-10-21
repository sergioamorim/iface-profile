angular.module("iFace").controller("profileCtrl", function($scope, $location, $routeParams, $cookies, profileAPI, friendshipAPI, relationshipAPI, logAPI) {
	$scope.friends=[];
	$scope.tab = 0;
	var currentUser = $cookies.getObject("user");
	$scope.currentUser = $cookies.getObject("user");
	
	var id2 = $routeParams.id;
	if ($routeParams.id == undefined) {
		id2 = currentUser.id;
	}
	friendshipAPI.findFriends(id2).success(function(data){
		console.log(data);
		$scope.friends = data;
	});
	
	relationshipAPI.getRelationships(id2).success(function(data){
		for(var i=0; i<data.length; i++){
			if(data[i].receiver.id == id2){
				data[i].kinship = data[i].relationshipType.senderDegreeOfKinship.degreeOfKinship;
				data[i].name = data[i].sender.userProfile.name+" "+data[i].sender.userProfile.lastname;
			}else{
				data[i].kinship = data[i].relationshipType.receiverDegreeOfKinship.degreeOfKinship;
				data[i].name = data[i].receiver.userProfile.name+" "+data[i].receiver.userProfile.lastname;
			}
		}
		$scope.relationships = data;
		
	});
	
	logAPI.getByUserId(currentUser.id).success(function(data){
		$scope.userLogs = data;
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
							$scope.buttonFriendship = "Cancelar amizade";
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
	$scope.clickButtonFriendship = function(){
		if($scope.buttonFriendship == "Adicionar aos amigos"){
			$scope.requestfriendship();
			$scope.buttonFriendship = "Cancelar solicitação";
		}
		else if($scope.buttonFriendship == "Aceita Solicitação"){
			$scope.acceptingfriendship($scope.friendship.id);
			$scope.buttonFriendship = "Cancelar amizade";
		}
		else{
			$scope.refusefriendship($scope.friendship.id);
			$scope.buttonFriendship = "Adicionar aos amigos";
		}
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
	$scope.acceptingfriendship = function(friendship_id){
		friendshipAPI.acceptingfriendship(friendship_id).success(function(data){
			$scope.popFriendshipRequest(friendship_id);
		});
	}
	$scope.refusefriendship = function(friendship_id){
		friendshipAPI.refusefriendship(friendship_id).success(function(data){
			$scope.popFriendshipRequest(friendship_id);
		});
	}
	$scope.getFriendshipRequests = function(){
		friendshipAPI.findFriendshipRequests(currentUser.id).success(function(data){
			$scope.findFriendshipRequests=data;
		});
	}
	$scope.popFriendshipRequest = function(friendship_id){
		$scope.findFriendshipRequests = $scope.findFriendshipRequests.filter(function (friendship){
			if(friendship.id!=friendship_id) 
				return friendship;
		});
	}
	
	$scope.$watch('amigoProcurado', function(scope){
		if($scope.amigoProcurado != undefined){
			var idUser = $scope.amigoProcurado.description.id;
			$location.path("/index/"+idUser);
		}
	   }, true);
	
});