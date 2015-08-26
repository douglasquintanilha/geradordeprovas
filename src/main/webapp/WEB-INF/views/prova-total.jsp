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
	<%-- <c:url var="urlPost" value='salva-questao'/> --%>
	<form:form action="${correcao-prova}" method="POST"
		commandName="questao">
		<c:forEach items="${listaQuestoes}" var="questao" varStatus="i">
				${questao.id} ${questao.titulo} <br />
				<%-- agr vai acessar a lista de alternativas, index/index. Multiplica por 5 e depois vai somando, pra pegar a resposta correspondente a questao--%>
				<input type="radio" name="alternativaQuestao+${i.index}">
				${listaAlternativas[i.index*5].descricao}<br />
				<input type="radio" name="alternativaQuestao+${i.index}">
				${listaAlternativas[i.index*5+1].descricao}<br />
				<input type="radio" name="alternativaQuestao+${i.index}">
				${listaAlternativas[i.index*5+2].descricao}<br />
				<input type="radio" name="alternativaQuestao+${i.index}">
				${listaAlternativas[i.index*5+3].descricao}<br />
				<input type="radio" name="alternativaQuestao+${i.index}">
				${listaAlternativas[i.index*5+4].descricao}<br />
		<br />
		</c:forEach>
		<button type="submit" class="btn btn-default">Adicionar</button>
	</form:form>
</body>
</html>