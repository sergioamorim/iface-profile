angular.module("iFace").factory("profileAPI", function($http, config){
	
	var _getByUserId = function(id){
		return $http.get(config.baseUrl+"/user_profile/user/"+id);
	}
	
	var _addProfile = function(profile){
		return $http({
		            url: config.baseUrl+"/user_profile",
		            method: "POST",
		            data: profile,
		            headers: {
						"Content-Type": "application/json"
					}
		        });
	}
	
	return{
		addProfile: _addProfile,
		getByUserId: _getByUserId
	}
});