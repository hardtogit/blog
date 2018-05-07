<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<div class="row">
<div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="panel-title">忘记密码</h3>
      </div>
      <div class="panel-body" style="min-height:450px;">
		<form id="forget-form" class="form-horizontal" action="${ctx}/forget" method="post">
			<div class="form-group" style="margin-top:100px;">
				<p class="text-center">请输入注册邮箱，我将发送找回密码邮件给你。</p>
			</div>
			<div class="form-group">
		     <label class="col-lg-offset-3 col-lg-1 control-label" for="username"><fmt:message key="forget.email" /></label>
		     <div class="col-lg-4">
		     	<input class="form-control required" type="email" id="email" name="email"  autocomplete="off" />
		     </div>
		    </div>
		    <div class="form-group">
		     <div class="col-lg-offset-4 col-lg-4">
		     	<input id="submit_btn" class="btn btn-primary" type="submit" value="<fmt:message key="forget.button.submit" />"/> 
		     </div>
		    </div>
			 
				
		 </form>
 	</div>
</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#email").focus();
		$("#forget-form").validate();
	});
</script>