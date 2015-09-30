<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cria Usuario</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/main.css">
</head>
<body>
<%-- 	<c:import url="header.jsp"></c:import> --%>

	<form action="<c:url value='/adiciona-usuario'/>" method="POST">
	
		Nome  <input type="text" name="nome"/><br/>
		Senha <input type="password" name="senha"/><br/>
		Admin?<br />
		Sim <input type="radio" name="admin" value="true"/><br/>
		Nao <input type="radio" name="admin" value="false"/><br/>
		<input type="submit" value="Criar">
		
	</form>


<%-- 	<c:import url="/footer.jsp"></c:import> --%>
</body>
</html>