<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Read by Id</title>
</head>
<body>
<center>
<h1 style="color: #0066CC">Add Employee Details</h1>
		<br />
		<br />
<form:form action="readExpenById" method="POST" modelAttribute="expense">
Expense Code:<form:input path="expenseCode"/>
<input type="submit"></input>  
<a href="home">Click here to go back</a><br/>
</form:form>
</center>
</body>
</html>