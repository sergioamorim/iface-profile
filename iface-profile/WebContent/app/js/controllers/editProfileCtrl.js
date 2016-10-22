angular.module("iFace").controller("editProfileCtrl", function($scope, $location, $cookies, profileAPI, degreeOfKinshipAPI, relationshipAPI, civilStatusAPI, uploadAPI, config) {
	var currentUser = $cookies.getObject("user");
	
	$scope.userAcademicProfiles = [];
	$scope.userProfessionalProfiles = [];
	$scope.relationships = []; 
	
	$scope.degreeOfKinshipByGender = [];
	$scope.civilStatusByGender = [];
	
	
	$(document).ready(function() {
        $('select').material_select();
	});
	
	$scope.onAutoComplete = function(index, relationship){
		if(relationship.receiver != undefined){
			console.log(index);
			userProfile = relationship.receiver.description;
			relationship.receiver = userProfile.user;
			setDegreeOfKinship(index, userProfile);
		}
	}
	
	relationshipAPI.getAllRelationships(currentUser.id).success(function(data){
		for(var i=0; i<data.length; i++){
			if(data[i].receiver.id == currentUser.id){
				data[i].kinship = data[i].relationshipType.senderDegreeOfKinship.degreeOfKinship;
				data[i].name = data[i].sender.userProfile.name+" "+data[i].sender.userProfile.lastname;
			}else{
				data[i].kinship = data[i].relationshipType.receiverDegreeOfKinship.degreeOfKinship;
				data[i].name = data[i].receiver.userProfile.name+" "+data[i].receiver.userProfile.lastname;
			}
		}
		$scope.existingRelationships = data;
		
	});
	
	profileAPI.getByUserId(currentUser.id).success(function(data){
		console.log(data);
		$scope.userProfile = data;
		$scope.userAcademicProfiles = data.userAcademicProfile;
		$scope.userProfessionalProfiles = data.userProfessionalProfile;
		$scope.urlImageProfile = data.picture;
		$scope.setSelects();
	});
	
	
	$scope.setSelects = function(){
		civilStatusAPI.getCivilStatusByGender($scope.userProfile.gender.id).success(function(data){
			$scope.civilStatusByGender = data;
		});
	};	
	
	function sleep(ms) {
	  return new Promise(resolve => setTimeout(resolve, ms));
	}
	
	function setDegreeOfKinship(index, user){
		console.log(user);
		degreeOfKinshipAPI.getDegreeOfKinshipByGender(user.gender.id).success(function(data){
			$scope.degreeOfKinshipByGender[index] = data;
			sleep(500).then(() => {
				$('select').material_select();
			});
		});
	}
	
	$scope.$watch('civilStatusByGender', function(scope){
		if($scope.civilStatusByGender != undefined){
			sleep(500).then(() => {
				$('select').material_select();
			});
			
		}
	   }, true);
	
	$scope.newUserProfessionalProfile = function(){
		$scope.userProfessionalProfiles.push({
			user : {id:currentUser.id},
			workplace : {status : false},
			office : '',
			dateStart : '',
			dateEnd : ''
		})
	};
	
	$scope.removeProfessional = function(index){
		$scope.userProfessionalProfiles.splice(index, 1);
	}
	
	
	$scope.newUserAcademicProfile = function(){
		$scope.userAcademicProfiles.push({
			user : {id:currentUser.id},
			educationalInstitution : {status : false},
			course : '',
			dateStart : '',
			dateEnd : ''
		})
	};
	
	$scope.removeAcademic = function(index){
		$scope.userAcademicProfiles.splice(index, 1);
	}
	
	$scope.newRelationships = function(){
		$scope.relationships.push({
			sender : $scope.userProfile.user,
			statusSolicitation : false
		});
		sleep(500).then(() => {
			$('select').material_select();
		});
		
	};
	
	$scope.removeRelationship = function(index){
		$scope.relationships.splice(index, 1);
	}
	
	$scope.deleteRelationship = function(id, index){
		relationshipAPI.deleteRelationship(id).success(function(){
			$scope.existingRelationships.splice(index, 1);
		}).error(function(){
			alert("Erro ao remover relacionamento");
		})
	}
	
	$scope.editProfile = function(profile, urlImageProfile){

		profile.userAcademicProfile = $scope.userAcademicProfiles;
		profile.picture = urlImageProfile;
		profile.userProfessionalProfile = $scope.userProfessionalProfiles;
		delete profile.user.userProfile;
		
		profileAPI.updateProfile(profile).success(function(data, status){
			relationshipWrapper = {};
			relationshipWrapper.genderSenderId = $scope.userProfile.gender.id;
			relationshipWrapper.relationships = $scope.relationships;
			relationshipAPI.saveAllRelationships(relationshipWrapper).success(function(data, status){
				alert("Perfil editado com sucesso.");
				$location.path("/index");
			}).error(function(data, status){
				$scope.error = "Ocorreu um error, não foi possível editar o perfil.";
			});
		}).error(function(data,status){
			$scope.error = "Ocorreu um error, não foi possível editar o perfil.";
		});
	};
	
	$scope.saveTempImage = function(image){
		uploadAPI.uploadFileToUrl(image, currentUser.id).success(function(data){
			$scope.urlImageProfile = config.rootUrl+data;
		}).error(function(){
			console.log("erro ao salvar");
			$scope.urlImageProfile = "";
		});
		
	};
	
	var featherEditor = new Aviary.Feather({
		apiKey: '64ab3d8694414ad8938d35a3adaddb71',
		theme: 'dark', 
		tools: 'all',
		appendTo: '',
		onSave: function(imageID, newURL) {
			var img = document.getElementById(imageID);
			img.src = newURL;
			featherEditor.close();
			uploadAPI.downloadEditedUserProfile(currentUser.id, newURL).success(function(data){
				$scope.urlImageProfile = config.rootUrl+data;
			}).error(function(){
				console.log("Erro ao salvar");
				$scope.urlImageProfile = "";
			});
		},
		onError: function(errorObj) {
			alert(errorObj.message);
		}
	});
	
	$scope.launchEditor = function() {
		featherEditor.launch({
			image: 'imagePreview',
			url: $scope.urlImageProfile
		});
		return false;
	};
});