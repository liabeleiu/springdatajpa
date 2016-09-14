<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}

#name_id {
	margin: 10px;
}

#description {
	margin: 10px;
}

#status {
	margin: 10px;
}

#history {
	margin: 10px;
}
</style>
</head>
<body>
	<!-- For login user -->
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			User : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>
	<div id="name_id">TASK: ${task.id}-${task.name}</div>
	<div id="description">DESCRIPTION: ${task.description}</div>
	<div id="status">STATUS: ${task.status}</div>
	<c:if test="${not empty task.statusChanges}">
		<div id="history">HISTORY:</div>
		<table>
			<c:forEach var="statuschange" items="${task.statusChanges}">
				<tr>
					<td>${statuschange.changeDate}</td>
					<td>${statuschange.changeInfo}</td>
					<c:choose>
						<c:when test="${statuschange.user != null}">
							<td>${statuschange.user.name}</td>
						</c:when>
						<c:otherwise>
							<td>UNKNOWN</td>
						</c:otherwise>
					</c:choose>
				
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>