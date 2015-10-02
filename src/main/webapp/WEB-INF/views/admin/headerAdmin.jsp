<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<header>
<meta charset="utf-8" />
<title>Caelum Provas</title>
<link rel="stylesheet" href="<c:url value='../static/css/bootstrap.min.css' />">
<link rel="stylesheet" href="<c:url value='../static/css/jquery-ui.min.css' /> ">
<link rel="stylesheet" href="<c:url value='../static/css/main.css' /> ">
	
	<a href="<c:url value='/'/>">
		<img src="<c:url value='/static/imagens/caelum-logo.svg' />" alt="Caelum - Ensino e Inovação" id="logo-header">
	</a>
	<a href="<c:url value='/logout'/>" id="link-logout">logout</a>
	<div class="clear"></div>
	<script src="<c:url value='/static/js/escondeLogout.js' />"></script>

</header>
<body>