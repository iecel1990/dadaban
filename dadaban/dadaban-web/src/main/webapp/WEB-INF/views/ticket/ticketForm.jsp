<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>票务管理</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/ticket/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${ticket.id}"/>
		<fieldset>
			<legend><small>事件管理</small></legend>
			<div class="control-group">
				<label for="ticket_title" class="control-label">任务名称:</label>
				<div class="controls">
					<input type="text" id="ticket_title" name="name"  value="${ticket.name}" class="input-large required" minlength="3"/>
				</div>
			</div>	

			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#ticket_title").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</body>
</html>
