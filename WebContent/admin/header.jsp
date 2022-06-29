<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
	<img src="../images/logo26.png" />
</div>
<div align="center">
	<div>
		Welcome, <c:out value="${sessionScope.useremail}" /> | <a href="logout">Logout</a> <br /> <br />
	</div>
	<div>
		<a href="list_users">Users</a> | 
		<a href="list_category">Categories</a> | 
		<a href="list_books">Books</a> | 
		<a href="list_customer">Customers</a> | 
		<a href="reviews">Reviews</a> | 
		<a href="orders">Orders</a>
	</div>
</div>