<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://ur6lad.co.ua/markdown-taglib' prefix ='md' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Caelum Provas</title>
<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css' />">
<link rel="stylesheet" href="<c:url value='/static/css/jquery-ui.min.css' />">
<link rel="stylesheet" href="<c:url value='/static/css/main.css' />">
<link rel="stylesheet" href="<c:url value='/static/css/github.css' /> ">

</head>
<body>
	<c:import url="../header.jsp"></c:import>
	
	<div class="container"> 
		<c:forEach items="${relatorios}" var="relatorio">
			Usuario: ${relatorio.usuario.login}<br>
			Nota: ${relatorio.nota}<br>
		</c:forEach>
	</div>
	
	<c:import url="../footer.jsp"></c:import>
</body>
</html>