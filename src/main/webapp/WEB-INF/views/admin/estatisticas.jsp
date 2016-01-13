<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gerador de Provas</title>
<link rel="stylesheet" href="../static/css/bootstrap.min.css">
<link rel="stylesheet" href="../static/css/main.css">
</head>
<body>
	<c:import url="../header.jsp"></c:import>
	
	<c:forEach items="${questoes}" var="questao">
		
		<div class="container">
			<div class="panel panel-default">
				
				<div class="panel-heading">
					${questao.titulo}
				</div>
				
				<table class="table">
				
					<tr>
						<th>Acertos</th>
						<th>Erros</th>
					</tr>
					
					<tr>
						<td>${questao.estatistica.acertos}</td>
						<td>${questao.estatistica.erros}</td>
					</tr>
				</table>
			</div>
		</div>
		
		
		
	</c:forEach>
	
	<c:import url="../footer.jsp"></c:import>
</body>
</html>
