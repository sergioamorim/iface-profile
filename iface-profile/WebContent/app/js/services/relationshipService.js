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
	
	var _deleteRelationship = function(id){
		return $http.delete(config.baseUrl+"/relationship/"+id);
	}
	var _acceptingRelationship = function(id_relationship){
		return $http({
            url: config.baseUrl+"/relationship/"+id_relationship,
            method: "PUT",
            headers: {
				"Content-Type": "application/json"
			}
        }); 
    }
	
	var _findRelationshipRequests = function(User_id){
		return $http({
            url: config.baseUrl+"/relationship/find_requests/"+User_id,
            method: "GET",
            headers: {
				"Content-Type": "application/json"
			}
        });
	}
	
	return {
		getRelationships: _getRelationships,
		saveAllRelationships: _saveAllRelationships,
		deleteRelationship: _deleteRelationship,
		findRelationshipRequests: _findRelationshipRequests,
		acceptingRelationship: _acceptingRelationship,
		refuseRelationship: _deleteRelationship
	} 
});