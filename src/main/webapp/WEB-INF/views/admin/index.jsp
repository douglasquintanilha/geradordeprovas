<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gerador de Provas</title>
<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/static/css/main.css' />">
</head>
<body>
	<c:import url="../header.jsp"></c:import>
	<div class="container">
		<h1>Bem vindo ao geraX!</h1>
		<a href="<c:url value='questao/adiciona'/>"><h3>Adicionar Questão</h3></a>
		<a href="<c:url value='questao/edita'/>"><h3>Editar Questão</h3></a>
		<a href="<c:url value='prova/monta'/>"><h3>Montar Prova</h3></a> 
		<a href="<c:url value='/liberadas'/>"><h3>Realizar Prova</h3></a>
		<a href="<c:url value='seleciona-tag'/>"><h3>Procurar Questão por tag</h3></a>
		<a href="<c:url value='usuario/novo/form'/>"><h3>Criar Usuário</h3></a>
		<a href="<c:url value='prova/libera'/>"><h3>Liberar Prova</h3></a>
		<a href="<c:url value='estatisticas'/>"><h3>Estatísticas</h3></a>
	</div>
	<c:import url="../footer.jsp"></c:import>
	
</body>
</html>
