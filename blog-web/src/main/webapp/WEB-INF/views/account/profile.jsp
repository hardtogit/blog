<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

    <p class="legend">修改资料</p>
	<form id="profile-form" class="form-horizontal">
        <div class="error alert alert-danger" style="display:none;"></div>
		<input type="hidden" id="id" name="id" value="${curUser.id }">
		<input type="hidden" id="loginName" name="loginName" value="${curUser.loginName }">
		<div class="form-group">
			<label for="email" class="col-sm-3 control-label"><span class="text-fill">*</span>电子邮件</label>
			<div class="col-lg-4 col-md-6 col-sm-10 col-xs-12">
				<input type="text" class="form-control input-sm" id="email" name="email" value="${curUser.email }">
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-3 control-label">姓名</label>
			<div class="col-lg-4 col-md-6 col-sm-10 col-xs-12">
				<input type="text" class="form-control input-sm" id="name" name="name" value="${curUser.name }">
			</div>
		</div>
		<div class="form-group">
			<label for="phone" class="col-sm-3 control-label">手机号</label>
			<div class="col-lg-4 col-md-6 col-sm-10 col-xs-12">
				<input type="text" class="form-control input-sm" id="phone" name="phone" value="${curUser.phone }">
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-offset-3 col-md-offset-4 col-sm-offset-2 col-xs-offset-2 col-lg-7 col-md-8 col-sm-8 col-xs-12">
				<button type="button" id="profile-submit" class="btn btn-primary btn-sm">保存</button>
			</div>
		</div>
	</form>

<c:if test="${env == 'pro' }">
	<script type="text/javascript">
		seajs.use("${ctx}/assets/js/profile-page");
	</script>
</c:if>
<c:if test="${env != 'pro' }">
	<script type="text/javascript">
		seajs.use("${ctx}/assets/js/profile-page");
	</script>
</c:if>