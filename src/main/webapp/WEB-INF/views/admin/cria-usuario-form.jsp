<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cria Usuario</title>
<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/static/css/main.css' />">
</head>
<body>
	<c:import url="../header.jsp"></c:import>
	<div class="container">
		<c:set var="urlPost" value="adiciona-usuario"/>
		<form action="${urlPost}" method="POST" class="form-horizontal col-xs-2">
			<div class="form-group">
				<label for="nome">Nome:</label>
				<input type="text" class="form-control" name="nome" id="nome">
			</div>
			<div class="form-group">	
				<label for="senha">Senha:</label>
				<input type="password" class="form-control" name="senha" id="senha">
			</div>
			<div class="form-group">	 
					<span>Admin? </span><br>
					<label class="radio-inline">
						<input type="radio" id="admin" name="admin" value="true">
						Sim 
					</label>
					<label class="radio-inline">
						 <input type="radio" id="admin" name="admin" value="false">
						 Nao
					 </label>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-default">Criar</button>
			</div>
		</form>
	</div>
	<c:import url="../footer.jsp"></c:import>
</body>
</html>