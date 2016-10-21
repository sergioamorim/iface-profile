angular.module("iFace").factory("relationshipAPI", function($http, config){
	
	var _getRelationships = function(id){
		return $http.get(config.baseUrl+"/relationship/find_relationship/"+id);
	}
	
	var _saveAllRelationships = function(relationshipWrapper){
		return $http({
            url: config.baseUrl+"/relationship/save_all",
            method: "POST",
            data: relationshipWrapper,
            headers: {
				"Content-Type": "application/json"
			}
        });
	}
	
	return {
		getRelationships: _getRelationships,
		saveAllRelationships: _saveAllRelationships
	} 
});