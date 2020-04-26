<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<base href="<%=basePath%>" />
	<title>${title }</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="block box">
	<div class="blank"></div>
	<div id="ur_here">
		当前位置: <a href=".">首页</a>
		<code> &gt; </code>
		<a href="all.action">全部服务</a>
		<code> &gt; </code>
		<a href="cate.action?id=${goods.cateid }">${goods.catename }</a>
		<code> &gt; </code>
		${goods.goodsname }
	</div>
</div>
<div class="blank"></div>
<div class="block clearfix">
	<div class="AreaL">
		<div id="category_tree">
			<div class="tit">所有服务分类</div>
			<dl class="clearfix" style="overflow: hidden;">
				<c:forEach items="${cateList}" var="cate">
					<div class="box1 cate" id="cate">
						<h1 style="border-top: none">
							<a href="cate.action?id=${cate.cateid }" class="  f_l">${cate.catename }</a>
						</h1>
					</div>
					<div style="clear: both"></div>
				</c:forEach>
			</dl>
		</div>
		<div class="blank"></div>
		<div class="box" id='history_div'>
			<div class="box_1">
				<h3>
					<span>热门服务</span>
				</h3>
				<div class="boxCenterList clearfix" id='history_list'>
					<c:forEach items="${hotList}" var="goods">
						<ul class="clearfix">
							<li class="goodsimg"><a href="detail.action?id=${goods.goodsid }" target="_blank"><img
									src="<%=path %>/showImg.action?imgurl=${goods.image}" alt="${goods.goodsname }" class="B_blue" /> </a></li>
							<li><a href="detail.action?id=${goods.goodsid }" target="_blank" title="${goods.goodsname }">${goods.goodsname }</a>
								<br /> 价格： <font class="f1">￥${goods.price }元</font></li>
						</ul>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="blank5"></div>
	</div>
	<div class="AreaR">

		<div id="goodsInfo" class="clearfix">
			<div class="imgInfo">
				<img src="<%=path %>/showImg.action?imgurl=${goods.image}" alt="${goods.goodsname }" width="360px;" height="360px" />
				<div class="blank5"></div>
				<div class="blank"></div>
			</div>
			<div class="textInfo">
				<form action="addcart.action" method="post" name="ECS_FORMBUY" id="ECS_FORMBUY">
					<h1 class="clearfix">${goods.goodsname }</h1>
					<ul class="ul2 clearfix">
						<li class="clearfix" style="width: 100%">
							<dd>
								<strong>价格：</strong><font class="shop" id="ECS_SHOPPRICE">￥${goods.price }元</font>
							</dd>
						</li>
						<li class="clearfix" style="width: 100%">
							<dd>
								<strong>服务类型：</strong><a href="cate.action?id=${goods.cateid }">${goods.catename }</a>
							</dd>
						</li>
						<li class="clearfix" style="width: 100%">
							<dd>
								<strong>上架日期：</strong>${goods.addtime}
							</dd>
						</li>
						<li class="clearfix" style="width: 100%">
							<dd>
								<strong>点击数：</strong>${goods.hits}
							</dd>
						</li>
						<li class="clearfix" style="width: 100%">
							<dd>
								<strong>预约量：</strong>${goods.sellnum}
							</dd>
						</li>
					</ul>
					<ul class="bnt_ul">
						<li class="clearfix"><input type="hidden" name="goodsid" value="${goods.goodsid }" /> <input type="hidden"
																													 name="price" value="${goods.price }" /></li>
						<li class="padd"><input type="image" src="themes/xecmoban_haier2015/images/goumai2.png" /></li>
					</ul>
				</form>
			</div>
		</div>
		<div class="blank"></div>

		<div class="box">
			<div style="padding: 0 0px;">
				<div id="com_b" class="history clearfix">
					<h2>服务描述</h2>
				</div>
			</div>
			<div class="box_1">
				<div id="com_v" class="  " style="padding: 6px;"></div>
				<div id="com_h">
					<blockquote>${goods.contents}</blockquote>
				</div>
			</div>
		</div>
		<div class="blank"></div>
		<div id="ECS_COMMENT">
			<div class="box">
				<div class="box_1">
					<h3>
						<span class="text">用户评论</span>(共<font class="f1">${tnum }</font>条评论)
					</h3>
					<div class="boxCenterList clearfix" style="height: 1%;">
						<ul class="comments">
							<c:forEach items="${topicList}" var="topic">
								<li class="word"><font class="f2">${topic.username }</font> <font class="f3">( ${topic.addtime } )</font><br />
									<img src="themes/xecmoban_haier2015/images/stars${topic.num }.gif" alt="" />
									<p>${topic.contents }</p></li>
							</c:forEach>
						</ul>
						<div class="blank5"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
