<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://ur6lad.co.ua/markdown-taglib' prefix='md'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Resultado da Prova</title>
	<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css' /> ">
	<link rel="stylesheet" href="<c:url value='/static/css/main.css' /> ">
	<link rel="stylesheet" href="<c:url value='/static/css/github.css' /> ">
	<link rel="stylesheet" href="<c:url value='/static/css/code.css' /> ">
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<div class="container">
		<h1>Sua nota foi:</h1>
		<h3>${avaliacao.nota}/${avaliacao.prova.quantidadeDeQuestoes}</h3>
		<h4>Gabarito:</h4>
		<c:forEach items="${avaliacao.prova.questoes}" var="questao" varStatus="i">
			<div class="questao">
				<h3>
					<md:render options="FencedCodeBlocks">${i.index+1} - ${questao.titulo}<br></md:render>
				</h3>
				<c:forEach items="${questao.alternativa}" var="alternativa">
					<c:set var="alternativaM" value="${false}"/>
					<div class="radio alternativa">
						<c:if test="${alternativa.alternativaCorreta}">
							<span class="icone glyphicon glyphicon-ok" aria-hidden="true"></span>
						</c:if>
						<c:forEach items="${avaliacao.alternativasMarcadas}" var="alternativaMarcada">
							<c:if test="${alternativa.id == alternativaMarcada.id}">
								<c:if test="${alternativa.alternativaCorreta == false}">
									<span class="icone glyphicon glyphicon-remove" aria-hidden="true"></span>
								</c:if>
							</c:if>
						</c:forEach>
						<label for="${alternativa.id}"> <input type="radio" value="${alternativa.id}"
							<c:forEach items="${avaliacao.alternativasMarcadas}" var="alternativaMarcada">
									<c:if test="${alternativa.id == alternativaMarcada.id}">
										checked="checked"
										<c:set var="alternativaM" value="${true}"/>										
									</c:if>
							</c:forEach> 
							<c:if test="${alternativaM == false}">
								disabled
							</c:if>							
							id="${alternativa.id}" name="alternativas[${i.index}]"> 
							<md:render options="FencedCodeBlocks">${alternativa.descricao}</md:render>
						</label>
					</div>
				</c:forEach>												
			</div>
		</c:forEach>
		
		<c:url var="urlPost" value='/feedback'/>
		<form:form method="POST" action="${urlPost}">
		<hr>
		<h3>Deixe aqui seu feedback sobre o sistema!</h3>
			<div class="form-group">
				
				<label class="radio-inline"><input type="radio" name="nota" value="1">Péssimo</label>
				<label class="radio-inline"><input type="radio" name="nota" value="2">Ruim</label>
				<label class="radio-inline"><input type="radio" name="nota" value="3">Razoável</label>
				<label class="radio-inline"><input type="radio" name="nota" value="4">Bom</label>
				<label class="radio-inline"><input type="radio" name="nota" value="5">Muito Bom</label>
										
				<textarea class="form-control entrada"  
					name="comentario" id="comentario" 
					placeholder="Nos ajude a melhorar!" required></textarea>
				<button type="submit" class="btn btn-default">Enviar!</button>
				
			</div>
		</form:form>
		
		<a href="<c:url value='/'/>">Voltar para o início</a>
	</div>
	<c:import url="footer.jsp"></c:import>
	<script src="<c:url value='/static/js/jquery-2.1.4.min.js' />"></script>
	<script src="<c:url value='/static/js/highlight.min.js' />"></script>
	<script src="<c:url value='/static/js/highlight-init.js' />"></script>
	<script src="<c:url value='/static/js/marca-questoes-corretas.js' />"></script>
</body>
</html>
