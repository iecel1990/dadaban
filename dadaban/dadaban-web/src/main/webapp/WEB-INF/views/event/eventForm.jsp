<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>创建大会</title>
    <link href="${ctx}/static/assets/plugins/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
    <link href="${ctx}/static/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/static/assets/plugins/datatimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" charset="utf-8" src="${ctx}/static/assets/plugins/umeditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx}/static/assets/plugins/umeditor/umeditor.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/assets/plugins/umeditor/lang/zh-cn/zh-cn.js"></script>

</head>

<body>
<!-- BEGIN PAGE CONTAINER -->
<div class="page-container">

<!-- BEGIN BREADCRUMBS -->
<div class="row breadcrumbs margin-bottom-40">
    <div class="container">
        <div class="col-md-4 col-sm-4">
            <h1>Forms</h1>
        </div>
        <div class="col-md-8 col-sm-8">
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">Home</a></li>
                <li><a href="">Features</a></li>
                <li class="active">Forms</li>
            </ul>
        </div>
    </div>
</div>
<!-- END BREADCRUMBS -->

<!-- BEGIN CONTAINER -->
<div class="container min-hight">

<!-- ROW 1 -->
<div class="row tabs-left">
<ul class="nav nav-tabs" id="eventTab">
    <li class="active"><a href="#tab-1" data-toggle="tab">基本信息</a></li>
    <li><a href="#tab-2" data-toggle="tab">票务信息</a></li>
    <li><a href="#tab-3" data-toggle="tab">Responsive</a></li>
</ul>
<div class="tab-content">
<div class="tab-pane row fade in active" id="tab-1">
    <div class="col-md-10">
        <div class="panel panel-success">
            <div class="panel-heading"><h3 class="panel-title">创建大会</h3></div>
            <div class="panel-body">
                <form class="form-horizontal" role="form" id="eventForm" action="${ctx}/event/${action}" method="post">
                    <div class="form-body">
                        <div class="form-group">
                            <label  class="col-md-3 control-label">活动名称</label>
                            <div class="col-md-9">
                                <input type="hidden" name="id" value="${event.id}"/>
                                <input type="text" class="form-control input-large" name="name" value="${event.name}" placeholder="爱运动才是好姑娘">
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-md-3 control-label">活动地点</label>
                            <div class="col-md-9" data-ng-controller="provinceCityCtrl">
                                <div class="col-md-3">
                                    <input type="hidden" name="province" value="{{defProvince.provinceId}}">
                                    <select ng-model = "defProvince" ng-change = "provinceChange(defProvince)" class="form-control input-small" ng-options = "province as province.provinceName for province in provinces">
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <input type="hidden" name="city" value="{{defCity.cityId}}">
                                    <select ng-model = "defCity" class="form-control input-small" ng-options = "city.cityName for city in cities">
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-md-3 control-label"></label>
                            <div class="col-md-9">
                                <input type="text" class="form-control input-large" name="address" value="${event.address}" placeholder="请输入详细地址">
                            </div>
                        </div>

                        <div class="form-group">
                            <label  class="col-md-3 control-label">活动时间</label>
                            <div class="col-md-9">
                                <div class="input-group date form_datetime col-md-4">
                                    <input data-date-format="YYYY-MM-DD hh:mm:ss" class="form-control" size="16" type="text" value="<fmt:formatDate value="${event.starttime}" pattern="YYYY-MM-dd HH:mm:ss"></fmt:formatDate>" name="starttime">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                                <div class="col-md-1 text-center">
                                    <label class="control-label">到</label>
                                </div>
                                <div class="input-group date form_datetime col-md-4">
                                    <input data-date-format="YYYY-MM-DD hh:mm:ss" class="form-control" size="16" type="text" value="<fmt:formatDate value="${event.endtime}" pattern="YYYY-MM-dd HH:mm:ss"></fmt:formatDate>" name="endtime">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                                <!--
                                <div class="col-md-3">
                                    <div class="input-group clockpicker">
                                        <input type="text" class="form-control" value="09:30">
                                           <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-time"></span>
                                           </span>
                                    </div>
                                </div>
                                -->
                                <div class="col-md-3">

                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-md-3 control-label">限制人数</label>
                            <div class="col-md-2">

                                <input type="text" class="input-xsmall spinner-input form-control" value="${event.limitNum}" name="limitNum" maxlength="3">

                            </div>

                        </div>

                        <div class="form-group">
                            <label  class="col-md-3 control-label">关键字</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control input-large" value="${event.tags}" name="tags" placeholder="空格隔开">
                            </div>
                        </div>

                        <div class="form-group">
                            <label  class="col-md-3 control-label">活动内容</label>
                            <div class="col-md-9">
                                <!--style给定宽度可以影响编辑器的最终宽度-->
                                <script type="text/plain" id="myEditor0" style="width:657px;height:240px;">
                                    ${content.content}
                                </script>
                            </div>
                        </div>

                    </div>
                    <div class="form-actions fluid">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="submit" class="btn green">创建</button>
                            <button type="button" class="btn default">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- BLOCK END -->
    </div>
</div>
<div class="tab-pane row fade" id="tab-2">
    <div class="col-md-10">
        <div class="row">
            <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
                添加票种
            </button>
        </div>
        <div class="row">
            <c:forEach items="${tickets}" var="ticket">
                <div class="well col-md-3 ticket margin-right-10">
                    <h4 class="text-success"><strong>${ticket.name}</strong></h4>
                    <div class="row">
                        <div class="col-md-4">
                            票价
                        </div>
                        <div class="col-md-8 text-info">
                            ${ticket.price}
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            限额
                        </div>
                        <div class="col-md-8 text-info">
                            ${ticket.limitNum}
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                           销售期
                        </div>
                        <div class="col-md-8 text-info">
                                <fmt:formatDate value="${ticket.startTime}" pattern="YYYY-MM-dd HH:mm"></fmt:formatDate><br />
                                <fmt:formatDate value="${ticket.endTime}" pattern="YYYY-MM-dd HH:mm"></fmt:formatDate>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            备注
                        </div>
                        <div class="col-md-8 text-info">
                            ${ticket.remark}
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 text-center">
                            <button type="button" class="btn btn-default btn-sm">编辑</button>
                        </div>
                        <div class="col-md-6 text-center">
                            <button type="button" class="btn btn-default btn-sm">停售</button>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog ticketModal">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel">添加票种</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form" id="ticketForm">
                        <div class="form-body" data-ng-controller="ticketTypeController">
                            <div class="form-group">
                                <label  class="col-md-3 control-label">是否收费</label>
                                <div class="col-md-9">
                                    <div class="col-md-4">
                                        <input type = "hidden" value="${event.id}" name="eventId"/>
                                        <select ng-model = "myColor" ng-options = "color.name for color in colors" class="form-control input-small" name="type">
                                            <option value="free">否</option>
                                            <option value="charge">是</option>
                                        </select>
                                    </div>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" name="name" placeholder="儿童票、成人票">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group {{myColor.display}}">
                                <label class="col-md-3 control-label">售价</label>
                                <div class="col-md-9">
                                    <div class="col-md-2">
                                        <input name="price" value="0" type="text" class="input-xsmall spinner-input form-control" maxlength="3">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-md-3 control-label">人数限制</label>
                                <div class="col-md-9">
                                    <div class="col-md-2">
                                        <input name="limitNum" type="text" class="input-xsmall spinner-input form-control" maxlength="3">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-md-3 control-label">售票时间</label>
                                <div class="col-md-9">
                                    <div class="input-group date form_datetime col-md-5">
                                        <input class="form-control" size="16" type="text" value="" name="startTime">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                    </div>
                                    <div class="col-md-1 text-center">
                                        <label class="control-label">到</label>
                                    </div>
                                    <div class="input-group date form_datetime col-md-5">
                                        <input class="form-control" size="16" type="text" value="" name="endTime">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-md-3 control-label">有效时间</label>
                                <div class="col-md-9">
                                    <div class="input-group date form_datetime col-md-5">
                                        <input class="form-control" size="16" type="text" value="" name="effStartTime">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                    </div>
                                    <div class="col-md-1 text-center">
                                        <label class="control-label">到</label>
                                    </div>
                                    <div class="input-group date form_datetime col-md-5">
                                        <input class="form-control" size="16" type="text" value="" name="effEndTime">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-md-3 control-label">备注</label>
                                <div class="col-md-9">
                                    <textarea name = "remark" class="form-control" rows="3"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-3 control-label">是否审核</label>
                                <div class="col-md-9">
                                    <div class="checkbox-list">
                                        <label class="checkbox-inline">
                                            <input type="checkbox"  value="option1">此类票种需要审核
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="createTicket" class="btn btn-primary">创建</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="tab-pane fade" id="tab-3">
    <p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade. Messenger bag gentrify pitchfork tattooed craft beer, iphone skateboard locavore carles etsy salvia banksy hoodie helvetica. DIY synth PBR banksy irony. Leggings gentrify squid 8-bit cred pitchfork. Williamsburg banh mi whatever gluten-free, carles pitchfork biodiesel fixie etsy retro mlkshk vice blog. Scenester cred you probably haven't heard of them, vinyl craft beer blog stumptown. Pitchfork sustainable tofu synth chambray yr.</p>
</div>
</div>
</div>
<!-- END ROW 1 -->

</div>
<!-- END CONTAINER -->

</div>
<!-- END PAGE CONTAINER -->  >


<script type="text/javascript">
    jQuery(document).ready(function() {
        $('#eventTab li:eq(${tab}) a').tab('show')
    });

</script>
<script src="${ctx}/static/assets/plugins/angular/angular.min.js"></script>
<script src="${ctx}/static/assets/plugins/datatimepicker/js/moment.js"></script>
<script src="${ctx}/static/assets/plugins/datatimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/static/assets/scripts/event.js"></script>
<script type="text/javascript">
    //实例化编辑器
    var um = UM.getEditor('myEditor0');
    </script>
</body>
</html>
