<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" data-ng-app = "ddb-app"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <title><sitemesh:title/> - 搭搭伴</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />


    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="${ctx}/static/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
    <link href="${ctx}/static/assets/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/static/assets/plugins/revolution_slider/css/rs-style.css" media="screen">
    <link rel="stylesheet" href="${ctx}/static/assets/plugins/revolution_slider/rs-plugin/css/settings.css" media="screen">
    <link href="${ctx}/static/assets/plugins/bxslider/jquery.bxslider.css" rel="stylesheet" />
    <!-- END PAGE LEVEL PLUGIN STYLES -->

    <!-- BEGIN THEME STYLES -->
    <link href="${ctx}/static/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/assets/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/assets/css/themes/green.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${ctx}/static/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/assets/css/pages/portfolio.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/assets/css/pages/prices.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/assets/css/custom.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
    <!--[if lt IE 9]>
    <script src="${ctx}/static/assets/plugins/respond.min.js"></script>
    <![endif]-->
    <script src="${ctx}/static/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>


    <script>
        $ctx = "${ctx}";
    </script>

    <link rel="shortcut icon" href="favicon.ico" />
    <sitemesh:head/>
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="page-header-fixed">


<%@ include file="/WEB-INF/layouts/index/header.jsp"%>
<sitemesh:body/>
<%@ include file="/WEB-INF/layouts/index/footer.jsp"%>

<script type="text/javascript">
    jQuery(document).ready(function() {
        App.init();
        App.initBxSlider();
        Index.initRevolutionSlider();
        Portfolio.init();
    });
</script>
<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
