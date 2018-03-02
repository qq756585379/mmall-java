<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>

<body>
<h2>Hello World!</h2>

springmvc上传文件
<form name="form1" action="http://localhost:8081/mmall/manage/test/upload.do" method="post"
      enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" value="springmvc上传文件"/>
</form>

富文本图片上传文件
<form name="form2" action="http://localhost:8081/mmall/manage/test/richtext_img_upload.do" method="post"
      enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" value="富文本图片上传文件"/>
</form>

</body>
</html>
