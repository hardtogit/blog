<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="sub-nav">
<div class="container">
	<ul class="nav nav-pills pull-right" style="margin-right: 20px;">
		<li id="profile-index"><a href="${ctx }/profile" >个人资料</a></li>
		<li id="profile-avatar"><a href="${ctx }/profile/avatar" >修改头像</a></li>
		<li id="profile-password"><a href="${ctx }/profile/password" >修改密码</a></li>
	</ul>
</div>
</div>