angular.module("iFace").controller("newProfileCtrl", function($scope, profileAPI) {
	$scope.app = "Cadastro";
	$scope.newProfile = function(profile){
		profile.user = {};
		profile.age = 20;
		profile.user.id = 1;
		profileAPI.addProfile(profile).success(function(data, status){
			alert("Perfil cadastrado com sucesso");
		}).error(function(data,status){
			$scope.error = "Não foi possivel cadastrar o perfil";
		});
	}
});