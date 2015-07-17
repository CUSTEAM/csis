<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Insert title here</title>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js"></script>
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
<strong>教學評量管理</strong> 

</div>
<form action="CoanswManager" method="post">
<table class="table">
	<tr>
		<td nowrap><input type="text"  name="question" class="span5" value=""/></td>
		<td nowrap>
		<input type="hidden" maxlength="3"  name="sequence" class="span1" value=""/>
		<button onClick="$('#e${t.Oid}').val('${t.Oid}')" class="btn btn-info" name="method:save" type="submit">新增問題</button>
		</td>
		<td>		
		
		<input type="hidden" name="debug" />		
		</td>
		<td width="100%">		
		<input type="hidden" id="e${t.Oid}" name="eOid" value=""/>
		<input type="hidden" id="d${t.Oid}" name="dOid" value=""/>
		</td>
	</tr>
	<tr>
		<td>名稱
		<input type="hidden" name="Oid" id="Oid" value="${Oid}"/>
		<input type="hidden" name="topic" value="${topic}"/>
		</td>
		<td>順序</td>
		<td nowrap>對應偵錯題</td>		
		<td width="100%"></td>
	</tr>
	
	<c:forEach items="${ques}" var="t">
	<tr>
		<td nowrap><input type="text"  name="question" class="span5" value="${t.question}"/></td>
		<td><input type="text" maxlength="3"  name="sequence" class="span1" value="${t.sequence}"/></td>
		<td>
		
		
		<select name="debug" class="span3">
			<option value="">選擇後本題目不計分</option>
			<c:forEach items="${ques}" var="q1">			
			<c:if test="${q1.Oid != t.Oid }">
			<option <c:if test="${q1.Oid==t.debug}">selected</c:if> value="${q1.Oid}">${q1.question}</option>
			</c:if>
			</c:forEach>
		</select>
		
		
		
		
		</td>
		<td width="100%">
		<button onClick="$('#e${t.Oid}').val('${t.Oid}')" class="btn" name="method:save" type="submit">修改</button>
		<button onClick="$('#d${t.Oid}').val('${t.Oid}')" class="btn btn-danger" name="method:save" type="submit">刪除</button>
		<input type="hidden" id="e${t.Oid}" name="eOid" value=""/>
		<input type="hidden" id="d${t.Oid}" name="dOid" value=""/>
		</td>
	</tr>
	
	</c:forEach> 

</table>

<a href="CoanswManager" class="btn btn-success">返回試卷列表</a>


</form>    

</body>
</html>