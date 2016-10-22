angular.module("iFace").controller("newProfileCtrl", function($scope, $location, $cookies, profileAPI, uploadAPI, config) {
	var currentUser = $cookies.getObject("user");
	
	$scope.urlImageProfile = "http://api.androidhive.info/images/ic_profile.png"
	$scope.newProfile = function(profile, urlImageProfile){
		profile.user = {};
		profile.user.id = currentUser.id;
		profile.userContacts = [];
		profile.birthPlace.status = false;
		profile.picture = urlImageProfile;
		$scope.userContact.user = {};
		$scope.userContact.user.id = currentUser.id;
		profile.userContacts.push($scope.userContact);
		profileAPI.addProfile(profile).success(function(data, status){
			
			alert("Perfil cadastrado com sucesso");
			$location.path("/index");
		}).error(function(data,status){
			$scope.error = "Não foi possivel cadastrar o perfil";
		});
	},
	
	$scope.saveTempImage = function(image){
		uploadAPI.uploadFileToUrl(image, currentUser.id).success(function(data){
			$scope.urlImageProfile = config.rootUrl+data;
		}).error(function(){
			console.log("erro ao salvar");
			$scope.urlImageProfile = "";
		});
		
	}
	
	var featherEditor = new Aviary.Feather({
		apiKey: '64ab3d8694414ad8938d35a3adaddb71',
		theme: 'dark', 
		tools: 'all',
		appendTo: '',
		onSave: function(imageID, newURL) {
			var img = document.getElementById(imageID);
			img.src = newURL;
			featherEditor.close();
			uploadAPI.downloadEditedUserProfile(currentUser.id, newURL).success(function(data){
				$scope.urlImageProfile = config.rootUrl+data;
			}).error(function(){
				console.log("Erro ao salvar");
				$scope.urlImageProfile = "";
			});
		},
		onError: function(errorObj) {
			alert(errorObj.message);
		}
	});
	
	$scope.launchEditor = function(id, idUser) {
		featherEditor.launch({
			image: 'imagePreview',
			url: $scope.urlImageProfile
		});
		return false;
	}
});