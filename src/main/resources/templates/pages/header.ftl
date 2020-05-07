<header>
	<div class="collapse bg-pink" id="navbarHeader">
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-md-4 py-3">
					<h4>关于：伊阳静美</h4>
					<p class="text-muted">This nest blongs to 张向阳 and 吕静, <br/>Loved for ${firstPage.loveTotalDays}
						days, Thanks for your visit. </p>
				</div>
				<div class="col-sm-7 col-md-4 py-3" style="text-align: right">
					<#--					<ul>&nbsp&nbsp</ul>-->
					<ul>
						<h4>联系我们: &nbsp&nbsp</h4>
					</ul>
				</div>
				<div class="col-sm-4 col-md-4 py-3">
					<ul class="list-unstyled">
						<li><a class="text-muted" target="_blank"
							   href="http://wpa.qq.com/msgrd?v=3&uin=${firstPage.contactQQ}&site=qq&menu=yes">QQ:${firstPage.contactQQ}</a>
						</li>
						<li><a href="http://weibo.com/u/2151990245?is_all=1" target="_blank" class="text-muted">阳微博</a>
							<a href="http://weibo.com/u/5345027252?is_all=1" target="_blank" class="text-muted">静微博</a>
						</li>
						<li><a href="/admin/" target="_blank" class="text-muted">登录后台管理</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="navbar navbar-dark bg-dark box-shadow">
		<div class="container d-flex justify-content-between">
			<a href="/" class="navbar-brand d-flex align-items-center"><strong>伊阳静美</strong></a>
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a href="/timeline" class="nav-link">时间线</a>
				</li>
			</ul>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader"
					aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
	</div>
</header>
