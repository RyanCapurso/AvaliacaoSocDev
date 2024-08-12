<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib prefix="s" uri="/struts-tags" %>

<header class="shadow-sm bg-primary d-flex flex-wrap justify-content-center py-2 px-4 mb-3 border-bottom">
  <s:url action="todosExames" var="exames"></s:url>
  <s:url action="todosFuncionarios" var="funcionarios"></s:url>
  <s:url action="todosExamesRealizados" var="examesRealizados"></s:url>

  <a
    href="${exames}"
    class="d-flex align-items-center mb-2 mb-md-0 me-md-auto link-light text-decoration-none"
  >
    <span class="fs-4">Health System</span>
  </a>

  <ul class="nav nav-pills">
    <li class="nav-item">
      <a href="${exames}" class="nav-link text-light">Exames</a>
    </li>
    <li class="nav-item">
      <a href="${funcionarios}" class="nav-link text-light">Funcionários</a>
    </li>
    <li class="nav-item">
      <a href="${examesRealizados}" class="nav-link text-light">Exames Realizados</a>
    </li>
  </ul>
</header>
