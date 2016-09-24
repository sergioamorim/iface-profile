angular.module("listaTelefonica").controller("listaTelefonicaCtrl", function($scope, $http, contatosAPI){
	$scope.app = "Lista Telefonica";
	$scope.contatos = [
    	{name: "Pedro", data: new Date(), operadora:{nome:"Oi", codigo: 88, categoria: "Celular"}, username: "pedro@p.com", cor: "blue"},
    	{name: "Ana", data: new Date(), operadora:{nome:"Vivo", codigo: 94, categoria: "Celular"}, username: "ana@ana.com", cor: "yellow"},
    	{name: "Carlos", data: new Date(), operadora:{nome:"Tim", codigo: 99, categoria: "Celular"}, username: "c@c.com", cor: "red"}
    ];
	$scope.operadoras = [ 
    	{nome:"Oi", codigo: 88, categoria: "Celular"},
    	{nome:"Vivo", codigo: 94, categoria: "Celular"},
    	{nome:"Tim", codigo: 99, categoria: "Celular"},
    	{nome:"Claro", codigo: 91, categoria: "Celular"},
    	{nome:"GVT", codigo: 25, categoria: "Fixo"},
    	{nome:"Embratel", codigo: 21, categoria: "Fixo"}
    ];
	$scope.adicionarContato = function (contato) {
		contato.data = new Date();
		$scope.contatos.push(angular.copy(contato));
		delete $scope.contato;
		$scope.contatoForm.$setPristine();
	};
	$scope.apagarContatos = function (contatos) {
		$scope.contatos = contatos.filter(function (contato){
			if (!contato.selecionado) return contato;
		});
		
	};
	
	$scope.isContatoSelecionado = function (contatos) {
		return contatos.some(function(contato){
			return contato.selecionado;
		});
	};
	
	var carregarContatos = function(contato){
		contatosAPI.getContatos().success(function(data, status){
			$scope.contatos = data;
		}).error(function(data,status){
			$scope.message= "Ocorreu um problema!";
		});
		
	};
	
	//carregarContatos();
});