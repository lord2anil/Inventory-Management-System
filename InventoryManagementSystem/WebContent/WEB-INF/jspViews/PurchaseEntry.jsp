<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
function displayPageElements() {
    if (document.getElementById("category").value === "") {
        document.getElementById("belowTables").style.display = "none";
    } else {
        document.getElementById("belowTables").style.display = "block";
    }
}

function submitForm() {
    document.getElementById("purchaseForm").submit();
}
</script>
</head>
<body>
<div class="container">
<jsp:include page="include.jsp" />
<h2 align="center">Material Purchase Entry</h2>

<center>
<f:form method="post" id="purchaseForm" action="${pageContext.request.contextPath}/getUnitAndTypeList.html" modelAttribute="purchaseBean">
   
   
    <table>
             
              <tr>
            <td><label for="vendor_name">Vendor Name:</label></td>
            <td>
                <f:select path="vendor_name" id="vendor_name">
                    <f:options items="${vendorList}"  />
                </f:select>
            </td>
        </tr>
             
        
        <tr>
            <td><label for="material_category_id">Material Category:</label></td>
            <td>
                <f:select path="material_category_id" id="material_category_id" onchange="submitForm()" >
                    <f:options items="${categoryList}"  />
                </f:select>
            </td>
        </tr>
        
        <%-- <tr id="belowTables" style="display: none;">
            <td><label for="material_type_id">Material Type:</label></td>
            <td>
                <f:select path="material_type_id" id="material_type_id">
                    <f:options items="${typeList}" itemValue="id" itemLabel="name" />
                </f:select>
            </td>
        </tr>
        
        <tr   id="belowTables" style="display: none;" >
            <td><label for="unit_id">Unit ID:</label></td>
            <td>
                <f:select path="unit_id" id="unit_id">
                    <f:options items="${unitList}" itemValue="id" itemLabel="name" />
                </f:select>
            </td>
        </tr>
        
         <tr  id="belowTables" style="display: none;" >
            <td><label for="brandname">Brand Name:</label></td>
            <td><f:input path="brandname" id="brandname" type="text" /></td>
        </tr>
        
        <tr   id="belowTables" style="display: none;" >
            <td><label for="quantity">Quantity:</label></td>
            <td><f:input path="quantity" id="quantity" type="text" /></td>
        </tr>
        
        <tr   id="belowTables" style="display: none;" >
            <td><label for="purchase_amount">Purchase Amount:</label></td>
            <td><f:input path="purchase_amount" id="purchase_amount" type="text" /></td>
        </tr>
        <tr   id="belowTables" style="display: none;" >
            <td><label for="purchase_date">Purchase Date:</label></td>
            <td><f:input path="purchase_date" id="purchase_date" type="text" /></td>
        </tr>
        
        <tr id="belowTables" style="display: none;" >
            <td><label for="status">Status:</label></td>
            <td><f:input path="status" id="status" type="text" /></td>
        </tr>
                
        --%>
       
       <%--  <tr  id="belowTables" style="display: none;"  >
            <td colspan="2" align="center">
                <f:submit value="Submit" />
                <f:reset value="Reset" />
            </td>
        </tr> --%>
    </table>
    <!-- <input type="submit" value="Submit"/> -->
</f:form>
</center>

</div>
<div class="terms2">
    <p align="center" style="font-family: calibri;color: #6666CC;">Copyright © 2018 Accenture All Rights Reserved.</p>
</div>
</body>
</html>
