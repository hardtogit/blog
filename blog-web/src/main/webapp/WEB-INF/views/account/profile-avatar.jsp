<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="col-md-6 col-lg-8 col-xs-12">
	<p class="legend">修改头像</p>
	<form id="avatar-form" class="form-horizontal" style="margin-top: 20px;">
		<input type="hidden" id="id" name="id" value="${curUser.id }">
		<div class="form-group">
			<label for="coverId" class="col-lg-4 col-md-4 col-sm-2 col-xs-12 control-label">预览</label>
			<div class="col-sm-10 col-md-8 col-lg-6 col-xs-12">
				<input type="hidden" id="avatar" name="avatar" >
			    <div id="file-preview">
			    	<img class="img-thumbnail" src="${curUser.avatarUrl }" style="height:200px;">
			    </div>
		    </div>
		</div>
		<div class="form-group">
			<label for="coverId" class="col-lg-4 col-md-4 col-sm-2 col-xs-12 control-label"></label>
			<div class="col-sm-10 col-md-8 col-lg-6 col-xs-12">
				<span class="btn btn-success btn-sm fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>修改图片</span>
                </span>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-lg-offset-4 col-md-offset-4 col-sm-offset-2 col-xs-offset-2 col-sm-10 col-md-8 col-lg-6 col-xs-12">
				<button type="button" class="btn btn-primary btn-sm add-submit">保存</button>
			</div>
		</div>
	</form>
</div>
<div class="col-md-6 col-lg-4 col-xs-12">
	<p class="legend">说明</p>
	<ol>
	  <li>图案格式支持GIF、JPG、PNG；</li>
	  <li>图片大小小于1M；</li>
	</ol>
</div>	

<c:if test="${env == 'pro' }">
	<script type="text/javascript">
		seajs.use("${ctx}/assets/js/profile-avatar-page");
	</script>
</c:if>
<c:if test="${env != 'pro' }">
	<script type="text/javascript">
		seajs.use("${ctx}/assets/js/profile-avatar-page");
	</script>
</c:if>