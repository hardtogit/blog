<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />


	<h4>密码修改</h4>
	<form id="change-password-form"class="form-horizontal well" role="form">
		<div class="form-group">
			<label for="inputEmail1" class="col-lg-2 col-md-3 col-sm-2 col-xs-12 control-label">当前密码</label>
			<div class="col-lg-4 col-md-6 col-sm-9 col-xs-12">
				<input type="hidden" id="uid" name="uid" value="${uid }"/>
				<input type="password" id="pwd" name="pwd" class="form-control required" autocomplete="off"/>
			</div>
		</div>
		<div class="form-group">
			<label for="inputEmail1" class="col-lg-2 col-md-3 col-sm-2 col-xs-12 control-label">新密码</label>
			<div class="col-lg-4 col-md-6 col-sm-9 col-xs-12">
				<input type="password" id="plainPassword" name="plainPassword" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label for="inputEmail1" class="col-lg-2 col-md-3 col-sm-2 col-xs-12 control-label">确认密码</label>
			<div class="col-lg-4 col-md-6 col-sm-9 col-xs-12">
				<input type="password" id="confirmPassword" name="confirmPassword" class="form-control required" equalTo="#plainPassword"/>
			</div>
		</div>
	</form>
	<div>
		<button type="submit" class="btn btn-primary btn-sm add-submit">保存密码</button>
	</div>
	
<c:if test="${env == 'pro' }">
	<script type="text/javascript">
		seajs.use("${ctx}/assets/js/user/profile-password");
	</script>
</c:if>
<c:if test="${env != 'pro' }">
	<script type="text/javascript">
		seajs.use("${ctx}/assets/js/user/profile-password");
	</script>
</c:if>
