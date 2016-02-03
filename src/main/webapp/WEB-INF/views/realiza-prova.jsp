<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://ur6lad.co.ua/markdown-taglib' prefix ='md' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Boa Sorte</title>
	<link rel="stylesheet" href="../static/css/bootstrap.min.css">
	<link rel="stylesheet" href="../static/css/main.css">
	<link rel="stylesheet" href="<c:url value='/static/css/github.css' /> ">
	
	<link rel="stylesheet" href="../static/css/clock.css">
</head>
<body>

	<c:import url="header.jsp"></c:import>
	<div class="container">
		<c:if test="${validacao == false}">
		Preencha todas as Respostas!
		</c:if>
		
		<div id="clock-div"></div>
		
		<c:url var="urlPost" value='correcao' />
		<form:form method="POST" action="${urlPost}" id="command" command="avaliacao" >
			<input type="hidden" name="prova" value="${prova.id}">
			<input type="hidden" id="duracao" value="${prova.duracao}">
			<ol>
				<c:forEach items="${prova.questoes}" var="questao" varStatus="i">
					<li>
						<h3><md:render>${questao.titulo}</md:render></h3>
						<c:forEach items="${questao.alternativa}" var="alternativa">
						<div class="radio">				
							<label for="${alternativa.id}">
								<input type="radio" value="${alternativa.id}" required="required"  id="${alternativa.id}"  name="alternativasMarcadas[${i.index}]">
								<md:render>${alternativa.descricao}</md:render>
							</label>
						</div>	
						</c:forEach>
					</li>
				</c:forEach>
			</ol>
			<label><input type="checkbox" required="required">  Estou ciente das opções marcadas e desejo enviar</label><br>
			<button type="submit" class="btn btn-default">Ok</button>
		</form:form>
	</div>		
<c:import url="footer.jsp"></c:import>
<script src="<c:url value='/static/js/jquery-2.1.4.min.js' />"></script>
<script src="<c:url value='/static/js/highlight.min.js' />"></script>
<script src="<c:url value='/static/js/highlight-init.js' />"></script>
<script src="<c:url value='/static/js/countdown.js' />"></script>
</body>
</html>