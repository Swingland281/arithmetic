<%--
  Created by IntelliJ IDEA.
  User: panzhongqiu
  Date: 2019/3/13
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>regist</title>
</head>
<body>
<form method="post" action="/regist">
    身份：<select name="type">
    <option value="1">学生</option>
    <option value="2">教师</option>
</select>
    用户名：<input type="text" name="username">
    邮箱：<input type="email" name="email">
    密码：<input type="password" name="password">
    <input type="submit" value="提交">
</form>
</body>
</html>
