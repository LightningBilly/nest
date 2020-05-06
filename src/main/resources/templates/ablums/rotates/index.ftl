<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <meta name="renderer" content="webkit" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1" />
	<meta name="Keywords" content="张向阳,吕静,Jonny.Chang,Dilys.Lyu"/>
	<meta name="description" content="张向阳,吕静,Jonny.Chang,Dilys.Lyu"/>
    <title>魔方相册</title>
    <link rel="stylesheet" type="text/css" href="index.css" />
    <style type="text/css">
        body{
            background: url(ablums/images/rotates/bg.jpg) 0 0 repeat;
            background-size: 100%;
        }
        .minbox li:nth-child(1){
            background: url(ablums/images/rotates/p1.jpg) no-repeat 0 0;
            transform:translateZ(60px);
        }
        .minbox li:nth-child(2){
            background: url(ablums/images/rotates/p2.jpg) no-repeat 0 0;
            transform:rotateX(180deg) translateZ(60px);
        }
        .minbox li:nth-child(3){
            background: url(ablums/images/rotates/p3.jpg) no-repeat 0 0;
            transform:rotateX(-90deg) translateZ(60px);
        }
        .minbox li:nth-child(4){
            background: url(ablums/images/rotates/p4.jpg) no-repeat 0 0;
            transform:rotateX(90deg) translateZ(60px);
        }
        .minbox li:nth-child(5){
            background: url(ablums/images/rotates/p5.jpg) no-repeat 0 0;
            transform:rotateY(-90deg) translateZ(60px);
        }
        .minbox li:nth-child(6){
            background: url(ablums/images/rotates/p6.jpg) no-repeat 0 0;
            transform:rotateY(90deg) translateZ(60px);
        }
    </style>
</head>

<body>
<div class="box">
    <ul class="minbox">
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>
    <ol class="maxbox">
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ol>
</div>
</body>
</html>
