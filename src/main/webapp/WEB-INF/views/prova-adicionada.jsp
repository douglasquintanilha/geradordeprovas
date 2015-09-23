<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Caelum Provas</title>
		<link rel="stylesheet" href="static/css/bootstrap.min.css">
		<link rel="stylesheet" href="static/css/main.css">

	</head>
	<body>
		<c:import url="header.jsp"></c:import>
		<div class="container">	

			<h1>Prova criada com sucesso!</h1>
			<a href="<c:url value='/'/>"> Voltar para o menu</a>
			
		</div>
		<c:import url="footer.jsp"></c:import>
	</body>
</html>	
			