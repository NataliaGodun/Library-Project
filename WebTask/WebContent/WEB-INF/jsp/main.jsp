<%@ page language="java" contentType="text/html; charset=utf-8"
	import="by.htp.library.domain.User" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Welcome</title>
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
	<br />
	<br />
	<h1>
		Hello,
		<c:out value="${ sessionScope.name} " />	
	</h1>
	<br />
	<c:if test="${not empty requestScope.message }">
		<br />
		<c:out value="${requestScope.message }"/>
	</c:if>
	<br />
		<form action="Controller" method="get">
		<input type="hidden" name="command" value="viewAllBooks" /> <br />
		<input type="submit" value="View all books" />
	</form>
	
	<c:if test="${not empty requestScope.errorMessage }">
		<br />
		<c:out value="${requestScope.errorMessage }"/>
	</c:if>
	
	<br />
		<form action="Controller" method="get">
		<input type="hidden" name="command" value="showEditProfileForm" /> <br />
		<input type="submit" value="Edit profile" />
	</form>
	<c:if test="${sessionScope.role=='admin' }">
		<br />
		<form action="Controller" method="get">
			<input type="hidden" name="command" value="showAddNewBookForm" /> <br />
			<input type="submit" value="Add book" />
		</form>
		<br />
		
	</c:if>
	<br />
		<form action="Controller" method="get">
		<input type="hidden" name="command" value="exit" /> <br />
		<input type="submit" value="Exit" />
	</form>
	
	<div id="footer"> All right reserved</div>
</div>		
	
</body>
</html>