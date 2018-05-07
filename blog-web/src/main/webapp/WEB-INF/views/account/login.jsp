<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="container" style="max-width: 1000px;">
    <form id="login-form" class="form-horizontal" role="form" style="margin:0 auto;margin-top:250px; background-color: #fff; background-size:100% 100%;width: 520px;height: 300px;padding-top: 100px;">
        <div class="form-group">
            <label for="username" class="col-sm-4 control-label">账号</label>
            <div class="col-sm-5">
                <input type="text" class="form-control input-sm" id="username" name="username" value="${username }">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-4 control-label">密码</label>
            <div class="col-sm-5">
                <input type="password" class="form-control input-sm" id="password" name="password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-5">
                <label>
                    <input type="checkbox" id="rememberMe" name="rememberMe"> 记住我
                </label>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-5">
                <button type="button" id="login-submit" class="btn btn-primary btn-sm pull-right" style="margin-top: 10px;">登录</button>
                <%--<button type="reset" class="btn btn-default btn-sm">重置</button>--%>
                <c:choose>
                    <c:when test="${shiroLoginFailure eq 'com.thon.security.CaptchaException'}">
                        <small><span class="text-danger"><fmt:message key="login.error.captcha" /></span></small>
                    </c:when>
                    <c:when
                            test="${shiroLoginFailure eq 'org.apache.shiro.authc.UnknownAccountException'}">
                        <small><span class="text-danger"><fmt:message key="login.error.notExist" /></span></small>
                    </c:when>
                    <c:when
                            test="${shiroLoginFailure eq 'org.apache.shiro.authc.IncorrectCredentialsException'}">
                        <small><span class="text-danger">登录名或密码错误</span></small>
                    </c:when>
                    <c:when
                            test="${shiroLoginFailure eq 'org.apache.shiro.authc.LockedAccountException'}">
                        <small><span class="text-danger"><fmt:message key="login.error.inactive" /><a href="#"><fmt:message key="login.error.activeEmail" /></a>.</span></small>
                    </c:when>
                    <c:when test="${shiroLoginFailure ne null}">
                        <small><span class="text-danger"><fmt:message key="login.error.other" /></span></small>
                    </c:when>
                </c:choose>
            </div>
        </div>
        <div class="error alert alert-danger col-sm-offset-4 col-sm-5" style="display:none;"></div>
    </form>
</div>


<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/login-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/login-page.js']);
    </c:if>
</script>
