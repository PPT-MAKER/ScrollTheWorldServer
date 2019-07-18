<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html charset=utf-8">
	<title>clx助力工具测试版</title>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
</head>
<body>
	Token:
    <input id="token" type="text">
    <br>
    <button type="button">submit</button>
    <p>使用方法：将助力链接中的page_key填入以上文本区并点击提交。示例：若助力链接为https://clx.163.com/201906/czch/?page_key=JpNhZtV0wHkrqwHFOjGl6，则需要填入JpNhZtV0wHkrqwHFOjGl6</p>
</body>
<script type="text/javascript">
	$("button").click(function(){
		var tokenStr = document.getElementById("token").value;
		console.log(tokenStr)
	    $.get("/myworld/help?token=" + tokenStr);
	});
</script>
</html>
