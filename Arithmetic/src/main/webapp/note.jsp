<%--
  Created by IntelliJ IDEA.
  User: panzhongqiu
  Date: 2019/4/12
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>错题本</title>
    <meta charset="utf-8">
    <script src="jquery.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="./css/customization.css">
</head>
<body>
<div id="center">

</div>
</body>

<script type="text/javascript">
    window.onload=function(){
        $.ajax({
            url:"/printQuestion",
            type:"POST",/*请求的方式*/
            // data:$('#form0').serialize(),
            dataType:"json",
            success:function(data){
                alert("ok");
                console.log(data);
                var html = "";
                for(var i=0;i<data.question.length;i++){
                    var ls = data.question[i];
                    html +="<tr class='paper'><td>"+ls+"</td></tr>";
                }
                $("#center").append(html);
            },
            error:function(){
                alert("获取失败");
            }
        })
    }
</script>
</html>
