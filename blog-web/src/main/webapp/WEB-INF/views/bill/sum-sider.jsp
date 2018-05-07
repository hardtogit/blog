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
                        <h6>当前部门：连镇项目<a id="office-change" class="btn-link" style="margin-left: 20px;">切换</a></h6>
                    </div>
                </div>
            </div>
        </div>
        <!-- BEGIN SIDEBAR MENU -->
        <ul class="sidebar-menu">
            <li class="sub-menu active" id="sum-bill">
                <a class="" href="javascript:;">
                    <i class="fa fa-plus" style="font-size: 13px;"></i>
                    <span>磅单监控</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li id="bill-sum-today" class="active"><a href="${ctx }/bill/sum/today" ><i class="fa fa-cube" style="font-size: 13px;"></i><span>今日</span></a></li>
                    <li id="bill-sum-last" ><a href="${ctx }/bill/sum/last" ><i class="fa fa-cube" style="font-size: 13px;"></i>过去7天</a></li>
                    <li id="bill-sum-history" ><a href="${ctx }/bill/sum/history" ><i class="fa fa-cube" style="font-size: 13px;"></i>历史汇总</a></li>
                </ul>
            </li>

        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
</div>
