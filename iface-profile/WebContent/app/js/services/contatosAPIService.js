angular.module("listaTelefonica").factory("contatosAPI", function($http, config){
	var _getContatos = function(){
		return $http.get(config.baseUrl+"/user");
	}
	
	return{
		getContatos: _getContatos
	}
});