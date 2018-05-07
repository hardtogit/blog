<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
    <link rel="shortcut icon" href="${ctx}/assets/img/favicon.ico">

    <c:if test="${env == 'pro' }">
        <link rel='stylesheet' href="${ctx}/assets/css/bootstrap.min.css?v=${ver}" type="text/css" media='screen'/>
        <link rel='stylesheet' href="${ctx}/assets/css/font-awesome.min.css?v=${ver}" type="text/css" media='screen'/>
        <link rel='stylesheet' href="${ctx}/assets/css/default.css?v=${ver}" type="text/css" media='screen'/>
        <link rel='stylesheet' href="${ctx}/assets/css/bootstrap-select.min.css?v=${ver}" type="text/css" media='screen'/>
        <link rel='stylesheet' href="${ctx}/assets/js/lib/jquery-layer/skin/layer.css?v=${ver}" type="text/css" media='screen'/>
        <link rel='stylesheet' href="${ctx}/assets/js/lib/jquery-icheck/square/blue.css?v=${ver}" type="text/css" media='screen'/>
        <link rel='stylesheet' href="${ctx}/assets/css/jstree/style.min.css?v=${ver}" type="text/css" media='screen'/>
    </c:if>

    <c:if test="${env != 'pro' }">
        <link rel='stylesheet' href="${ctx}/assets/css/bootstrap.css?v=${ver}" type="text/css" media='screen'/>
        <link rel='stylesheet' href="${ctx}/assets/css/font-awesome.min.css?v=${ver}" rtype="text/css" media='screen'/>
        <link rel='stylesheet' href="${ctx}/assets/css/default.css?v=${ver}" rtype="text/css" media='screen'/>
        <link rel='stylesheet' href="${ctx}/assets/css/bootstrap-select.min.css?v=${ver}" type="text/css" media='screen'/>
        <link rel='stylesheet' href="${ctx}/assets/js/lib/jquery-layer/skin/layer.css?v=${ver}" type="text/css" media='screen'/>
        <link rel='stylesheet' href="${ctx}/assets/js/lib/jquery-icheck/square/blue.css?v=${ver}" type="text/css" media='screen'/>
        <link rel='stylesheet' href="${ctx}/assets/css/jstree/style.min.css?v=${ver}" type="text/css" media='screen'/>
    </c:if>

    <script type="text/javascript" src="${ctx}/assets/js/lib/require.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${ctx}/assets/js/html5shiv.js"></script>
    <script src="${ctx}/assets/js/respond.min.js"></script>
    <![endif]-->

    <script>
        requirejs.config({
            baseUrl: '${ctx}/assets/js/lib',
            paths: {
                'jquery': 'jquery/jquery-1.11.1.min',
                'jquery.validate': 'jquery-validation/jquery.validate.min',
                "jquery.layer": "jquery-layer/layer.min",
                "jquery.timeago": "jquery-timeago/jquery.timeago",
                "jquery.icheck": "jquery-icheck/icheck.min",
                "jquery.jstree": "jquery-jstree/jstree.min",
                'bootstrap': 'bootstrap/bootstrap.min',
                'bootstrap.select': 'bootstrap-select/bootstrap-select.min',
                'template': 'template/template',
                'ueditor': 'ueditor/ueditor.all.min',
                'echarts': 'echarts/echarts-map',
                'echarts/chart/bar': 'echarts/echarts-map',
                'echarts/chart/line': 'echarts/echarts-map',
                'echarts/chart/pie': 'echarts/echarts-map'
            },
            shim : {
                "bootstrap" : { "deps" :['jquery']},
                "bootstrap.select" : { "deps" :['jquery']},
                "jquery.timeago" : { "deps" :['jquery']},
                "jquery.icheck" : { "deps" :['jquery']},
                "jquery.layer": { "deps" :['jquery']},
                "jquery.jstree": { "deps" :['jquery']}
            }
        });

        $CONFIG = {
            base_url: '${ctx}',
            img_url: '${img_url}',
            env: '${env}',
            ver: '${ver}',
            office: '${curOffice.id}',
            uid: '<shiro:principal property="id"/>',
            uname: '<shiro:principal property="name"/>',
            psize: 50
        };

    </script>
</head>
<body>
<!-- Container begin -->
<tiles:insertAttribute name="header"/>
<div class="">
    <tiles:insertAttribute name="l-content"/>
    <div id="main-content">
        <tiles:insertAttribute name="r-content"/>
    </div>
</div>

<script type="text/javascript">
    <c:if test="${env == 'pro' }">
    requirejs(['${ctx}/assets/js/menu.js']);
    </c:if>
    <c:if test="${env != 'pro' }">
    requirejs(['${ctx}/assets/js/menu.js']);
    </c:if>
</script>
</body>
</html>
