<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:import url="header.jsp"></c:import>
<form action="<c:url value='salva-questao'/>" method="POST">

<div class="form-group">
	<label for="titulo">Titulo da Questão:</label>
	<form:errors path="questao.titulo" cssClass="alert alert-danger" element="div"/>
	<input type="text" name="titulo" class="form-control" id="titulo">
</div>

<form:errors path="questao.alternativas" cssClass="alert alert-danger" element="div"/>


<div class="form-group">
	<label for="alternativa">Alternativa A:</label>
	<div class="input-group">
		<span class="input-group-addon">
			<input type="radio" name="alternativaCorreta" value="0">
		</span>
		<input type="text" name="alternativa" class="form-control" id="alternativa">
	</div>
</div>			

<div class="form-group">
	<label for="alternativa">Alternativa B:</label>
	<div class="input-group">
		<span class="input-group-addon">
			<input type="radio" name="alternativaCorreta" value="1">
		</span>
		<input type="text" name="alternativa" class="form-control" id="alternativa">
	</div>
</div>			

<div class="form-group">
	<label for="alternativa">Alternativa C:</label>
	<div class="input-group">
		<span class="input-group-addon">
			<input type="radio" name="alternativaCorreta" value="2">
		</span>
		<input type="text" name="alternativa" class="form-control" id="alternativa">
	</div>
</div>			

<div class="form-group">
	<label for="alternativa">Alternativa D:</label>
	<div class="input-group">
		<span class="input-group-addon">
			<input type="radio" name="alternativaCorreta" value="3">
		</span>
		<input type="text" name="alternativa" class="form-control" id="alternativa">
	</div>
</div>	

<div class="form-group">
	<label for="alternativa">Alternativa E:</label>
	<div class="input-group">
		<span class="input-group-addon">
			<input type="radio" name="alternativaCorreta" value="4">
		</span>
		<input type="text" name="alternativa" class="form-control" id="alternativa">
	</div>
</div>			

<button type="submit" class="btn btn-default">Adicionar</button>
</form>		
<c:import url="footer.jsp"></c:import>

