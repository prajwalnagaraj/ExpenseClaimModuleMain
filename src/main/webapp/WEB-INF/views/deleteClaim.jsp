<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Claim Details</title>
</head>
<body>
	<center>
		<h1 style="color: #0066CC">Delete Claim Details</h1>
		<table bgcolor="#DCDCDC" border="1">
			<form:form action="deleteExpense" method="POST"
				modelAttribute="claim">
				<tr>
					<th>Claim ID:</th>
					<td><form:input path="claimCode" /></td>
				</tr>
				<br />
				<br />
				<tr>
					<th>Employee Code:</th>
					<td>${bean.employeeId}</td>
				</tr>
				<tr>
					<th>Employee Name:</th>
					<td>${bean.employeeName}</td>
				</tr>
				<tr>
					<th>Project Code:</th>
					<td>${bean.projectCode}</td>
				</tr>
				<tr>
					<th>Expense Code:</th>
					<td>${bean.expenseCode}</td>
				</tr>
				<tr>
					<th>Start Date:</th>
					<td>${bean.startDate}</td>
				</tr>
				<tr>
					<th>End Date:</th>
					<td>${bean.endDate}</td>
				</tr>
				<tr>
					<th>Expense Amount:</th>
					<td>${bean.expenseAmount}</td>
				</tr>
				<tr>
					<td><input type="submit" value="Delete"></input></td>
				</tr>
				<a href="home">Click here to go back</a>
				<br />
			</form:form>

		</table>
	</center>
</body>
</html>