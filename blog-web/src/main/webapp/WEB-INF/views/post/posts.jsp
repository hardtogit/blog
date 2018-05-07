<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="col-sm-8 blog-main">

    <c:forEach items="${page.result}" var="post" >
    <div class="blog-post">
        <h2 class="blog-post-title"><a href="${ctx}/p?p=${post.id}">${post.postTitle}</a></h2>
        <p class="blog-post-meta">
            <a href="#">${post.author.name}</a><span> 发布于 </span>
            <span class="glyphicon glyphicon-time"></span>
            <span class="timeago" title='<fmt:formatDate value="${post.postDate}" pattern="yyyy-MM-dd HH:mm:ss"/>'></span>
            <a class="btn-link" href="${ctx}/p?p=${post.id}#comment-form"><span class="glyphicon glyphicon-comment"></span>评论（${post.commentCount}）</a>
            <shiro:user>
            <a class="btn-link" href="${ctx}/admin/post/form?id=${post.id}"><span class="glyphicon glyphicon-edit"></span>编辑</a>
            </shiro:user>
        </p>

        <div class="fmt-post-content">
                ${post.postAbstract}
        </div>
        <div class="tag-links">
            <c:forEach items="${post.termList}" var="term">
                <a class="btn-link" href="${ctx}/?month=${month}&tag=${term.name}#">${term.name}</a>
            </c:forEach>
        </div>

    </div><!-- /.blog-post -->
    </c:forEach>

    <c:if test="${page.totalPages > 1}" >
    <ul class="pager">
        <c:if test="${page.hasPre}" >
            <li><a class="btn-link" href="${ctx}?pno=${page.pageNo-1}&month=${month}&tag=${tag}#"><</a></li>
        </c:if>
        <c:if test="${page.begin != 1}" >
            <li><a class="btn-link" href="${ctx}?pno=1&month=${month}&tag=${tag}#">1</a></li>
        </c:if>
        <c:forEach begin="${page.begin}" end="${page.end}" var="i" >
            <c:if test="${i == page.pageNo}" >
                <li class="active"><a class="btn-link" href="${ctx}?pno=${i}&month=${month}&tag=${tag}#">${i}</a></li>
            </c:if>
            <c:if test="${i != page.pageNo}" >
                <li><a class="btn-link" href="${ctx}?pno=${i}&month=${month}#&tag=${tag}">${i}</a></li>
            </c:if>
        </c:forEach>
        <c:if test="${page.end != page.totalPages}" >
            <li><a class="btn-link" href="${ctx}?pno=${page.totalPages}&month=${month}&tag=${tag}#">${page.totalPages}</a></li>
        </c:if>
        <c:if test="${page.hasNext}" >
            <li><a class="btn-link" href="${ctx}?pno=${page.pageNo+1}&month=${month}&tag=${tag}#">></a></li>
        </c:if>
    </ul>
    </c:if>

</div>

<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/app/post/posts-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/post/posts-page.js']);
    </c:if>
</script>
