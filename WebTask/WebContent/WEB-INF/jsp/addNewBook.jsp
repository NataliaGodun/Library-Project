<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <title>File Upload</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <form action="Controller" method="post" >
        <input type="hidden" name="command" value="addNewBook"/>
        writer: <input type="text" name="writer" value="" /> 
        <br/>
		 name Book: <input type="text" name="nameBook" value="" /> 
        <br/>
         genre: <input type="text" name="genre" value="" /> 
        <br/>
         Publishing house: <input type="text" name="house" value="" /> 
        <br/>
         Publishing year: <input type="text" name="year" value="" /> 
        <br/>
            <input type="submit" value="Add new book" />
	</form>
    </body>
    </html>