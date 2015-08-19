<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<c:import url="header.jsp"></c:import>
	<form action="adiciona">
		<div class="form-group">
			<label for="titulo">Titulo da Questão:</label>
			<input type="text" name="titulo" class="form-control" id="titulo">
		</div>

		<div class ="form-group">
			<label for="alternativaE">Alternativa A:</label>
			<input type="text" name="alternativaA" class="form-control" id="alternativaA">
		</div>

		<div class="form-group">
			<label for="alternativaE">Alternativa B:</label>
			<input type="text" name="alternativaB" class="form-control" id="alternativaB">
		</div>

		<div class="form-group">
			<label for="alternativaE">Alternativa C:</label>
			<input type="text" name="alternativaC" class="form-control" id="alternativaC">
		</div>			

		<div class="form-group">	
			<label for="alternativaE">Alternativa D:</label>
			<input type="text" name="alternativaD" class="form-control" id="alternativaD">
		</div>

		<div class="form-group">	
			<label for="alternativaE">Alternativa E:</label>
			<input type="text" name="alternativaE" class="form-control" id="alternativaE">
		</div>
	</form>		
	<c:import url="footer.jsp"></c:import>