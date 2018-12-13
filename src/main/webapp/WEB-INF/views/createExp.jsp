<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Expense Details</title>
</head>
<body>
	<center>
		<h1 style="color: #0066CC">Add Expense Details</h1>
		<br />
		<br />
		<h2>Please Enter Details</h2>
		<form:form action="createExpen" method="post"
			modelAttribute="expense">
			<table>
				<tr bgcolor="#DCDCDC">
					<td>Expense Code:<span style="color: red;">*</span></td>
					<td><form:input path="expenseCode" /></td>
					<td style="color: red;"><form:errors path="expenseCode"></form:errors></td>
				</tr>
				<tr bgcolor="#DCDCDC">
					<td>Expense Type:<span style="color: red;">*</span></td>
					<td><form:input path="expenseType" /></td>
					<td style="color: red;"><form:errors path="expenseType"></form:errors></td>
				</tr>
				<tr bgcolor="#DCDCDC">
					<td>Expense Description:<span style="color: red;">*</span></td>
					<td><form:input path="expenseDescription" /></td>
					<td style="color: red;"><form:errors path="expenseDescription"></form:errors></td>
				</tr>
				<tr align="center">
					<td colspan="2"><input type="submit" name="submit"
						value="Add Expense"></input></td>
				</tr>
			</table>
			<a href="home">Click here to go back</a><br/>
		</form:form>
	</center>
</body>
</html>