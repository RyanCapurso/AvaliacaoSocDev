<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
<title>Editar Exame</title>
</head>
<body class="bg-secondary">
	<div class="container">
		<s:form action="/salvarEdicaoExamesRealizados.action">
			<div class="card mt-5">
				<div class="card-header">
					<div class="row">
						<div class="col-sm-5">
							<s:url action="todosExamesRealizados" var="todos"/>
							<a href="${todos}" class="btn btn-success">Exames Realizados</a>
						</div>
						
						<div class="col-sm">
							<h5 class="card-title">Editar Exame</h5>
						</div>
					</div>
				</div>
				
				<div class="card-body">
					<s:hidden name="exameRealizadoVo.idExameRealizado" value="%{exameRealizadoVo.idExameRealizado}"/>
					
					<div class="row align-items-center mt-3">
						<label for="nomed" class="col-sm-2 col-form-label">Exame:</label>	
						<div class="col-sm-6">
							<s:textfield type="text" cssClass="form-control" id="nomed" name="exameRealizadoVo.exameVo.nome" readonly="true"/>							
						</div>
						<s:hidden name="exameRealizadoVo.exameVo.idExame" value="%{exameRealizadoVo.exameVo.idExame}"/>
					</div>

					<div class="row align-items-center mt-3">
						<label for="nomeFuncionario" class="col-sm-2 col-form-label">Nome Funcionário:</label>	
						<div class="col-sm-6">
							<s:textfield type="text" cssClass="form-control" id="nomeFuncionario" name="exameRealizadoVo.funcionarioVo.nome" readonly="true"/>							
						</div>
						<s:hidden name="exameRealizadoVo.funcionarioVo.idFuncionario" value="%{exameRealizadoVo.funcionarioVo.idFuncionario}"/>
					</div>

					<div class="row align-items-center mt-3">
						<label for="dataExame" class="col-sm-2 col-form-label">Data do Exame:</label>	
						<div class="col-sm-4">
							<s:textfield type="date" cssClass="form-control" id="dataExame" name="exameRealizadoVo.dataExame"/>							
						</div>	
					</div>
				</div>

				<div class="card-footer">
					<div class="form-row">
						<button class="btn btn-primary col-sm-4 offset-sm-1">Salvar</button>
						<button type="reset" class="btn btn-secondary col-sm-4 offset-sm-2">Limpar Formulário</button>
					</div>
				</div>
			</div>
		</s:form>			
	</div>
	
	<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>
