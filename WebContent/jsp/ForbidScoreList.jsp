<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>1/3缺課名單列印</title>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/js/plugin/json2.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<script>  
$(document).ready(function() {	
	$('#funbtn').popover("show");
	setTimeout(function() {
		$('#funbtn').popover("hide");
	}, 10000);
	
	//$("#selectClass").load("/eis/inc/jsp-kit/classSelector.jsp?r="+Math.floor(Math.random()*11));
	$("input[id='idiot']").typeahead({
		remote:"#stdNo",
		source : [],
		items : 10,
		updateSource:function(inputVal, callback){			
			$.ajax({
				type:"POST",
				url:"/eis/autoCompleteStmd",
				dataType:"json",
				data:{length:10, nameno:inputVal},
				success:function(d){
					callback(d.list);
				}
			});
		}		
	});
	
});



</script>

</head>
<body>
    
    
<div class="bs-callout bs-callout-info">
<button type="button" class="close" data-dismiss="alert">&times;</button>
<strong>1/3缺課名單列印</strong> 名單於學務單位結算操行成績時建立 

</div>

<form action="ForbidScoreList" method="post" class="form-inline">

<div class="panel panel-primary">
<div class="panel-heading">查詢</div>
<table class="table">
	<tr>
		<td nowrap>
			<%@ include file="/inc/jsp-kit/dhnSelector.jsp"%>
		</td>

		<td  width="100%">				
		<div class="input-append control-group info" style="float:left;">			
			<select class="form-control" name="grade">
				<option <c:if test="${grade eq ''}">selected</c:if> value="">全部</option>
				<option <c:if test="${grade eq '0'}">selected</c:if> value="0">非畢業班</option>
				<option <c:if test="${grade eq '1'}">selected</c:if> value="1">畢業班</option>
			</select>
		    <button class="btn btn-primary" name="method:print" type="submit">名單列印</button>
		    <div id="funbtn" rel="popover" title="說明" 
		data-content="列印或查詢個別學生1/3缺課標記，學期結束建立歷年成績時，系統將以0分匯入" 
		data-placement="right" class="btn btn-warning">?</div>	
		</div>	
		
		</td>
		<td width="100%"></td>
	</tr>

	<tr>
		<td colspan="2">
		<input type="hidden" id="stdNo" value="${stdNo}" name="stdNo"/>
		<div class="input-group">
			<input onClick="$('#idiot').val(''), $('#stdNo').val('');" autocomplete="off" 
			type="text" id="idiot" value="${nameno}" name="nameno" class="form-control"
			data-provide="typeahead" onClick="addStd()" placeholder="學號或姓名" />
			<span class="input-group-btn">
		    <button class="btn btn-danger" name="method:search" type="submit">修改學生記錄</button>
		    </span>
		</div>	
		</td>
		<td width="100%"></td>
	</tr>
</table>
</div>
<c:if test="${!empty std}">
<table class="table control-group info text-info">
	<tr>
		<td class="text-info" nowrap>1/3標記</td>
		<td class="text-info" nowrap>學號</td>
		<td class="text-info" nowrap>姓名</td>
		<td class="text-info" nowrap>課程代碼</td>
		<td class="text-info" nowrap>課程名稱</td>
		<td class="text-info" nowrap>缺課(規定扣考假別)</td>
		<td class="text-info" nowrap>期末總成績</td>
		<td class="text-info" width="100%"></td>
	</tr>
	<c:forEach items="${std}" var="s">
	<tr>
		<td nowrap>
		<input type="hidden" name="Oid" value="${s.Oid}"/>
		<select name="status" class="form-control">
			<option value="">無標記</option>
			<option <c:if test="${s.status eq'1' }">selected</c:if> value="1">標記為1/3缺課</option>			
		</select>
		</td>
		<td nowrap>${s.student_no}</td>
		<td nowrap>${s.student_name}</td>
		<td nowrap>${s.cscode}</td>
		<td nowrap>${s.chi_name}</td>
		<td nowrap>${s.cnt}</td>
		<td nowrap>${s.score}</td>
		<td width="100%"><button class="btn btn-danger btn-small" name="method:save" type="submit">儲存修改</button></td>
	</tr>
	</c:forEach>
</table>

</c:if>



</form>    
<script>$("input[name='beginDate'], input[name='endDate']" ).datepicker();</script>
</body>
</html>