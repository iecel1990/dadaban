<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <title><sitemesh:title/> - 搭搭伴</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="${ctx}/static/v/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/v/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/v/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/v/assets/plugins/select2/select2_metro.css" />
    <!-- END PAGE LEVEL SCRIPTS -->
    <!-- BEGIN THEME STYLES -->
    <link href="${ctx}/static/v/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/v/assets/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/v/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/v/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/v/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${ctx}/static/v/assets/css/pages/login-soft.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/v/assets/css/custom.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="login">


<sitemesh:body/>

<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
