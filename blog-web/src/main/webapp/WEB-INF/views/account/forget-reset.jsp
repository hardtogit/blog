<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="panel panel-default">
           <div class="panel-heading">
             <h3 class="panel-title">用户密码重置</h3>
           </div>
           <div class="panel-body">
				<form id="forget-reset-form" action="${ctx}/forget/reset" method="post" >
					<input type="hidden" name="uid" value="${user.id }">
					<div class="form-group col-lg-offset-4 col-md-offset-4 col-sm-offset-2 col-xs-offset-2">
					<h4>
						<h4><fmt:message key="forget.reset.tips.title" /></h4>
					</h4>
						<p><fmt:message key="forget.reset.tips.content" /></p>
					</div>
					<div class="form-group col-lg-offset-4 col-md-offset-4 col-sm-offset-2 col-xs-offset-2">
						<label for="username" class="control-label"><fmt:message key="forget.reset.password" /></label>
						<input type="password" class="form-control required" style="width:50%;" id="password" name="password">
					</div>
					<div class="form-group col-lg-offset-4 col-md-offset-4 col-sm-offset-2 col-xs-offset-2">
						<label for="password" class="control-label"><fmt:message key="forget.reset.confirmPassword" /></label><br>
						<input type="password" class="form-control required" style="width:50%;display:inline;" id="confirmPassword" name="confirmPassword">					
					</div>
					<div class="form-group col-lg-offset-4 col-md-offset-4 col-sm-offset-2 col-xs-offset-2">
						<button type="submit" class="btn btn-primary"><fmt:message key="forget.reset.button.submit" /></button>
					</div>
				</form>
			</div>
         </div>		
	</div>
</div>

<script>
	$(document).ready(function() {
		//聚焦第一个输入框
		$("#password").focus();
		//为inputForm注册validate函数
		$("#forget-reset-form").validate({
			rules : {
				password : {
					required : false
				}
			}
		});
		$("#password").valid();
	});
</script>