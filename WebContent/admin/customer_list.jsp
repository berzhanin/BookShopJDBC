<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Customers - Thalia Bookstore Administration</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2>Customers Management</h2>
		<h3>
			<a href="customer_form.jsp">Create new Customer</a>
		</h3>
	</div>

	<c:if test="${message != null}">
		<div align="center">
			<h4>
				<i>${message}</i>
			</h4>
		</div>
	</c:if>

	<div align="center">
		<table border="1" cellpadding="10">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Email</th>
				<th>Full Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="customer" items="${listCustomer}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${customer.customerId}</td>
					<td>${customer.email}</td>
					<td>${customer.fullname}</td>
					<td><a href="edit_customer?id=${customer.customerId}">Edit</a> &nbsp; 
					    <a href="javascript:confirmDelete(${customer.customerId})">Delete</a></td>
					</tr>
			</c:forEach>
		</table>
	</div>
	<jsp:directive.include file="footer.jsp" />
	
	<script>
		function confirmDelete(customerId){
			if(confirm('Are you sure you want to delete the customer with ID ' + customerId+ ' ?')){
				window.location = 'delete_customer?id=' + customerId;
			}
		}
	</script>
</body>
</html>