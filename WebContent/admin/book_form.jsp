<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Book</title>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	<br />
	<br />
	<div align="center">
		<h2>
			<c:if test="${book != null}">
				Edit Book
			</c:if>
			<c:if test="${book == null}">
				Create New Book
			</c:if>
		</h2>
	</div>

	<div align="center">
		<c:if test="${book != null}">
			<form action="update_book" method="post" id="bookForm" 
				enctype="multipart/form-data" onsubmit="return validateFormInput()">
				<input type="hidden" name="bookId" value="${book.bookId}">
		</c:if>
		<c:if test="${book == null}">
			<form action="create_book" method="post" id="bookForm"
				enctype="multipart/form-data" onsubmit="return validateFormInput()">
		</c:if>
		<form>
			<table>
				<tr>
					<td>Category:</td>
					<td><select name="category">
						<c:forEach items="${listCategory}" var="category">
							<option value="${category.categoryId}">
								${category.name}
							</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right">Title:</td>
					<td><input type="text" id="title" name="title" size="20"
						value="${book.title}" /></td>
				</tr>
 				<tr>
					<td align="right">Author:</td>
					<td><input type="text" id="author" name="author" size="20"
						value="${book.author}" /></td>
				</tr>
				<tr>
					<td align="right">ISBN:</td>
					<td><input type="text" id="isbn" name="isbn" size="20"
						value="${book.isbn}" />
					</td>
				</tr>
				<tr>
					<td align="right">Publish Date:</td>
					<td><input type="text" id="publishDate" name="publishDate" size="20" 
					value='<fmt:formatDate pattern='MM/dd/yyyy' value="${book.publishDate}" />' /></td>
				</tr>
				<tr>
					<td align="right">Book Image:</td>
					<td><input type="file" id="bookImage" name="bookImage" size="20" /><br /> 
					<img id="thumbnail" alt="Image Preview" style="width: 20%; margin-top: 10px"
						src="data:image/jpg;base64,${book.base64Image}" /></td>
				</tr>
				<tr>
					<td align="right">Price:</td>
					<td><input type="text" id="price" name="price" size="20"
						value="${book.price}" /></td>
				</tr>
				<tr>
					<td align="right">Description:</td>
					<td align="left"><textarea rows="5" cols="50"
							name="description" id="description">${book.description}</textarea>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save"> <input type="button" value="Cancel"
						onclick="javascript:history.go(-1);"></td>
				</tr>
			</table>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
<script type="text/javascript">
	function validateFormInput() {
		var title = document.getElementById("title");
		var author = document.getElementById("author");
		var isbn = document.getElementById("isbn");
		var publishDate = document.getElementById("publishDate");
		var bookImage = document.getElementById("bookImage");
		var price = document.getElementById("price");
		var description = document.getElementById("description");
		
		if (title.value.length == 0) {
			alert("Title is required!");
			title.focus();
			return false;
		}

		if (author.value.length == 0) {
			alert("Author is required!");
			author.focus();
			return false;
		}

		if (isbn.value.length == 0) {
			alert("ISBN is required!");
			isbn.focus();
			return false;
		}
		
		if (publishDate.value.length == 0) {
			alert("publishDate is required!");
			publishDate.focus();
			return false;
		}
		
		if (bookImage.value.length == 0) {
			alert("bookImage is required!");
			bookImage.focus();
			return false;
		}
		
		if (price.value.length == 0) {
			alert("price is required!");
			price.focus();
			return false;
		}
		
		if (description.value.length == 0) {
			alert("description is required!");
			description.focus();
			return false;
		}
	}
</script>
</html>