<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Read Expense Details</title>
</head>
<body>
	<center>
		<h1 style="color: #0066CC">Read Operation</h1>
		<br />
		<br />
<table bgcolor="#DCDCDC" border=1>
<tr><th>Expense Code</th>
<th>Expense Type</th>
<th>Expense Description</th></tr>
<c:forEach var ="list" items="${expenseList}"> 
<tr>
<td>${list.expenseCode}</td>
<td>${list.expenseType}</td>
<td>${list.expenseDescription}</td></tr></c:forEach>
</table>
		<a href="home">Click here to go back</a><br/>
	</center>
</body>
</html>