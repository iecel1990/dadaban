<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>任务管理</title>
    <link href="${ctx}/static/assets/css/pages/gallery.css" rel="stylesheet" type="text/css"/>

</head>

<body>
    <!-- BEGIN PAGE CONTAINER -->
    <div class="page-container">
        <!-- BEGIN CONTAINER -->
        <div class="container min-hight gallery-page margin-bottom-30">
            <div class="row">
                <c:forEach items="${events.content}" var="event">
                    <div class="col-md-3 col-sm-4 gallery-item event-item">
                        <div class="row">
                            <a data-rel="fancybox-button" title="Project Name" href="${ctx}/event/${event.id}" class="fancybox-button">
                                <img alt="" src="${ctx}/static/assets/img/works/img1.jpg" class="img-responsive" />
                                <div class="zoomix"><i class="fa fa-search"></i></div>
                            </a>
                        </div>
                        <div class="row name">
                            <div class="col-md-12">
                                ${event.name}
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-7">
                                <fmt:formatDate value="${event.starttime}" pattern="MM/dd EEEE HH:mm"></fmt:formatDate>
                            </div>
                            <div class="col-md-5">
                                3000人想去
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-7">
                                众艺羽毛球
                            </div>
                            <div class="col-md-5">
                                <a href="${ctx}/event/${event.id}">去看看</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <tags:pagination page="${events}" paginationSize="5"/>
        </div>
    </div>
</body>
</html>
