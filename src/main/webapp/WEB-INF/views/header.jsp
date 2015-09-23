<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
	<a href="<c:url value='/'/>">
		<img src="static/imagens/caelum-logo.svg" alt="Caelum - Ensino e Inovação" id="logo-header">
	</a>
	<a href="logout" id="link-logout">logout</a>
	<div class="clear"></div>
	<script src="static/js/escondeLogout.js"></script>
</header>	