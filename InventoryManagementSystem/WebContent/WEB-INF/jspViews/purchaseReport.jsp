<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/datetimepicker.js"></script>
<title>Purchase Entry</title>
<script type="text/javascript">



</script>
</head>
<body>
<div class="container">
<jsp:include page="include.jsp" />
<h2 align="center">Material Purchase Entry</h2>

<center>
<f:form method="post" id="purchaseForm" action="${pageContext.request.contextPath}/report.html" >
   
   
    <table>
             
              <tr>
            <td><label for="vendor_name">Vendor Name:</label></td>
            <td>
                       <select id="vendor_name" name="vendor_name">
                        <option value="">Select Vendor</option>
                        
                        <c:forEach var="entry" items="${vendorList}">
                            <option value="${entry}">${entry}</option>
                        </c:forEach>
                    </select>
            </td>
        </tr>
        
         <tr>
                <td><label for="StartDate">Start Date:</label></td>
                <td><input type="date" id="StartDate" name="StartDate" /></td>
            </tr>
            <tr>
                <td><label for="EndDate">End Date:</label></td>
                <td><input type="date" id="EndDate" name="EndDate" /></td>
            </tr>
             
        
       
    </table>
     <input type="submit" value="Submit"/>
</f:form>
</center>

</div>
<div class="terms2">
    <p align="center" style="font-family: calibri;color: #6666CC;">Copyright © 2018 Accenture All Rights Reserved.</p>
</div>
</body>
</html>
