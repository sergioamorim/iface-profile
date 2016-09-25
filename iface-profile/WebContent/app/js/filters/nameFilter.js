angular.module("listaTelefonica").filter("name", function(){
	return function(input){
		var listaNomes = input.split(" ");
		var listaNomesFormatados = listaNomes.map(function(nome){
			if(/(da|de|do)/.test(nome)) return nome;
			return nome.charAt(0).toUpperCase() + nome.substring(1).toLowerCase();
		});
		return listaNomesFormatados.join(" ");
	};
});