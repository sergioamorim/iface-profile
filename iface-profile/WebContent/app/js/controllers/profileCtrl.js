angular.module("iFace").controller("profileCtrl", function($scope, $location, $routeParams, $cookies, profileAPI, friendshipAPI, relationshipAPI, logAPI) {
	$scope.friends=[];
	$scope.tab = 0;
	$scope.buttonUserBlock = "Bloquear Usuario";
	$scope.isUserBlocked = false;
	$scope.IAmBlocked = false;
	
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
			
			if($scope.isNotMyPerfil()){
				friendshipAPI.hasFriendship(currentUser.id,$routeParams.id).success(function(data){
					$scope.friendship=data;
					if($scope.friendship==""){
						$scope.buttonFriendship = "Adicionar aos amigos";
					}
					else{
						$scope.statusBlockUser();
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
	$scope.isNotMyPerfil  = function(){
		return ($routeParams.id != undefined && $routeParams.id != currentUser.id)
	}	
	
	$scope.openEditProfile = function(){
		$location.path("/edit_profile");
	}
	$scope.clickButtonFriendship = function(){
		if($scope.buttonFriendship == "Adicionar aos amigos"){
			$scope.requestFriendship();
		
		}
		else if($scope.buttonFriendship == "Aceita Solicitação"){
			$scope.acceptingFriendship($scope.friendship.id);
			
		}
		else{
			$scope.refuseFriendship($scope.friendship.id);
			
		}
	}
	$scope.requestFriendship = function(){	
		profileAPI.getByUserId(currentUser.id).success(function(data){
			var user_x = data.user;
			profileAPI.getByUserId($routeParams.id).success(function(data){
				var user_y = data.user;
				friendshipAPI.requestFriendship(user_x,user_y).success(function(data){										
					$scope.newFriendship = data;
					$scope.buttonFriendship = "Cancelar solicitação";
				});
			});
		});
	}
	$scope.acceptingFriendship = function(friendship_id){
		friendshipAPI.acceptingFriendship(friendship_id).success(function(data){
			if(!$scope.isNotMyPerfil()){
				$scope.popFriendshipRequest(friendship_id)
			};
			$scope.buttonFriendship = "Cancelar amizade";
		});
	}
	$scope.refuseFriendship = function(friendship_id){
		if(friendship_id == undefined){
			friendship_id = $scope.newFriendship.id;
		}
		friendshipAPI.refuseFriendship(friendship_id).success(function(data){
			if(!$scope.isNotMyPerfil()){
				$scope.popFriendshipRequest(friendship_id)
			};
			$scope.buttonFriendship = "Adicionar aos amigos";
		});
	}
	$scope.refuseRelationship = function(relationship_id){
		relationshipAPI.refuseRelationship(relationship_id).success(function(data){
			$scope.popRelationshipRequest(relationship_id);
		});
	}
	$scope.acceptingRelationship = function(relationship_id){
		relationshipAPI.acceptingRelationship(relationship_id).success(function(data){
			$scope.popRelationshipRequest(relationship_id);
		});
	}
	$scope.getRequests = function(){
		friendshipAPI.findFriendshipRequests(currentUser.id).success(function(data){
			$scope.findFriendshipRequests=data;
			relationshipAPI.findRelationshipRequests(currentUser.id).success(function(data){
				$scope.findRelationshipRequests = data;
			});
		});
	}
	$scope.popFriendshipRequest = function(friendship_id){
		$scope.findFriendshipRequests = $scope.findFriendshipRequests.filter(function (friendship){
			if(friendship.id!=friendship_id) 
				return friendship;
		});
	}
	$scope.popRelationshipRequest = function(relationship_id){
		$scope.findRelationshipRequests = $scope.findRelationshipRequests.filter(function (relationship){
			if(relationship.id!=relationship_id) 
				return relationship;
		});
	}
	$scope.blockUser = function(){
		profileAPI.getByUserId(currentUser.id).success(function(data){
			var user_x = data.user;
			profileAPI.getByUserId($routeParams.id).success(function(data){
				var user_y = data.user;
				friendshipAPI.userBlock(user_x,user_y).success(function(data){											
					if($scope.buttonUserBlock == "Bloquear Usuario"){
						$scope.buttonUserBlock = "Desbloquear Usuario";
						$scope.isUserBlocked = true;
						$scope.newFriendship = data;
					}
					else{
						$scope.buttonUserBlock = "Bloquear Usuario";
						$scope.buttonFriendship = "Cancelar amizade";
						$scope.isUserBlocked = false;
					}
				});
			});
		});
	}
	$scope.statusBlockUser = function(){
		var idBlocked = $scope.friendship.blocked_user;
		if(idBlocked!=null){
			$scope.isUserBlocked = true;
			if(idBlocked==currentUser.id){	
				$scope.IAmBlocked = true;
			}
			else{
				$scope.buttonUserBlock = "Desbloquear Usuario";
			}
		}
	}
	
	$scope.$watch('amigoProcurado', function(scope){
		if($scope.amigoProcurado != undefined){
			var idUser = $scope.amigoProcurado.description.id;
			$location.path("/index/"+idUser);
		}
	   }, true);
	
});