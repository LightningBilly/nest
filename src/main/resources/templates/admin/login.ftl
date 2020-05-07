<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8"/>
	<meta name="renderer" content="webkit"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
	<meta name="Keywords" content="${firstPage.keywords}"/>
	<meta name="description" content="${firstPage.description}"/>
	<title>伊阳静美-后台-登录</title>
	<link rel="stylesheet" type="text/css" href="${firstPage.cdnBootstrapCss}"/>
</head>

<body>
<div class="container mt-3" style="width: 520px">
	<form action="/admin/doLogin" method="post" style="padding: 54px;border-style: double">
		<input type="hidden" name="loginSessionId" id="loginSessionId" value="${loginSessionId}">
		<div class="form-group">
			<label for="username">用户名：</label>
			<input type="text" class="form-control" id="username" name="username" placeholder="用户名/邮箱">
		</div>
		<div class="form-group">
			<label for="passwd">密码：</label>
			<input type="password" class="form-control" id="passwd" name="passwd" placeholder="密码">
		</div>
		<#--		<div id="yanzhengma-parent" class="form-group">-->
		<#--			<label for="veri" style="width: 100%">验证码: <img onclick="refreshimage(this)" src="veri"/> <span-->
		<#--						style="float: right"> 点击图片重新加载</span></label>-->
		<#--			<input type="text" class="form-control" id="veri" name="veri" placeholder="验证码">-->
		<#--		</div>-->
		<div>
			<button type="submit" class="btn btn-primary" style="float: right">登录</button>
		</div>
	</form>
</div>

<script type="text/javascript" src="${firstPage.cdnJqueryJs}"></script>
<script type="text/javascript" src="${firstPage.cdnBootstrapJs}"></script>
<script>
	function refreshimage(obj) {
		obj.src = "veri?r=" + Math.random();
	}
</script>
</body>
</html>
