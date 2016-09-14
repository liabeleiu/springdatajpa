<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

	<form:form method="POST" action="taskSearch" modelAttribute="tasksearch">
		<form:input path="name" />
		<form:select path="status"> 
		<form:option value="${null}">ANY STATUS</form:option>
			<form:options items="${statuslist}" />
		</form:select>

		<input type="submit" value="Search" />
	</form:form>
	
	<c:if test="${not empty taskform.tasks}">

		<form:form method="POST" action="updateTasksStatus"
			modelAttribute="taskform">
			<table>
				<c:forEach var="task" varStatus="status" items="${taskform.tasks}">
					<tr>
						<form:input path="tasks[${status.index}].id" type="hidden" />
						<td><a href="<c:url value="/taskdetails/${task.id}"/>">${task.id}</a></td>
						<td>${task.name}</td>
						<td><form:select path="tasks[${status.index}].status"
								onchange="this.form.submit()">
								<form:options items="${statuslist}" />
							</form:select></td>
					</tr>
				</c:forEach>
			</table>
		</form:form>

	</c:if>
</body>
</html>