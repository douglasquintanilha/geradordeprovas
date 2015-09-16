<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="static/css/bootstrap.min.css">
		<link rel="stylesheet" href="static/css/main.css">

		<title>Seu Destino</title>
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<div class="container">
		<c:url var="urlPost" value='correcao-prova' />
		<form:form action="correcao-prova" method="GET" commandName="resposta">
				<c:forEach items="${listaDeQuestoes}" var="questao">
					<b>${questao.titulo}</b><br />
					<c:forEach items="${questao.alternativa}" var="alternativa">
						<input type="radio" value="${alternativa.id}" name="${alternativa}">${alternativa.descricao}<br />
					</c:forEach>
				</c:forEach>
			<button type="submit" class="btn btn-default">Enviar</button>
		</form:form>
	</div>
	<c:import url="footer.jsp"></c:import>
</body>
</html>