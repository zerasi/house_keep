<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>" />
	<title>${title }</title>
	<script type="text/javascript" src="laydate/laydate.js" charset="utf-8"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="block box">
	<div class="blank"></div>
	<div id="ur_here">
		当前位置: <a href="<%=basePath%>">首页</a>
		<code> &gt; </code>
		购物流程
	</div>
</div>
<div class="blank"></div>
<div class="block table">
	<form action="checkout.action" method="post" name="theForm" id="theForm" onsubmit="return checkConsignee(this)">
		<div class="flowBox">
			<h6>
				<span>收货人信息</span>
			</h6>
			<table width="99%" align="center" border="0" cellpadding="5" cellspacing="1" bgcolor="#dddddd">
				<tr>
					<td bgcolor="#ffffff">详细地址:</td>
					<td bgcolor="#ffffff" colspan="2"><input name="address" type="text" class="inputBg" id="address" /> (必填)</td>
				</tr>
				<tr>
					<td bgcolor="#ffffff">联系方式:</td>
					<td bgcolor="#ffffff" colspan="2"><input name="contact" type="text" class="inputBg" id="contact" /> (必填)</td>
				</tr>
				<tr>
					<td bgcolor="#ffffff">开始日期:</td>
					<td bgcolor="#ffffff" colspan="2"><input name="workdate" type="text" class="inputBg" id="workdate"
															 onclick="laydate()" readonly="readonly" /> (必填)</td>
				</tr>
				<tr>
					<td bgcolor="#ffffff">结束日期:</td>
					<td bgcolor="#ffffff" colspan="2"><input name="enddate" type="text" class="inputBg" id="enddate"
															 onclick="laydate()" readonly="readonly" /> (必填)</td>
				</tr>
				<tr>
					<td bgcolor="#ffffff">时间段:</td>
					<td bgcolor="#ffffff" colspan="2"><input name="worktime" type="text" class="inputBg" id="worktime" /> (必填)</td>
				</tr>
				<tr>
					<td colspan="4" align="center" bgcolor="#ffffff"><input type="submit" class="bnt_blue_2" value="确定" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
