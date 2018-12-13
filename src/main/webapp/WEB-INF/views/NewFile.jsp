<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Claim Details</title>
</head>
<body>

	<h1 style="color: #0066CC">Update Operation</h1>
	<br />
	<br />


	<table bgcolor="#DCDCDC">

		<tr>
			<td>Please Enter the claim code:<span style="color: red;">*</span></td>
			<td><form:input path="bean" /></td>


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
			<c:url var="myAction" value="updateClaimExpense">
				<form:form action="${myAction}" method="post" modelAttribute="bean">
					<table>



						<tr>
							<th>Employee ID:</th>
							<td><form:input path="employeeId" value="${bean.employeeId}" /></td>
							<td style="color: red;"><form:errors path="employeeId"></form:errors></td>
						</tr>
						<tr>
							<th>Project Code:</th>
							<td><form:input path="projectCode" /></td>
							<td style="color: red;"><form:errors path="projectCode"></form:errors></td>
						</tr>
						<tr>
							<th>Expense Code:</th>
							<td><form:input path="expenseCode" /></td>
							<td style="color: red;"><form:errors path="expenseCode"></form:errors></td>
						</tr>
						<tr>
							<th>Start Date:</th>
							<td><form:input path="startDate" type="date" /></td>
							<td style="color: red;"><form:errors path="startDate"></form:errors></td>
						</tr>
						<tr>
							<th>End Date:</th>
							<td><form:input path="endDate" type="date" /></td>
							<td style="color: red;"><form:errors path="endDate"></form:errors></td>
						</tr>
						<tr>
							<th>Expense Amount:</th>
							<td><form:input path="expenseAmount" /></td>
							<td style="color: red;"><form:errors path="expenseAmount"></form:errors></td>
						</tr>
						<tr>
							<td><input type="submit" value="modify Expense"></input></td>
					</table>
					<a href="home">Click here to go back</a>
				</form:form>
			</c:url>
			
			
</body>
</html>
>
