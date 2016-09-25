angular.module("listaTelefonica").controller("listaTelefonicaCtrl", function($scope, contatosAPI){
	$scope.app = "Lista Telefonica";
	$scope.contatos = [
    	{name: "Pedro", data: new Date(), operadora:{nome:"Oi", codigo: 88, categoria: "Celular"}, username: "pedro@p.com"},
    	{name: "Ana", data: new Date(), operadora:{nome:"Vivo", codigo: 94, categoria: "Celular"}, username: "ana@ana.com"},
    	{name: "Carlos", data: new Date(), operadora:{nome:"Tim", codigo: 99, categoria: "Celular"}, username: "c@c.com"}
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
		//contato.data = new Date();
		//$scope.contatos.push(angular.copy(contato));
		adicionarContato2(contato);
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
	
	var adicionarContato2 = function(contato){
		contatosAPI.addContato(contato).success(function(data, status){
			carregarContatos();
			alert("Contato adicionado com sucesso");
		}).error(function(data,status){
			$scope.error = "Não foi possivel carregar os dados";
		});
	}
	
	var carregarContatos = function(){
		contatosAPI.getContatos().success(function(data, status){
			$scope.contatos = data;
		}).error(function(data,status){
			$scope.error = "Não foi possivel carregar os dados";
		});
		
	};
	
	carregarContatos();
});