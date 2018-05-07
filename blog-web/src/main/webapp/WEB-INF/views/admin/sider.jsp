<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="sidebar-scroll" style="overflow: hidden; outline: none;">
	<div class="nav-collapse" id="sidebar" style="height: 0px;">
         <!-- BEGIN SIDEBAR MENU -->
          <ul class="sidebar-menu">
              <li class="sub-menu" id="admin-bill">
                  <a class="" href="javascript:;">
                      <i class="icon-user"></i>
                      <span>磅单</span>
                      <span class="arrow"></span>
                  </a>
                  <ul class="sub">
                      <li id="admin-bill-manage"><a href="${ctx }/admin/bill/manage" >所有磅单</a></li>
                  </ul>
              </li>
              <%--<li class="sub-menu" id="admin-post">--%>
                  <%--<a class="" href="javascript:;">--%>
                      <%--<i class="icon-user"></i>--%>
                      <%--<span>文章</span>--%>
                      <%--<span class="arrow"></span>--%>
                  <%--</a>--%>
                  <%--<ul class="sub">--%>
                      <%--<li id="admin-post-manage"><a href="${ctx }/admin/post/manage" >所有文章</a></li>--%>
                      <%--<li id="admin-post-form"><a href="${ctx }/admin/post/form" >写文章</a></li>--%>
                      <%--<li id="admin-post-category"><a href="${ctx }/admin/post/category" >分类</a></li>--%>
                      <%--<li id="admin-post-tags"><a href="${ctx }/admin/post/tags" >标签</a></li>--%>
                  <%--</ul>--%>
              <%--</li>--%>
		  	  <li class="sub-menu" id="admin-user">
                  <a class="" href="javascript:;">
                      <i class="icon-dashboard"></i>
                      <span>用户</span>
                      <span class="arrow"></span>
                  </a>
                  <ul class="sub">
                      <li id="admin-user-manage"><a href="${ctx }/admin/user/manage" >所有用户</a></li>
                      <li id="admin-user-form"><a href="${ctx }/admin/user/form" >添加用户</a></li>
                      <li id="admin-user-profile"><a href="${ctx }/admin/profile" >个人资料</a></li>
                  </ul>
              </li>
              <li class="sub-menu" id="admin-system">
                  <a class="" href="javascript:;">
                      <i class="icon-food"></i>
                      <span>设置</span>
                      <span class="arrow"></span>
                  </a>
                  <ul class="sub">
                      <li id="admin-office-manage"><a href="${ctx }/admin/office/manage">组织机构</a></li>
                      <li id="admin-dict-manage"><a href="${ctx }/admin/dict/manage">字典</a></li>
                  </ul>
              </li>

          </ul>
         <!-- END SIDEBAR MENU -->
      </div>
</div>
