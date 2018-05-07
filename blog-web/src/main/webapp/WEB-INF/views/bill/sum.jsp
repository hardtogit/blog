<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="row" style="height: 30px; background-color: #F2F2F2;">
    <div class="col-xs-12">
        <ul class="nav nav-tabs custom-tabs" role="tablist" id="sum-tabs">
            <li role="presentation" class="active"><a href="#sum-today"  data-type="0">今日</a></li>
            <li role="presentation"><a href="#sum-last" aria-controls="profile"   data-type="1">过去7天</a></li>
            <li role="presentation"><a href="#sum-history" aria-controls="messages"  data-toggle="tab" data-type="2">历史汇总</a></li>
        </ul>
    </div>
</div>

<div class="tab-content" style="border:none; margin:20px 15px 10px 15px;">
    <div id="sum-today">

    </div>
</div>

<div class="clearfix"></div>

<script type="text/html" id="sum-template">
    <div class="col-sm-12">
        <div class="row">
            <div class="col-sm-4">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">进场车次</h3>
                    </div>
                    <div class="panel-body">
                        <i class="fa fa-sign-in pull-left" style="font-size: 45px;padding: 30px;color: #008200"></i>
                        <span style="display: block;font-size: 45px;padding: 30px;width: 100%; text-align: center;"><span class="sum-in-site">0</span><span style="font-size: 14px;margin-left: 10px;">次</span></span>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">出场车次</h3>
                    </div>
                    <div class="panel-body">
                        <i class="fa fa-sign-out pull-left" style="font-size: 45px;padding: 30px;"></i>
                        <span style="display: block;font-size: 45px; padding: 30px;width: 100%; text-align: center;"><span class="sum-out-site">0</span><span style="font-size: 14px;margin-left: 10px;">次</span></span>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="panel panel-danger">
                    <div class="panel-heading">
                        <h3 class="panel-title">未出场</h3>
                    </div>
                    <div class="panel-body">
                        <i class="fa fa-truck pull-left" style="font-size: 45px;padding: 30px;color: red"></i>
                        <span style="display: block;font-size: 45px; padding: 30px;width: 100%; text-align: center;"><span class="sum-not-out-site">0</span><span style="font-size: 14px;margin-left: 10px;">辆</span></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="panel panel-info">
                    <div class="panel-body">
                        <div id="chart" style="height:400px;background: #fff;"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>

<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/app/bill/sum-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/bill/sum-page.js']);
    </c:if>
</script>

