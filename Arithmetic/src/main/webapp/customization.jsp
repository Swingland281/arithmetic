<%--
  Created by IntelliJ IDEA.
  User: panzhongqiu
  Date: 2019/4/10
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>定制试卷</title>
    <meta charset="utf-8">
    <script src="/jquery.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="./css/customization.css">
</head>
<body>
<div id="center">
    <h1 id="h1">定制出题要求</h1>
    <form id="form0" >
        <div id="part1">
            <label for="number">题目的数量</label>
            <input type="text" name="n" placeholder="填写1-1000的数字" onblur="checknum()" id="number">
            <span id="notice1"></span>
        </div>
        <div id="part2">
            <label>数值的范围</label>
            <input type="text" name="min" placeholder="填写1-100的数字" onblur="checkrange()" id="range1">
            <input type="text" name="max" placeholder="填写50-1000的数字" onblur="checkrange()" id="range2">
            <span id="notice2"></span>
            <span id="notice3"></span>
        </div>
        <div id="part3">
            <label for="k">运算符上限</label>
            <input type="text" name="k"  placehold="请填写1-10的正整数" onblur="checkk()" id="k">
            <span id="notice4"></span>
        </div>
        <div id="part4">
            <label>是否包含乘除</label>
            <select name="flag2">
                <option value="true">是</option>
                <option value="false" selected>否</option>
            </select>
        </div>
        <div id="part5">
            <label>是否包含括号</label>
            <select name="flag1">
                <option value="true">是</option>
                <option value="false" selected>否</option>
            </select>
        </div>
        <div id="btn">
            <button type="button" value="开始出题" onclick="paper()" id="button">开始出题</button>
        </div>
    </form>
    <!-- <div id="btn2">
            <button type="button" value="开始答题" id="button2" onclick="start()">开始答题</button>
            <button type="button" value="答题结束" id="button3" onclick="finish()">答题结束</button>
    </div> -->
    <div id="btn2">
        <h1 id=mytime>显示时间</h1>
        <button id="start" name="button" onclick="start()">开始考试</button>
        <button id="stop" name="button" onclick="stop()">结束考试</button>
    </div>
</div>
</body>
<script type="text/javascript">
    var number=document.getElementById("number");
    var range1=document.getElementById("range1");
    var range2=document.getElementById("range2");
    var k=document.getElementById("k");
    var notice1=document.getElementById("notice1");
    var notice2=document.getElementById("notice2");
    var notice3=document.getElementById("notice3");
    var notice4=document.getElementById("notice4");
    var h1=document.getElementById("h1");
    var form0=document.getElementById("form0");
    var btn2=document.getElementById("btn2");
    var ans=document.getElementsByClassName("ans");

    function checknum(){
        if(number.value.length == 0) {
            notice1.style.display="inline-block";
            notice1.innerHTML = '题目数量不能为空';
        }
        else if((/^\+?[1-9][0-9]*$/.test(number.value))&&number.value<1000){
            notice1.style.display="none";
            a=1;
        } else {
            notice1.style.display="inline-block";
            notice1.innerHTML = '输入必须为1-1000的整数';
        }
    }

    function checkrange(){
        if(range1.value.length ==0){
            notice2.style.display="block";
            notice2.innerHTML = '输入不能为空值';
        }
        else if((/^\+?[1-9][0-9]*$/.test(range1.value))&&range1.value<100){
            notice2.style.display="none";
            b=1;
        }
        else {
            notice2.style.display="block";
            notice2.innerHTML = '输入下界为1-100的整数';
        }

        if(range2.value.length ==0){
            notice3.style.display="block";
            notice3.innerHTML = '输入不能为空值';
        }
        else if(range2.value<=range1.value){
            notice3.style.display="block";
            notice3.innerHTML="题目上界应大于下界";
        }
        else if((/^\+?[1-9][0-9]*$/.test(range2.value))&&range2.value<1000&&range2.value>50){
            notice3.style.display="none";
            c=1;
        }
        else {
            notice3.style.display="block";
            notice3.innerHTML = '输入上界为50-1000的整数';
        }
    }

    function checkk(){
        if(k.value.length == 0) {
            notice4.style.display="inline-block";
            notice4.innerHTML = '题目数量不能为空';
        }
        else if((/^\+?[1-9][0-9]*$/.test(k.value))&&k.value<10){
            notice4.style.display="none";
        } else {
            notice4.style.display="inline-block";
            notice4.innerHTML = '输入必须为1-10的整数';
        }
    }


    function paper(){
        h1.style.display="none";
        form0.style.display="none";
        btn2.style.display="block";
    }

    var h=m=s=ms= 0;  //定义时，分，秒，毫秒并初始化为0；
    var time=0;

    function timer(){   //定义计时函数
        ms=ms+50;         //毫秒
        if(ms>=1000){
            ms=0;
            s=s+1;         //秒
        }
        if(s>=60){
            s=0;
            m=m+1;        //分钟
        }
        if(m>=60){
            m=0;
            h=h+1;        //小时
        }
        str =toDub(h)+"时"+toDub(m)+"分"+toDub(s)+"秒"+toDubms(ms)+"毫秒";
        mytime = document.getElementById('mytime');
        mytime.innerHTML = str;
    }

    function start(){  //开始
        time=setInterval(timer,50);
        // $(function(){
            $.ajax({
                url:"/question",/*请求的地址*/
                type:"POST",/*请求的方式*/
                data:$('#form0').serialize(),
                dataType:"json",
                success: function(data) {
                    console.log(data);
                    // btn2.style.display="inline-block";
                    // var j= JSON.stringify(data);
                    // console.log(data);
                    // console.log(Object.length);
                    // console.log(data.question);
                    // console.log(data.question.length);
                    var html = "";
                    for(var i=0;i<data.question.length;i++){
                        var ls = data.question[i];
                        j=i+1;
                        html +="<tr class='paper'><td>"+j+"、"+"</td>"+"<td>"+ls+"</td><td>"+"="+"</td><td><input type='text' class='ans' name='ans'></td></tr>";
                    }
                    // var tr=$("<tr>");
                    // tr.attr('margin','100px');
                    $("#center").append(html);
                    //在html页面id=test的标签里显示html内容
                },
                error: function () {
                    alert("生成失败");
                    h1.style.display="block";
                    form0.style.display="block";
                }
            })
        // })
    }

    function stop(){  //暂停
        var ans1=document.getElementsByName("ans");
        var array=[];
        clearInterval(time);
        var html = "";
        html+="<span>考试用时："+str+"</span>";
        $("#center").append(html);
        // alert(ans.innerHTML);
        for(var i=0;i<ans1.length;i++){
            var m=ans1[i].value;
            //   if(m.length==0){
            // 	array.push('Null');
            // }
            array.push(m);
            // if(m.length==0){
            // 	array.push('Null');
            // }
        }
        console.log(array);
        // console.log(ans.innerHTML);
        $.ajax({
            url:"/judge",
            type:"POST",/*请求的方式*/
            data:{
                "list":array
            },
            dataType:"json",
            // xhrFields:{withCredentials:true},
            success:function(data){
                var html2="";
                var count=0;
                var noCount=0;
                for(var i=0;i<data.result.length;i++){
                    var judge=data.result[i];
                    // html2 +="<tr>"+judge+"</tr>";
                    if(judge=="f"){
                        judge="×";
                        noCount++;
                    }
                    else if(judge=="r"){
                        judge="√";
                        count++;
                    }
                    j=i+1;
                    html2+="<td>"+judge+"</td>";
                }
                // console.log(count);
                // console.log(noCount);
                $("#center").append(html2);
                alert("正确："+count+"道题"+";"+"错误："+noCount+"道题");
            },
            error: function () {
                alert("操作错误");
            }
        })
        // var html2="";
        // html2 +="<span>答案正确："+count+"答案错误："+"</span>";
        // $("#center").append(html2);
    }

    function toDub(n){  //补0操作
        if(n<10){
            return "0"+n;
        }
        else {
            return ""+n;
        }
    }

    function toDubms(n){  //给毫秒补0操作
        if(n<10){
            return "00"+n;
        }
        else {
            return "0"+n;
        }

    }
</script>
</html>