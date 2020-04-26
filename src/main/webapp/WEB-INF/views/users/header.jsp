<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<link href="/themes/xecmoban_haier2015/style.css" rel="stylesheet" type="text/css" />


<div class="ng-toolbar">
	<div class="ng-toolbar-con block">
		<div class="ng-toolbar-left">&nbsp;</div>
		<div class="ng-toolbar-right">
			<div class="ng-bar-node reg-bar-node" id="reg-bar-node" style="display: block;">
				<c:if test="${sessionScope.userid == null }">
					欢迎光临本站！
					<s></s>&nbsp;
					<span> <a style="color: #FF6766;" href="preLogin.action">[用户登录]</a> <a style="color: #FF6766;"
																						   href="preReg.action">[用户注册]</a>
					</span>

				</c:if>
				<c:if test="${sessionScope.userid != null }">
					你好 <b>${sessionScope.username }</b>
					<span><a style="color: #FF6766;" href="cart.action">[购物车]</a><a style="color: #FF6766;"
																					href="usercenter.action">[用户中心]</a> <a style="color: #FF6766;" href="exit.action">[退出系统] </a> </span>
				</c:if>
			</div>
		</div>
	</div>
</div>
<div class="ng-header">
	<div class="ng-header-con block">
		<div class="ng-header-box">
			<a href="/index.action" class="logo"> <img src="themes/xecmoban_haier2015/images/logo (1).png" style="width: 250px;height: 50px;" />
			</a>
			<div class="ng-search">
				<div class="g-search">
					<form id="searchForm" name="searchForm" method="post" action="query.action">
						<div class="search-keyword-box">
							<input name="name" type="text" id="name" value="" class="search-keyword" />
						</div>
						<input type="submit" value="搜索" class="btn-search" style="cursor: pointer;" />
						<div class="clear"></div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="ng-nav-bar">
	<div class="block">
		<div class="ng-nav-index">
			<ul class="ng-nav">
				<li><a href="index.action" class="cur">首页</a></li>
				<li><a href="article.action" class="cur">网站公告</a></li>
				<li><a href="all.action" class="cur">全部服务</a></li>
			</ul>
		</div>
	</div>
</div>
<div class="blank"></div>