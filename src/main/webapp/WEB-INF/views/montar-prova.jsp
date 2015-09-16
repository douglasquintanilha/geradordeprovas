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
		
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span></span> <input type="text" class="form-control" id="tags-filtering" placeholder="Filtre por tags..." aria-describedby="basic-addon1">
		</div>
		<br>
		
		<form>
		<div class="questoes">
			<ul class="list">
				<li>
					<c:forEach items="${questoes}" var="questao">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<label><input type="checkbox" name="questoesEscolhidas" value="${questao.id}"> Questão ${questao.id}</label>
								</div>
								<div class="panel-body">
									<p>${questao.titulo}</p>
									<span>Tags: </span>
									<c:forEach items="${questao.tags}" var="tag">
											<b><span class="tags">${tag} </span></b>,
									</c:forEach>
									<br>
									<button id="botao-exibir-alternativas" class="btn btn-primary" type="button" data-toggle="collapse" data-target="#alternativas" aria-expanded="false" aria-controls="alternativas">Exibir alternativas</button>
									<button id="botao-esconder-alternativas" class="btn btn-primary" type="button" data-toggle="collapse" data-target="#alternativas" aria-expanded="false" aria-controls="alternativas" style="display: none">Esconder alternativas</button>
									<ol class="collapse" id="alternativas" type="A">
										<c:forEach items="${questao.alternativa}" var="alternativa">
											<li>${alternativa.descricao})</li>
										</c:forEach>
									</ol>
								</div>
							</div>
					</c:forEach>
				<li>
			</ul>
		</div>
		<form>
		
	</div>
	<c:import url="footer.jsp"></c:import>
	<script src="static/js/jquery-2.1.4.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/list.min.js"></script>
	<script src="static/js/troca-texto.js"></script>
	<script src="static/js/filtra-por-tag.js"></script>
</body>
</html>