<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Customer</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<br />
	<br />
	<div align="center">
		<h2>
			<c:if test="${customer != null}">
				Edit Customer
			</c:if>
			<c:if test="${customer == null}">
				Create New Customer
			</c:if>
		</h2>
	</div>

	<div align="center">
		<c:if test="${customer != null}">
			<form action="update_customer" method="post" onsubmit="return validateFormInput()">
				<input type="hidden" name="customerId" value="${customer.customerId}">
		</c:if>
		<c:if test="${customer == null}">
			<form action="create_customer" method="post" onsubmit="return validateFormInput()">
		</c:if>
		<form>
			<table>
				<tr>
					<td align="right">E-mail:</td>
					<td><input type="text" id="email" name="email" size="45" value="${customer.email}" /></td>
				</tr>
 				<tr>
					<td align="right">Full name:</td>
					<td><input type="text" id="fullname" name="fullname" size="45" value="${customer.fullname}" /></td>
				</tr>
				<tr>
					<td align="right">Password:</td>
					<td><input type="text" id="password" name="password" size="45" value="${customer.password}" />
					</td>
				</tr>
				<tr>
					<td align="right">Confirm Password:</td>
					<td><input type="text" id="confirmPassword" name="confirmPassword" size="45" value="${customer.password}" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Save"> 
						<input type="button" value="Cancel" onclick="javascript:history.go(-1);">
					</td>
				</tr>
			</table>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	function validateFormInput() {
		var email = document.getElementById("email");
		var fullname = document.getElementById("fullname");
		var password = document.getElementById("password");
		var confirmPassword = document.getElementById("confirmPassword");
		
		if (email.value.length == 0) {
			alert("Email is required!");
			email.focus();
			return false;
		}

		if (fullname.value.length == 0) {
			alert("Full Name is required!");
			fullname.focus();
			return false;
		}

		if (password.value.length == 0) {
			alert("Password is required!");
			password.focus();
			return false;
		}
		
		if (confirmPassword.value.length == 0) {
			alert("Confirm your Password is required!");
			confirmPassword.focus();
			return false;
		}
	}
</script>
</html>