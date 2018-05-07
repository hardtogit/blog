<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<div class="row">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 well">
	<p class="legend"><fmt:message key="regist.active.title" /></p>
	<div class="simple-form">
	<c:if test="${!empty error}">
		<h4><fmt:message key="regist.active.failed" /></h4>
		<%-- <p>Sorry! ${error } </p> --%>
		<p><a class="btn btn-primary" href="${ctx }/regist"><fmt:message key="regist.active.button.regist" /></a></p>
	</c:if>
	<c:if test="${!empty success}">
		<h4><fmt:message key="regist.active.success" /></h4>
		<%-- <p>Congratulation! ${success }</p> --%>
		<p><a class="btn btn-primary" href="${ctx }/login"><fmt:message key="regist.active.button.login" /></a></p>
	</c:if>
	</div>
</div>
</div>