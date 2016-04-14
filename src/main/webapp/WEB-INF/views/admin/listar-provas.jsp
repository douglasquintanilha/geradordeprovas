<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Caelum Provas</title>
	<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css' />">
	<link rel="stylesheet" href="<c:url value='/static/css/jquery-ui.min.css' />">
	<link rel="stylesheet" href="<c:url value='/static/css/main.css' />">
</head>
<body>
	<c:import url="../header.jsp"></c:import>
	<div class="container">
	<c:if test="${not empty prova.id}">
		<div class="alert alert-success" role="alert">Prova #${prova.id} foi editada com sucesso!</div>
	</c:if>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th>Nome</th>
					<th>Descrição</th>
					<th>Ações<th>
				<tr>
			</thead>
			<tbody>
				<c:forEach var="prova" 	items="${provas}">
					<tr>
						<td>${prova.id}</td>
						<td>${prova.nome}</td>
						<td>${prova.descricao}</td>
						<td>
							<a href="<c:url value='editar/${prova.id}'/>">
								<span class="glyphicon glyphicon-pencil icone-tabela"></span>
							</a>
							<span class="glyphicon glyphicon-remove icone-tabela"></span>							
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	
	</div>		
	<c:import url="../footer.jsp"></c:import>	
</body>
</html>