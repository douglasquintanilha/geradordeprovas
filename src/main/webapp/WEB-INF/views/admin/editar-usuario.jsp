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
		
		<c:url var="urlPost" value='${usuario.id}'/>	
		<form:form action="${urlPost}" method="POST" commandName="usuario" class="form-horizontal col-xs-2">
			<input type="hidden" value="${usuario.id}" name="id">
			<div class="form-group">
				<label for="nome">Nome:</label>
				<form:errors path="login" cssClass="alert alert-danger" element="div" />
				<input type="text" value="${usuario.login}" class="form-control" name="login" id="nome">
			</div>
			<div class="form-group">	
				<label for="senha">Senha:</label><br>
				<input type="checkbox" name="senhaAlterada" id="habilita-senha"><label for="habilita-senha"> Marque para habilitar</label>				
				<form:errors path="senha" cssClass="alert alert-danger" element="div" />
				<input type="password" disabled class="form-control" name="senha" id="senha">
			</div>
			<div class="form-group">	 
					<span>Admin? </span><br>
					<label class="radio-inline">
						<input type="radio" id="admin" name="admin" <c:if test="${usuario.admin == true}">checked="checked"</c:if> value="true">
						Sim 
					</label>
					<label class="radio-inline">
						 <input type="radio" id="admin" name="admin" <c:if test="${usuario.admin == false}">checked="checked"</c:if> value="false">
						 Nao
					 </label>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-default">Editar</button>
			</div>
		</form:form>
	</div>
	<c:import url="../footer.jsp"></c:import>
	<script src="<c:url value='/static/js/jquery-2.1.4.min.js' />"></script>	
	<script>
		var estado = true;
		$("#habilita-senha").on("change",function(){
			estado = !estado;
			$("#senha").prop("disabled", estado);
		});
	</script>
</body>
</html>