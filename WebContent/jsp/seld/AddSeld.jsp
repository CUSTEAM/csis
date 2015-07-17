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
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<style>
    body .modal {
    /* new custom width */
    width: 960px;
    /* must be half of the width, minus scrollbar on the left (30px) */
    margin-left: -480px;

}
</style>
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
	
	<c:if test="${!empty scrolltop}">
	$("html, body").scrollTop(${scrolltop});
	</c:if>
	
});


function getSeldHist(stdNo){
	
	var str;
	$.get("/eis/getSeldHist?stdNo="+stdNo+"&"+Math.floor(Math.random()*999),
		function(d){
		
		str="<table class='table table-bordered table-hover'>";
		
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
<div id="seldInfo" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
<h3 id="title"></h3>
</div>
<div class="modal-body" id="info">
</div>
<div class="modal-footer">
<button class="btn" data-dismiss="modal" aria-hidden="true">關閉</button>
</div>
</div>
<div class="alert">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>第1學期選課管理</strong>    
</div>
<form action="AddSeld" method="post" class="form-inline">
<table class="table">
	<tr>
		<td nowrap>指定班級</td>
		<td width="100%">
		<input type="hidden" name="term" id="term" value="1"/>
		<%@ include file="/inc/jsp-kit/classSelector.jsp"%>
		<button class="btn" name="method:searchClass" type="submit">查詢課程</button>
		</td>
	</tr>
	<tr>
		<td nowrap>指定課程</td>
		<td width="100%">
		<div class="input-append control-group" style="float:left;">
			<input class="span6" onClick="$('#dkey').val(''), $('#dvalue').val('');" autocomplete="off" type="text" id="dkey" value="${dkey}" name="dkey"
			 data-provide="typeahead" onClick="addStd()" placeholder="課程編號、代碼、教師姓名或班級代碼與班級名稱片段" />
			<input type="hidden" id="dvalue" value="${dvalue}" name="dvalue"/>
		    <button class="btn" id="searchDtime" name="method:searchDtime" type="submit">查詢課程選課</button>		    
		</div>
		</td>
	</tr>
	<tr>
		<td nowrap>指定學生</td>
		<td width="100%" colspan="2">
		<div class="input-append control-group" style="float:left;">
			<input class="span4" onClick="$('#stdName').val(''), $('#stdNo').val('');" autocomplete="off" type="text" id="stdName" value="${stdName}" name="stdName"
			 data-provide="typeahead" onClick="addStd()" placeholder="學號或姓名片段" />
			<input type="hidden" id="stdNo" value="${stdNo}" name="stdNo"/>
			<input type="hidden" id="delNo" name="delNo"/>
		    <button class="btn" id="searchStd" name="method:searchStd" type="submit">查詢學生選課</button>		    		   
		</div>&nbsp;
		<button class="btn btn-danger" name="method:createSeldByStd" type="submit">建立學生選課</button>
		<button id="compel" style="display:none;" name="method:compel" type="submit">compel</button>
		</td>
		 
	</tr>
</table>
<input type="hidden" name="Oid" value="" id="Oid"/>
<c:if test="${!empty css}">
<script>

</script>
<input type="hidden" name="scrolltop" id="scrolltop" value="${scrolltop}" />
<table class="table table-hover">
	<tr>
		<td class="text-info" nowrap>課程編號</td>
		<td class="text-info" nowrap>開課班級</td>
		<td class="text-info" nowrap>課程名稱</td>
		<td class="text-info" nowrap>授課教師</td>
		<td class="text-info" nowrap>選別</td>
		<td class="text-info" nowrap>學分</td>
		<td class="text-info" nowrap>時數</td>
		<td class="text-info" nowrap>已選/上限</td>
		<td class="text-info"></td>
		<td class="text-info"></td>
	</tr>
	<c:forEach items="${css}" var="c">
	<tr>
		<td class="text-info" nowrap>${c.Oid}</td>
		<td class="text-info" nowrap>${c.ClassName}</td>
		<td class="text-info" width="100%">${c.cscode}${c.chi_name}</td>
		<td class="text-info" nowrap>${c.cname}</td>
		<td class="text-info" nowrap>${c.opt}</td>
		<td class="text-info" nowrap>${c.credit}</td>
		<td class="text-info" nowrap>${c.thour}</td>
		<td class="text-info" nowrap>${c.cnt}/${c.Select_Limit}</td>
		<td>  
    		<div class="btn-group">
		    <a class="btn dropdown-toggle" data-toggle="dropdown"><span class="icon-print"></span></a>
		    <ul class="dropdown-menu">
		    	<li><a class="btn btn-link" href="SylDoc?Oid=${c.Oid}">課程大綱</a></li>
		    	<li><a class="btn btn-link" href="IntorDoc?Oid=${c.Oid}">中英文簡介</a></li>
		    	<li><a class="btn btn-link" href="DtimeSelds?Oid=${c.Oid}">選課學生</a></li>
		    </ul>
		    </div>
	   	</td>
		<td class="text-info" nowrap>
		
		<div class="btn-group" onClick="$('#Oid').val('${c.Oid}')">
		<button class="btn" name="method:clearSeld" onClick="javascript: return(confirm('確定刪除本班學生所有選課?(保留其它班學生)')); void('')" type="submit">清除本班</button>
		<button class="btn" name="method:clearAllSeld" onClick="javascript: return(confirm('確定刪除所有選課?')); void('')" type="submit">清除全部</button>
		<button class="btn" name="method:generateThisTerm" onClick="$('#scrolltop').val($(document).scrollTop())" type="submit">建立本班選課</button>
		<button class="btn" name="method:generateUpgrade" onClick="$('#scrolltop').val($(document).scrollTop())" type="submit">建立升級選課</button>
		</div>
		</td>
	</tr>
	</c:forEach>
</table>
</c:if>
<c:if test="${!empty sumCredit}">
<div class="alert">個人學分: ${sumCredit.credit}, 時數: ${sumCredit.thour} <a href="#seldInfo" data-toggle="modal" onClick="getSeldHist($('#stdNo').val())" >查看選課歷程</a></div>
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