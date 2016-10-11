<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>網路選課設定</title>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
</head>
<body>
<div class="bs-callout bs-callout-info"><h4>網路選課設定</h4><br>
第1階段不限制人數上限、第2階段開始限制人數上限(先選先贏)<br>
第2學期開學時間內設定<b>開課學期第1學期的情況下</b>，學生選課時會模擬升級為課程對應年級
</div>

<form action="ElectiveManager" method="post">

<c:forEach items="${list}" var="l">
<div class="panel panel-primary">
<div class="panel-heading">
<c:if test="${l[0].depart=='D'}">日間部</c:if> 		
<c:if test="${l[0].depart=='N'}">夜間部</c:if>
<c:if test="${l[0].depart=='H'}">進修學院</c:if>
</div>
<table class="table table-bordered">
	<tr>
		<td nowrap>階段</td>
		<td nowrap>開課學期</td>
		<td nowrap>部制</td>
		<td nowrap>年趿</td>
		<td nowrap>人數下限</td>
		<td nowrap>學分上限</td>
		<td nowrap>學分下限</td>
		<td nowrap>通識上限</td>
		<td nowrap>開始日期</td>
		<td nowrap>結束日期</td>
		<td nowrap>建立者</td>
	</tr>	
	<c:forEach items="${l}" var="s">		
	<tr onClick="$('#Oid${s.Oid}').val('${s.Oid}');">
		<td>
		<select name="level" onChange="$('#Oid${s.Oid}').val('${s.Oid}');" class="form-control">
			<option <c:if test="${s.level=='1'}">selected</c:if> value="1">第1階段</option>				
			<option <c:if test="${s.level=='2'}">selected</c:if> value="2">第2階段</option>
			<option <c:if test="${s.level=='3'}">selected</c:if> value="3">第3階段</option>
		</select>
		</td>
		<td>
		<select name="term" onChange="$('#Oid${s.Oid}').val('${s.Oid}');" class="form-control">
			<option <c:if test="${s.term=='1'}">selected</c:if> value="1">第1學期</option>				
			<option <c:if test="${s.term=='2'}">selected</c:if> value="2">第2學期</option>
		</select>
		</td>
		<td>
		<select name="depart" readonly class="form-control">
			<option <c:if test="${s.depart=='D'}">selected</c:if> value="D">日間部</option>				
			<option <c:if test="${s.depart=='N'}">selected</c:if> value="N">夜間部</option>
			<option <c:if test="${s.depart=='H'}">selected</c:if> value="H">進修學院</option>
		</select>	
		</td>
		<td>
		<select name="grade" readonly class="form-control">
			<option <c:if test="${s.grade=='1'}">selected</c:if> value="1">1年級</option>				
			<option <c:if test="${s.grade=='2'}">selected</c:if> value="2">2年級</option>
			<option <c:if test="${s.grade=='3'}">selected</c:if> value="3">3年級</option>
			<option <c:if test="${s.grade=='4'}">selected</c:if> value="4">4年級</option>
			<option <c:if test="${s.grade=='5'}">selected</c:if> value="5">5年級</option>
			<option <c:if test="${s.grade=='6'}">selected</c:if> value="6">6年級</option>
		</select>
		
		</td>
		<td nowrap><input type="text" style="width:60px;" class="form-control" name="sel_min" value="${s.sel_min}" onKeyUp="$('#Oid${s.Oid}').val('${s.Oid}');"/></td>
		<td><input type="text" class="form-control" style="width:60px;" name="max" value="${s.max}" onKeyUp="$('#Oid${s.Oid}').val('${s.Oid}');"/></td>
		<td><input type="text" class="form-control" style="width:60px;" name="min" value="${s.min}" onKeyUp="$('#Oid${s.Oid}').val('${s.Oid}');"/></td>
		<td><input type="text" class="form-control" style="width:60px;" name="nor" value="${s.nor}" onKeyUp="$('#Oid${s.Oid}').val('${s.Oid}');"/></td>
		<td nowrap><input type="text" style="width:130px;" class="form-control" name="begin" value="${s.begin}" onKeyUp="$('#Oid${s.Oid}').val('${s.Oid}');"/></td>
		<td nowrap><input type="text" style="width:130px;" class="form-control" name="end" value="${s.end}" onKeyUp="$('#Oid${s.Oid}').val('${s.Oid}');"/></td>
		<td nowrap>${s.cname}
		<input type="hidden" id="Oid${s.Oid}" name="Oid" value="" />
		</td>
	</tr>
	</c:forEach>	
</table>
<div class="panel-body">

<button class="btn btn-danger" name="method:edit"onClick="javascript: return(confirm('確定修改?')); void('')" type="submit">修改設定</button>
<a class="btn btn-default" href="ElectiveManager">取消</a>

</div>
</div>

</p>
</c:forEach>

</form>
<script>
//$("input[name='begin'], input[name='end']" ).datetimepicker();
$("input[name='begin'], input[name='end']" ).datetimepicker({
    //comment the beforeShow handler if you want to see the ugly overlay
    beforeShow: function() {
        setTimeout(function(){
            $('.ui-datepicker').css('z-index', 99999999999999);
        }, 0);
    }
});
</script>

</body>
</html>