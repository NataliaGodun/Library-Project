<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <title>File Upload</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style2.css" />"/>
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
    
    
        <form action="Controller" method="post" enctype="multipart/form-data">
        
		
            writer:
            <input type="text" value="" name="writer"/> <br/>
            name book:
            <input type="text" value="" name="nameBook"/> <br/>
           genre:
            <input type="text" value="" name="genre"/> <br/>
           house:
            <input type="text" value="" name="house"/> <br/>
            year :
            <input type="text" value="" name="year"/>
            
            <br/>
            File:
            <input type="file" name="file" id="file" /> <br/>
            <input type="submit" value="Upload" name="upload" id="upload" />
	</form>
	<div id="footer"> All right reserved</div>
</div>	
    </body>
    </html>