<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h4>${empty user? "添加用户":"修改用户"  }</h4>
<div class="error alert alert-danger" style="display:none;">
</div>
<form id="user-form" class="form-horizontal" style="padding: 15px;">
	<input type="hidden" id="id" name="id" value="${user.id }"/>
	<input type="hidden" name="password" value="${user.password }"/>
	<input type="hidden" name="salt" value="${user.salt }"/>
    <input type="hidden" name="avatar" value="${user.avatar }"/>
	<div class="form-group">
        <label for="loginName" class="col-sm-2 control-label"><span class="text-fill">*</span>登录名</label>
        <div class="col-sm-3">
            <input type="text" class="form-control input-sm required" id="loginName" name="loginName" <c:if test="${user.id ne null}">readonly</c:if> value="${user.loginName }"  title="登录名必须填写">
        </div>
	</div>
    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">姓名</label>
        <div class="col-sm-3">
            <input type="text" class="form-control input-sm" id="name" name="name" value="${user.name }">
        </div>
    </div>
	<!-- 初始密码 -->
	<c:if test="${user.id eq null}">
	<div class="form-group">
		<label for="plainPassword" class="col-sm-2 control-label"><span class="text-fill">*</span>初始密码</label>
		<div class="col-sm-3">
			<input type="password" class="form-control input-sm required" id="plainPassword" name="plainPassword" title="密码必须填写">
		</div>
		<label for="confirmPassword" class="col-sm-2 control-label"><span class="text-fill">*</span>重复密码</label>
		<div class="col-sm-3">
			<input type="password" class="form-control input-sm required" id="confirmPassword" name="confirmPassword" equalTo="#plainPassword" title="两次输入密码必须一致"/>
		</div>
	</div>
	</c:if>
    <div class="form-group">
        <label for="email" class="col-sm-2 control-label"><span class="text-fill">*</span>邮箱</label>
        <div class="col-sm-3">
            <input type="email" class="form-control input-sm" id="email" name="email" value="${user.email }" title="邮箱必须填写">
        </div>
    </div>
	<div class="form-group">
		<label for="phone" class="col-sm-2 control-label">手机号</label>
		<div class="col-sm-3">
			<input type="text" class="form-control input-sm" id="phone" name="phone" value="${user.phone }">
		</div>
	</div>
    <div class="form-group">
        <label for="delFlag" class="col-sm-2 control-label"><span class="text-fill">*</span>角色</label>
        <div class="col-sm-8">
            <c:forEach items="${allRoles}" var="role">
                    <input type="radio" id="radio${role.id }" class="required" id="roleId" name="role.id" ${user.role.id==role.id?"checked":""} value="${role.id }" title="用户角色——必须选择至少一个"> ${role.name }
            </c:forEach>
        </div>
    </div>
    <div class="form-group">
        <label for="delFlag" class="col-sm-2 control-label"><span class="text-fill">*</span>状态</label>
        <div class="col-sm-8">
            <c:forEach items="${fns:getDictList('del_flag_type')}" var="dict">
                <input type="radio" id="radio${dict.id }" class="required" id="delFlag" name="delFlag" ${user.delFlag==dict.value?"checked":""} value="${dict.value }" title="用户角色——必须选择至少一个"> ${dict.label }
            </c:forEach>
        </div>
    </div>
</form>

<div >
	<shiro:hasPermission name="sys:menu:edit">
		<button id="user-submit" class="btn btn-primary btn-sm" type="button">保 存</button>
	</shiro:hasPermission>
		<button id="user-cancel" class="btn btn-sm" type="button" onclick="history.go(-1)">返 回</button>
</div>

<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/user-form-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/user-form-page.js']);
    </c:if>
</script>
