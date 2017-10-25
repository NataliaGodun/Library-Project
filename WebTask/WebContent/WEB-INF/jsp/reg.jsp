<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<fmt:setLocale value="en" />
			<c:if test="${ not empty  requestScope.locale}">
				<c:set var="language" value="${requestScope.locale}" scope="session" />
				<fmt:setLocale value="${language}" />
			</c:if>
	<fmt:setBundle basename="resources.pagecontent" scope="session" />

	<fmt:message key="label.welcome" />
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="registration" /> 
		<fmt:message key="label.nickname" /><br />
		<input type="text" name="name" value="" /><br /> 
		<fmt:message key="label.login" />
		<br /> <input type="text" name="login" value="" /><br />
		<fmt:message key="label.password" />
		<br /> <input type="password" name="password" value="" /><br />
		<input type="submit" value="registration" />
	</form>
</body>
</html>