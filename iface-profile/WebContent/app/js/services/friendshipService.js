angular.module("iFace").factory("friendshipAPI", function($http, config){

	var _requestfriendship = function(User_x,User_y){
		frienship = {
				user_x: User_x,
				user_y: User_y,
				approved: false
		}
		return $http({
            url: config.baseUrl+"/friendship",
            method: "POST",
            data: frienship,
            headers: {
				"Content-Type": "application/json"
			}
        });
    }
	var _acceptingfriendship = function(id_friendship){
		return $http({
            url: config.baseUrl+"/friendship/"+id_friendship,
            method: "PUT",
            headers: {
				"Content-Type": "application/json"
			}
        }); 
    }
	var _refusefriendship = function(id_friendship){
		return $http({
            url: config.baseUrl+"/friendship/"+id_friendship,
            method: "DELETE",
            headers: {
				"Content-Type": "application/json"
			}
        });
    }
	var _findFriendshipRequests = function(id_friendship){
		return $http({
            url: config.baseUrl+"/friendship/find_requests/"+id_friendship,
            method: "GET",
            headers: {
				"Content-Type": "application/json"
			}
        });
	}
	return {
		requestfriendship: _requestfriendship,
		acceptingfriendship: _acceptingfriendship,
		refusefriendship: _refusefriendship,
		findFriendshipRequests: _findFriendshipRequests
	}
	
});