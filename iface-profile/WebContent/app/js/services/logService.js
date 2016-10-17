angular.module("iFace").factory("logAPI", function($http, config){
	
	var _getByUserId = function(id){
		return $http.get(config.baseUrl+"/user_log/individual/"+id);
	}
	
	return{
		getByUserId: _getByUserId
	}
});