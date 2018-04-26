<%--
  Created by IntelliJ IDEA.
  User: lw
  Date: 2018/3/19
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login jsp</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/user_login.action" method="post">
    用户名:<input type="text" name="user.name"><br>
    密码:<input type="password" name="user.password"><br>
    <input type="submit" value="登陆">
</form>
</body>
</html>
