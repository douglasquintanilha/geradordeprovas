<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Erro</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/main.css">
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<h3>Algo de inesperado aconteceu, =/</h3>
	<h4>Por favor, tentar novamente ou utilizar outra funcionalidade:</h4>
	<a href="/GeradorDeProvas/adiciona-questao"><h3>Adicionar Questão</h3></a>
	<a href="/GeradorDeProvas/prova"><h3>Realizar Prova</h3></a>
	<c:import url="footer.jsp"></c:import>
	
</body>
</html>
