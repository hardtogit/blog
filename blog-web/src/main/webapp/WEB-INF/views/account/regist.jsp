<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="row">
    <form id="regist-form" role="form" style="max-width:330px; min-height: 450px; background: #f2f2f2; border-radius: 15px; padding: 5px 15px; margin: 0 auto;margin-top: 50px;">
        <h4 class="text-center" style="margin: 10px;">注册</h4>
        <div class="error alert alert-danger" style="display:none;"></div>
        <div class="form-group">
            <label for="loginName"><span class="text-fill">*</span>登录名</label>
            <input type="text" id="loginName" name="loginName" class="form-control required" title="请填写登录名"/>
        </div>
        <div class="form-group">
            <label for="plainPassword"><span class="text-fill">*</span>密码</label>
            <input type="password" id="plainPassword" name="plainPassword" class="form-control required" title="请填写密码，长度不少于6位"/>
        </div>
        <div class="form-group">
            <label for="confirmPassword"><span class="text-fill">*</span>确认密码</label>
            <input type="password" id="confirmPassword" name="confirmPassword" class="form-control required" equalTo="#plainPassword" title="两次输入密码不一致"/>
        </div>

        <div class="form-group">
            <label for="loginName"><span class="text-fill">*</span>电子邮件</label>
            <input type="email" id="email" name="email" class="form-control required" autocomplete="off"  title="电子邮件不能为空"/>
        </div>
        <div class="form-group">
            <button id="regist-submit" class="btn btn-primary" type="button" style="width: 100%;">提交</button>
        </div>
    </form>
</div>

<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/regist-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/regist-page.js']);
    </c:if>
</script>