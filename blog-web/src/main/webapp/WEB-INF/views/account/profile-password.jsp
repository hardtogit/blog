<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

	<p class="legend">修改密码</p>
    <form id="password-form"class="form-horizontal" role="form">
        <div class="error alert alert-danger" style="display:none;"></div>
        <div class="form-group">
            <label for="pwd" class="col-sm-2 col-md-3 col-lg-4 col-xs-12 control-label">当前密码</label>
            <div class="col-sm-9 col-md-6 col-lg-4 col-xs-12">
                <input type="hidden" id="uid" name="uid" value="${curUser.id }"/>
                <input type="password" id="pwd" name="pwd" class="form-control required" autocomplete="off"/>
            </div>
        </div>
        <div class="form-group">
            <label for="plainPassword" class="col-sm-2 col-md-3 col-lg-4 col-xs-12 control-label">新密码</label>
            <div class="col-sm-9 col-md-6 col-lg-4 col-xs-12">
                <input type="password" id="plainPassword" name="plainPassword" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label for="confirmPassword" class="col-sm-2 col-md-3 col-lg-4 col-xs-12 control-label">确认密码</label>
            <div class="col-sm-9 col-md-6 col-lg-4 col-xs-12">
                <input type="password" id="confirmPassword" name="confirmPassword" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-4 col-md-offset-3 col-sm-offset-2 col-xs-offset-2 col-lg-7 col-md-7 col-sm-6 col-xs-6">
                <button type="button" id="password-submit" class="btn btn-primary">保存密码</button>
            </div>
        </div>
    </form>

<c:if test="${env == 'pro' }">
	<script type="text/javascript">
		seajs.use("${ctx}/assets/js/profile-password");
	</script>
</c:if>
<c:if test="${env != 'pro' }">
	<script type="text/javascript">
		seajs.use("${ctx}/assets/js/profile-password");
	</script>
</c:if>