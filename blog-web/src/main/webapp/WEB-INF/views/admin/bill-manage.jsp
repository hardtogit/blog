<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h4>所有磅单</h4>
<form class="form-inline well" role="form" id="search-form">
	<div class="form-group">
		<label for="billNo">单据号:</label>
		<input class="form-control input-sm" name="billNo" size="16" type="text">
	</div>
	<div class="form-group">
		<label for="vehicleNo">车牌号:</label>
		<input class="form-control input-sm" name="vehicleNo" size="10" type="text">
	</div>
	<div class="form-group">
		<button type="button" class="btn btn-primary btn-sm btn-search-submit">确认</button>
		<a href="${ctx }/admin/bill/manage" class="btn btn-default btn-sm">刷新</a>
	</div>
</form>
<div id="list-data"></div>

<script id="list-template" type="text/html">
<table id="dict-table" class="table table-striped table-hover">
	<thead>
		<tr>
            <th style="width:3%;"><input type="checkbox" value="24"></th>
			<th style="width:15%;">单据号</th>
			<th style="width:8%;">车牌号</th>
			<th style="width:12%;">进场时间</th>
			<th style="width:12%;">出场时间</th>
			<th style="width:6%;">毛重</th>
            <th style="width:6%;">皮重</th>
			<th style="width:6%;">净重</th>
		</tr>
	</thead>
	<tbody>
        {{each result as value i}}
		<tr>
            <td><input type="checkbox" name="" value="24"></td>
			<td class="row-title">
                <a class="btn-link" href="">{{ value.billNo }}</a>
                <div class="row-actions">
                    <a class="btn-link" target="_blank" href="${ctx}/bill/d?no={{ value.billNo }}">详情</a>
                </div>
            </td>
			<td>{{ value.vehicleNo }}</td>
			<td>{{ value.inDate }}</td>
			<td>{{ value.outDate }}</td>
			<th>{{ value.grossWeight }}</th>
			<th>{{ value.tareWeight }}</th>
			<th>{{ value.netWeight }}</th>
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
    requirejs(['${ctx}/assets/js/app/admin/bill-manage-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/bill-manage-page.js']);
    </c:if>
</script>
