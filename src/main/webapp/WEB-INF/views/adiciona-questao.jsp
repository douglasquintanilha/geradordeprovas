<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:import url="header.jsp"></c:import>
<c:url var="urlPost" value='salva-questao'/>
<form:form action="${urlPost}" method="POST" commandName="questao">

	<div class="form-group">
		<label for="titulo">Titulo da Questão:</label>
		<form:errors path="titulo" cssClass="alert alert-danger" element="div"/>
		<form:input path="titulo" type="text" cssClass="form-control" id="titulo" />
	</div>

	<div class="form-group">
		<label for="alternativa">Alternativa A:</label>
		<div class="input-group">
			<span class="input-group-addon">
				<form:radiobutton path="alternativaCorreta" value="0"/>
			</span>
			<form:input path="alternativa" cssClass="form-control" id="alternativa"/>
		</div>
	</div>
 
	<div class="form-group">
		<label for="alternativa">Alternativa B:</label>
		<div class="input-group">
			<span class="input-group-addon">
				<form:radiobutton path="alternativaCorreta" value="1"/>
			</span>
			<form:input path="alternativa" cssClass="form-control" id="alternativa"/>
		</div>
	</div>			
	

	<button type="submit" class="btn btn-default">Adicionar</button>
</form:form>		
<c:import url="footer.jsp"></c:import>

