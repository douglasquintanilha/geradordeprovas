<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8" /> 
		<title>Caelum Provas</title>
		<link rel="stylesheet" href="<c:url value='../static/css/bootstrap.min.css' />">
		<link rel="stylesheet" href="<c:url value='../static/css/jquery-ui.min.css' /> ">
		<link rel="stylesheet" href="<c:url value='../static/css/main.css' /> ">
		<link rel="stylesheet" href="<c:url value='../static/css/highlight.min.css' /> ">
		

	</head>
	<body>
		<c:import url="../header.jsp"></c:import>
		<div class="container">	

			
			<c:url var="urlPost" value='salva-questao' />
			<form:form action="${urlPost}" method="POST" commandName="questao">

				<form:errors path="alternativaCorreta" cssClass="alert alert-danger" element="div" />
				<div class="form-group">
					<label for="titulo">Titulo da Questão:</label>
					<form:errors path="titulo" cssClass="alert alert-danger" element="div" />
					<div class='esconde'>
						<span>Preview:</span>
						<div id='titulo-markdown-preview' class="preview-markdown"></div>
					</div>
					<input type="text" value="${questao.titulo}" placeholder="Digite aqui o título da sua questão" name="titulo" class="form-control" id="titulo" />
				</div>
					
				<div class="form-group">
					<label for="tags">Escolha as tags da questão:</label> 
					<input type="text" placeholder="Digite aqui as tags da questão e vá selecionando-as, para adicionar uma nova tag basta colocá-la entre vírgulas que na próxima vez ela será preenchida automaticamente." value="${questao.tags}" name="tags" id="tags" class="form-control">
				</div>

				<span>Escolha qual será a alternativa correta:</span>
				<div class="form-group">
					<label for="alternativa">Alternativa A:</label>
					<div class="alternativaA-markdown-preview"></div>
					<form:errors path="alternativa[0].descricao" cssClass="alert alert-danger" element="div" />
					<div class='esconde'>
						<span>Preview:</span>
						<div id="alternativaA-markdown-preview" class="preview-markdown"></div>
					</div>	
					<div class="input-group">
						<span class="input-group-addon"> <input type="radio"
							name="alternativaCorreta" value="0" id="botaoA"/>
						</span> 
						<textarea class="form-control alternativa" name="alternativa" id="alternativaA">
							${alternativa[0].descricao}
						</textarea>	
					</div>
				</div>

				<div class="form-group">
					<label for="alternativa">Alternativa B:</label>
					
					<form:errors path="alternativa[1].descricao" cssClass="alert alert-danger" element="div" />
					<div class='esconde'>
						<span>Preview:</span>
						<div id="alternativaB-markdown-preview" class="preview-markdown"></div>
					</div>						
					<div class="input-group">
						<span class="input-group-addon"> <input type="radio"
							name="alternativaCorreta" value="1" />
						</span> 
						<textarea class="form-control alternativa" name="alternativa" id="alternativaB">
							${alternativa[1].descricao}
						</textarea>	
					</div>
				</div>

				<div class="form-group">
					<label for="alternativa">Alternativa C:</label>
					<form:errors path="alternativa[2].descricao" cssClass="alert alert-danger" element="div" />
					<div class='esconde'>
						<span>Preview:</span>
						<div id="alternativaC-markdown-preview" class="preview-markdown"></div>
					</div>	
					<div class="input-group">
						<span class="input-group-addon"> <input type="radio"
							name="alternativaCorreta" value="2" >
						</span> 
						<textarea class="form-control alternativa" name="alternativa" id="alternativaC">
							${alternativa[2].descricao}
						</textarea>
					</div>
				</div>

				<div class="form-group">
					<label for="alternativa">Alternativa D:</label>
					<form:errors path="alternativa[3].descricao" cssClass="alert alert-danger" element="div" />
					<div class='esconde'>
						<span>Preview:</span>
						<div id="alternativaD-markdown-preview" class="preview-markdown"></div>
					</div>	
					<div class="input-group">
						<span class="input-group-addon"> <input type="radio"
							name="alternativaCorreta" value="3" />
						</span>
						<textarea class="form-control alternativa" name="alternativa" id="alternativaD">
							${alternativa[3].descricao}
						</textarea>
					</div>
				</div>


				<div class="form-group">
					<label for="alternativa">Alternativa E:</label>
					<form:errors path="alternativa[4].descricao" cssClass="alert alert-danger" element="div" />
					<div class='esconde'>
						<span>Preview:</span>
						<div id="alternativaE-markdown-preview" class="preview-markdown"></div>
					</div>
					<div class="input-group">
						<span class="input-group-addon"> <input type="radio"
							name="alternativaCorreta" value="4" />
						</span> 
						<textarea class="form-control alternativa" name="alternativa" id="alternativaE">
							${alternativa[4].descricao}
						</textarea>
					</div>
				</div>

				<button type="submit" class="btn btn-default">Adicionar Questão</button>

			</form:form>

			<script src="<c:url value='../static/js/jquery-2.1.4.min.js' />"></script>
			<script src="<c:url value='../static/js/jquery-ui.min.js' />"></script>
			<script src="<c:url value='../static/js/autocomplete-tags.js' />"></script>
			<script src="<c:url value='../static/js/limpa-campo.js' />"></script>
			<script src="<c:url value='../static/js/markdown.min.js' />"></script>
			<script src="<c:url value='../static/js/markdown-preview.js' />"></script>
			<script src="<c:url value='../static/js/highlight.min.js' />"></script>
			<script src="<c:url value='../static/js/preview.js' />"></script>
		</div>
		<c:import url="../footer.jsp"></c:import>
	</body>
</html>