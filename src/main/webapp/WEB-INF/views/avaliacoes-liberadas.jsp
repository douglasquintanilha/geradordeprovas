<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://ur6lad.co.ua/markdown-taglib' prefix ='md' %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EscolhaSuaProva</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/main.css">
</head>
<body>

	<c:import url="header.jsp"></c:import>
	<div class="container">
		<c:if test="${param.existeTurma == true}">
			<div class="alert alert-success" role="alert">Matriculado em #${nomeTurma} com sucesso!</div>
		</c:if>
		<h3>Matricule-se numa turma!</h3>
		<form:form method="POST" action="matricula">
			<input type="text" name="nomeTurma"><input type="submit" value="Matricular!">
		</form:form>

		<c:if test="${not empty provas}">
			<h3>Selecione uma prova:</h3>
			<div class="row">
			<c:forEach items="${provas}" var="prova">
			    <div class="col-xs-3">
			    <form:form action="avaliacao" method="POST" id="${prova.id}" command="avaliacao">
			        <input type="hidden" name="id" value="${prova.id}">
			        <a onclick="document.getElementById(${prova.id}).submit();" class="thumbnail">
			             <img src="<c:url value='/static/imagens/prova-miniatura.jpg' />" class="img-responsive">
			             <div class="caption">
			             	<label for="${prova.id}">
								${prova.nome}
							</label>
			             </div>
			        </a>
			    </form:form>
			    </div>
			</c:forEach>
			
			</div>
		</c:if>
	</div>
	<c:import url="footer.jsp"></c:import>
</body>
</html>