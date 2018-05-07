<%@page import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<%@page import="java.io.PrintWriter,java.io.StringWriter" %>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%response.setStatus(200);%>
<%
	Throwable ex = null;
	if (exception != null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(ex.getMessage(), ex);
%>

<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
	<div class="" style="padding:20px 0px;">
		<h3 style="display:inline-block;margin-right:50px;"><img style="width:80px;" src="${ctx }/assets/img/error.gif">噢，出错了，谢谢你帮助我们发现了……</h3>
		<%-- <img alt="" src="${ctx }/assets/img/wrong.png"> --%>
		<p style="font-size:24px;display:inline-block;"><a href="javascript:" onclick="history.go(-1);" class="btn btn-link">返回上一页  <span class="glyphicon glyphicon-hand-right"></span></a></p>
	</div>
	<h4>错误信息：</h4>
	<hr style="margin:10px 0; border: 1px solid #317eac;">
	<p>ps：请将以下信息告诉管理员，^_^</p>
	<div>
		
		<%
			if (ex!=null){
				StringWriter sw=new StringWriter();
				PrintWriter pw=new PrintWriter(sw);
				ex.printStackTrace(pw);
				out.print(sw);
			}
		%>
		</p>
	</div>
</div>