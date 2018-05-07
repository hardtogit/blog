<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="col-sm-8 blog-main">

    <div class="blog-post">
        <div class="bdsharebuttonbox pull-right">
            <a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
            <a href="#" class="bds_douban" data-cmd="douban" title="分享到豆瓣"></a>
            <a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a>
            <a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a>
        </div>
        <h2 class="blog-post-title"><a href="${ctx}/p?p=${post.id}">${post.postTitle}</a></h2>
        <p class="blog-post-meta">
            <a href="#">${post.author.name}</a>
            <span> 发布于 </span><span class="glyphicon glyphicon-time"></span>
            <span class="timeago" title='<fmt:formatDate value="${post.postDate}" pattern="yyyy-MM-dd HH:mm:ss"/>'></span>
            <a class="btn-link" href="#comment-form"><span class="glyphicon glyphicon-comment"></span>评论（${post.commentCount}）</a>
            <shiro:user>
                <a class="btn-link" href="${ctx}/admin/post/form?id=${post.id}"><span class="glyphicon glyphicon-edit"></span>编辑</a>
            </shiro:user>
        </p>

        <div class="fmt-post-content">
            ${post.postContent}
        </div>
        <div class="tag-links">
            <c:forEach items="${post.termList}" var="term">
                <a class="btn-link" href="${ctx}/?tag=${tag}#">${term.name}</a>
            </c:forEach>
        </div>
    </div><!-- /.blog-post -->


    所有评论（${post.commentCount}）
    <hr>
    <div id="comment-list"></div>

    <c:if test="${post.commentStatus == 'open'}" >
    发表评论
    <hr>
    <div class="error alert alert-danger" style="display:none;font-size: 12px;"></div>
    <form id="comment-form" role="form" class="well">
        <div id="comment-to-preview"></div>
        <input type="hidden" name="post.id" value="${post.id}">
        <input type="hidden" name="parent.id" id="comment-parent">
        <shiro:guest>
            <div class="form-group">
                <div class="row">
                <div class="col-sm-6">
                <label for="commentAuthor">姓名<span class="glyphicon glyphicon-asterisk required"></span></label>
                <input type="text" class="form-control required" id="commentAuthor" name="commentAuthor" title="哟，~留下大名如何">
                </div>
                <div class="col-sm-6">
                <label for="commentAuthorEmail">电子邮件<span class="glyphicon glyphicon-asterisk required"></span></label>
                <input type="email" class="form-control required" id="commentAuthorEmail" name="commentAuthorEmail" title="噢，~木有邮箱怎么行">
                </div>
                </div>
            </div>
        </shiro:guest>
        <div class="form-group">
            <div class="row">
                <div class="col-sm-6">
                    <label for="captcha">验证码<span class="glyphicon glyphicon-asterisk required"></span></label>
                    <div class="input-group">
                        <input type="text" class="form-control required" style="width:95%;" id="captcha" name="captcha" title="验证码，必须滴">
                            <span class="input-group-btn">
                                <img title="点击更换" id="img-captcha" style="height:32px;" class="img-captcha" src="${ctx }/servlet/captcha" />
                            </span>
                    </div>
                </div>
                <div class="col-sm-6">
                    <label for="commentAuthorUrl">网站</label>
                    <input type="password" class="form-control" id="commentAuthorUrl" name="commentAuthorUrl">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="commentContent">评论<span class="required">*</span></label>
            <textarea class="form-control required" rows="3" id="commentContent" name="commentContent" title="写点内容？"></textarea>
        </div>
        <div class="form-group">

        </div>
        <button type="button" class="btn btn-primary" id="comment-submit" >发表评论</button>
        <span class="help-block"><span class="glyphicon glyphicon-asterisk required"></span>表示必须输入</span>
    </form>
    </c:if>
</div>

<script type="text/html" id="comment-list-template">
    {{each result as value i}}
    <div class="media comment-meta" id="comment-{{ value.id }}">
        <a class="pull-left"><img class="img-thumbnail" width="45" src="${ctx}/assets/img/avatar.png"></a>
        <div class="media-body">
            <p class="media-heading"><a>{{ value.commentAuthor }}</a><span>{{ value.commentContent }}</span></p>
            <small>
                <span class="timeago text-muted" title='{{ value.commentDate }}'></span>
                <span><a class="btn-link comment-to" data='{"id":{{ value.id }}}'><span class="glyphicon glyphicon-share-alt"></span>回复</a></span>
            </small>
        </div>
    </div>
    {{/each}}
    {{#pagination }}
</script>

<script type="text/html" id="comment-meta">
    <div class="media comment-meta" id="comment-{{ id }}" style="border-bottom: 0">
        <a class="pull-left"><img class="img-thumbnail" width="35" src="${ctx}/assets/img/avatar.png"></a>
        <div class="media-body">
            <p class="media-heading"><a>{{ commentAuthor }}</a><span>{{ commentContent }}</span></p>
            <small>
                <span class="timeago text-muted" title='{{ commentDate }}'></span>
                <span><a class="btn-link comment-to" data='{"id":{{ id }}}'><span class="glyphicon glyphicon-share-alt"></span>回复</a></span>
            </small>
        </div>
    </div>
</script>

<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/app/blog/post-page.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/app/blog/post-page.js']);
    </c:if>
</script>
<script>
    window._bd_share_config={
        "common":{"bdSnsKey":{},"bdText":"","bdMini":"1","bdMiniList":false,"bdPic":"","bdStyle":"1","bdSize":"16",bdText : '${post.postShareText}',bdDesc : '${post.postTitle}'},
        "share":{},
        "image":{"viewList":["qzone","tsina","weixin","douban"],"viewText":"分享到：","viewSize":"24"}
    };
    with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
</script>
