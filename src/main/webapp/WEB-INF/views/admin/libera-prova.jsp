<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>Caelum Provas</title>
<link rel="stylesheet"
	href="<c:url value='../static/css/bootstrap.min.css' />">
<link rel="stylesheet"
	href="<c:url value='../static/css/jquery-ui.min.css' /> ">
<link rel="stylesheet" href="<c:url value='../static/css/main.css' /> ">
</head>
<body>
	<c:import url="../header.jsp"></c:import>
	<c:url var="urlPost" value='salva-liberacao'/>
	<form:form method="POST" action="${urlPost}">
	
	<c:forEach items="${provas}" var="prova">
		<input type="checkbox" name="provas" value="${prova.id}">${prova.id} / ${prova.nome}<br>
	</c:forEach>
	------------------------------------------------------------------------------------------------
		<br>
		<c:forEach items="${usuarios}" var="usuario">

			<input type="checkbox" name="usuarios" value="${usuario.login}">${usuario.login}
			<br>

		</c:forEach>
		<input type="submit" value="LiberoSim">
	</form:form>
		<c:import url="../footer.jsp"></c:import>
</body>
</html>