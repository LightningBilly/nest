<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<meta name="renderer" content="webkit"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="Keywords" content="张向阳,吕静,Jonny.Chang,Dilys.Lyu"/>
	<meta name="description" content="张向阳,吕静,Jonny.Chang,Dilys.Lyu"/>
	<title>我们的画展</title>
	<link rel="stylesheet" href="css/renderer_styles.css"/>
	<link rel="stylesheet" href="css/renderer.css"/>
</head>
<body>
<div class="container">
	<div id="ipresenter">
		<div class="step" data-x="0" data-y="0" data-thumbnail="images/ipresenter/thumbs/1.jpg">
			<img src="images/ipresenter/photos/p1.jpg"/>
		</div>
		<div class="step" data-x="1500" data-y="0" data-rotatex="180" data-easing="easeInOutQuint"
			 data-thumbnail="images/ipresenter/thumbs/2.jpg">
			<img src="images/ipresenter/photos/p2.jpg"/>
		</div>
		<div class="step" data-x="3000" data-y="0" data-rotatey="180" data-thumbnail="images/ipresenter/thumbs/3.jpg">
			<img src="images/ipresenter/photos/p3.jpg"/>
		</div>
		<div class="step" data-x="4500" data-y="0" data-rotatex="180" data-thumbnail="images/ipresenter/thumbs/4.jpg">
			<img src="images/ipresenter/photos/p4.jpg"/>
		</div>
	</div>
</div>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/ipresenter.packed.js"></script>
<script>
	$(document).ready(function () {
		$('#ipresenter').iPresenter({
			animSpeed: 3000,
			pauseTime: 2000,
			timer: '360Bar',
			timerDiameter: 80,
			timerStroke: 5,
			timerPadding: 5,
			timerColor: '#000',
			timerBg: '#FFF',
			timerOpacity: 0.8,
			directionNav: false,
			controlNav: true
		});
	});
</script>
</body>

</html>
