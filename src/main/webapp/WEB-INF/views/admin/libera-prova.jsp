<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:import url="headerAdmin.jsp"></c:import>


<c:url var="urlPost" value='salva-liberacao'/>
	<form:form method="GET" action="${urlPost}">
	
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

<c:import url="footerAdmin.jsp"></c:import>