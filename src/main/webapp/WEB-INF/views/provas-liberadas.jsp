<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://ur6lad.co.ua/markdown-taglib' prefix ='md' %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EscolhaSuaProva</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/main.css">
</head>
<body>

	<c:import url="header.jsp"></c:import>
	<div class="container">
			<h3>Selecione uma prova:</h3>
			<div class="row">
			<c:forEach items="${provas}" var="prova">
			    <div class="col-xs-3">
			        <a href="<c:url value='/avaliacao/${prova.id}'/>" class="thumbnail">
			             <img src="http://eliteuniversal.org/wp-content/uploads/2015/11/vestibular_carreiras.jpg" class="img-responsive">
			             <div class="caption">
			             	<label for="${prova.id}">
								${prova.nome}
							</label>
			             </div>
			        </a>
			    </div>
			</c:forEach>
			</div>
	</div>
	<c:import url="footer.jsp"></c:import>
</body>
</html>