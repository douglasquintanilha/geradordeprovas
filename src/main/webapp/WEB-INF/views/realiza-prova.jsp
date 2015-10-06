<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Boa-Sorte</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/main.css">
</head>
<body>

	<c:import url="header.jsp"></c:import>
	<div class="container">
	<c:url var="urlPost" value='correcao-prova' />
	<c:if test="${validacao == false}">
		Preencha todas as Respostas!
	</c:if>
	<form:form method="POST" action="${urlPost}" commandName="resposta">
		<c:forEach items="${prova.questoes}" var="questao" varStatus="i">
			${questao.titulo}<br>
			<c:forEach items="${questao.alternativa}" var="alternativa">
				<input type="radio" value="${alternativa.id}" name="alternativas[${i.index}]">${alternativa.descricao}<br>
			</c:forEach>
		</c:forEach>
		<input type="hidden" name="provaId" value="${prova.id}">
		<input type="submit" value="Ok">
	</form:form>
	</div>
	<c:import url="footer.jsp"></c:import>


</body>
</html>