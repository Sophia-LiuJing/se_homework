<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title></title>
    <style type="text/css">
        div {
            width: 200px;
            height: 50px;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: -25px;
            margin-left: -100px;
            color: white;
        }

        body {
            font-size: 30px;
            background: url("${pageContext.request.contextPath}/bg.gif");
        }
    </style>
</head>
<body>
<form action="/index">
    <div>操作成功</div>
</form>
<script>
    setTimeout(
       " javascript:location.href='/index'"
    ,1500)

</script>
</body>
</html>