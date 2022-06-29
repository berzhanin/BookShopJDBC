<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results for ${keyword} - Thalia Books - Online Shop</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<div align="center">
		<c:if test="${fn:length(result) == 0}">
			<h2>No Results for "${keyword}"</h2>
		</c:if>
		<c:if test="${fn:length(result) > 0}">
			<div align="center" style="width: 80%; margin: 0 auto;">
				<h2>Results for "${keyword}":</h2>
				<c:forEach items="${listNewBooks}" var="book">
					<div style="display: inline-block; margin: 20px">
						<div>
							<a href="view_book?id=${book.bookId}"> <img
								scr="data:image/jsp;base64,${book.base64Imaghe}" width="128"
								height="164" />
							</a>
						</div>
						<div>
							<a href="view_book?id=${book.bookId}"> <b>${book.title}</b>
							</a>
						</div>
						<div>Rating *****</div>
						<div>
							<i>by ${book.author}</i>
						</div>
						<div>
							<b>$${book.price}</b>
						</div>
					</div>
				</c:forEach>
				<h2>Best-selling:</h2>
				<h2>Most-favored Books:</h2>
				<br /> <br />
			</div>
		</c:if>
	</div>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>