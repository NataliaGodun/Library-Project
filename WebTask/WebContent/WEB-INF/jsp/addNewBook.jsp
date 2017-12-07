<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>File Upload</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style4simple.css" />" />
</head>
<body>

	<div id="main">

		<div id="wrapper">
			<div id="header">
				<img
					src="${pageContext.request. contextPath}/resources/images/books.jpg"
					width="100%" />
				<h1>
					<fmt:message key="label.electronicLibrary" />
				</h1>
			</div>
		</div>

		<br /> <br />
		<c:if test="${not empty requestScope.errorMessage }">
			<br />
			<c:out value="${requestScope.errorMessage }" />
		</c:if>
		<div id="content">
			<form action="ImageController" method="post"
				enctype="multipart/form-data">
				<fmt:message key="label.writer" />
				<input type="text" value="" name="writer" /> <br /> <br />
				<fmt:message key="label.name" />
				<input type="text" value="" name="nameBook" /> <br /> <br />
				<fmt:message key="label.genre" />
				<input type="text" value="" name="genre" /> <br /> <br />
				<fmt:message key="label.publishedHouse" />
				<input type="text" value="" name="house" /> <br /> <br />
				<fmt:message key="label.year" />
				<input type="text" value="" name="year" /> <br /> <br />
				<fmt:message key="label.file" />
				<input type="file" name="file" id="file" /> <br /> <input
					type="submit" value="<fmt:message key="label.upload" />"
					name="upload" id="upload" />
			</form>
		</div>
		<div id="footer">
			<br />
			<fmt:message key="label.allRightReserved" />
		</div>
	</div>
</body>
</html>