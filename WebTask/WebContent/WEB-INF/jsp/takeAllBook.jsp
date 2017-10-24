<%@ page language="java" contentType="text/html; charset=utf-8"
	import="by.htp.library.domain.Book" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>


	<h1>
		<c:if test="${not empty  requestScope.Message }">
			<c:out value="${  requestScope.Message }" />
			<br />
		</c:if>

<fmt:setLocale value="en" />
<c:if test="${ not empty  requestScope.LOCALE}">
			<c:set var="language"
    value="${requestScope.LOCALE}"
    scope="session" />
    <fmt:setLocale value="${language}" />
		</c:if>


<fmt:setBundle basename="resources.pagecontent" scope="session" />


		
		
	</h1>
	<form action="Controller" method="get">
		<input type="hidden" name="command" value="VIEWALLBOOKS" /> <input
			type="hidden" name="loc" value="ru" /> <input
			type="submit" value="russian" />
	</form>
	<br />

	<form action="Controller" method="get">
		<input type="hidden" name="command" value="VIEWALLBOOKS" /> 
		<input type="hidden" name="loc" value="en" />
		<input type="submit" value="en" />
	</form>
	
	<fmt:message key="label.availableBooks" />
	<c:forEach items="${requestScope.List}" var="List">
		<h2>
			<c:out value=" ${List.nazvanie}" />
			<c:out value=" ${List.avtor}" />
		</h2>
		<form action="Controller" method="get">
			<input type="hidden" name="command" value="viewBook" /> <input
				type="hidden" name="id" value=" ${List.id}" /> <input type="submit"
				value="View book" />
		</form>
		<br />
		<form action="Controller" method="get">
			<input type="hidden" name="command" value="ReadBook" /> <input
				type="submit" value="Read book" />
		</form>
		<br />
	</c:forEach>
	
	<br />
</body>
</html>