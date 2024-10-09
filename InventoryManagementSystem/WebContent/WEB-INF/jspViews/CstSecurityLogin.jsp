<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>

</head>
<body >

<center>
	<h1>Employee Management Secure and Validate Login Form [Custom]</h1>

						<c:if test="${param.error != null}">
									Invalid username and password.
							</c:if>
							<c:if test="${param.logout != null}">
									You have been logged out successfully.
							</c:if>

		<h2>Login with Username and Password</h2>

		<form name='loginForm' action="<c:url value='/cstValidateSecureLogin'/>" method='POST'>

		  <table border="2">
			<tr>
				<td>User:</td>
				<td><input type='text' name='msd_uname123' ></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='msd_pwd123' /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" /></td>
			</tr>
		  </table>

		 <%--  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> --%>

		</form>
		<a href="${pageContext.request.contextPath}/index.jsp">Home</a>
</center>
</body>
</html>