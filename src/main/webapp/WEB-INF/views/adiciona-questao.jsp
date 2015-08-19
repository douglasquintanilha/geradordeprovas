<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="header.jsp"></c:import>
<form action="salva-questao" method="POST">
	<div class="form-group">
		<label for="titulo">Titulo da Questão:</label>
		<input type="text" name="titulo" class="form-control" id="titulo">
	</div>

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

