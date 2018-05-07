<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="language" value="${pageContext.response.locale.language}"/>

<!-- Fixed navbar -->
<div class="navbar navbar-default navbar-fixed-top" style="border-radius: 0; margin-bottom: 0;">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a href="${ctx }/" class="navbar-brand" target="_blank"><span class="glyphicon glyphicon-home"></span>行云物料验收管理监控平台</a>
    </div>
    <div class="navbar-collapse collapse" style="padding-left:0;">
      <ul class="nav navbar-nav navbar-right">
      	<li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img class="img-rounded" width="32" src="${ctx}/assets/img/avatar.png" />
                  <shiro:principal property="name" /><b class="caret"></b>
              </a>
              <ul class="dropdown-menu">
                <li><a href=""><i class="icon-book"></i>帮助</a></li>
                <li><a href="${ctx }/logout" title="退出"><i class="icon-key"></i>退出</a></li>
              </ul>
         </li>
      </ul>
    </div><!--/.nav-collapse -->
</div>
