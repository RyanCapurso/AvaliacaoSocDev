<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
  <head>
    <title><s:text name="label.titulo.pagina.consulta"/></title>
    <link
      rel="stylesheet"
      href="webjars/bootstrap/5.1.3/css/bootstrap.min.css"
    />
  </head>
  <body class="bg-secondary">
    <jsp:include page="/componentesGerais/header.jsp" />

    <main class="container text-center">
      <h1>Desculpe, página não encontrada :(</h1>

      <span class="d-none"><s:property value="mensagemErro" /></span>
    </main>
  </body>
</html>
