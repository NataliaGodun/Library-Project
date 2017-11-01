<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <title>File Upload</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
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
    </body>
    </html>