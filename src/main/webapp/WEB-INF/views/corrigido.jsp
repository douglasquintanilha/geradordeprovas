<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resultado da Prova</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/main.css">
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<div class="container">
		<h1>Sua nota foi:</h1>
		<h3>${relatorio.nota}</h3>
		<c:if test="${relatorio.temErrada == true}">
			<h3>Você errou a(s) questão(ões):</h3>
			<c:forEach items="${relatorio.resultado}" var="teste" varStatus = "i">
				<c:if test="${teste == false}">
					"${relatorio.questoes[i.index].titulo}"<br />
				</c:if>
			</c:forEach>
		</c:if>
	</div>
	<a href="/GeradorDeProvas/">Voltar para o início</a>
	<c:import url="footer.jsp"></c:import>
</body>
</html>