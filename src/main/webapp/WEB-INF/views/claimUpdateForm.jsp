<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Claim Update Form</title>
</head>
<body>
<center>
<h1 style="color: #0066CC">Modify Claim Details</h1>
<table>
<form:form action="claimUpdate" method="POST" modelAttribute="claim">
<tr>
<th>Claim ID:<span style="color: red;">*</span></th><td><form:input path="claimCode"/></td></tr>
<tr><td><input type="submit" value="Claim Expense"></input></td></tr>
<a href="home">Click here to go back</a><br/>
</form:form>
</table>
</center>
</body>
</html>