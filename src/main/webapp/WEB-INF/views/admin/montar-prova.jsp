<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Caelum Provas</title>
<link rel="stylesheet" href="<c:url value='../static/css/bootstrap.min.css' />">
<link rel="stylesheet" href="<c:url value='../static/css/jquery-ui.min.css' />">
<link rel="stylesheet" href="<c:url value='../static/css/main.css' />">

</head>
<body>
	<c:import url="../header.jsp"></c:import>
	<div class="container"> 
		<h1>Monte uma prova:</h1>
		<br>
		<c:url var="urlPost" value='salvar-prova'/>
		<form:form method="POST" action="${urlPost}" commandName="prova" >
			<div class="form-group">
				<label for="nome">Nome da Prova:</label>
				<form:errors path="nome" cssClass="alert alert-danger" element="div" />
				<input type="text" value="${prova.nome}" name="nome" class="form-control" id="nome" required>
			</div>

			<h3>Escolha as questões:</h3>
			<h4>
				Questões escolhidas: <span id="questoes-marcadas">0</span>
			</h4>
				<form:errors path="questoes" cssClass="alert alert-danger" element="div" />
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span></span> <input type="text" class="form-control" id="busca" placeholder="Filtre por tags..." aria-describedby="basic-addon1">
			</div>
			<br>
			<c:forEach items="${questoes}" var="questao">
				<div class="panel panel-primary questao">
					<div class="panel-heading">
						<label><input type="checkbox" name="questoes" value="${questao.id}"> Questão ${questao.id}</label>
					</div>
					<div class="panel-body">
						<p>${questao.titulo}</p>
						<span>Tags: </span> <span class="tags"><c:forEach items="${questao.tags}" var="tag">${tag},</c:forEach></span> <br>
						<button class="btn btn-primary botao-exibir-alternativas" type="button" data-toggle="collapse" data-target="#alternativas${questao.id}" aria-expanded="false" aria-controls="alternativas${questao.id}">Exibir alternativas</button>	
						<ol class="collapse" id="alternativas${questao.id}" type="A">
							<c:forEach items="${questao.alternativa}" var="alternativa">
								<li>${alternativa.descricao})</li>
							</c:forEach>
						</ol>
					</div>
				</div>
			</c:forEach>
			<button type="submit" class="btn btn-default">Criar Prova</button>
		</form:form>

	</div>
	<c:import url="../footer.jsp"></c:import>
	
	<script src="<c:url value='../static/js/jquery-2.1.4.min.js' />"></script>
	<script src="<c:url value='../static/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='../static/js/troca-texto.js' />"></script>
	<script src="<c:url value='../static/js/filtra-por-tag.js' />"></script>
	<script src="<c:url value='../static/js/contador-de-questoes.js' />"></script>
</body>
</html>