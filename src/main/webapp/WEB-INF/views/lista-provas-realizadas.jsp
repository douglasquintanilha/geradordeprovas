<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://ur6lad.co.ua/markdown-taglib' prefix ='md' %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Provas</title>
<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css' />">
<link rel="stylesheet" href="<c:url value='/static/css/main.css' />">
</head>
<body>

	<c:import url="header.jsp"></c:import>
	<div class="container">
		
		<div class="container">
			<h4>Deseja tentar novamente?</h4>
			<c:set var="urlPost" value="realiza"/>
			<form:form action="${urlPost}" method="POST" class="form-horizontal col-xs-2">
			<div class="form-group">
				<input type="hidden" value="${idProva}" name="provaId">
				<input type="submit" class="btn btn-info" value="Sim">
			</div>
			</form:form>
		</div>
		
		<div>
			<c:forEach items="${avaliacoes}" var="avaliacao" varStatus="i">
				<div class="col-xs-3">
				        <a href="<c:url value='/avaliacao/realizada/${avaliacao.id}'/>" class="thumbnail">
				             <div class="caption">
				             	<label for="${avaliacao.id}">
									Nota: ${avaliacao.nota} / ${avaliacao.prova.quantidadeDeQuestoes}<br> 
									Data: ${avaliacao.dataRealizada}<br>
									Tempo gasto: ${avaliacao.duracao}min
								</label>
				             	<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				             </div>
				        </a>
				</div>
			</c:forEach>
		</div>
		
	</div>
	<c:import url="footer.jsp"></c:import>
</body>
</html>