<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://ur6lad.co.ua/markdown-taglib' prefix ='md' %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Caelum Provas</title>
<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css' />">
<link rel="stylesheet" href="<c:url value='/static/css/jquery-ui.min.css' />">
<link rel="stylesheet" href="<c:url value='/static/css/main.css' />">

</head>
<body>
	<c:import url="../header.jsp"></c:import>
	<div class="container">

		<c:if test="${not empty turma.id}">
			<div class="alert alert-success" role="alert">Turma #${turma.nome} foi criada com sucesso!</div>
		</c:if>
		
		<div class="row">
			<div class="col-md-3">
				Alunos:
			</div>
			
			<div class="col-md-6">
				<form:form action="criaTurma" method="POST" commandName="Turma">
					<input type="text" name="nome" placeholder="Nome">
					<c:forEach items="${usuarios}" var="usuario">
						<p>
							<input type="checkbox" name="usuarios" value="${usuario.id}">${usuario.login}
						</p>
					</c:forEach>
					<hr>
					<c:forEach items="${provas}" var="prova">
						<p>
							<input type="checkbox" name="provas" value="${prova.id}">${prova.nome}
						</p>
					</c:forEach>
					<input type="submit" value="Criar">
				</form:form>
			</div>
			
			<div class="col-md-3">
				Provas:
			</div>
		</div>
			
	</div>
	<c:import url="../footer.jsp"></c:import>
</body>
</html>