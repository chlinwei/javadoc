<%--
  Created by IntelliJ IDEA.
  User: lw
  Date: 2018/1/12
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>保存成功!</title>
</head>
<body>
<table border="1" width="500px">
    <tr>
        <th>编号</th>
        <th>文件名</th>
        <th>大小</th>
        <th>类型</th>
        <th>文件说明</th>
        <th>上传时间</th>
    </tr>
    <c:forEach items="${fileList}" var="bean" varStatus="varSta">
        <tr>
            <td>${varSta.count}</td>
            <td>${bean.name}</td>
            <td>${bean.size}</td>
            <td>${bean.type}</td>
            <td>${bean.info}</td>
            <td>${bean.addTime}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
