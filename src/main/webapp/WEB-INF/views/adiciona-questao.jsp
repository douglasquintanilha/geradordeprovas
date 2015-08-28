<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Caelum Provas</title>
		<link rel="stylesheet" href="static/css/bootstrap.min.css">
		<link rel="stylesheet" href="static/css/main.css">

	</head>
	<body>
		<c:import url="header.jsp"></c:import>
		<div class="container">	

			
			<c:url var="urlPost" value='salva-questao' />
			<form:form action="${urlPost}" method="POST" commandName="questao">

				<form:errors path="alternativaCorreta" cssClass="alert alert-danger" element="div" />
				<div class="form-group">
					<label for="titulo">Titulo da Questão:</label>
					<form:errors path="titulo" cssClass="alert alert-danger" element="div" />
					<input type="text" value="${questao.titulo}" name="titulo"class="form-control" id="titulo" />
				</div>

				<div class="form-group">
					<label for="tags">Escolha as tags da questão:</label> 
					<input type="text" name="tags" id="tags">
				</div>

				<span>Escolha qual será a alternativa correta:</span>
				<div class="form-group">
					<label for="alternativa">Alternativa A:</label>
					<form:errors path="alternativa[0].descricao"
					cssClass="alert alert-danger" element="div" />
					<div class="input-group">
						<span class="input-group-addon"> <input type="radio"
							name="alternativaCorreta" value="0" />
						</span> <input type="text" value="${alternativa[0].descricao}"
						class="form-control" name="alternativa">
					</div>
				</div>

				<div class="form-group">
					<label for="alternativa">Alternativa B:</label>
					<form:errors path="alternativa[1].descricao"
					cssClass="alert alert-danger" element="div" />
					<div class="input-group">
						<span class="input-group-addon"> <input type="radio"
							name="alternativaCorreta" value="1" />
						</span> <input type="text" value="${alternativa[1].descricao}"
						class="form-control" name="alternativa">
					</div>
				</div>

				<div class="form-group">
					<label for="alternativa">Alternativa C:</label>
					<form:errors path="alternativa[2].descricao"
					cssClass="alert alert-danger" element="div" />
					<div class="input-group">
						<span class="input-group-addon"> <input type="radio"
							name="alternativaCorreta" value="2" />
						</span> <input type="text" value="${alternativa[2].descricao}"
						class="form-control" name="alternativa">
					</div>
				</div>

				<div class="form-group">
					<label for="alternativa">Alternativa D:</label>
					<form:errors path="alternativa[3].descricao"
					cssClass="alert alert-danger" element="div" />
					<div class="input-group">
						<span class="input-group-addon"> <input type="radio"
							name="alternativaCorreta" value="3" />
						</span> <input type="text" value="${alternativa[3].descricao}"
						class="form-control" name="alternativa">
					</div>
				</div>


				<div class="form-group">
					<label for="alternativa">Alternativa E:</label>
					<form:errors path="alternativa[4].descricao"
					cssClass="alert alert-danger" element="div" />
					<div class="input-group">
						<span class="input-group-addon"> <input type="radio"
							name="alternativaCorreta" value="4" />
						</span> <input type="text" value="${alternativa[4].descricao}"
						class="form-control" name="alternativa">
					</div>
				</div>

				<button type="submit" class="btn btn-default">Adicionar</button>

			</form:form>

			<script src="jquery-2.1.4.min.js"></script>
			<script src="jquery-ui.min.js"></script>
			<script src="autocomplete-tags.js"></script>

			<c:import url="footer.jsp"></c:import>

		</div>
	</body>
</html>