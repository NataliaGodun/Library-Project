<%@ page language="java" contentType="text/html; charset=utf-8"
	import="by.htp.library.domain.Book" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ELibrary</title>
<link rel="stylesheet" type="text/css" 
	href="<c:url value="/resources/css/style2.css" />"/>
</head>
<body>

<div id="main">
	
	<div id="wrapper">
		<div id="header">
			<img src="${pageContext.request. contextPath}/resources/images/books.jpg" width="100%" />
			<h1>
				<fmt:message key="label.electronicLibrary" />
			</h1>
		</div>	
	</div>

	<div id="book">
				<div id="image">
				<img src="${pageContext.request. contextPath}/Controller?index=${book.image}" width="70%"/>
				</div>
				
				<div id="information">
				
				<strong>Name:</strong>
					<c:out value=" ${requestScope.book.nameBook}" />
					<br />
					<strong>Writer:</strong>
					<c:out value=" ${requestScope.book.writer}" />
					<br />
					<strong>Genre:</strong>
					<c:out value="${ requestScope.book.genre}"/>
					<br />
					<strong>Published House:</strong>
					<c:out value="${requestScope.book.house}"/>
					<br />
					<strong>Year:</strong>
					<c:out value="${ requestScope.book.year}"/>
					<br />
					</div>
	<c:if test="${not empty  requestScope.Message }">
			<c:out value="${  requestScope.Message }" />
		</c:if>
		<c:if test="${not empty  requestScope.errorMessage}">
			<c:out value="${  requestScope.errorMessage }" />
		</c:if>
	
		
		<form action="Controller" method="get">
						<input type="hidden" name="command" value="ReadBook" />
						 <input type="submit" value="Read book" />
					</form>
			<c:if test="${sessionScope.role=='admin' }">
				
	<a href="${pageContext.request.contextPath}/Controller?command=DeleteBook&id=${requestScope.book.id}" 
	onclick="if (!(confirm('Are you sure you want to delete this book?'))) return false">DELETE</a>
	
	</c:if>
	</div>
<br />
<br />

	
<div id="footer"> All right reserved</div>
</div>	
		
</body>
</html>
