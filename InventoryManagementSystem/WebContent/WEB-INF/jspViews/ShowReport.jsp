<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/datetimepicker.js"></script>
<title>Purchase Entry</title>
<script type="text/javascript">


	
</script>
<style>
th, td {
	  padding: 15px;
	}

</style>
</head>
<body>
	<div class="container">
		<jsp:include page="include.jsp" />
		<h2 align="center">Material Purchase Entry</h2>

		<center>
		
		<table >
			<tr>
	    <td >
		Vendor Name: ${vendorName}
		</td>
		<td>
		From-Date: ${startDateStr}
		</td>
		<td>
		End-Date: ${endDateStr}
		</td>
		
		</tr>
		</table>
		
		<table >
			<tr>
	    <td >
		Address: ${vendorObj.vendorAddress}
		</td>
		<td>
		Contact-Number: ${vendorObj.contactNumber}
		</td>
		<td>
		Contact-Person: ${vendorObj.contactPerson}
		</td>
		
		</tr>
		</table>
		
			<table border="2" >


<tr>

<td>Material Category</td>
<td>Material Type</td>
<td>Brand</td>
<td>Quantity</td>
<td>Unit</td>
<td>Price</td>
<td>Purchase Date</td>

</tr>
                
	

				<c:forEach var="bean" items="${resObj}">
					<tr>
						<td>${bean.material_category_id}</td>
						<td>${bean.material_type_id}</td>
						<td>${bean.brandname}</td>
						<td>${bean.quantity}</td>
						<td>${bean.unit_id}</td>
						<td>${bean.purchase_amount}</td>
						<td>${bean.purchase_date}</td>

					</tr>

				</c:forEach>

			</table>


		</center>

	</div>
	<div class="terms2">
		<p align="center" style="font-family: calibri; color: #6666CC;">Copyright
			© 2018 Accenture All Rights Reserved.</p>
	</div>
</body>
</html>
