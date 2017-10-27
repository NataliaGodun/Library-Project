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
	<fmt:setLocale value="en" />
			<c:if test="${ not empty  requestScope.locale}">
				<c:set var="language" value="${requestScope.locale}" scope="session" />
				<fmt:setLocale value="${language}" />
			</c:if>
	<fmt:setBundle basename="resources.pagecontent" scope="session" />
	
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
				
	<div id="language">
		<div id="languageRu">
			<form action="Controller" method="get">
				<input type="hidden" name="command" value="VIEWALLBOOKS" /> 
				<input type="hidden" name="loc" value="ru" /> 
				<input type="submit" value="Russian" />
			</form>
		</div>
		<div id="languageRu">
			<form action="Controller" method="get">
				<input type="hidden" name="command" value="VIEWALLBOOKS" />
				 <input type="hidden" name="loc" value="en" /> 
			 	<input type="submit" value="English" />
			</form>
		</div>
	</div>
		
	<c:if test="${not empty  requestScope.Message }">
		<c:out value="${  requestScope.Message }" />
			<br />
	</c:if>
	<br />
	
	<div id="inline">
		<div id="content">
			<h3>
				<fmt:message key="label.availableBooks" />
			</h3>
			<br />
	
			<c:forEach items="${requestScope.List}" var="List">
				<h2>
				<c:out value=" ${List.nazvanie}" />
				<c:out value=" ${List.avtor}" />
				
				<c:if test="${not empty  List.image}">
				 <img src="${pageContext.request. contextPath}/resources/images/${  List.image }"/>
				</c:if>
				</h2>
			
				<form action="Controller" method="get">
					<input type="hidden" name="command" value="viewBook" /> 
					<input type="hidden" name="id" value=" ${List.id}" /> 
					<input type="submit" value="View book" />
				</form>
		
				<form action="Controller" method="get">
					<input type="hidden" name="command" value="ReadBook" />
					 <input type="submit" value="Read book" />
				</form>
			</c:forEach>

			<br />
	</div>

	<div id="enterReg">
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="authorization" />
			<fmt:message key="label.login" />
			<br /> <input type="text" name="login" value="" /><br />
			<fmt:message key="label.password" />
			<br /> <input type="password" name="password" value="" /><br /> 
			<input type="submit" value="sign in" /> <br />
			
			<c:if test="${not empty  requestScope.errorMessage }">
				<c:out value="${  requestScope.errorMessage }" />
			</c:if>
		</form>
				
		<br />
		<form action="Controller" method="get">
			<input type="hidden" name="command" value="SHOWREGISTRATIONFORM" /> 
			<input type="hidden" name="loc" value="${requestScope.locale}">
			<input type="submit" value="Registration" />
		</form>
	</div>
	</div>
	<div id="w"> lzzz</div>
	<div id="ww"> pppp</div>
	</div>
</body>
</html>
