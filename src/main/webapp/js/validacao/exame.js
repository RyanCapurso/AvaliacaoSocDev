

const txtNome = document.getElementById('txtNome');


function validacaoExame(){
	limpar();
	
	if(txtNome.value == ""){
		erroNome.textContent = "O nome é Obrigatório!";
		return false;
	}
	return true;
}

function limpar(){
	erroBanco.textContent = "";
	erroNome.textContent = "";
}

