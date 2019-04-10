<%--
  Created by IntelliJ IDEA.
  User: panzhongqiu
  Date: 2019/4/10
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
</head>
<body>
<form  method="post" action="/question">
    最大值:<input type="text" name="max">
    最小值:<input type="text" name="min">
    算符数:<input type="text" name="k">
    式子数:<input type="text" name="n">
    是否生成括号：<select name="flag1">
    <option value="true">是</option>
    <option value="false">否</option>
            </select>
    是否生成分数：<select name="flag2">
    <option value="true">是</option>
    <option value="false">否</option>
            </select>
    <input type="submit" value="提交">
</form>
</body>
</html>
