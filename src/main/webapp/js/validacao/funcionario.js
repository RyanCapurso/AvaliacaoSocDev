

function validacaoFuncionario(){
	limpar();
	
	if(txtNome.value == ""){
		erroNome.textContent = "O Nome é Obrigatório!";
		return false;
	}
	return true;
}

function limpar(){
	erroBanco.textContent = "";
	erroNome.textContent = "";
}

