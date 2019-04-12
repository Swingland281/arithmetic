<%--
  Created by IntelliJ IDEA.
  User: panzhongqiu
  Date: 2019/3/13
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>注册</title>
    <meta charset="utf-8">
    <script src="/jquery.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="./css/customization.css">
</head>
<body>
<div id="center">
    <form id="form0">
        <div id="part1">
            <label>身份</label>
            <select name="type" id="type" >
                <option value="1">学生</option>
                <option value="2">老师</option>
            </select>
        </div>
        <div id="part2">
            <label for="username">用户名：</label>
            <input type="text" name="username"  id="username">
        </div>
        <div id="part3">
            <label for="password">用户密码</label>
            <input type="password" name="password" id="password">
        </div>
        <div id="part4">
            <label for="email">邮箱</label>
            <input type="text" name="email" id="email">
        </div>

    </form>
    <div id="btn">
        <button type="button" onclick="regist()" id="button">注册</button>
    </div>
</div>
</body>
<script type="text/javascript">
    function regist(){
        $.ajax({
            url:"/regist",/*请求的地址*/
            type:"POST",/*请求的方式*/
            data:$('#form0').serialize(),
            dataType:"json",
            success:function(status){
                console.log(status);
                alert("注册成功");
                window.location.href='/log.jsp';
            },
            error:function(){
                alert("注册失败");
            }
        })
    }
</script>
</html>