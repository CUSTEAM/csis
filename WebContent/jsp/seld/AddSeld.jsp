<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>第2學期選課管理</title>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/js/plugin/json2.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js"></script>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<script src="/eis/inc/js/plugin/stupidtable.min.js"></script>

<script>  
$(document).ready(function() {
	$("input[id='stdName']").typeahead({
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
	
	$("input[id='dkey']").typeahead({
		remote:"#dvalue",
		source : [],
		items : 99999,
		updateSource:function(inputVal, callback){
			
			$.ajax({
				type:"POST",
				url:"/eis/autoCompleteDtime",
				dataType:"json",
				data:{key:inputVal, term:$('#term').val()},
				success:function(d){
					callback(d.list);
				}
			});
		}		
	});
	
	
	$('#note1').popover("show");
	$('#note2').popover("show");
	$('#note3').popover("show");
	setTimeout(function() {
		$('#note1').popover("hide");
		$('#note2').popover("hide");
		$('#note3').popover("hide");
	}, -1);
});


function getSeldHist(stdNo){
	
	var str;
	$.get("/eis/getSeldHist?stdNo="+stdNo+"&"+Math.floor(Math.random()*999),
		function(d){
		
		str="<table class='table'>";
		
		$("#info").html("");
		if(d.list.length>0){
			for(i=0; i<d.list.length; i++){				
				str=str+"<tr><td nowrap>"+d.list[i].ClassName+"</td><td nowrap>"+d.list[i].chi_name+"</td><td>"+d.list[i].edate.replace("T", " ")+"</td><td nowrap>"+d.list[i].cname+"</td>";
				if(d.list[i].type=='A'){
					str=str+"<td class='text-success'>加選</td></tr>";
				}else{
					str=str+"<td class='text-error'>退選</td></tr>";
				}
			}
		}else{
			str=str+"<tr><td>無加退選記錄</td></tr>";
		}
		str=str+"</table>";
		
		$("#title").text(stdNo);
		$("#info").append(str);
		
	}, "json");	
}
</script>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">加退選記錄</h4>
      </div>
      <div class="modal-body" id="info"></div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
        
      </div>
    </div>
  </div>
</div>
<div class="bs-callout bs-callout-info" id="callout-helper-pull-navbar">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>第1學期選課管理</strong>    
</div>
<form action="AddSeld" method="post" class="form-inline">
<table class="table">
	<tr>
		
		<td>
		<input type="hidden" name="term" id="term" value="1"/>
		<%@ include file="/inc/jsp-kit/classSelector.jsp"%>
		<button class="btn btn-danger" name="method:searchClass" type="submit">查詢班級開設課程</button>
		</td>
	</tr>
	<tr>
		<td>
		<div class="input-group">
		  	<span class="input-group-addon" id="sizing-addon2">指定課程</span>
			<input class="form-control" onClick="$('#dkey').val(''), $('#dvalue').val('');" autocomplete="off" type="text" id="dkey" value="${dkey}" name="dkey"
			 data-provide="typeahead" onClick="addStd()" placeholder="課程編號、代碼、教師姓名或班級代碼與班級名稱片段" />
			<input type="hidden" id="dvalue" value="${dvalue}" name="dvalue"/>
		    <span class="input-group-btn">
		    <button class="btn btn-default" id="searchDtime" name="method:searchDtime" type="submit">查詢課程選課</button>		    
			</span>
		</div>
		</td>
	</tr>
	<tr>
		
		<td width="100%" colspan="2">
		
		<div class="input-group">
		  	<span class="input-group-addon" id="sizing-addon2">指定學生</span>
			<input class="form-control" onClick="$('#stdName').val(''), $('#stdNo').val('');" autocomplete="off" type="text" id="stdName" value="${stdName}" name="stdName"
			 data-provide="typeahead" onClick="addStd()" placeholder="學號或姓名片段" />
			<input type="hidden" id="stdNo" value="${stdNo}" name="stdNo"/>
			<input type="hidden" id="delNo" name="delNo"/>
		    <span class="input-group-btn">
		    <button class="btn btn-default" id="searchStd" name="method:searchStd" type="submit">查詢學生選課</button>		    		   
			</span>
		</div>&nbsp;
		<button class="btn btn-danger" name="method:createSeldByStd" type="submit">建立學生選課</button>
		<button id="compel" style="display:none;" name="method:compel" type="submit">compel</button>
		</td>
		 
	</tr>
</table>
<input type="hidden" name="Oid" value="" id="Oid"/>
<c:if test="${!empty css}">
<table class="table table-hover" id="row">
	<thead>
		<tr>
			<th nowrap>課程編號 </th>
			<th nowrap data-sort="string" style="cursor:n-resize;">開課班級 <i class="icon-chevron-down"></i></th>
			<th nowrap data-sort="string" style="cursor:n-resize;">課程名稱 <i class="icon-chevron-down"></i></th>
			<th nowrap data-sort="string" style="cursor:n-resize;">授課教師 <i class="icon-chevron-down"></i></th>
			<th nowrap data-sort="string" style="cursor:n-resize;">選別 <i class="icon-chevron-down"></i></th>
			<th nowrap data-sort="string" style="cursor:n-resize;">學分 <i class="icon-chevron-down"></i></th>
			<th nowrap data-sort="string" style="cursor:n-resize;">時數 <i class="icon-chevron-down"></i></th>
			<th nowrap data-sort="string" style="cursor:n-resize;">已選/上限 <i class="icon-chevron-down"></i></th>
			<th nowrap></th>
			<th nowrap></th>
		</tr>
	</thead>
	<c:forEach items="${css}" var="c">
	<tr>
		<td nowrap>${c.Oid}</td>
		<td nowrap>${c.ClassName}</td>
		<td nowrap>${c.cscode}${c.chi_name}</td>
		<td nowrap>${c.cname}</td>
		<td nowrap>${c.opt}</td>
		<td nowrap>${c.credit}</td>
		<td nowrap>${c.thour}</td>
		<td nowrap>${c.cnt}/${c.Select_Limit}</td>
		<td nowrap> 
		
		<div class="dropdown">
		  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">列印 <span class="caret"></span></button>
		  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
		    <li><a class="btn btn-link" href="SylDoc?Oid=${c.Oid}">課程大綱</a></li>
		    <li><a class="btn btn-link" href="IntorDoc?Oid=${c.Oid}">中英文簡介</a></li>
		    <li><a class="btn btn-link" href="DtimeSelds?Oid=${c.Oid}">選課學生</a></li>
		  </ul>
		</div>
		    
	   	</td>
		<td nowrap onClick="$('#Oid').val('${c.Oid}')">		
		<button class="btn btn-default" name="method:generateThisTerm" type="submit">建立本班選課</button>
		<button class="btn btn-default" name="method:clearSeld" onClick="javascript: return(confirm('確定刪除?')); void('')" type="submit">清除本班選課</button>
		<button class="btn btn-danger" name="method:clearAllSeld" onClick="javascript: return(confirm('確定刪除?')); void('')" type="submit">清除所有選課</button>
		
		
		</td>
	</tr>
	</c:forEach>
</table>
<script>
$("#row").stupidtable();
</script>
</c:if>
<c:if test="${!empty sumCredit}">
<div class="alert alert alert-warning" role="alert">個人學分: ${sumCredit.credit}, 
時數: ${sumCredit.thour} 
<a type="button" data-toggle="modal" 
data-target="#myModal" 
onClick="getSeldHist($('#stdNo').val())">
		  查看加退選歷程</a>
</div>

</c:if>



<c:if test="${!empty dtime_selds}">
<%@ include file="dtime_selds.jsp"%>
</c:if>
<c:if test="${!empty std_selds}">
<%@ include file="std_selds.jsp"%>
</c:if>
</form>
</body>
</html>