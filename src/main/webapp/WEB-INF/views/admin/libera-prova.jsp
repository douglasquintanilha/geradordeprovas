<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://ur6lad.co.ua/markdown-taglib' prefix ='md' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Caelum Provas</title>
<link rel="stylesheet" href="<c:url value='/static/css/bootstrap.min.css' />">
<link rel="stylesheet" href="<c:url value='/static/css/jquery-ui.min.css' />">
<link rel="stylesheet" href="<c:url value='/static/css/main.css' />">

</head>
<body>
	<c:import url="../header.jsp"></c:import>
	<div class="container">

		<c:url var="urlPost" value='salvaLiberacao' />
		<c:if test="${validacao == false}">
			Insira Pelo menos 1 prova e 1 usuario!
		</c:if>
		<form:form method="POST" action="${urlPost}" commandName="provas">
			<h4>
				Selecione a prova que será liberada:
				</h3>
				<ul class='list-unstyled'>
					<c:forEach items="${provas}" var="prova">
						<div class="checkbox">
							<label> <input type="checkbox" name="provas" value="${prova.id}"> ${prova.id} / ${prova.nome}
							</label>
						</div>
					</c:forEach>
					<li role="separator" class="divider"></li>
				</ul>
				<h4>
					Selecione os usuários que podem ter acesso a esta prova:
					</h3>
					<ul class='list-unstyled'>
						<c:forEach items="${usuarios}" var="usuario">
							<li>
								<div class="checkbox">
									<label> <input type="checkbox" name="usuarios" value="${usuario.login}"> ${usuario.login}
									</label>
								</div>
							</li>
						</c:forEach>
					</ul>
					<button type="submit" class="btn btn-default">Liberar Provas</button>
		</form:form>
	</div>
	<c:import url="../footer.jsp"></c:import>

</body>
</html>
