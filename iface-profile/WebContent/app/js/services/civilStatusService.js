angular.module("iFace").factory("civilStatusAPI", function($http, config){
	
	var _getCivilStatusByGender = function(id){
		return $http.get(config.baseUrl+"/civil_status/find_civil_status_by_gender/"+id);
	}
	
	return {
		getCivilStatusByGender: _getCivilStatusByGender
	} 
});