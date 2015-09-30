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
	<div class="container">
		<form:form action="efetuaLogin" method="POST" commandName="usuario" class="form col-xs-3" >
			<div class="form-group">
					<label for="login">Login:</label>
					<input type="text" name="login" id="login" class="form-control" >
			</div>
			<div class="form-group">
					<label for="senha">Senha:</label>
					<input type="password" name="senha" id="senha" class="form-control" >
			</div>
			
			<div class="form-group">
					<button type="submit" class="btn btn-default">Entrar</button>
			</div>
	
		</form:form>

	</div>
	<c:import url="footer.jsp"></c:import>
</body>
</html>