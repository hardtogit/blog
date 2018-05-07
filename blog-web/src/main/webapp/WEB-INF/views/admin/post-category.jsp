<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h4>字典<a class="btn-link btn-sm pull-right" href="${ctx }/admin/dict/form" class="btn-link"><span class="glyphicon glyphicon-plus-sign"></span>添加字典</a></h4>
<form class="form-inline well" role="form" id="search-form">
	<div class="form-group">
		<label for="type">类型 :</label>
		<select class="form-control input-sm selectpicker" multiple data-width="300px" data-style="btn-sm" data-selected-text-format="count>2" data-size=10 name="type" >
			<option value=""></option>
			<c:forEach items="${dictTypes }" var="dictType">
				<option value="${dictType }">${dictType }</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group">
		<label for="description">描述:</label>
		<input class="form-control input-sm" name="description" size="30" type="text">
	</div>
	<button type="button" class="btn btn-primary btn-sm btn-search-submit">确认</button>
	<a href="${ctx }/admin/dict/manage" class="btn btn-default btn-sm">刷新</a>
</form>
<div id="list-data"></div>

<script id="list-template" type="text/html">
<table id="dict-table" class="table table-bordered table-condensed table-hover">
	<thead>
		<tr>
			<th style="width:15%;">标签名</th>
			<th style="width:20%;">数据值</th>
			<th style="width:10%;">类型</th>
			<th style="width:5%;text-align:center;">排序</th>
			<th style="width:20%;">描述</th>
			<th style="width:15%;">备注</th>
			<shiro:hasPermission name="sys:menu:edit"><th>操作</th></shiro:hasPermission>
		</tr>
	</thead>
	<tbody>
        {{each result as value i}}
		<tr>
			<td>{{ value.label }}</td>
			<td>{{ value.value }}</td>
			<td>{{ value.type }}</td>
			<td>{{ value.sort }}</td>
			<td>{{ value.description }}</td>
			<td>{{ value.remarks }}</td>
			<shiro:hasPermission name="sys:menu:edit">
			<td>
				<a class="btn-link" href="${ctx}/admin/dict/form?id={{ value.id }}">修改</a>
				<button type="button" class="btn-link dict-del" data='{"id":"{{ value.id }}"}'>删除</button>
			</td>
			</shiro:hasPermission>
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
    requirejs(['${ctx}/assets/js/app/admin/dict-manage-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/dict-manage-page.js']);
    </c:if>
</script>
