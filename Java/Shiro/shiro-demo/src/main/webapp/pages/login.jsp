<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login page</title>
</head>
<body>
<h1>登录界面</h1>
<form action="/login-user" method="post">
    <label>
        <input type="text" name="username">
    </label> <br>
    <label>
        <input type="text" name="password">
    </label> <br>
    <button type="submit">提交</button>
</form>
</body>
</html>