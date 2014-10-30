<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>首页</title>
</head>
<body>
<!-- BEGIN PAGE CONTAINER -->
<div class="page-container">
<!-- BEGIN REVOLUTION SLIDER -->
<div class="fullwidthbanner-container slider-main">
<div class="fullwidthabnner">
<ul id="revolutionul" style="display:none;">
<!-- THE FIRST SLIDE -->
<li data-transition="fade" data-slotamount="8" data-masterspeed="700" data-delay="9400" data-thumb="${ctx}/static/assets/img/sliders/revolution/thumbs/thumb2.jpg">
    <!-- THE MAIN IMAGE IN THE FIRST SLIDE -->
    <img src="${ctx}/static/assets/img/sliders/revolution/bg1.jpg" alt="">

    <div class="caption lft slide_title slide_item_left"
         data-x="0"
         data-y="105"
         data-speed="400"
         data-start="1500"
         data-easing="easeOutExpo">
        Need a website design ?
    </div>
    <div class="caption lft slide_subtitle slide_item_left"
         data-x="0"
         data-y="180"
         data-speed="400"
         data-start="2000"
         data-easing="easeOutExpo">
        This is what you were looking for
    </div>
    <div class="caption lft slide_desc slide_item_left"
         data-x="0"
         data-y="220"
         data-speed="400"
         data-start="2500"
         data-easing="easeOutExpo">
        Lorem ipsum dolor sit amet, dolore eiusmod<br>
        quis tempor incididunt. Sed unde omnis iste.
    </div>
    <a class="caption lft btn green slide_btn slide_item_left" href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes"
       data-x="0"
       data-y="290"
       data-speed="400"
       data-start="3000"
       data-easing="easeOutExpo">
        Purchase Now!
    </a>
    <div class="caption lfb"
         data-x="640"
         data-y="55"
         data-speed="700"
         data-start="1000"
         data-easing="easeOutExpo"  >
        <img src="${ctx}/static/assets/img/sliders/revolution/man-winner.png" alt="Image 1">
    </div>
</li>

<!-- THE SECOND SLIDE -->
<li data-transition="fade" data-slotamount="7" data-masterspeed="300" data-delay="9400" data-thumb="${ctx}/static/assets/img/sliders/revolution/thumbs/thumb2.jpg">
    <img src="${ctx}/static/assets/img/sliders/revolution/bg2.jpg" alt="">
    <div class="caption lfl slide_title slide_item_left"
         data-x="0"
         data-y="125"
         data-speed="400"
         data-start="3500"
         data-easing="easeOutExpo">
        Powerfull & Clean
    </div>
    <div class="caption lfl slide_subtitle slide_item_left"
         data-x="0"
         data-y="200"
         data-speed="400"
         data-start="4000"
         data-easing="easeOutExpo">
        Responsive Admin & Website Theme
    </div>
    <div class="caption lfl slide_desc slide_item_left"
         data-x="0"
         data-y="245"
         data-speed="400"
         data-start="4500"
         data-easing="easeOutExpo">
        Lorem ipsum dolor sit amet, consectetuer elit sed diam<br> nonummy amet euismod dolore.
    </div>
    <div class="caption lfr slide_item_right"
         data-x="635"
         data-y="105"
         data-speed="1200"
         data-start="1500"
         data-easing="easeOutBack">
        <img src="${ctx}/static/assets/img/sliders/revolution/mac.png" alt="Image 1">
    </div>
    <div class="caption lfr slide_item_right"
         data-x="580"
         data-y="245"
         data-speed="1200"
         data-start="2000"
         data-easing="easeOutBack">
        <img src="${ctx}/static/assets/img/sliders/revolution/ipad.png" alt="Image 1">
    </div>
    <div class="caption lfr slide_item_right"
         data-x="735"
         data-y="290"
         data-speed="1200"
         data-start="2500"
         data-easing="easeOutBack">
        <img src="${ctx}/static/assets/img/sliders/revolution/iphone.png" alt="Image 1">
    </div>
    <div class="caption lfr slide_item_right"
         data-x="835"
         data-y="230"
         data-speed="1200"
         data-start="3000"
         data-easing="easeOutBack">
        <img src="${ctx}/static/assets/img/sliders/revolution/macbook.png" alt="Image 1">
    </div>
    <div class="caption lft slide_item_right"
         data-x="865"
         data-y="45"
         data-speed="500"
         data-start="5000"
         data-easing="easeOutBack">
        <img src="${ctx}/static/assets/img/sliders/revolution/hint1-blue.png" id="rev-hint1" alt="Image 1">
    </div>
    <div class="caption lfb slide_item_right"
         data-x="355"
         data-y="355"
         data-speed="500"
         data-start="5500"
         data-easing="easeOutBack">
        <img src="${ctx}/static/assets/img/sliders/revolution/hint2-blue.png" id="rev-hint2" alt="Image 1">
    </div>

</li>

<!-- THE THIRD SLIDE -->
<li data-transition="fade" data-slotamount="8" data-masterspeed="700" data-delay="9400" data-thumb="${ctx}/static/assets/img/sliders/revolution/thumbs/thumb2.jpg">
    <img src="${ctx}/static/assets/img/sliders/revolution/bg3.jpg" alt="">
    <div class="caption lfl slide_item_left"
         data-x="20"
         data-y="95"
         data-speed="400"
         data-start="1500"
         data-easing="easeOutBack">
        <iframe src="http://player.vimeo.com/video/56974716?portrait=0" width="420" height="240" style="border:0" allowFullScreen></iframe>
    </div>
    <div class="caption lfr slide_title"
         data-x="470"
         data-y="100"
         data-speed="400"
         data-start="2000"
         data-easing="easeOutExpo">
        Responsive Video Support
    </div>
    <div class="caption lfr slide_subtitle"
         data-x="470"
         data-y="170"
         data-speed="400"
         data-start="2500"
         data-easing="easeOutExpo">
        Youtube, Vimeo and others.
    </div>
    <div class="caption lfr slide_desc"
         data-x="470"
         data-y="220"
         data-speed="400"
         data-start="3000"
         data-easing="easeOutExpo">
        Lorem ipsum dolor sit amet, consectetuer elit sed diam<br> nonummy amet euismod dolore.
    </div>
    <a class="caption lfr btn yellow slide_btn" href=""
       data-x="470"
       data-y="280"
       data-speed="400"
       data-start="3500"
       data-easing="easeOutExpo">
        Watch more Videos!
    </a>
</li>

<!-- THE FORTH SLIDE -->
<li data-transition="fade" data-slotamount="8" data-masterspeed="700" data-delay="9400" data-thumb="${ctx}/static/assets/img/sliders/revolution/thumbs/thumb2.jpg">
    <!-- THE MAIN IMAGE IN THE FIRST SLIDE -->
    <img src="${ctx}/static/assets/img/sliders/revolution/bg4.jpg" alt="">
    <div class="caption lft slide_title"
         data-x="0"
         data-y="105"
         data-speed="400"
         data-start="1500"
         data-easing="easeOutExpo">
        What else included ?
    </div>
    <div class="caption lft slide_subtitle"
         data-x="0"
         data-y="180"
         data-speed="400"
         data-start="2000"
         data-easing="easeOutExpo">
        The Most Complete Admin Theme
    </div>
    <div class="caption lft slide_desc"
         data-x="0"
         data-y="225"
         data-speed="400"
         data-start="2500"
         data-easing="easeOutExpo">
        Lorem ipsum dolor sit amet, consectetuer elit sed diam<br> nonummy amet euismod dolore.
    </div>
    <a class="caption lft slide_btn btn red slide_item_left" href="http://www.keenthemes.com/preview/index.php?theme=metronic_admin" target="_blank"
       data-x="0"
       data-y="300"
       data-speed="400"
       data-start="3000"
       data-easing="easeOutExpo">
        Learn More!
    </a>
    <div class="caption lft start"
         data-x="670"
         data-y="55"
         data-speed="400"
         data-start="2000"
         data-easing="easeOutBack"  >
        <img src="${ctx}/static/assets/img/sliders/revolution/iphone_left.png" alt="Image 2">
    </div>

    <div class="caption lft start"
         data-x="850"
         data-y="55"
         data-speed="400"
         data-start="2400"
         data-easing="easeOutBack"  >
        <img src="${ctx}/static/assets/img/sliders/revolution/iphone_right.png" alt="Image 3">
    </div>
</li>
</ul>
<div class="tp-bannertimer tp-bottom"></div>
</div>
</div>
<!-- END REVOLUTION SLIDER -->

<!-- BEGIN CONTAINER -->
<div class="container">
    <!-- BEGIN FILTER -->
    <div class="filter-v1 margin-top-10">
        <ul class="mix-filter">
            <li class="filter" data-filter="all">全部</li>
            <li class="filter" data-filter="category_1">羽毛球<li>
            <li class="filter" data-filter="category_2">乒乓球</li>
            <li class="filter" data-filter="category_3">骑行</li>
            <li class="filter" data-filter="category_3 category_1">跑步</li>
        </ul>
        <div class="row mix-grid thumbnails">
            <div class="col-md-4 col-sm-6 mix category_1">
                <div class="mix-inner">
                    <img class="img-responsive" src="${ctx}/static/assets/img/works/img1.jpg" alt="">
                    <div class="mix-details">
                        <h4>Cascusamus et iusto odio</h4>
                        <p>At vero eos et accusamus et iusto odio digniss imos duc sasdimus qui sint blanditiis prae sentium voluptatum deleniti atque corrupti quos dolores.</p>
                        <a class="mix-link"><i class="fa fa-link"></i></a>
                        <a class="mix-preview fancybox-button" href="${ctx}/static/assets/img/works/img1.jpg" title="Project Name" data-rel="fancybox-button"><i class="fa fa-search"></i></a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-6 mix category_2">
                <div class="mix-inner">
                    <img class="img-responsive" src="${ctx}/static/assets/img/works/img2.jpg" alt="">
                    <div class="mix-details">
                        <h4>Cascusamus et iusto odio</h4>
                        <p>At vero eos et accusamus et iusto odio digniss imos duc sasdimus qui sint blanditiis prae sentium voluptatum deleniti atque corrupti quos dolores.</p>
                        <a class="mix-link"><i class="fa fa-link"></i></a>
                        <a class="mix-preview fancybox-button" href="${ctx}/static/assets/img/works/img2.jpg" title="Project Name" data-rel="fancybox-button"><i class="fa fa-search"></i></a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-6 mix category_3">
                <div class="mix-inner">
                    <img class="img-responsive" src="${ctx}/static/assets/img/works/img3.jpg" alt="">
                    <div class="mix-details">
                        <h4>Cascusamus et iusto odio</h4>
                        <p>At vero eos et accusamus et iusto odio digniss imos duc sasdimus qui sint blanditiis prae sentium voluptatum deleniti atque corrupti quos dolores.</p>
                        <a class="mix-link"><i class="fa fa-link"></i></a>
                        <a class="mix-preview fancybox-button" href="${ctx}/static/assets/img/works/img3.jpg" title="Project Name" data-rel="fancybox-button"><i class="fa fa-search"></i></a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-6 mix category_1 category_2">
                <div class="mix-inner">
                    <img class="img-responsive" src="${ctx}/static/assets/img/works/img4.jpg" alt="">
                    <div class="mix-details">
                        <h4>Cascusamus et iusto odio</h4>
                        <p>At vero eos et accusamus et iusto odio digniss imos duc sasdimus qui sint blanditiis prae sentium voluptatum deleniti atque corrupti quos dolores.</p>
                        <a class="mix-link"><i class="fa fa-link"></i></a>
                        <a class="mix-preview fancybox-button" href="${ctx}/static/assets/img/works/img4.jpg" title="Project Name" data-rel="fancybox-button"><i class="fa fa-search"></i></a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-6 mix category_2 category_1">
                <div class="mix-inner">
                    <img class="img-responsive" src="${ctx}/static/assets/img/works/img5.jpg" alt="">
                    <div class="mix-details">
                        <h4>Cascusamus et iusto odio</h4>
                        <p>At vero eos et accusamus et iusto odio digniss imos duc sasdimus qui sint blanditiis prae sentium voluptatum deleniti atque corrupti quos dolores.</p>
                        <a class="mix-link"><i class="fa fa-link"></i></a>
                        <a class="mix-preview fancybox-button" href="${ctx}/static/assets/img/works/img5.jpg" title="Project Name" data-rel="fancybox-button"><i class="fa fa-search"></i></a>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-6 mix category_1 category_2">
                <div class="mix-inner">
                    <img class="img-responsive" src="${ctx}/static/assets/img/works/img6.jpg" alt="">
                    <div class="mix-details">
                        <h4>Cascusamus et iusto odio</h4>
                        <p>At vero eos et accusamus et iusto odio digniss imos duc sasdimus qui sint blanditiis prae sentium voluptatum deleniti atque corrupti quos dolores.</p>
                        <a class="mix-link"><i class="fa fa-link"></i></a>
                        <a class="mix-preview fancybox-button" href="${ctx}/static/assets/img/works/img6.jpg" title="Project Name" data-rel="fancybox-button"><i class="fa fa-search"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END FILTER -->
</div>
<!-- END CONTAINER -->

<div class="container min-hight">
    <div class="row">
        <div class="col-md-3">
            <div class="pricing-table">
                <h3>Standard</h3>
                <div class="desc">
                    Duis mollis, est non commodo luctus, nisi erat
                    porttitor ligula, eget lacinia.
                </div>
                <ul>
                    <li><i class="icon-angle-right"></i> 100 Email Accounts</li>
                    <li><i class="icon-angle-right"></i> 100 User Accounts</li>
                    <li><i class="icon-angle-right"></i> 1 TB Storage</li>
                    <li><i class="icon-angle-right"></i> 24X7 Support</li>
                    <li><i class="icon-angle-right"></i> Full Backup</li>
                    <li><i class="icon-angle-right"></i> Free Setup</li>
                    <li>&nbsp;</li>
                </ul>
                <div class="rate">
                    <div class="price">
                        <div class="currency ">
                            $<br />
                            Monthly
                        </div>
                        <div class="amount ">
                            299
                        </div>
                    </div>
                    <a href="#" class="btn theme-btn">Sign Up</a>
                </div>
            </div>
        </div>
        <div class="spance10 visible-phone"></div>
        <div class="col-md-3">
            <div class="pricing-table">
                <h3>Standard</h3>
                <div class="desc">
                    Duis mollis, est non commodo luctus, nisi erat
                    porttitor ligula, eget lacinia.
                </div>
                <ul>
                    <li><i class="icon-angle-right"></i> 100 Email Accounts</li>
                    <li><i class="icon-angle-right"></i> 100 User Accounts</li>
                    <li><i class="icon-angle-right"></i> 1 TB Storage</li>
                    <li><i class="icon-angle-right"></i> 24X7 Support</li>
                    <li><i class="icon-angle-right"></i> Full Backup</li>
                    <li><i class="icon-angle-right"></i> Free Setup</li>
                    <li>&nbsp;</li>
                </ul>
                <div class="rate">
                    <div class="price">
                        <div class="currency ">
                            $<br />
                            Monthly
                        </div>
                        <div class="amount ">
                            299
                        </div>
                    </div>
                    <a href="#" class="btn theme-btn">Sign Up</a>
                </div>
            </div>
        </div>
        <div class="spance10 visible-phone"></div>
        <div class="col-md-3">
            <div class="pricing-table">
                <h3>Standard</h3>
                <div class="desc">
                    Duis mollis, est non commodo luctus, nisi erat
                    porttitor ligula, eget lacinia.
                </div>
                <ul>
                    <li><i class="icon-angle-right"></i> 100 Email Accounts</li>
                    <li><i class="icon-angle-right"></i> 100 User Accounts</li>
                    <li><i class="icon-angle-right"></i> 1 TB Storage</li>
                    <li><i class="icon-angle-right"></i> 24X7 Support</li>
                    <li><i class="icon-angle-right"></i> Full Backup</li>
                    <li><i class="icon-angle-right"></i> Free Setup</li>
                    <li>&nbsp;</li>
                </ul>
                <div class="rate">
                    <div class="price">
                        <div class="currency ">
                            $<br />
                            Monthly
                        </div>
                        <div class="amount ">
                            299
                        </div>
                    </div>
                    <a href="#" class="btn theme-btn">Sign Up</a>
                </div>
            </div>
        </div>
        <div class="spance10 visible-phone"></div>
        <div class="col-md-3">
            <div class="pricing-table">
                <h3>Standard</h3>
                <div class="desc">
                    Duis mollis, est non commodo luctus, nisi erat
                    porttitor ligula, eget lacinia.
                </div>
                <ul>
                    <li><i class="icon-angle-right"></i> 100 Email Accounts</li>
                    <li><i class="icon-angle-right"></i> 100 User Accounts</li>
                    <li><i class="icon-angle-right"></i> 1 TB Storage</li>
                    <li><i class="icon-angle-right"></i> 24X7 Support</li>
                    <li><i class="icon-angle-right"></i> Full Backup</li>
                    <li><i class="icon-angle-right"></i> Free Setup</li>
                    <li>&nbsp;</li>
                </ul>
                <div class="rate">
                    <div class="price">
                        <div class="currency ">
                            $<br />
                            Monthly
                        </div>
                        <div class="amount ">
                            299
                        </div>
                    </div>
                    <a href="#" class="btn theme-btn">Sign Up</a>
                </div>
            </div>
        </div>
        <div class="spance10 visible-phone"></div>
    </div>
</div>

</div>
<!-- END PAGE CONTAINER -->
</body>
</html>
