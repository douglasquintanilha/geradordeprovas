<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cria Usuario</title>

<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css' />">
<link rel="stylesheet" href="<c:url value='/static/css/jquery-ui.min.css' />">
<link rel="stylesheet" href="<c:url value='/static/css/main.css' />">
<link rel="stylesheet" href="<c:url value='/static/css/github.css' /> ">

</head>
<body>
	
	<c:import url="../header.jsp"></c:import>
	
	
	
	
	<div class="container">
		<h1>Procure a questão pela prova</h1>
		
		<div class="input-group col-md-12">
			<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-tag" aria-hidden="true"></span></span> 
			<input type="text" class="form-control" id="busca" placeholder="Nome da prova..." aria-describedby="basic-addon1">
		</div>

		<div id="provas-list">
		<c:forEach items="${provas}" var="prova">
			<div class="panel panel-primary questao">
				<div class="panel-heading prova">
					${prova.nome}
				</div>
				<div class="panel-body">
					<button class="btn btn-primary botao-exibir-alternativas" type="button" data-toggle="collapse" 
						data-target="#prova${prova.id}" aria-expanded="false" aria-controls="prova${prova.id}">
						Exibir Questoes
					</button>	
					<ol class="collapse" id="prova${prova.id}" type="1">
						<c:forEach items="${prova.questoes}" var="questao">
							<li><md:render  options="FencedCodeBlocks"><a href="<c:url value='edita/${questao.id}'/>">${questao.titulo}</a></md:render></li>
						</c:forEach>
					</ol>
				</div>
			</div>
		</c:forEach>
		</div>	
	
	</div>



	<script src="<c:url value='/static/js/jquery-2.1.4.min.js' />"></script>
	<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/static/js/troca-texto.js' />"></script>
	<script src="<c:url value='/static/js/filtra-por-tag.js' />"></script>
	<script src="<c:url value='/static/js/contador-de-questoes.js' />"></script>
	<script src="<c:url value='/static/js/highlight.min.js' />"></script>
	<script src="<c:url value='/static/js/highlight-init.js' />"></script>	<script src="<c:url value='/static/js/filtra-por-prova.js' />"></script>
	<c:import url="../footer.jsp"></c:import>
	
</body>
</html>