<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h4>添加字典</h4>
<div class="error alert alert-danger" style="display:none;">
</div>
<form id="dict-form" class="form-horizontal well">
	<input type="hidden" name="id" value="${dict.id }"/>
	<input type="hidden" name="delFlag" value="${dict.delFlag }"/>
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label"><span class="text-fill">*</span>标签名</label>
		<div class="col-sm-3">
			<input type="text" class="form-control input-sm required" id="label" name="label" value="${dict.label }" title="标签名——必须填写">
		</div>
	</div>
	<div class="form-group">
		<label for="grade" class="col-sm-2 control-label"><span class="text-fill">*</span>数据值</label>
		<div class="col-sm-3">
			<input type="text" class="form-control input-sm required" id="value" name="value" value="${dict.value }" title="数据值——必须填写">
		</div>
	</div>
	<div class="form-group">
		<label for="grade" class="col-sm-2 control-label"><span class="text-fill">*</span>类型</label>
		<div class="col-sm-3">
			<input type="text" class="form-control input-sm required" id="type" name="type" value="${dict.type }" title="类型——必须填写">
		</div>
	</div>
	<div class="form-group">
		<label for="username" class="col-sm-2 control-label"><span class="text-fill">*</span>排序</label>
		<div class="col-sm-3">
			<input type="text" class="form-control input-sm required abc" id="sort" name="sort" value="${dict.sort }" title="排序——必须填写，数字">
		</div>
	</div>
	<div class="form-group">
		<label for="username" class="col-sm-2 control-label">描述</label>
		<div class="col-sm-5">
			<textarea id="description" name="description" rows="5" class="form-control input-sm">${dict.description }</textarea>
		</div>
	</div>
	<div class="form-group">
		<label for="username" class="col-sm-2 control-label">附注</label>
		<div class="col-sm-5">
			<textarea id="description" name="remarks" rows="7" class="form-control input-sm">${dict.remarks }</textarea>
		</div>
	</div>
</form>
<div>
	<shiro:hasPermission name="sys:menu:edit">
		<button id="dict-submit" class="btn btn-primary btn-sm" type="button">保 存</button>
		<button id="goon-dict-submit" class="btn btn-warning btn-sm" type="button">保存后继续</button>
	</shiro:hasPermission>
	<a href="${ctx }/admin/dict/manage" class="btn btn-link btn-sm"><span class="glyphicon glyphicon-link"></span>返 回</a>
</div>

<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/dict-form-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/dict-form-page.js']);
    </c:if>
</script>
