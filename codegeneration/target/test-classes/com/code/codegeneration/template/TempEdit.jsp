<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%@include file="/view/resource.jsp" %>
  </head>
  <body>
  
   <!-- 必须引入,用于弹出错误提示 -->
	<form name="theForm" method="post" action="${msUrl}sys/Classify/save.do" enctype="multipart/form-data">

		<input type="hidden" name="id"  value="">
	
		
		<div style="margin:5px">
		<fieldset style="color: red; border-bottom-color: #CC0099 border-bottom-width : medium";>
		<legend>
			<strong> 管理</strong>
		</legend>
		<table class="tablelistcontent1" style="margin: 8px;" border="0" cellspacing="0" cellPadding="0">
			<%-- template 编辑字段区 --%>
			<tr>
				<td width="13%" class="td_title">
					分类名称：
				</td>
				<td width="37%" class="td_content">
					<input class="inputsearch" type="text" name="name" id="bean.name"
						value="" maxlength="15" />
					<div class="reg_on" id="bean.name"  msg="请输入微博名称。" errmsg="微博名称不能为空。"></div>
				</td>
			</tr>	
		</table>
		</fieldset>
		<div style="height: 5px;"></div>
		<div align="center">
			<img src="${msUrl}static/sys/images/report/save.gif" onclick="toSubmit();" style="cursor: pointer;">&nbsp;
			<img src="${msUrl}static/sys/images/report/fh.gif" onclick="YiYa.close()" style="cursor: pointer;">
		</div>
		</div>
	</form>
	<script type="text/javascript">
	function toSubmit(){
		var theform = $("form[name='theForm']")[0];
		YiYa.save(theform);
	}
	</script>
	<script type="text/javascript" src="${msUrl}/js/ux/siteMain/siteMain.js"></script>
  </body>
</html>
