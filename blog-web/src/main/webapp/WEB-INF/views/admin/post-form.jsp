<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<h2 style="font-size: 23px;font-weight: 400;padding: 9px 15px 4px 0;line-height: 29px;margin-top: 0;">
    ${empty post.id?"撰写新文章":'编辑文章<a class="btn-link btn-sm" href="./form" class="btn-link"><span class="glyphicon glyphicon-edit"></span>写文章</a>'}
</h2>
<form id="post-form" class="form-horizontal" role="form" style="padding-left: 10px;">
    <div class="col-sm-9">
        <input type="hidden" name="id" value="${post.id }"/>
        <input type="hidden" name="author.id" value="${post.author.id }"/>

        <div class="form-group">
            <input type="text" class="form-control required" id="postTitle" name="postTitle" value="${post.postTitle }"
                   placeholder="在此填写标题" style="font-weight: 500;font-size: 18px; height: 42px;" title="还没有写标题呢~^_^">
        </div>
        <div class="form-group">
            <input type="hidden" id="postContent" name="postContent"/>
            <script id="editor" type="text/plain" style="height:500px;">${post.postContent}</script>
        </div>
    </div>
    <div class="col-sm-3" style="padding-left: 25px;">
        <div class="error alert alert-danger" style="display:none;"></div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">发布</h3>
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <label class="col-sm-3 control-label">文章状态</label>
                    <div class="col-sm-5">
                        <select class="form-control input-sm" name="postStatus">
                            <c:forEach items="${fns:getDictList('post_status')}" var="dict">
                                <option value="${dict.value}" ${post.postStatus == dict.value?"selected":""}>${dict.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">评论状态</label>
                    <div class="col-sm-9">
                        <c:forEach items="${fns:getDictList('comment_status')}" var="dict">
                            <input type="radio" name="commentStatus" value="${dict.value}" ${post.commentStatus == dict.value?"checked":""}> ${dict.label}
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="panel-footer">
                <button id="post-submit" class="btn btn-primary btn-sm pull-right" type="button">保 存</button>
                <button id="post-preview" class="btn btn-default btn-sm" type="button">预览</button>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">分类</h3>
            </div>
            <div class="panel-body">
                Panel content
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">标签</h3>
            </div>
            <div class="panel-body">
                <div class="input-group">
                    <input type="hidden" name="postTags" id="post-tags" value="${post.termNames}">
                    <input type="text" class="form-control" id="post-tag">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button" id="post-tag-add" >添加</button>
                    </span>
                </div>
                <p class="help-block">多个标签请用英文逗号（,）分开</p>
                <div id="post-tags-check" class="row col-sm-12">
                    <c:forEach items="${post.termList}" var="term">
                        <span><a class="btn-link tag-del"><span class="glyphicon glyphicon-remove-sign"></span></a><span class="tag-name">${term.name}</span></span>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/post-form-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/admin/post-form-page.js']);
    </c:if>
</script>
