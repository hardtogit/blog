<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="col-sm-3 col-sm-offset-1 blog-sidebar">
    <%--<div class="sidebar-module sidebar-module-inset">--%>
        <%--<h4>关于</h4>--%>
        <%--<p>待更新……</p>--%>
    <%--</div>--%>
    <div class="sidebar-module">
        <h4>标签</h4>
        <div class="tag-links">
            <c:forEach items="${hotTags}" var="hotTag" >
                <a class="btn-link" href="${ctx}/?tag=${hotTag.name}#">${hotTag.name} ${hotTag.total}</a>
            </c:forEach>
        </div>
    </div>
    <div class="sidebar-module">
        <h4>归档</h4>
        <ol class="list-unstyled">
            <c:forEach items="${archives}" var="archive" >
                <li><a href="${ctx}/?month=${archive.year}-${archive.month}-01">${archive.year}年${archive.month}月 - ${archive.num}</a></li>
            </c:forEach>
        </ol>
    </div>
    <div class="sidebar-module">
        <h4>链接</h4>
        <ol class="list-unstyled">
            <li><a href="https://github.com/thon-ju" target="_blank">GitHub</a></li>
        </ol>
    </div>
    <div class="sidebar-module">
        <h4>功能</h4>
        <ol class="list-unstyled">
            <shiro:guest>
                <li><a href="${ctx}/login" target="_blank">登录</a></li>
            </shiro:guest>
            <shiro:user>
                <li><a href="${ctx}/admin/post/manage">后台管理</a></li>
                <li><a href="${ctx}/logout">注销</a></li>
            </shiro:user>
        </ol>
    </div>
</div>