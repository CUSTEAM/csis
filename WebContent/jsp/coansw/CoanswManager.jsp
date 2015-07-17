<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Insert title here</title>
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
    
    
<div class="alert">
<button type="button" class="close" data-dismiss="alert">&times;</button>
<strong>教學評量管理</strong> 

</div>

<form action="CoanswManager" method="post">


<table class="table control-group info">
	<tr>
		<td nowrap>日期</td>
		<td nowrap>名稱</td>
		
		<td nowrap>修改日期</td>
		<td width="100%">編輯者</td>
	</tr>
	<c:forEach items="${cals}" var="c">
	<tr>
		<td>
		
		<input onClick="$('#name${c.name}').val('${c.name}')" 
		class="dtpick" type="text" id="cdate${c.name}" placeholder="點一下輸入日期" 
		<c:if test="${param.sys ne c.sys && param.sys !=null}">readonly</c:if>
		name="cdate" value="${c.date}"/>
		</td>
		<td nowrap>${c.note}</td>
		<td nowrap>${c.edate}</td>
		<td width="100%">${c.editor}
		<input type="hidden" id="name${c.name}" name="name" value="${c.name}">
		<input type="hidden" value="${c.sys}">
		</td>
	</tr>
	
	</c:forEach>
	<tr>
		<td colspan="4">
		<button class="btn btn-success" id="subInfo" name="method:update" onClick="javascript: return(confirm('請確認您輸入的資料後,按下確定')); void('')" type="submit">變更日期設定</button>
		<!-- button class="btn" name="method:reset" type="submit">@</button>
		<button class="btn" name="method:setInvalid" type="submit">%</button-->
		</td>
	</tr>
</table>


<table class="table">
	<tr>
		<td>名稱</td>
		<td><input type="hidden" name="Oid" id="Oid"/></td>
		<td>說明</td>
	</tr>
	<c:forEach items="${topic }" var="t">
	<tr>
		<td nowrap width="400">${t.name}</td>
		<td width="100"><button class="btn btn-danger" onClick="$('#Oid').val('${t.Oid}')" name="method:edit" type="submit">編輯</button></td>
		<td>${t.note}</td>
	</tr>
	
	</c:forEach> 

</table>
<div style="height:800px;"></div> 
</form>  
 
<script>
//$(".dtpick" ).datetimepicker();
$('.dtpick').datetimepicker({
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