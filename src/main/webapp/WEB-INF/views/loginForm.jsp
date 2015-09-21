<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Bixcoito</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/main.css">
</head>
<body>
	<c:import url="header.jsp"></c:import>

	<form:form action="efetuaLogin" method="POST" commandName="usuario">

		<p>Login:</p> <input type="text" name="login" /><br /> 
		<p>Senha:</p> <input type="password" name="senha" /><br />
		<input type="submit" value="Entrar">

	</form:form>


	<c:import url="footer.jsp"></c:import>
</body>
</html>