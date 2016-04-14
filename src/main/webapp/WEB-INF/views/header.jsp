<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
	<a href="<c:url value='/'/>">
		<img src="<c:url value='/static/imagens/caelum-logo.svg' />" alt="Caelum - Ensino e Inovação" id="logo-header">
	</a>

	<c:set var="env" value="${constantes.environment}"/>
	<c:if test="${ env == 'local' }">
		<strong><h1 class="header-dev">LOCAL</h1></strong>
	</c:if>
	<c:if test="${ env == 'dev' }">
		<strong><h1 class="header-dev">DEV</h1></strong>
	</c:if>
	<a href="<c:url value='/logout'/>" role="button" class="btn btn-info" id="logout">logout</a>
	<div class="clear"></div>
	<script src="<c:url value='/static/js/escondeLogout.js' />"></script>
</header>	