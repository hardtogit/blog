<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="language" value="${pageContext.response.locale.language}"/>

<div class="blog-header">
    <blockquote>
        <p>喜欢就会放肆，但爱就是克制。</p>
        <%--<footer>摘录自 <cite title="Source Title">Source Title</cite></footer>--%>
    </blockquote>
</div>
