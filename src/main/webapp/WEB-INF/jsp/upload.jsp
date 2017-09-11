<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
</head>
<body>
    <form id="file_form" action="${pageContext.request.contextPath }/festival/importFestival" enctype="multipart/form-data"
        method="post">
        <tr><td>年份:<input type="text" name="year" /> </td></tr>
        <input type="file" name="file" id="file_input" /> 
        <input type="submit" value="文件上传" id='upFile-btn'>
    </form>
</body>
</html>