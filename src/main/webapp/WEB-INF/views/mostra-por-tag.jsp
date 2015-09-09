<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${nomeTag}</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/main.css">
</head>
<body>
<c:import url="header.jsp"></c:import>
<form action="mostra-por-tag" method="GET">
		<select name="tagSelecionada">
			<c:forEach items="${tags}" var="tag">
				<option value="${tag.nome}">${tag.nome}</option>
			</c:forEach>
		</select>
		<input type="submit" value="Procurar">
	</form>
<h4>${nomeTag}</h4>
<c:forEach items="${questoes}" var="questao">
	${questao.titulo}
</c:forEach>

</body>
</html>