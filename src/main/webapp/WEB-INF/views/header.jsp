<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
	<a href="<c:url value='/'/>">
		<img src="<c:url value='/static/imagens/caelum-logo.svg' />" alt="Caelum - Ensino e Inova��o" id="logo-header">
	</a>
	<a href="<c:url value='/novo-usuario-form'/>" id="link-novo-usuario">Cadastre-se</a>
	<a href="<c:url value='/logout'/>" id="link-logout">logout</a>
	<div class="clear"></div>
	<script src="<c:url value='/static/js/escondeLogout.js' />"></script>
</header>	