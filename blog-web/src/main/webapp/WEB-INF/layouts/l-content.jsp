<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="sidebar-scroll" style="overflow: hidden; outline: none;">
    <div class="nav-collapse" id="sidebar" style="height: 0px;">
        <!-- BEGIN SIDEBAR MENU -->
        <ul class="sidebar-menu">
            <li class="sub-menu active" id="sum-bill">
                <a class="" href="javascript:;">
                    <i class="fa fa-plus" style="font-size: 13px;"></i>
                    <span>磅单监控</span>
                    <span class="arrow"></span>
                </a>
                <ul class="sub">
                    <li id="bill-sum-today" class="active"><a href="${ctx }/sum/bill/today" ><i class="fa fa-cube" style="font-size: 13px;"></i><span>今日</span></a></li>
                    <li id="bill-sum-last" ><a href="${ctx }/sum/bill/last" ><i class="fa fa-cube" style="font-size: 13px;"></i>过去7天</a></li>
                    <li id="bill-sum-history" ><a href="${ctx }/sum/bill/history" ><i class="fa fa-cube" style="font-size: 13px;"></i>历史汇总</a></li>
                </ul>
            </li>

        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
</div>
