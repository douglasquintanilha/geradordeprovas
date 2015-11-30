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
</head>
<body>

	<c:import url="header.jsp"></c:import>
	<div class="container">
		<c:if test="${validacao == false}">
		Preencha todas as Respostas!
		</c:if>
		<c:url var="urlPost" value='correcao' />
		<form:form method="GET" action="${urlPost}" command="avaliacao" >
			<input type="hidden" name="prova.id" value="${prova}">
			<ol>
				<c:forEach items="${prova.questoes}" var="questao" varStatus="i">
					<li>
						<h3><md:render>${questao.titulo}</md:render></h3>
						<c:forEach items="${questao.alternativa}" var="alternativa">
						<div class="radio">				
							<label for="${alternativa.id}">
								<input type="radio" value="${alternativa.id}"  id="${alternativa.id}" required="required" name="alternativasMarcadas[${i.index}]">
								<md:render>${alternativa.descricao}</md:render>
							</label>
						</div>	
						</c:forEach>
					</li>
				</c:forEach>
			</ol>
			<button type="submit" class="btn btn-default">Ok</button>
		</form:form>
	</div>		
<c:import url="footer.jsp"></c:import>

</body>
</html>