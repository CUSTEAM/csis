<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批次匯入選課資料</title>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<link href="/eis/inc/css/wizard-step.css" rel="stylesheet"/>
<link href="/eis/inc/bootstrap/plugin/bootstrap-fileinput/css/fileinput.min.css" rel="stylesheet">
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/bootstrap/plugin/bootstrap-fileinput/js/fileinput.min.js"></script>
<script src="/eis/inc/bootstrap/plugin/bootstrap-fileinput/js/fileinput_locale_zh-TW.js"></script>
  
</head>
<body>
<div class="bs-callout bs-callout-warning" id="callout-helper-pull-navbar">
	<button type="button" class="close" data-dismiss="alert">&times;</button>
	<strong>選課資料匯入</strong> 請依格式匯入資料
</div>

<form action="AddSeldBatch" method="post" enctype="multipart/form-data">
<div class="panel panel-primary">
<div class="panel-heading">匯入</div>
<table class="table">	
	<tr>
		<td>
		<a class="btn btn-link btn-lg btn-block" href="jsp/seld/batchSeld.xlsx"><span class="glyphicon glyphicon-cloud-download" aria-hidden="true"></span>下載格式</a>	<br>			    
		<input name="upload" type="file" data-show-upload="false"
		id="upload" class="file file-loading "/><br>
		<script>
		$("#upload").fileinput({
			multiple: false,			
		    language: "zh-TW",
		    uploadUrl: "",
		    allowedFileExtensions: ["xlsx"]
		});
		</script>		  	
		  	<select name="term" class="form-control">
			<option <c:if test="${term eq'1'}">selected</c:if> value="1">第1學期</option>
			<option <c:if test="${term eq'2'}">selected</c:if> value="2">第2學期</option>
			</select>		  	
			<br>
			<select name="limit" class="form-control">
			<option <c:if test="${limit eq'0'}">selected</c:if> value="0">不檢查人數上限</option>
			<option <c:if test="${limit eq'1'}">selected</c:if> value="1">要檢查人數上限</option>
			</select>		  	
			<br>
		  	<button class="btn btn-danger btn-lg btn-block" id="saveTxtFile" name="method:upload" type="submit"><span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span>匯入選課資料</button> 
		  	
		
				
		</td>
	</tr>
</table>
</div>



<c:if test="${!empty allBatch}">
<input type="hidden" name="batchOid" id="batchOid" />
<div class="panel panel-primary">
<div class="panel-heading">匯入記錄</div>
<table class="table control-group info text-info">
	<tr>
		<td class="text-info" ></td>
		<td class="text-info" nowrap>時間</td>
		<td class="text-info" nowrap>匯入者</td>
		<td class="text-info" width="100%">人數</td>
		
	</tr>
	<c:forEach items="${allBatch}" var="a">
	<tr>
		<td><button onClick="$('#batchOid').val(${a.Oid})" class="btn btn-danger btn-small" name="method:del" type="submit">刪除</button></td>
		<td nowrap>
		
		${a.sdate}
		</td>
		<td nowrap>${a.cname}</td>
		<td nowrap>${a.cnt}</td>
		
	</tr>
	</c:forEach>
</table>
</div>
</c:if>
</form>

</body>
</html>