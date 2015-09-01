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
<div class="alert"><b>網路選課設定</b><br>
● 第1階段不限制人數上限、第2階段開始限制人數上限(先選先贏)<br>
● 於第2學期開學時間內<b>設定第1學期的情況下</b>，學生自動升級為課程對應年級
</div>

<form action="ElectiveManager" method="post">

<c:forEach items="${list}" var="l">
<table class="table table-bordered">
	<tr>
		<td nowrap>階段</td>
		<td nowrap>開課學期</td>
		<td nowrap>部制</td>
		<td nowrap>年趿</td>
		<td nowrap>退選下限</td>
		<td nowrap>學分上限</td>
		<td nowrap>學分下限</td>
		<td nowrap>通識上限</td>
		<td nowrap>開始日期</td>
		<td nowrap>結束日期</td>
		<td nowrap>最後修改</td>
	</tr>	
	<c:forEach items="${l}" var="s">		
	<tr onClick="$('#Oid${s.Oid}').val('${s.Oid}');">
		<td>
		<select name="level" onChange="$('#Oid${s.Oid}').val('${s.Oid}');">
			<option <c:if test="${s.level=='1'}">selected</c:if> value="1">第1階段</option>				
			<option <c:if test="${s.level=='2'}">selected</c:if> value="2">第2階段</option>
			<option <c:if test="${s.level=='3'}">selected</c:if> value="3">第3階段</option>
		</select>
		</td>
		<td>
		<select name="term" onChange="$('#Oid${s.Oid}').val('${s.Oid}');">
			<option <c:if test="${s.term=='1'}">selected</c:if> value="1">第1學期</option>				
			<option <c:if test="${s.term=='2'}">selected</c:if> value="2">第2學期</option>
		</select>
		</td>
		<td>
		<select name="depart" readonly>
			<option <c:if test="${s.depart=='D'}">selected</c:if> value="D">日間部</option>				
			<option <c:if test="${s.depart=='N'}">selected</c:if> value="N">夜間部</option>
			<option <c:if test="${s.depart=='H'}">selected</c:if> value="H">進修學院</option>
		</select>	
		</td>
		<td>
		<select name="grade" readonly>
			<option <c:if test="${s.grade=='1'}">selected</c:if> value="1">1年級</option>				
			<option <c:if test="${s.grade=='2'}">selected</c:if> value="2">2年級</option>
			<option <c:if test="${s.grade=='3'}">selected</c:if> value="3">3年級</option>
			<option <c:if test="${s.grade=='4'}">selected</c:if> value="4">4年級</option>
			<option <c:if test="${s.grade=='5'}">selected</c:if> value="5">5年級</option>
			<option <c:if test="${s.grade=='6'}">selected</c:if> value="6">6年級</option>
		</select>
		
		</td>
		<td nowrap><input type="text" class="span1" name="sel_min" value="${s.sel_min}" onKeyUp="$('#Oid${s.Oid}').val('${s.Oid}');"/></td>
		<td><input type="text" class="span1" name="max" value="${s.max}" onKeyUp="$('#Oid${s.Oid}').val('${s.Oid}');"/></td>
		<td><input type="text" class="span1" name="min" value="${s.min}" onKeyUp="$('#Oid${s.Oid}').val('${s.Oid}');"/></td>
		<td><input type="text" class="span1" name="nor" value="${s.nor}" onKeyUp="$('#Oid${s.Oid}').val('${s.Oid}');"/></td>
		<td nowrap><input type="text" class="span2" name="begin" value="${s.begin}" onKeyUp="$('#Oid${s.Oid}').val('${s.Oid}');"/></td>
		<td nowrap><input type="text" class="span2" name="end" value="${s.end}" onKeyUp="$('#Oid${s.Oid}').val('${s.Oid}');"/></td>
		<td nowrap>${s.cname}
		<input type="hidden" id="Oid${s.Oid}" name="Oid" value="" />
		</td>
	</tr>
	</c:forEach>	
</table>
<p>
<div class="btn-group">
<a class="btn" href="ElectiveManager">取消</a>
<button class="btn btn-danger" name="method:edit"onClick="javascript: return(confirm('確定修改?')); void('')" type="submit">修改設定</button>

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