<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Cadastro de Funcionário</title>
    <link
      rel="stylesheet"
      href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
    />
  </head>
  <body class="bg-secondary">
    <div class="container">
      <s:form
        action="/novoFuncionarios.action"
        onsubmit="return validacaoFuncionario()"
      >
        <div class="card mt-5">
          <div class="card-header">
            <div class="row">
              <div class="col-sm-5">
                <s:url action="todosFuncionarios" var="todos" />
                <a href="${todos}" class="btn btn-success">Funcionários</a>
              </div>

              <div class="col-sm">
                <h5 class="card-title">Novo Funcionário</h5>
              </div>
            </div>
          </div>

          <div class="card-body">
            <div class="row align-items-center mt-3">
              <label for="nome" class="col-sm-1 col-form-label text-center">
                Nome:
              </label>

              <div class="col-sm-5">
                <s:textfield
                  cssClass="form-control"
                  id="txtNome"
                  name="funcionarioVo.nome"
                />
              </div>

              <span id="erroNome" class="col-sm-5 text-danger"></span>
            </div>
            <span id="erroBanco" class="col-sm-5 text-danger"
              ><s:property value="mensagemErro"
            /></span>
          </div>

          <div class="card-footer">
            <div class="form-row">
              <button class="btn btn-primary col-sm-4 offset-sm-1">
                Salvar
              </button>
              <button
                type="reset"
                class="btn btn-secondary col-sm-4 offset-sm-2"
              >
                Limpar Formulario
              </button>
            </div>
          </div>
        </div>
      </s:form>
    </div>

    <script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="js/validacao/funcionario.js"></script>
  </body>
</html>
