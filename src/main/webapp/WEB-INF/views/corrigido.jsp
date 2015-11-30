<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://ur6lad.co.ua/markdown-taglib' prefix ='md' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resultado da Prova</title>
<link rel="stylesheet" href="../static/css/bootstrap.min.css">
<link rel="stylesheet" href="../static/css/main.css">
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<div class="container">
		<h1>Sua nota foi:</h1>
		<h3>${avaliacao.nota}/${avaliacao.prova.quantidadeDeQuestoes}</h3>
		<h4>Gabarito:</h4>
			<c:forEach items="${avaliacao.prova.questoes}" var="questao" varStatus = "i">
					<h3><md:render>${questao.titulo}<br></md:render></h3>
					<c:forEach items="${questao.alternativa}" var="alternativa">
						<div class="radio">
							<label for="${alternativa.id}"<c:choose><c:when test="${alternativa.alternativaCorreta == true}">class="green"</c:when>
							 <c:otherwise>class="red"</c:otherwise></c:choose>>
								<input type="radio" disabled <c:if test="${alternativa.id == avaliacao.alternativasMarcadas[i.index].id }">checked="checked"</c:if>
								 value="${alternativa.id}"  id="${alternativa.id}" name="alternativas[${i.index}]">
								<md:render>${alternativa.descricao}</md:render>
							</label>
						</div>	
					</c:forEach>
			</c:forEach>
		<a href="<c:url value='/'/>">Voltar para o início</a>
	</div>
	<c:import url="footer.jsp"></c:import>
</body>
</html>