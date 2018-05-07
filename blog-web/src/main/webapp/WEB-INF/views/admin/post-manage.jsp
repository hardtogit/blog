<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h4>所有文章<a class="btn-link btn-sm" href="${ctx }/admin/post/form" class="btn-link"><span class="glyphicon glyphicon-edit"></span>写文章</a></h4>
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
<table id="dict-table" class="table table-striped table-hover">
	<thead>
		<tr>
            <th style="width:3%;"><input type="checkbox" name="post[]" value="24"></th>
			<th style="width:42%;">标题</th>
			<th style="width:10%;">作者</th>
			<th style="width:10%;">分类</th>
			<th style="width:10%;">标签</th>
			<th style="width:10%;"><span class="glyphicon glyphicon-comment"></span></th>
            <th style="width:15%;">日期</th>
		</tr>
	</thead>
	<tbody>
        {{each result as value i}}
		<tr>
            <td><input type="checkbox" name="post[]" value="24"></td>
			<td class="row-title">
                <a class="btn-link" href="${ctx}/admin/post/form?id={{ value.id }}">{{ value.postTitle }}</a> -
                <c:forEach items="${fns:getDictList('post_status')}" var="dict">
                   {{if value.postStatus == '${dict.value}'}}${dict.label}{{/if}}
                </c:forEach>
                <div class="row-actions">
                    <a class="btn-link" href="${ctx}/admin/post/form?id={{ value.id }}">编辑</a>|
                    <a class="btn-link dict-del" data='{"id":"{{ value.id }}"}'>删除</a>|
                    <a class="btn-link" href="${ctx}/d?p={{ value.id }}">查看</a>
                </div>
            </td>
			<td>{{ value.author.name }}</td>
			<td></td>
			<td></td>
			<th>{{ value.commentCount }}</th>
			<td><span class="timeago" title="{{ value.postDate }}" ></span></td>
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
    requirejs(['${ctx}/assets/js/app/admin/post-manage-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/post-manage-page.js']);
    </c:if>
</script>
