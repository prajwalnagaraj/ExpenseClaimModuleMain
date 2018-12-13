<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Read Claim</title>
</head>
<body>
	<table bgcolor="#DCDCDC" border="1">
	<tr>
			<th>Claim Code:</th>
			<td>${bean.claimCode}</td>
		</tr>
		<tr>
			<th>Employee Code:</th>
			<td>${bean.employeeId}</td>
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
	</table>
	<br />
	<br />
	<table bgcolor="#DCDCDC" border=1>
		<form:form action="updateClaimExpense" method="POST" modelAttribute="bean">
			<tr>
				<th>Claim Code:</th>
				<td><form:input path="claimCode" /></td>
				<td style="color: red;"></td>
			</tr>
			
			<tr>
				<th>Employee code:</th>
				<td><form:input path="employeeId" /></td>
				<td style="color: red;"></td>
			</tr>
			<tr>
				<th>Employee Name:</th>
				<td><form:input path="employeeName" /></td>
				<td style="color: red;"></td>
			</tr>
		<tr>
				<th>Project Code:</th>
				<td><form:input path="projectCode" /></td>
				<td style="color: red;"></td>
			</tr>
			<tr>
				<th>Expense Code:</th>
				<td><form:input path="expenseCode" /></td>
				<td style="color: red;"></td>
			</tr>
			<tr>
				<th>Start Date:</th>
				<td><form:input path="startDate" type="date" /></td>
				
			<tr>
				<th>End Date:</th>
				<td><form:input path="endDate" type="date" /></td>
				
			<tr>
				<th>Expense Amount:</th>
				<td><form:input path="expenseAmount" /></td>
				
			<tr>
			<tr>
				<td><input type="submit" value="Claim Expense"></input></td>
			</tr>
			<br />
			<a href="home">Click here to go back</a>
			<br />
		</form:form>
	</table>
</body>
</html>