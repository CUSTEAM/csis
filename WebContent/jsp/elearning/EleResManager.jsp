<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Insert title here</title>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/js/plugin/stupidtable.min.js"></script>
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
    
    
<div class="alert">
<button type="button" class="close" data-dismiss="alert">&times;</button>
<strong>課程規劃審核</strong>
</div>

<form action="EleResManager" method="post" class="form-inline">
<table class="table control-group info">
	<tr>
		<td>		
		<div class="input-prepend">
			<span class="add-on">學年</span>
			<select class="span1" name="year">
				<option <c:if test="${year eq ''}">selected</c:if> value="">全部</option>
				<c:forEach varStatus="i" begin="${school_year}" end="${school_year+5}">
				<option <c:if test="${year eq i.index}">selected</c:if> value="${i.index}">${i.index}</option>
				</c:forEach>
			</select>
		</div>
		</td>
		<td>		
		<div class="input-prepend">
			<span class="add-on">學期</span>
			<select name="term">
				<option <c:if test="${term eq''}">selected</c:if> value="">全部</option>
				<option <c:if test="${term eq'1'}">selected</c:if> value="1">第1學期</option>
				<option <c:if test="${term eq'2'}">selected</c:if> value="2">第2學期</option>
			</select>
		</div>
		</td>		
		<td>
		<div class="input-prepend">
			<span class="add-on">科系</span>
			<select name="dept">
				<option <c:if test="${dept eq ''}">selected</c:if> value="">全部</option>
				<c:forEach items="${CODE_DEPT}" var="d">
				<option <c:if test="${dept eq d.id}">selected</c:if> value="${d.id}">${d.name}</option>
				</c:forEach>
			</select>
		</div>
		</td>		
		<td>
		<div class="input-prepend">
			<span class="add-on">狀態</span>
			<select name="check">
				<option <c:if test="${check eq''}">selected</c:if> value="">全部</option>
				<option <c:if test="${check eq'1'}">selected</c:if> value="1">已確認</option>
				<option <c:if test="${check eq'2'}">selected</c:if> value="2">未確認</option>
			</select>
		</div>
		</td>
		<td  width="100%">
		<div class="btn-group">
			<button name="method:search" class="btn btn-danger">查詢</button>
			<button name="method:print" class="btn">列印</button>
		</div>
		</td>
	</tr>	
</table>

<c:if test="${!empty cs}">

<table class="table table-hover control-group info" id="row">
	<thead>
	<tr>
		<th nowrap data-sort="string" style="cursor:n-resize;">系所</td>
		<th nowrap data-sort="string" style="cursor:n-resize;">員工分類</td>
		<th nowrap data-sort="string" style="cursor:n-resize;">姓名</td>
		<th nowrap data-sort="string" style="cursor:n-resize;">課程名稱</td>
		<th nowrap data-sort="int" style="cursor:n-resize;">學年</td>
		<th nowrap data-sort="int" style="cursor:n-resize;">學期</td>
		<th nowrap data-sort="float" style="cursor:n-resize;">學分</td>
		<th nowrap data-sort="float" style="cursor:n-resize;">時數</td>
		<th nowrap data-sort="float" style="cursor:n-resize;">分鐘</td>
		<th nowrap data-sort="float" style="cursor:n-resize;">確認時數</td>
		<th nowrap data-sort="string" style="cursor:n-resize;">確認日期</td>
		<th></td>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${cs}" var="c">
	<tr>		
		<td nowrap>${c.name}</td>
		<td nowrap>${c.ctname}</td>
		<td nowrap>${c.cname}</td>
		<td nowrap>${c.chi_name}</td>
		<td>${c.school_year}</td>
		<td>${c.school_term}</td>
		<td>${c.credit}</td>
		<fmt:parseNumber var="t" type="number" value="${c.thour}" />
		
		<td>
		<fmt:formatNumber type="number" maxFractionDigits="0" value="${(t/60)-0.5}" />:
		<fmt:formatNumber type="number" minIntegerDigits="2" value="${c.thour%60}" />
		</td>
		<td>${c.thour}</td>
		<td><input type="text" name="thour_real" class="span1" value="${c.thour_real}" style="ime-mode:disabled" autocomplete="Off"/></td>
		<td nowrap><span class="label label-info">${c.checked}</span></td>
		<td width="100%">
		<input type="hidden" name="Oid" id="Oid${c.Oid}" value=""/>
		
		<div class="btn-group">	
		<c:if test="${empty c.checked}">		
		<button name="method:edit" onClick="$('#Oid${c.Oid}').val(${c.Oid})" class="btn btn-success">確認</button>	
		</c:if>		
		<c:if test="${!empty c.checked}">		
		<button name="method:cancel" onClick="$('#Oid${c.Oid}').val(${c.Oid})" class="btn btn-warning">取消</button>
		</c:if>	
		<button name="method:del" onClick="$('#Oid${c.Oid}').val(${c.Oid})" class="btn">刪除</button>
		</div>
		</td>
	</tr>
	</c:forEach>
	</tbody>
</table>
<script>
$("#row").stupidtable();
</script>
</c:if>
</form>    
</body>
</html>