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

		<c:url var="urlPost" value='salvaLiberacao' />
		<form:form method="POST" action="${urlPost}" commandName="liberacaoForm">
			<h4>Selecione a prova que será liberada:</h4>
				<form:errors path="provas" cssClass="alert alert-danger" element="div" />		
				<ul class='list-unstyled'>
					<c:forEach items="${provas}" var="prova">
						<div class="checkbox">
							<label> <input type="checkbox" name="provas" value="${prova.id}">
								<b>${prova.nome}</b><i>(${prova.descricao})</i>
								<b>Criada em: </b>${prova.dataFormatada}
							</label>
						</div>
					</c:forEach>
					<li role="separator" class="divider"></li>
				</ul>
				<h4>Selecione as turmas:</h4>
				<c:forEach items="${turmas}" var="turma">
					<input type="checkbox" name="turmas" value="${turma.id}">${turma.nome}
				</c:forEach>
				<h4>Selecione os usuários que podem ter acesso a esta prova:</h4>
				<form:errors path="usuarios" cssClass="alert alert-danger" element="div" />
					<ul class='list-unstyled'>
						<c:forEach items="${usuarios}" var="usuario">
							<li>
								<div class="checkbox">
									<label> <input type="checkbox" name="usuarios" value="${usuario.id}"> ${usuario.login}
									</label>
								</div>
							</li>
						</c:forEach>
					</ul>
					<button type="submit" class="btn btn-default">Liberar Provas</button>
		</form:form>
	</div>
	<c:import url="../footer.jsp"></c:import>

</body>
</html>
