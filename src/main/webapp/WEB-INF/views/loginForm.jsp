<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login geraX</title>
	<link href="<c:url value='/static/imagens/favicon.ico' />" rel="icon" type="image/x-icon" />
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/main.css">
	<link rel="stylesheet" href="static/css/font-awesome.min.css">
	<link rel="stylesheet" href="static/css/bootstrap-social.css">
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<div class="container">
		<div class="form-de-login col-md-4 col-md-offset-4">						
			<form:form action="efetuaLogin" method="POST" commandName="usuario" class="form">
				<div class="form-group">
					<label for="login">Login:</label>
					<c:if test="${validacao == false}">
						<span>Login ou Senha Incorretos!</span>
					</c:if>
					<input type="text" name="login" id="login" placeholder="Email ou nome de usuário" class="form-control" autofocus>
				</div>
				<div class="form-group">
					<label for="senha">Senha:</label> <input type="password" placeholder="Senha" name="senha" id="senha" class="form-control">
				</div>
	
				<div class="form-group col-md-offset-4">
					<button type="submit" class="btn btn-default btn-lg">Entrar</button>
				</div>	
			</form:form>
			<hr>
			<div>
				<a class="btn btn-block btn-social btn-github btn-lg" href="<c:url value='/oauth/github-login'/>">
	    			<span class="fa fa-github"></span>Login com Github
	  			</a>
	  			<a class="btn btn-block btn-social btn-google btn-lg" href="<c:url value='/oauth/google-login'/>">
	    			<span class="fa fa-google"></span>Login com Google
	  			</a>
			</div>
		</div>
	</div>
	<c:import url="footer.jsp"></c:import>
</body>
</html>