<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://ur6lad.co.ua/markdown-taglib' prefix ='md' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Caelum Provas</title>
	<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css' /> ">
	<link rel="stylesheet" href="<c:url value='/static/css/main.css' /> ">
</head>
<body>

	<c:import url="header.jsp"></c:import>
	<div class="container">
		<div class="form-de-login col-md-4 col-md-offset-4">
			<c:if test="${not empty usuarioExistente}">
				<div class="alert alert-danger" role="alert">Usuário existente!</div>
			</c:if>
			
			<div class="form-group">
				<form:form method="POST" action="efetuaCadastro">
					
					<div class="form-group">
						<label for="name">Email:</label>
						<input type="email" name="login" class="form-control" placeholder="Email" required="required">
					</div>
					<div class="form-group">
					<label for="senha">Senha:</label>					
						<input type="password" name="senha" class="form-control" placeholder="Senha" required="required">
					</div>

					<div class="form-group col-md-offset-4">
						<button type="submit" class="btn btn-default btn-lg">Cadastrar</button>
					</div>				
				</form:form>
			</div>
		</div>
	</div>
	<c:import url="footer.jsp"></c:import>

</body>
</html>