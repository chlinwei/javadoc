<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
    <h3>注册页面</h3>
    <form action="${pageContext.request.contextPath}/RegisterServlet" method="post">
        用户名:<input type="text" name="name"><br>
        密码:<input type="password" name="password"><br>
        <input type="submit" value="注册">
    </form>
</head>
<body>

</body>
</html>
