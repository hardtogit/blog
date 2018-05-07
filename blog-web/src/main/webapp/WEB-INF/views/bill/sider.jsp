<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="sidebar-scroll" style="overflow: hidden; outline: none;">
    <div class="nav-collapse" id="sidebar" style="height: 0px;">
        <div class="panel panel-primary" style="border-radius: 0;margin-bottom: 10px;">
            <div class="panel-body" style="padding:10px;">
                <div class="media">
                    <div class="media-body" style="float: left;">
                        <h6 class="media-heading">姓名：<shiro:principal property="name" /></h6>
                        <h6>职务：技术员</h6>
                        <h6>当前部门：<span style="color: #428bca;">${curOffice.name}</span></h6>
                    </div>
                </div>
            </div>
        </div>
        <!-- BEGIN SIDEBAR MENU -->
        <div class="panel panel-primary" style="border-radius: 0;margin-bottom: 10px;">
            <div class="panel-body" style="padding:10px; padding-left: 0;">
                <div id="office-tree" style="min-height: 250px;"></div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/app/bill/sider-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/bill/sider-page.js']);
    </c:if>
</script>
