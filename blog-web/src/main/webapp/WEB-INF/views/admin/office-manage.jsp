<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h4>用户<a href="${ctx }/admin/user/form" class="btn-link btn-sm pull-right"><span class="glyphicon glyphicon-plus-sign"></span>添加用户</a></h4>
<form class="form-inline well" role="form" id="search-form">
	<input type="hidden" name="s" value="20">
	<div class="form-group">
		<label for="delFlag">状态 :</label>
		<select class="form-control input-sm selectpicker" data-width="100px" data-style="btn-sm" data-size=10 id="delFlag" name="delFlag" >
			<option value="">全部</option>
            <c:forEach items="${fns:getDictList('del_flag_type')}" var="dict">
				<option value="${dict.value }">${dict.label }</option>
			</c:forEach>
		</select>
	</div>
	<div class="form-group">
		<label for="loginName">登录名:</label>
		<input class="form-control input-sm" id="loginName" name="loginName" size="30" type="text">
	</div>
    <div class="form-group">
        <label for="email">邮件地址:</label>
        <input class="form-control input-sm" id="email" name="email" size="30" type="text">
    </div>
	<button type="button" class="btn btn-primary btn-sm btn-search-submit">确认</button>
	<a href="${ctx }/admin/user/manage" class="btn btn-default btn-sm">刷新</a>
</form>
<div id="list-data"></div>

<script id="list-template" type="text/html">
<table id="user-table" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th>登录名</th>
			<th>姓名</th>
			<th width="20%;">邮箱</th>
			<th width="20%;">手机号</th>
			<th width="10%;">角色</th>
            <th width="10%;">状态</th>
			<th width="10%;">操作</th>
		</tr>
	</thead>
	<tbody>
    {{each result as value i}}
		<tr>
			<td><img class="img-rounded" width="22" src="{{ value.avatarUrl }}/50x50" style="margin-right: 5px;" >{{ value.loginName }}</td>
			<td>{{ value.name }}</td>
			<td>{{ value.email }}</td>
			<td>{{ value.phone }}</td>
			<td>{{ value.roleNames }}</td>
            <td>
                {{ if value.delFlag == 0 }}<label class="label label-info">正常</label>{{/if}}
                {{ if value.delFlag == 1 }}<label class="label label-default">失效</label>{{/if}}
                {{ if value.delFlag == 2 }}<label class="label label-warning">锁定</label>{{/if}}
            </td>
			<td>
                {{ if !result[i].admin }}
                    <a class="btn-link" href="${ctx}/admin/user/form?id={{ value.id }}"><span class="glyphicon glyphicon-edit"></span>编辑</a>
                    <a type="button" class="btn-link user-del" data='{"id":"{{ value.id }}"}'><span class="glyphicon glyphicon-trash"></span>删除</a>
                {{/if}}
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
    requirejs(['${ctx}/assets/js/app/admin/office-manage-page.js']);
</c:if>
<c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/office-manage-page.js']);
</c:if>
</script>
