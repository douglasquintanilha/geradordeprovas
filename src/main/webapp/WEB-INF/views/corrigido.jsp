<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<h3>${nota}/${total}</h3>
<%-- 		<c:if test="${erradas eq null}"> --%>
			<h3>Você errou a(s) questão(ões):</h3>
			<c:forEach items="${erradas}" var="errou">
				${errou.questao.titulo}
		</c:forEach>
<%-- 		</c:if> --%>
	</div>
	<c:import url="footer.jsp"></c:import>
</body>
</html>