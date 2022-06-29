<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Login</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2>Customer Login</h2>
		
		<c:if test="${message != null}">
			<div align="center">
				<h4 class="message">${message}</h4>
			</div>
		</c:if>
		
		<form id="loginForm" action="login" method="post" onsubmit="return validateFormInput()">
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" id="email" size="20"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="text" name="password" id="password" size="20"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="submit">Login</button>
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
		var password = document.getElementById("password");
		
		if (email.value.length == 0) {
			alert("Email is required!");
			email.focus();
			return false;
		}

		if (password.value.length == 0) {
			alert("Password is required!");
			password.focus();
			return false;
		}
		
	}
</script>
</html>