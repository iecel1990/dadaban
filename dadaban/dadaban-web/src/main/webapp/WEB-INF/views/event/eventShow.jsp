<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<html>
<head>
    <title>${event.name}</title>
    <link href="${ctx}/static/assets/css/pages/gallery.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<!-- BEGIN PAGE CONTAINER -->
<div class="page-container">

<!-- BEGIN BREADCRUMBS -->
<div class="row breadcrumbs margin-bottom-40">
    <div class="container">
        <div class="col-md-4 col-sm-4">
            <h1>Portfolio Item</h1>
        </div>
        <div class="col-md-8 col-sm-8">
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">Pages</a></li>
                <li class="active">Portfolio Item</li>
            </ul>
        </div>
    </div>
</div>
<!-- END BREADCRUMBS -->

<!-- BEGIN CONTAINER -->
<div class="container min-hight portfolio-page margin-bottom-30">
<!-- BEGIN PORTFOLIO ITEM -->
<div class="row">
    <!-- BEGIN CAROUSEL -->
    <div class="col-md-5 front-carousel">
        <div id="myCarousel" class="carousel slide">
            <!-- Carousel items -->
            <div class="carousel-inner">
                <c:forEach items="${focusImgs}" var="focusImg" varStatus="status">
                    <div class="item ${status.index eq 0 ? 'active' : ''}">
                        <img src="${ctx}/${focusImg.filePath}${focusImg.fileName}" style="width: 462px;height:346px;">
                        <div class="carousel-caption">
                            <p>Excepturi sint occaecati cupiditate non provident</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <!-- Carousel nav -->
            <a class="carousel-control left" href="#myCarousel" data-slide="prev">
                <i class="fa fa-angle-left"></i>
            </a>
            <a class="carousel-control right" href="#myCarousel" data-slide="next">
                <i class="fa fa-angle-right"></i>
            </a>
        </div>
    </div>
    <!-- END CAROUSEL -->

    <!-- BEGIN PORTFOLIO DESCRIPTION -->
    <div class="col-md-7">
        <h2>${event.name}</h2>

        <div class="row front-lists-v2 margin-bottom-15">

            <ul class="list-unstyled">
                <li><i class="fa fa-clock-o"></i>
                    <fmt:formatDate value="${event.starttime}" pattern="YYYY年MM月dd日 HH:mm:ss"></fmt:formatDate> ~
                    <fmt:formatDate value="${event.endtime}" pattern="YYYY年MM月dd日 HH:mm:ss"></fmt:formatDate>
                </li>
                <li><i class="fa fa-map-marker"></i>${event.address}</li>
                <li><i class="fa fa-user"></i>限制${event.limitNum}人</li>
            </ul>
        </div>
        <div class="row margin-bottom-10">
            <c:forEach items="${tickets}" var="ticket">
                <button title = "${ticket.remark}" type="button" class="btn btn-default">￥${ticket.price} ${ticket.name}</button>
            </c:forEach>
        </div>
        <div class="row">
            <a href="#" class="btn theme-btn">我要报名</a>
        </div>
    </div>
    <!-- END PORTFOLIO DESCRIPTION -->
</div>
<!-- END PORTFOLIO ITEM -->

<!-- BEGIN BLOCKQUOTE BLOCK -->
<div class="row margin-top-20">
    <div class="col-md-9">
        <div class="row">
            <ul class="nav nav-tabs">
                <c:forEach items="${contents}" var="content" varStatus="contentStatus">
                    <li class="${contentStatus.index == '0' ? 'active' : ''}">
                        <a href="#tab-${contentStatus.index}" data-toggle="tab">${content.name}</a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="row">
            <div class="tab-content">
                <c:forEach items="${contents}" var="content" varStatus="contentStatus">
                    <div class="tab-pane row fade in ${contentStatus.index == '0' ? 'active' : ''}" id="tab-${contentStatus.index}">
                        <div class="col-md-0">
                            </div>
                        <div class="col-md-12">
                            ${content.content}
                        </div>
                    </div>
                </c:forEach>


            </div>
        </div>
        <div class="row text-center margin-bottom-20">
            <button type="button" class="btn btn-success">分享到微信</button>
            <button type="button" class="btn btn-info">想去 (90)</button>
        </div>
        <div class="row">
            <div class="col-md-3 col-sm-4 gallery-item">
                <a data-rel="fancybox-button" title="Project Name" href="${ctx}/static/assets/img/works/img6.jpg" class="fancybox-button">
                    <img alt="" src="${ctx}/static/assets/img/works/img6.jpg" class="img-responsive">
                    <div class="zoomix"><i class="fa fa-search"></i></div>
                </a>
                <h6>what's the fuck</h6>
            </div>
            <div class="col-md-3 col-sm-4 gallery-item">
                <a data-rel="fancybox-button" title="Project Name" href="${ctx}/static/assets/img/works/img1.jpg" class="fancybox-button">
                    <img alt="" src="${ctx}/static/assets/img/works/img1.jpg" class="img-responsive">
                    <div class="zoomix"><i class="fa fa-search"></i></div>
                </a>
                <h6>what's the fuck</h6>
            </div>
            <div class="col-md-3 col-sm-4 gallery-item">
                <a data-rel="fancybox-button" title="Project Name" href="${ctx}/static/assets/img/works/img2.jpg" class="fancybox-button">
                    <img alt="" src="${ctx}/static/assets/img/works/img2.jpg" class="img-responsive">
                    <div class="zoomix"><i class="fa fa-search"></i></div>
                </a>
                <h6>what's the fuck</h6>
            </div>
            <div class="col-md-3 col-sm-4 gallery-item">
                <a data-rel="fancybox-button" title="Project Name" href="${ctx}/static/assets/img/works/img3.jpg" class="fancybox-button">
                    <img alt="" src="${ctx}/static/assets/img/works/img3.jpg" class="img-responsive">
                    <div class="zoomix"><i class="fa fa-search"></i></div>
                </a>
                <h6>what's the fuck</h6>
            </div>
            <div class="col-md-3 col-sm-4 gallery-item">
                <a data-rel="fancybox-button" title="Project Name" href="${ctx}/static/assets/img/works/img4.jpg" class="fancybox-button">
                    <img alt="" src="${ctx}/static/assets/img/works/img4.jpg" class="img-responsive">
                    <div class="zoomix"><i class="fa fa-search"></i></div>
                </a>
            </div>
            <div class="col-md-3 col-sm-4 gallery-item">
                <a data-rel="fancybox-button" title="Project Name" href="${ctx}/static/assets/img/works/img5.jpg" class="fancybox-button">
                    <img alt="" src="${ctx}/static/assets/img/works/img5.jpg" class="img-responsive">
                    <div class="zoomix"><i class="fa fa-search"></i></div>
                </a>
            </div>
            <div class="col-md-3 col-sm-4 gallery-item">
                <a data-rel="fancybox-button" title="Project Name" href="${ctx}/static/assets/img/works/img2.jpg" class="fancybox-button">
                    <img alt="" src="${ctx}/static/assets/img/works/img2.jpg" class="img-responsive">
                    <div class="zoomix"><i class="fa fa-search"></i></div>
                </a>
            </div>
            <div class="col-md-3 col-sm-4 gallery-item">
                <a data-rel="fancybox-button" title="Project Name" href="${ctx}/static/assets/img/works/img6.jpg" class="fancybox-button">
                    <img alt="" src="${ctx}/static/assets/img/works/img6.jpg" class="img-responsive">
                    <div class="zoomix"><i class="fa fa-search"></i></div>
                </a>
            </div>
        </div>
        <div class="row blog-item">
            <div class="media">
                <h3>Comments</h3>
                <a href="#" class="pull-left">
                    <img src="${ctx}/static/assets/img/people/img1-small.jpg" alt="" class="media-object">
                </a>
                <div class="media-body">
                    <h4 class="media-heading">Media heading <span>5 hours ago / <a href="#">Reply</a></span></h4>
                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                    <hr>
                    <!-- Nested media object -->
                    <div class="media">
                        <a href="#" class="pull-left">
                            <img src="${ctx}/static/assets/img/people/img2-small.jpg" alt="" class="media-object">
                        </a>
                        <div class="media-body">
                            <h4 class="media-heading">Media heading <span>17 hours ago / <a href="#">Reply</a></span></h4>
                            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                        </div>
                    </div>
                    <!--end media-->
                    <hr>
                    <div class="media">
                        <a href="#" class="pull-left">
                            <img src="${ctx}/static/assets/img/people/img3-small.jpg" alt="" class="media-object">
                        </a>
                        <div class="media-body">
                            <h4 class="media-heading">Media heading <span>2 days ago / <a href="#">Reply</a></span></h4>
                            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                        </div>
                    </div>
                    <!--end media-->
                </div>
            </div>
        </div>
        <div class="row text-centergs">
            <ul class="pagination">
                <li><a href="#">Prev</a></li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li class="active"><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">Next</a></li>
            </ul>
        </div>
        <div class="row">
            <div class="post-comment">
                <h3>Leave a Comment</h3>
                <form role="form">
                    <div class="form-group">
                        <label>Message</label>
                        <textarea class="form-control" rows="8"></textarea>
                    </div>
                    <p class="text-right"><button class="btn btn-default theme-btn" type="submit">Post a Comment</button></p>
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="blog-tags margin-bottom-20">
            <h2>Tags</h2>
            <ul>
                <li><a href="#"><i class="fa fa-tags"></i>OS</a></li>
                <li><a href="#"><i class="fa fa-tags"></i>Metronic</a></li>
                <li><a href="#"><i class="fa fa-tags"></i>Dell</a></li>
                <li><a href="#"><i class="fa fa-tags"></i>Conquer</a></li>
                <li><a href="#"><i class="fa fa-tags"></i>MS</a></li>
                <li><a href="#"><i class="fa fa-tags"></i>Google</a></li>
                <li><a href="#"><i class="fa fa-tags"></i>Keenthemes</a></li>
                <li><a href="#"><i class="fa fa-tags"></i>Twitter</a></li>
            </ul>
            <div class="blog-photo-stream margin-bottom-20">
                <h2>Photos Stream</h2>
                <ul class="list-unstyled">
                    <li><a href="#"><img src="${ctx}/static/assets/img/people/img5-small.jpg" alt=""></a></li>
                    <li><a href="#"><img src="${ctx}/static/assets/img/works/img1.jpg" alt=""></a></li>
                    <li><a href="#"><img src="${ctx}/static/assets/img/people/img4-large.jpg" alt=""></a></li>
                    <li><a href="#"><img src="${ctx}/static/assets/img/works/img6.jpg" alt=""></a></li>
                    <li><a href="#"><img src="${ctx}/static/assets/img/pics/img1-large.jpg" alt=""></a></li>
                    <li><a href="#"><img src="${ctx}/static/assets/img/pics/img2-large.jpg" alt=""></a></li>
                    <li><a href="#"><img src="${ctx}/static/assets/img/works/img3.jpg" alt=""></a></li>
                    <li><a href="#"><img src="${ctx}/static/assets/img/people/img2-large.jpg" alt=""></a></li>
                </ul>
                <h6>h4. Heading 4</h6>
            </div>
        </div>
    </div>
</div>


<div class="clearfix"></div>


</div>
<!-- END CONTAINER -->

</div>
<!-- END PAGE CONTAINER -->
</body>
</html>
