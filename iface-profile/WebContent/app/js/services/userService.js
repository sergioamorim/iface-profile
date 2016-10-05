angular.module('iFace').factory("userAPI", function($http, config){
	var _newUser = function(user){
		return $http({
		            url: config.baseUrl+"/user",
		            method: "POST",
		            data: user,
		            headers: {
						"Content-Type": "application/json"
					}
		        });
	}
	
	return{
		newUser: _newUser
	}
});