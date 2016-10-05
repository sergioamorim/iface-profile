angular.module("iFace").controller("newUserCtrl", function($scope, $location, userAPI) {
	

	
	$scope.createUser = function(user){
		userAPI.newUser(user).success(function(){
			$location.path("/new_profile");
		}).error(function(){
			alert("Erro ao salvar usuário!");
			$location.path("/signup");
		});
	};
});