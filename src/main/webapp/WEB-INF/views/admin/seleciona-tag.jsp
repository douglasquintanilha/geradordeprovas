<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Questoes Por Tag</title>
<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/static/css/main.css' />">
</head>
<body>
	<c:import url="../header.jsp"></c:import>
	<form action="mostra-por-tag" method="GET">
		<select name="tagSelecionada">
			<c:forEach items="${tags}" var="tag">
				<option value="${tag.nome}">${tag.nome}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Procurar">
	</form>
	<c:import url="../footer.jsp"></c:import>
	
</body>
</html>
