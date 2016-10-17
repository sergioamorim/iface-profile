angular.module("iFace").controller("editProfileCtrl", function($scope, $location, $cookies, profileAPI, degreeOfKinshipAPI, civilStatusAPI) {
	var currentUser = $cookies.getObject("user");
	
	$scope.userAcademicProfiles = [];
	$scope.userProfessionalProfiles = [];
	
	profileAPI.getByUserId(currentUser.id).success(function(data){
		console.log(data);
		$scope.userProfile = data;
		$scope.userAcademicProfiles = data.userAcademicProfile;
		$scope.userProfessionalProfiles = data.userProfessionalProfile;
	});
	
	degreeOfKinshipAPI.getDegreeOfKinshipByGender(currentUser.id).success(function(data){
		console.log(data);
		$scope.degreeOfKinshipByGender = data;
	});
	
	civilStatusAPI.getCivilStatusByGender(currentUser.id).success(function(data){
		console.log(data);
		$scope.civilStatusByGender = data;
	})
	
	$scope.newUserProfessionalProfile = function(){
		$scope.userProfessionalProfiles.push({
			user : {id:currentUser.id},
			workplace : {status : false},
			office : '',
			dateStart : '',
			dateEnd : ''
		})
	}
	$scope.newUserAcademicProfile = function(){
		$scope.userAcademicProfiles.push({
			user : {id:currentUser.id},
			educationalInstitution : {status : false},
			course : '',
			dateStart : '',
			dateEnd : ''
		})
	}
	$scope.editProfile = function(profile, urlImageProfile){

			profile.userAcademicProfile = $scope.userAcademicProfiles;
		
			profile.userProfessionalProfile = $scope.userProfessionalProfiles;
		profileAPI.updateProfile(profile).success(function(data, status){
			alert("Perfil editado com sucesso.");
			$location.path("/index");
		}).error(function(data,status){
			$scope.error = "Ocorreu um error, não foi possível editar o perfil.";
		});
	}
});