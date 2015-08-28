<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mostra todas as Questões</title>
</head>
<body>
	<c:import url="header.jsp"></c:import>
	<div class="container">
		<c:url var="urlPost" value='correcao-prova' />
		<form:form action="correcao-prova" method="GET" commandName="resposta">
			<c:forEach items="${listaQuestoes}" var="questao" varStatus="i">
					${questao.id} ${questao.titulo} <br />
				<%-- agr vai acessar a lista de alternativas, index/index. Multiplica por 5 e depois vai somando, pra pegar a resposta correspondente a questao--%>
				<input type="radio" name="alternativas[${i.index}]"
					value="${listaAlternativas[i.index*5].id}">
				${listaAlternativas[i.index*5].descricao}<br />
				<input type="radio" name="alternativas[${i.index}]"
					value="${listaAlternativas[i.index*5+1].id}">
					${listaAlternativas[i.index*5+1].descricao}<br />
				<input type="radio" name="alternativas[${i.index}]"
					value="${listaAlternativas[i.index*5+2].id}">
					${listaAlternativas[i.index*5+2].descricao}<br />
				<input type="radio" name="alternativas[${i.index}]"
					value="${listaAlternativas[i.index*5+3].id}">
					${listaAlternativas[i.index*5+3].descricao}<br />
				<input type="radio" name="alternativas[${i.index}]"
					value="${listaAlternativas[i.index*5+4].id}">
					${listaAlternativas[i.index*5+4].descricao}<br />
				<br />
			</c:forEach>
			<button type="submit" class="btn btn-default">Enviar</button>
		</form:form>
	</div>
	<c:import url="footer.jsp"></c:import>
</body>
</html>