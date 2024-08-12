<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib prefix="s" uri="/struts-tags" %> <%@
taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Exames Realizados</title>
    <link
      rel="stylesheet"
      href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
    />
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body class="bg-secondary">
    <jsp:include page="/componentesGerais/header.jsp" />

    <div class="container">
      <div class="row mt-5 mb-2">
        <div class="col-sm p-0">
          <s:form action="/filtrarExamesRealizados.action">
            <div class="input-group">
              <span class="input-group-text">
                <strong><s:text name="label.buscar.por" /> DATA</strong>
              </span>

              <span class="input-group-text">
                <strong>DE:</strong>
              </span>

              <s:textfield
                type="date"
                cssClass="form-control"
                id="nome"
                name="DataInicio"
              />

              <span class="input-group-text">
                <strong>ATÃ‰:</strong>
              </span>

              <s:textfield
                type="date"
                cssClass="form-control"
                id="nome"
                name="DataFim"
              />

              <button class="btn btn-primary" type="submit">
                <s:text name="label.pesquisar" />
              </button>
            </div>
          </s:form>
        </div>
      </div>

      <div class="row">
        <table class="table table-light table-striped align-middle">
          <thead>
            <tr>
              <th>ID FuncionÃ¡rio</th>
              <th>FuncionÃ¡rio</th>
              <th>ID EXAME</th>
              <th>EXAME</th>
              <th>DATA EXAME</th>
              <th class="text-end mt-5"><s:text name="label.acao" /></th>
            </tr>
          </thead>

          <tbody>
            <s:iterator value="examesRealizados">
              <tr>
                <td>${funcionarioVo.idFuncionario}</td>
                <td>${funcionarioVo.nome}</td>
                <td>${exameVo.idExame}</td>
                <td>${exameVo.nome}</td>
                <td>
                  <fmt:formatDate
                    value="${dataExame}"
                    type="date"
                    pattern="dd/MM/yyyy"
                  />
                </td>
                <td class="text-end">
                  <!-- BotÃ£o Editar -->
                  <s:url action="editarExamesRealizados" var="editar">
                    <s:param
                      name="exameRealizadoVo.idExameRealizado"
                      value="%{idExameRealizado}"
                    ></s:param>
                  </s:url>
                  <a href="${editar}" class="btn btn-warning text-white">
                    <s:text name="label.editar" />
                  </a>

					                  <!-- Botão Excluir -->
					<a
					  href="#"
					  class="btn btn-danger btnExcluir"
					  data-bs-toggle="modal"
					  data-bs-target="#confirmarExclusao-${idExameRealizado}"
					>
					  <s:text name="label.excluir" />
					</a>
					
					<!-- Modal de Confirmação -->
					<div
					  class="modal fade"
					  id="confirmarExclusao-${idExameRealizado}"
					  data-bs-backdrop="static"
					  data-bs-keyboard="false"
					  tabindex="-1"
					  aria-labelledby="staticBackdropLabel"
					  aria-hidden="true"
					>
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title">
					          <s:text name="label.modal.titulo" />
					        </h5>
					        <button
					          type="button"
					          class="btn-close"
					          data-bs-dismiss="modal"
					          aria-label="Close"
					        ></button>
					      </div>
					      <div class="modal-body">
					        <span><s:text name="label.modal.corpo" /></span>
					      </div>
					      <div class="modal-footer">
					        <button
					          type="button"
					          class="btn btn-secondary"
					          data-bs-dismiss="modal"
					        >
					          <s:text name="label.nao" />
					        </button>
					        <s:form
					          action="excluirExamesRealizados"
					          method="post"
					        >
					          <s:hidden
					            name="exameRealizadoVo.idExameRealizado"
					            value="%{idExameRealizado}"
					          />
					          <button
					            type="submit"
					            class="btn btn-primary"
					            style="width: 75px"
					          >
					            <s:text name="label.sim" />
					          </button>
					        </s:form>
					      </div>
					    </div>
					  </div>
					</div>
                </td>
              </tr>
            </s:iterator>
          </tbody>

          <tfoot class="table-secondary">
            <tr>
              <td colspan="6">
                <s:url action="novoExamesRealizados" var="novo" />

                <a href="${novo}" class="btn btn-success">
                  MARCAR <s:text name="label.novo" /> EXAME
                </a>

                <s:url
                  action="baixarRelatorioExamesRealizados"
                  var="baixarRelatorio"
                >
                  <s:param value="DataInicio" name="DataInicio"></s:param>
                  <s:param value="DataFim" name="DataFim"></s:param>
                </s:url>

                <a href="${baixarRelatorio}" class="btn btn-success">
                  BAIXAR RELATÃ“RIO
                </a>

                <span id="erroBanco" class="col-sm-5"
                  ><s:property value="mensagemErro"
                /></span>
              </td>
            </tr>
          </tfoot>
        </table>
      </div>

      <div class="row"></div>
    </div>

    <script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
