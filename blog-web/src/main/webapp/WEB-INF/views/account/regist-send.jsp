<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<div class="row">
	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">注册成功</h3>
			</div>
			<div class="panel-body">
				<br> <br>
				<h4>
					<fmt:message key="regist.send.title" />
				</h4>
				<p>
					<fmt:message key="regist.send.tips" />
				</p>
			</div>
		</div>
	</div>
</div>