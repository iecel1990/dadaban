<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- BEGIN STYLE CUSTOMIZER -->
<div class="color-panel hidden-sm">
    <div class="color-mode-icons icon-color"></div>
    <div class="color-mode-icons icon-color-close"></div>
    <div class="color-mode">
        <p>THEME COLOR</p>
        <ul class="inline">
            <li class="color-blue current color-default" data-style="blue"></li>
            <li class="color-red" data-style="red"></li>
            <li class="color-green" data-style="green"></li>
            <li class="color-orange" data-style="orange"></li>
        </ul>
        <label>
            <span>Header</span>
            <select class="header-option form-control input-small">
                <option value="default" selected>Default</option>
                <option value="fixed">Fixed</option>
            </select>
        </label>
    </div>
</div>
<!-- END BEGIN STYLE CUSTOMIZER -->
<!-- BEGIN HEADER -->
<div class="header navbar navbar-default navbar-static-top navbar-fixed-top ">
    <div class="container">
        <div class="navbar-header">
            <!-- BEGIN RESPONSIVE MENU TOGGLER -->
            <button class="navbar-toggle btn navbar-btn" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!-- END RESPONSIVE MENU TOGGLER -->
            <!-- BEGIN LOGO (you can use logo image instead of text)-->
            <a class="navbar-brand logo-v1" href="${ctx}/index">
                <img src="${ctx}/static/assets/img/ddb_logo.png" id="logoimg" alt="" width="135px" height="38px">
            </a>
            <!-- END LOGO -->
        </div>

        <!-- BEGIN TOP NAVIGATION MENU -->
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav dropdown-menu-righ">
                <li class="active"><a href="/" target="_blank">首页</a></li>
                <li><a href="${ctx}/event">搭伴</a></li>
                <li><a href="/" target="_blank">帮派</a></li>
                <li><a href="/" target="_blank">约TA</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false" href="#">
                        筱筱
                        <i class="fa fa-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="blog.html">我的搭搭伴</a></li>
                        <li><a href="blog_item.html">修改资料</a></li>
                        <li><a href="${ctx}/logout">退出</a></li>
                    </ul>
                </li>
                <li class="menu-search">
                    <span class="sep"></span>
                    <i class="fa fa-search search-btn"></i>

                    <div class="search-box">
                        <form action="#">
                            <div class="input-group input-large">
                                <input class="form-control" type="text" placeholder="Search">
                                    <span class="input-group-btn">
                                        <button type="submit" class="btn theme-btn">Go</button>
                                    </span>
                            </div>
                        </form>
                    </div>
                </li>
            </ul>
        </div>
        <!-- BEGIN TOP NAVIGATION MENU -->
    </div>
</div>
<!-- END HEADER -->