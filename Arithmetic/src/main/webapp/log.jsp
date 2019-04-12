<%--
  Created by IntelliJ IDEA.
  User: panzhongqiu
  Date: 2019/3/13
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <meta charset="utf-8">
    <script src="jquery.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="./css/customization.css">
</head>
<body>
<div id="center">
    <form id="form0">
        <div id="part1">
            <label for="username">用户名：</label>
            <input type="text" name="username" placeholder=""  id="username">
        </div>
        <div id="part2">
            <label for="password">用户密码</label>
            <input type="password" name="password" id="password">
        </div>
        <!-- <div id="btn">
            <button type="button" onclick="log()" id="button">登录</button>
        </div> -->
    </form>
    <div id="btn">
        <button type="button" onclick="log()" id="button">登录</button>
    </div>
</div>
</body>
<script type="text/javascript">

    function log(){
        $.ajax({
            url:"/login",/*请求的地址*/
            type:"POST",/*请求的方式*/
            data:$('#form0').serialize(),
            dataType:"json",
            success:function(status){
                console.log(status);
                alert("登录成功");
                window.location.href='/start.jsp';
            },
            error:function(){
                alert("登录失败");
            }
        })
    }
</script>
</html>