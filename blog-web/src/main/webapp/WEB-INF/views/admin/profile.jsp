<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

	<h4>资料修改</h4>
	<form id="profile-form" class="form-horizontal well">
		<input type="hidden" id="id" name="id" value="${user.id }">
		<input type="hidden" id="loginName" name="loginName" value="${user.loginName }">
		<div class="form-group">
			<label for="name" class="col-lg-2 col-md-3 col-sm-2 col-xs-12 control-label">昵称</label>
			<div class="col-lg-4 col-md-6 col-sm-9 col-xs-12">
				<input type="text" class="form-control" id="name" name="name" value="${user.name }">
			</div>
		</div>
		<div class="form-group">
			<label for="phone" class="col-lg-2 col-md-3 col-sm-2 col-xs-12 control-label">联系电话</label>
			<div class="col-lg-4 col-md-6 col-sm-9 col-xs-12">
				<input type="text" class="form-control" id="phone" name="phone" value="${user.phone }">
			</div>
		</div>
		<div class="form-group">
			<label for="qq" class="col-lg-2 col-md-3 col-sm-2 col-xs-12 control-label">QQ</label>
			<div class="col-lg-4 col-md-6 col-sm-9 col-xs-12">
				<input type="text" class="form-control" >
			</div>
		</div>
	</form>
	<div>
		<button type="submit" class="btn btn-primary btn-sm add-submit">保存</button>
	</div>
	
<c:if test="${env == 'pro' }">
	<script type="text/javascript">
		seajs.use("${ctx}/assets/js/admin/profile-page");
	</script>
</c:if>
<c:if test="${env != 'pro' }">
	<script type="text/javascript">
		seajs.use("${ctx}/assets/js/admin/profile-page");
	</script>
</c:if>