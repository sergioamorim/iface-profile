angular.module("iFace").factory("degreeOfKinshipAPI", function($http, config){
	
	var _getDegreeOfKinshipByGender = function(id){
		return $http.get(config.baseUrl+"/degree_of_kinship/find_degree_of_kinship_by_gender/"+id);
	}
	
	return {
		getDegreeOfKinshipByGender: _getDegreeOfKinshipByGender
	} 
	
});