angular.module("iFace").controller("newProfileCtrl", function($scope, $location, profileAPI, uploadAPI, config) {
	$scope.app = "Cadastro";
	$scope.newProfile = function(profile, urlImageProfile){
		profile.user = {};
		profile.user.id = 1;
		profile.userContacts = [];
		profile.birthPlace.status = false;
		profile.picture = urlImageProfile
		$scope.userContact.user = {};
		$scope.userContact.user.id = 1;
		profile.userContacts.push($scope.userContact);
		profileAPI.addProfile(profile).success(function(data, status){
			alert("Perfil cadastrado com sucesso");
			$location.path("/index");
		}).error(function(data,status){
			$scope.error = "Não foi possivel cadastrar o perfil";
		});
	},
	
	$scope.saveTempImage = function(image){
		uploadAPI.uploadFileToUrl(image, 1).success(function(data){
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
			uploadAPI.downloadEditedUserProfile(1, newURL).success(function(data){
				$scope.urlImageProfile = config.rootUrl+data;
			}).error(function(){
				console.log("Erro ao salvar");
			});
		},
		onError: function(errorObj) {
			alert(errorObj.message);
		}
	});
	
	$scope.launchEditor = function(id, idUser) {
		urlImage = config.rootUrl+"/app/profile-images/"+idUser;
		featherEditor.launch({
			image: 'imagePreview',
			url: urlImage
		});
		return false;
	}
});