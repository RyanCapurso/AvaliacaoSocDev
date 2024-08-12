function validacaoExameRealizado() {
    limpar();

    let valido = true;
    let dataAtual = new Date().toISOString().split('T')[0];

    if (txtIdExame.value == "") {
        erroIdExame.textContent = "O Id do Exame é Obrigatório!";
        valido = false;
    }

    if (txtIdFuncionario.value == "") {
        erroIdFuncionario.textContent = "O Id do Funcionário é Obrigatório!";
        valido = false;
    }

    if (txtData.value == "") {
        erroData.textContent = "Por favor selecione uma data";
        valido = false;
    } else if (txtData.value < dataAtual) {
        erroData.textContent = "A data não pode ser menor que a data atual!";
        valido = false;
    }

    return valido;
}

function limpar() {
    erroBanco.textContent = "";
    erroIdFuncionario.textContent = "";
    erroIdExame.textContent = "";
    erroData.textContent = "";
}
