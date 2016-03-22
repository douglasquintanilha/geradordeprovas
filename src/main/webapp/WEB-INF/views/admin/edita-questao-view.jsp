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

	<c:forEach items="${questoes}" var="questao">
		<a href="<c:url value='edita/${questao.id}'/>">${questao.titulo}</a><br>
	</c:forEach>

	</div>
	<c:import url="../footer.jsp"></c:import>
	
</body>
</html>