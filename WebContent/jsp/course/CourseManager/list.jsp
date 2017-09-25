<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="table table-hover" id="row">
   	<thead>
        <tr>
	        <th nowrap>課程編號</th>
	        <th nowrap data-sort="string" style="cursor:n-resize;">開課班級 <i class="icon-chevron-down"></i></th>
	        <th nowrap data-sort="string" style="cursor:n-resize;">科目代碼 <i class="icon-chevron-down"></i></th>
	        <th nowrap>科目名稱</th>
	        <th nowrap data-sort="string" style="cursor:n-resize;">授課教師 <i class="icon-chevron-down"></i></th>
	        <th nowrap data-sort="string" style="cursor:n-resize;">選別 <i class="icon-chevron-down"></i></th>
	        <th nowrap data-sort="string" style="cursor:n-resize;">形態 <i class="icon-chevron-down"></i></th>
	        <th nowrap data-sort="string" style="cursor:n-resize;">學分 <i class="icon-chevron-down"></i></th>
	        <th nowrap data-sort="float" style="cursor:n-resize;">時數 <i class="icon-chevron-down"></i></th>
	        <th nowrap data-sort="int" style="cursor:n-resize;">人數 <i class="icon-chevron-down"></i></th>
	        <th></th>
	        <th></th>
        </tr>
    </thead>
    <tbody>
	   	<c:forEach items="${css}" var="c">
	   	<tr>
	   		<td nowrap>${c.Oid}</td>
	   		<td nowrap>${c.ClassName}</td>
	   		<td nowrap>${c.cscode}</td>
	   		<td nowrap>${c.chi_name}</td>
	   		<td nowrap>${c.cname}</td>
	   		<td nowrap>${c.optName}</td>
	   		<td nowrap>${c.eleName}</td>
	   		<td nowrap>${c.credit}</td>
	   		<td nowrap>${c.thour}</td>
	   		<td>${c.cnt}/${c.Select_Limit}</td>
	   		
	   		<td nowrap>
	   		<c:forEach items="${c.time}" var="t">
		  	<c:choose>
		    <c:when test="${t.week==7}">週日</c:when>
		    <c:when test="${t.week==1}">週一</c:when>
		    <c:when test="${t.week==2}">週二</c:when>
		    <c:when test="${t.week==3}">週三</c:when>
		    <c:when test="${t.week==4}">週四</c:when>
		    <c:when test="${t.week==5}">週五</c:when>
		    <c:when test="${t.week==6}">週六</c:when>
		    <c:otherwise>週${t.week},</c:otherwise>
			</c:choose>${t.begin}~${t.end}節${t.place}<br>
		  	</c:forEach>
	   		</td>
	   		
	   		<td width="100%" nowrap>    			
	    		<button class="btn btn-default" onClick="$('#Dtime_oid').val('${c.Oid}')" name="method:edit">修改</button>
	    		<div class="btn-group">
			    <a class="btn btn-default dropdown-toggle" data-toggle="dropdown">列印</a>
			    <ul class="dropdown-menu">
			    	<li><a class="btn btn-link" href="/pis/SylDoc?Dtime_oid=${c.Oid}">課程大綱</a></li>
			    	<li><a class="btn btn-link" href="/pis/IntorDoc?Dtime_oid=${c.Oid}">中英文簡介</a></li>
			    	<li><a class="btn btn-link" href="/pis/DtimeSelds?Oid=${c.Oid}">選課學生</a></li>
			    </ul>
			    </div>
	   		</td>
	   		
	   	</tr>
	   	</c:forEach>
   	</tbody>
</table>