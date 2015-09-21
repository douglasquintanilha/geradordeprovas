<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Caelum Provas</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/jquery-ui.min.css">
<link rel="stylesheet" href="static/css/main.css">

</head>
<body>
	<c:import url="header.jsp"></c:import>
	<div class="container">
		<h1>Montar a prova:</h1>
		<h3>Questões escolhidas: <span id="questoes-marcadas">0</span></h3>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span></span> <input type="text" class="form-control" id="busca" placeholder="Filtre por tags..." aria-describedby="basic-addon1">
		</div>
		<br>		
		<form>
			<c:forEach items="${questoes}" var="questao">
				<div class="panel panel-primary questao">
					<div class="panel-heading">
						<label><input type="checkbox" name="questoesEscolhidas" value="${questao.id}"> Questão ${questao.id}</label>
					</div>
					<div class="panel-body">
						<p>${questao.titulo}</p>
						<span>Tags: </span>
						<span class="tags"><c:forEach items="${questao.tags}" var="tag">${tag},</c:forEach></span>
						<br>
						<button class="btn btn-primary botao-exibir-alternativas" type="button" data-toggle="collapse" data-target="#alternativas${questao.id}" aria-expanded="false" aria-controls="alternativas${questao.id}">Exibir alternativas</button>
						<button class="btn btn-primary botao-esconder-alternativas" type="button" data-toggle="collapse" data-target="#alternativas${questao.id}" aria-expanded="false" aria-controls="alternativas${questao.id}" style="display: none">Esconder alternativas</button>
						<ol class="collapse" id="alternativas${questao.id}" type="A">
							<c:forEach items="${questao.alternativa}" var="alternativa">
								<li>${alternativa.descricao})</li>
							</c:forEach>
						</ol>
					</div>
				</div>
			</c:forEach>
			
		</form>
		
	</div>
	<c:import url="footer.jsp"></c:import>
	<script src="static/js/jquery-2.1.4.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/troca-texto.js"></script>
	<script src="static/js/filtra-por-tag.js"></script>
	<script src="static/js/contador-de-questoes.js"></script>
</body>
</html>