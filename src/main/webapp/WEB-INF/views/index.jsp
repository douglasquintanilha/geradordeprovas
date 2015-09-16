<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gerador de Provas</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/main.css">
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<div class="container">
		<h1>Gerador de provas Caelum!</h1>
		<a href="<c:url value='adiciona-questao'/>"><h3>Adicionar Questão</h3></a>
		<a href="<c:url value='montar-prova'/>"><h3>Montar Prova</h3></a> 
		<a href="<c:url value='prova-aluno2'/>"><h3>Realizar Prova</h3></a>
		<a href="<c:url value='seleciona-tag'/>"><h3>Procurar Questão por tag</h3></a>
	</div>
</body>
</html>
