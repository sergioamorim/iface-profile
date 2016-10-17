angular.module("iFace").controller("newUserCtrl", function($scope, $location, $cookies, userAPI) {

	$scope.createUser = function(user){
		userAPI.newUser(user).success(function(data){
			$cookies.putObject("user", data);
			$location.path("/new_profile");
		}).error(function(){
			alert("Erro ao salvar usuário!");
			$location.path("/signup");
		});
	};
});