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
		Select file to upload: <input type="file" name="uploadFile" />
            <br/><br/>
            <input type="submit" value="Upload" />
	</form>
    </body>
    </html>