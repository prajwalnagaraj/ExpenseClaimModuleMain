<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>read by id</title>
</head>
<body>
<center>
		<h1 style="color: #0066CC">Read Operation</h1>
</center>
<center></center><br>
<center>
<table bgcolor="#DCDCDC" border=1>
<tr><th>Expense Code</th><td>${expense.expenseCode}</td></tr>
<tr><th>Expense Type</th><td>${expense.expenseType}</td></tr>
<tr><th>Expense Description</th><td>${expense.expenseDescription}</td></tr>
<a href="home">Click here to go back</a><br/>
</table>
</center>
</body>
</html>