<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Caelum Provas</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/jquery-ui.min.css">
<link rel="stylesheet" href="static/css/main.css">


</head>
<body>
	<c:import url="header.jsp"></c:import>
	<div class="container">
		<h1>Montar a prova:</h1>
		<form>
		<c:forEach items="${questoes}" var="questao">
			<div class="panel panel-default">
				<div class="">${questao.titulo}</div>
				<c:forEach items="${questao.tags}" var="tag">
					<span><b>${tag}</b></span>
				</c:forEach>
				<ol type="A">
					<c:forEach items="${questao.alternativa}" var="alternativa">
						<li>${alternativa.descricao})</li>
					</c:forEach>
				</ol>
			</div>
		<form>										
		</c:forEach>
	</div>
	<c:import url="footer.jsp"></c:import>
</body>
</html>