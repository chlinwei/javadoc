<%--
  Created by IntelliJ IDEA.
  User: lw
  Date: 2018/3/5
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆页面</title>
    <meta charset="utf-8">
    <h3>登陆页面</h3>
    <form action="${pageContext.request.contextPath}/login.action" method="post">
        用户名:<input type="text" name="name"><br>
        密码:<input type="password" name="password"><br>
        <input type="submit" value="登陆">
    </form>
</head>
<body>

</body>
</html>
