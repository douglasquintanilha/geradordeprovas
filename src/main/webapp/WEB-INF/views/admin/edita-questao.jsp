<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://ur6lad.co.ua/markdown-taglib' prefix='md'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Caelum Provas</title>

	<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css' />">
	<link rel="stylesheet" href="<c:url value='/static/css/jquery-ui.min.css' /> ">
	<link rel="stylesheet" href="<c:url value='/static/css/main.css' /> ">
	<link rel="stylesheet" href="<c:url value='/static/css/github.css' /> ">
	<link rel="stylesheet" href="<c:url value='/static/css/jquery.tagit.css' /> ">
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/flick/jquery-ui.css">
</head>


</head>
<body>
	<c:import url="../header.jsp"></c:import>
	<span id="json-url" data-baseurl="<c:url value='/json' />"></span>
	<div class="container">
		<c:url var="urlPost" value='salva' />
		<form:form action="${urlPost}" method="POST" commandName="questao">

			<input type="hidden" name="questaoId" value="${questao.id}">
			<form:errors path="alternativaCorreta" cssClass="alert alert-danger" element="div" />
			<div class="form-group grupo-titulo">
				<label for="titulo">Titulo da Questão:</label>
				<form:errors path="titulo" cssClass="alert alert-danger" element="div" />
				<div class='esconde'>
					<span>Preview:</span>
					<div class="preview-markdown"></div>
				</div>
				<textarea class="form-control entrada" placeholder="Digite aqui o título da sua questão" name="titulo" id="titulo">${questao.titulo}</textarea>
			</div>

			<div class="form-group">
				<label for="tags">Escolha as tags da questão:</label> 
				<ul id="myTags">
					<c:forEach items="${questao.tags}" var="tag">
						<li>${tag}</li>
					</c:forEach>
				</ul>
			</div>

			<span>Escolha qual será a alternativa correta:</span>
			<div class="form-group alternativa">
				<label for="alternativa">Alternativa A:</label>
				<form:errors path="alternativa[0].descricao" cssClass="alert alert-danger" element="div" />
				<div class='esconde'>
					<span>Preview:</span>
					<div class="previ	ew-markdown"></div>
				</div>
				<div class="input-group">
					<span class="input-group-addon"> 
						<input type="radio" name="alternativaCorreta" value="0" id="botaoA" <c:if test="${questao.alternativa[0].alternativaCorreta == true}">checked="checked"</c:if>/>
					</span>
					<textarea class="form-control entrada" name="alternativa" id="alternativa">${questao.alternativa[0].descricao}</textarea>
				</div>
			</div>

			<div class="form-group alternativa">
				<label for="alternativa">Alternativa B:</label>

				<form:errors path="alternativa[1].descricao" cssClass="alert alert-danger" element="div" />
				<div class='esconde'>
					<span>Preview:</span>
					<div class="preview-markdown"></div>
				</div>
				<div class="input-group">
					<span class="input-group-addon"> <input type="radio" name="alternativaCorreta" value="1" <c:if test="${questao.alternativa[1].alternativaCorreta == true}">checked="checked"</c:if>/>
					</span>
					<textarea class="form-control entrada" name="alternativa" id="alternativa">${questao.alternativa[1].descricao}</textarea>
				</div>
			</div>

			<div class="form-group alternativa">
				<label for="alternativa">Alternativa C:</label>
				<form:errors path="alternativa[2].descricao" cssClass="alert alert-danger" element="div" />
				<div class='esconde'>
					<span>Preview:</span>
					<div class="preview-markdown"></div>
				</div>
				<div class="input-group">
					<span class="input-group-addon"> <input type="radio" name="alternativaCorreta" value="2" <c:if test="${questao.alternativa[2].alternativaCorreta == true}">checked="checked"</c:if>>
					</span>
					<textarea class="form-control entrada" name="alternativa" id="alternativa">${questao.alternativa[2].descricao}</textarea>
				</div>
			</div>

			<div class="form-group alternativa">
				<label for="alternativa">Alternativa D:</label>
				<form:errors path="alternativa[3].descricao" cssClass="alert alert-danger" element="div" />
				<div class='esconde'>
					<span>Preview:</span>
					<div class="preview-markdown"></div>
				</div>
				<div class="input-group">
					<span class="input-group-addon"> <input type="radio" name="alternativaCorreta" value="3" <c:if test="${questao.alternativa[3].alternativaCorreta == true}">checked="checked"</c:if> />
					</span>
					<textarea class="form-control entrada" name="alternativa" id="alternativa">${questao.alternativa[3].descricao}</textarea>
				</div>
			</div>


			<div class="form-group alternativa">
				<label for="alternativa">Alternativa E:</label>
				<form:errors path="alternativa[4].descricao" cssClass="alert alert-danger" element="div" />
				<div class='esconde'>
					<span>Preview:</span>
					<div class="preview-markdown"></div>
				</div>
				<div class="input-group">
					<span class="input-group-addon"> <input type="radio" name="alternativaCorreta" value="4" <c:if test="${questao.alternativa[4].alternativaCorreta == true}">checked="checked"</c:if>/>
					</span>
					<textarea class="form-control entrada" name="alternativa" id="alternativa">${questao.alternativa[4].descricao}</textarea>
				</div>
			</div>

			<button type="submit" class="btn btn-default">Editar Questão</button>

		</form:form>

		<script src="<c:url value='/static/js/jquery-2.1.4.min.js' />"></script>
		<script src="<c:url value='/static/js/jquery-ui.min.js' />"></script>
		<script src="<c:url value='/static/js/tag-it.min.js' />"></script>
		<script src="<c:url value='/static/js/tag-it-init.js' />"></script>
		<script src="<c:url value='/static/js/showdown.min.js' />"></script>
		<script src="<c:url value='/static/js/markdown-preview.js' />"></script>
		<script src="<c:url value='/static/js/highlight.min.js' />"></script>
		<script src="<c:url value='/static/js/autogrowtextarea.min.js' />"></script>
		<script src="<c:url value='/static/js/autogrow-init.js' />"></script>
	</div>
	<c:import url="../footer.jsp"></c:import>
</body>
</html>