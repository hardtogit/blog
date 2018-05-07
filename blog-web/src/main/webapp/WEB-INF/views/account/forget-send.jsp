<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<div class="row">
<div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="panel-title">邮件发送成功</h3>
      </div>
      <div class="panel-body text-center" style="min-height:450px; padding-top:100px;">
      	<h4><fmt:message key="forget.send.title" /></h4>
		<p><fmt:message key="forget.send.tips" /></p>
		<a class="btn btn-primary" style="margin-top:20px;" href="${ctx }/forget"><fmt:message key="forget.send.button.submit" /></a>
      </div>
</div>