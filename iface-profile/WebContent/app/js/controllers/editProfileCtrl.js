angular.module("iFace").controller("editProfileCtrl", function($scope, $location, $cookies, profileAPI, degreeOfKinshipAPI, relationshipAPI, civilStatusAPI, uploadAPI, config) {
	var currentUser = $cookies.getObject("user");
	
	$scope.userAcademicProfiles = [];
	$scope.userProfessionalProfiles = [];
	$scope.relationships = []; 
	
	$(document).ready(function() {
        $('select').material_select();
	});
	
	relationshipAPI.getRelationships(currentUser.id).success(function(data){
		console.log(data);
		$scope.relationships = data;
	});
	
	profileAPI.getByUserId(currentUser.id).success(function(data){
		console.log(data);
		$scope.userProfile = data;
		$scope.userAcademicProfiles = data.userAcademicProfile;
		$scope.userProfessionalProfiles = data.userProfessionalProfile;
		$scope.urlImageProfile = data.picture;
		$scope.setSelects();
	});
	
	$scope.degreeOfKinshipByGender = [];
	$scope.civilStatusByGender = [];
	
	$scope.setSelects = function(){
		degreeOfKinshipAPI.getDegreeOfKinshipByGender($scope.userProfile.gender.id).success(function(data){
			$scope.degreeOfKinshipByGender = data;
		});
		
		civilStatusAPI.getCivilStatusByGender($scope.userProfile.gender.id).success(function(data){
			$scope.civilStatusByGender = data;
		});
	};	
	
	function sleep(ms) {
		  return new Promise(resolve => setTimeout(resolve, ms));
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
	
	
	$scope.newUserAcademicProfile = function(){
		$scope.userAcademicProfiles.push({
			user : {id:currentUser.id},
			educationalInstitution : {status : false},
			course : '',
			dateStart : '',
			dateEnd : ''
		})
	};
	
	$scope.newRelationships = function(){
		$scope.relationships.push({
			sender : {id:currentUser.id},
			statusSolicitation : false
		});
		sleep(500).then(() => {
			$('select').material_select();
		});
		
	};
	
	$scope.editProfile = function(profile, urlImageProfile){

			profile.userAcademicProfile = $scope.userAcademicProfiles;
			profile.picture = urlImageProfile;
			profile.userProfessionalProfile = $scope.userProfessionalProfiles;
			delete profile.user.userProfile;
		profileAPI.updateProfile(profile).success(function(data, status){
			alert("Perfil editado com sucesso.");
			$location.path("/index");
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