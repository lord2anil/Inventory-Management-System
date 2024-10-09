<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>

</head>
<body >

<center>
<%session.invalidate(); %>
	<h3>Logged in User: ${pageContext.request.userPrincipal.name} is not authorized to access the page</h3>
	<h4><a href="${pageContext.request.contextPath}/index.jsp"> Try Again</a></h4>
	
		
</center>
</body>