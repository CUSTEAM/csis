<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>證照代碼管理</title>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>


<script src="/eis/inc/js/plugin/bootstrap-tooltip.js"></script>

<script src="/eis/inc/js/autoComplete.js"></script>

</head>
<body>
<div class="bs-callout 
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>證照代碼管理</strong>    
</div>

<form action="LicenseCodeManager" method="post" class="form-inline">

<div class="panel panel-primary">
<div class="panel-heading">快速查詢</div>

<table class="table">
	<tr>
		<td>
		<div class="input-group">
		  	<span class="input-group-addon" id="sizing-addon2">快速查詢</span>
		  	<input id="licenseCode" name="licenseCode" style="width:500px;" class="form-control licenseCode" value="${licenseCode}" data-provide="typeahead" onClick="this.value=''" placeholder="名稱或代碼" autocomplete="off" type="text"/>
		  	<span class="input-group-btn"><button name="method:searchMix" class="btn btn-default">查詢</button></span>
		</div>
		</td>
	</tr>
	
</table>
</div>


<div class="panel panel-primary">
<div class="panel-heading">新增或查詢</div>
<table class="table">	
	<tr>
		<td nowrap>

			<div class="input-group">
			  	<span class="input-group-addon">證照代碼</span>
			  	<input type="text" placeholder="代碼" class="form-control" name="Code" id="Code" value="${Code}" onClick="this.value='';" autocomplete="off" data-provide="typeahead"/>
				
			</div>
		
			<div class="input-group">
		  	<span class="input-group-addon">證照名稱</span>
				<input type="text" placeholder="名稱" style="width:300px;" class="form-control" name="Name" id="Name" value="${Name}" onClick="this.value='';" autocomplete="off" data-provide="typeahead"/>
			</div>
		</td>
	</tr>
	<tr>
		<td>
		
			<div class="input-group">
		  	<span class="input-group-addon">區域</span>
            <select name="Locale" class="form-control">
				<option value=""></option>
				<option <c:if test="${Locale eq'1'}">selected</c:if> value="1">國內</option>
				<option <c:if test="${Locale eq'2'}">selected</c:if> value="2">國外</option>

			</select>
			</div>
			
			<div class="input-group">
		  	<span class="input-group-addon">類別</span>
            <select name="Type" class="form-control">
				<option value=""></option>
				<c:forEach items="${CODE_LICENSE}" var="c">
				<option <c:if test="${Type eq c.id}">selected</c:if> value="${c.id}">${c.name}</option>
				</c:forEach>
			</select>
			</div>
			
			<div class="input-group">
			<span class="input-group-addon">級別</span>
		  	<input id="Level" name="Level" value="${Level}" class="form-control" onClick="this.value=''" placeholder="級別" autocomplete="off" type="text"/>
			</div>
			
		</td>
	</tr>
	<tr>
		<td>
		<div class="input-group">
			<span class="input-group-addon">發照單位</span>
		  	<input id="DeptName" name="DeptName" value="${DeptName}" style="width:300px;" class="form-control" onClick="this.value=''" placeholder="發照單位" autocomplete="off" type="text"/>
			</div>
		<button name="method:search" class="btn btn-default">查詢</button>
		<button name="method:add" class="btn btn-primary">新增</button>
		</td>
	</tr>
	
	
</table>

</div>





<c:if test="${!empty licenses}">
<input type="hidden" name="DelCode" id="DelCode" />
<div class="panel panel-primary">
	<div class="panel-heading">查詢結果</div>
<display:table name="${licenses}" requestURI="LicenseCodeManager?method=search" pagesize="20" export="false" id="row" sort="list" class="table table-hover" >
	<display:column title="證照代碼" class="left">${row.Code}</display:column>
  	<display:column title="證照名稱" property="Name" sortable="true" class="left" />
  	<display:column title="區域"  sortable="true" style="white-space:nowrap;"  class="left"> 
  	<c:if test="${row.Locale eq '1' }">國內</c:if>
  	<c:if test="${row.Locale eq '2' }">國外</c:if>
  	</display:column>
  	<display:column title="類別" style="white-space:nowrap;" property="TypeName" sortable="true" class="left"/>
  	<display:column title="級數" style="width:250px;" property="Level" sortable="true" class="left"/>
  	<display:column title="發照單位" property="DeptName" sortable="true" class="left"/>
  	<display:column title="建立" style="white-space:nowrap;" property="cnt" sortable="true" class="left"/>
  	<display:column title="">
  	<button name="method:del" onMouseOver="$('#DelCode').val('${row.Code}')" class="btn btn-danger">刪除</button>
  	</display:column>
  	</display:table>
  	</div>
  	
</c:if>











<script>  
$(document).ready(function() {
	
	$(".licenseCode").typeahead({
		//remote:"#agent",
		source : [],
		items : 10,
		updateSource:function(inputVal, callback){			
			$.ajax({
				url:"/eis/autoCompAnyCode",
			    dataType: 'jsonp',
			    jsonp:'back',          //jsonp請求方法
			    data:{bootstrap:1, table:"LicenseCode", idCol:"Code",nameCol:"Name",value:inputVal},
			    cache:false,
			    type:'POST',
			    success: function(d) {    			    	
			    	callback(d.list);
			    }
			});
		}		
	});
	
});



</script>

</div>
</form>
</body>
</html>