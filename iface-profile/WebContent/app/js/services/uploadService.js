angular.module("iFace").factory("uploadAPI", function($http, config){

	var _uploadFileToUrl = function(file, idUser){
        var fd = new FormData();
        fd.append('file', file);
        return $http.post(config.baseUrl+"/user_profile/upload_picture/"+idUser, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        });
    }
	
	var _downloadEditedUserProfile = function(user_id, url){
		return $http({
		            url: config.baseUrl+"/user_profile/download_picture",
		            method: "POST",
		            params: {id: user_id, url: url},
		            headers: {
						"Content-Type": "application/json"
					}
		        });
	}
	
	return {
		uploadFileToUrl: _uploadFileToUrl,
		downloadEditedUserProfile: _downloadEditedUserProfile
	}
	
});