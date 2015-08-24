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
			<form:input path="alternativa" cssClass="form-control" id="alternativa1"/>
			<form:errors path="alternativa" cssClass="alert alert-danger" element="div"/>
		</div>
	</div>
 
	<div class="form-group">
		<label for="alternativa">Alternativa B:</label>
		<div class="input-group">
			<span class="input-group-addon">
				<form:radiobutton path="alternativaCorreta" value="1"/>
			</span>
			<form:input path="alternativa" cssClass="form-control" id="alternativa2"/>
			<form:errors path="alternativa" cssClass="alert alert-danger" element="div"/>
		</div>
	</div>			
	
	<div class="form-group">
		<label for="alternativa">Alternativa C:</label>
		<div class="input-group">
			<span class="input-group-addon">
				<form:radiobutton path="alternativaCorreta" value="2"/>
			</span>
			<form:input path="alternativa" cssClass="form-control" id="alternativa3"/>
			<form:errors path="alternativa" cssClass="alert alert-danger" element="div"/>
		</div>
	</div>			
	
	<div class="form-group">
		<label for="alternativa">Alternativa D:</label>
		<div class="input-group">
			<span class="input-group-addon">
				<form:radiobutton path="alternativaCorreta" value="3"/>
			</span>
			<form:input path="alternativa" cssClass="form-control" id="alternativa4"/>
			<form:errors path="alternativa" cssClass="alert alert-danger" element="div"/>
		</div>
	</div>			
	
	<div class="form-group">
		<label for="alternativa">Alternativa E:</label>
		<div class="input-group">
			<span class="input-group-addon">
				<form:radiobutton path="alternativaCorreta" value="4"/>
			</span>
			<form:input path="alternativa" cssClass="form-control" id="alternativa5"/>
			<form:errors path="alternativa" cssClass="alert alert-danger" element="div"/>
		</div>
	</div>			
	

	<button type="submit" class="btn btn-default">Adicionar</button>
</form:form>		
<c:import url="footer.jsp"></c:import>

