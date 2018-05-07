<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<form class="form-inline well" role="form" id="search-form" style="border-radius: 0;padding: 10px 10px 5px 10px;">
    <div class="form-group">
        <label for="billNo">单据号:</label>
        <input class="form-control input-sm" name="billNo" size="20" type="text">
    </div>
    <div class="form-group">
        <label for="vehicleNo">车牌号:</label>
        <input class="form-control input-sm" name="vehicleNo" size="10" type="text">
    </div>
    <div class="form-group">
        <label for="supplier">供应商:</label>
        <input class="form-control input-sm" name="supplier" size="10" type="text">
    </div>
    <div class="form-group">
        <label>材料类型:</label>
        <input class="form-control input-sm" name="materialType" type="text">
    </div>
    <div class="form-group">
        <button type="button" class="btn btn-primary btn-sm btn-search-submit">确认</button>
        <a href="${ctx }/bill/search" class="btn btn-default btn-sm">刷新</a>
    </div>
</form>

<div style="margin: 0px 10px 0 10px">

    <div id="list-data"></div>
</div>
<div class="clearfix"></div>

<script id="list-template" type="text/html">
    <table id="dict-table" class="table table-bordered table-striped table-hover">
        <thead>
        <tr>
            <%--<th style="width:3%;"><input type="checkbox" value="24"></th>--%>
            <th style="width:12%;">单据号</th>
            <th style="width:7%;">车牌号</th>
            <th style="width:12%;">供应商</th>
            <th style="width:10%;">材料</th>
            <th style="width:12%;">进场时间</th>
            <th style="width:12%;">出场时间</th>
            <th style="width:6%;">毛重</th>
            <th style="width:5%;">皮重</th>
            <th style="width:5%;">净重</th>
            <th style="width:5%;">操作</th>
        </tr>
        </thead>
        <tbody>
        {{each result as value i}}
        <tr>
            <%--<td><input type="checkbox" name="" value="24"></td>--%>
            <td class="row-title">
                <a class="btn-link" href="">{{ value.billNo }}</a>

            </td>
            <td>{{ value.vehicleNo }}</td>
            <td>{{ value.supplier }}</td>
            <td>{{ value.materialType }}{{ value.materialCode }}</td>
            <td>{{ value.inDate }}</td>
            <td>{{ value.outDate }}</td>
            <th class="text-right">{{ value.grossWeight }}</th>
            <th class="text-right">{{ value.tareWeight }}</th>
            <th class="text-right">{{ value.netWeight }}</th>
            <td>
                <div class="row-actions">
                    <a class="btn-link" target="_blank" href="${ctx}/bill/d?no={{ value.billNo }}">详情</a>
                </div>
            </td>
        </tr>
        {{/each}}
        </tbody>
    </table>
    <div>
        {{#pagination }}
    </div>
</script>

<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/app/bill/search-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/bill/search-page.js']);
    </c:if>
</script>
