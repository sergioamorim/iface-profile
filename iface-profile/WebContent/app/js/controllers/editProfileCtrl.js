angular.module("iFace").controller("editProfileCtrl", function($scope, $location ,profileAPI, degreeOfKinshipAPI, civilStatusAPI) {
	
	$scope.userAcademicProfiles = [];
	$scope.userProfessionalProfiles = [];
	
	profileAPI.getByUserId(1).success(function(data){
		console.log(data);
		$scope.userProfile = data;
		$scope.userAcademicProfiles = data.userAcademicProfile;
		$scope.userProfessionalProfiles = data.userProfessionalProfile;
	});
	
	degreeOfKinshipAPI.getDegreeOfKinshipByGender(1).success(function(data){
		console.log(data);
		$scope.degreeOfKinshipByGender = data;
	});
	
	civilStatusAPI.getCivilStatusByGender(1).success(function(data){
		console.log(data);
		$scope.civilStatusByGender = data;
	})
	
	$scope.newUserProfessionalProfile = function(){
		$scope.userProfessionalProfiles.push({
			user : {id:1},
			workplace : {status : false},
			office : '',
			dateStart : '',
			dateEnd : ''
		})
	}
	$scope.newUserAcademicProfile = function(){
		$scope.userAcademicProfiles.push({
			user : {id:1},
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