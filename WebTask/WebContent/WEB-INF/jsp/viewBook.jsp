<%@ page language="java" contentType="text/html; charset=utf-8"
	import="by.htp.library.domain.Book" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ELibrary</title>
<link rel="stylesheet" type="text/css" 
	href="<c:url value="/resources/css/style2.css" />"/>
</head>
<body>
	
	<div id="book">
				<div id="image">
					<c:if test="${not empty  requestScope.book}">
						 <img src="${pageContext.request. contextPath}/resources/images/${  requestScope.book.image }" width="70%"/>
					</c:if>
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
	<c:if test="${not empty  requestScope.Message }">
			<c:out value="${  requestScope.Message }" />
		</c:if>
		<c:if test="${not empty  requestScope.errorMessage}">
			<c:out value="${  requestScope.errorMessage }" />
		</c:if>
	</div>
	</div>

	<form action="Controller" method="get">
						<input type="hidden" name="command" value="AddImageBook" /> 
						<input type="hidden" name="id" value="${ requestScope.book.id}"/> 
						<input type="submit" value="Add image book" />
					</form>
	
		
</body>
</html>
