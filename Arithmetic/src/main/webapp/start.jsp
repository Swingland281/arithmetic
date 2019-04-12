<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <meta charset="utf-8">
    <script src="jquery.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="./css/start.css">
</head>

<body>
<div class="flip-container">
    <div class="flipper">
        <div class="front">
            <img src="./img/leaf.png">
        </div>
        <div class="back">
            <div id="btn">
                <button type="button" onclick="note()" >错题本</button>
                <button type="button" onclick="change()" >定制试卷</button>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    function change(){
        window.location.href='customization.jsp';
    }

    function note(){
        window.location.href='note.jsp';
    }
</script>
</html>