angular.module("listaTelefonica").factory("contatosAPI", function($http, config){
	var _getContatos = function(){
		return $http.get(config.baseUrl+"/user2");
	}
	
	var _addContato = function(contato){
		return $http({
		            url: config.baseUrl+"/user",
		            method: "POST",
		            data: contato,
		            headers: {
						"Content-Type": "application/json"
					}
		        });
	}
	
	return{
		getContatos: _getContatos,
		addContato: _addContato
	}
});