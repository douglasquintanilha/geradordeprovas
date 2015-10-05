<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<html>
<head>
<meta charset="utf-8" />
<title>Caelum Provas</title>
<link rel="stylesheet" href="<c:url value='../static/css/bootstrap.min.css' />">
<link rel="stylesheet" href="<c:url value='../static/css/jquery-ui.min.css' /> ">
<link rel="stylesheet" href="<c:url value='../static/css/main.css' /> ">

</head>
<body>
	<c:import url="headerAdmin.jsp"></c:import>
	<div class="container">

		<c:url var="urlPost" value='salva-liberacao' />
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
	<c:import url="footerAdmin.jsp"></c:import>

</body>
</html>
