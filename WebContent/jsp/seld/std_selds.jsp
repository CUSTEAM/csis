<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="table table-hover">
	<tr>
		<td nowrap></td>
		<td nowrap>課程編號</td>
		<td nowrap>開課班級</td>
		<td nowrap>課程名稱</td>
		<td nowrap>選別</td>
		<td nowrap>學分</td>
		<td nowrap>時數</td>
		<td nowrap>學號姓名</td>
		<td nowrap>已選/上限</td>
		<td nowrap>實習費</td>
		<td nowrap>(星期)節次 地點</td>
	</tr>
	<c:forEach items="${std_selds}" var="s">
	<tr>
		<td nowrap>
		
		<button class="btn btn-danger btn-small" name="method:deleteSeldByStd" onClick="javascript: return(confirm('確定刪除?')); void('')" 
		onMouseOver="$('#Oid').val('${s.Oid}'), $('#delNo').val('${s.student_no}')" type="submit">刪除</button>
		</td>		
		<td nowrap>${s.Dtime_oid}</td>
		<td nowrap>${s.ClassName}</td>
		<td nowrap>${s.chi_name}</td>
		<td>${s.opt}</td>
		<td>${s.credit}</td>
		<td>${s.thour}</td>
		<td nowrap>
		<c:if test="${!empty s.student_no}">
		
		<a type="button" data-toggle="modal" data-target="#myModal" onClick="getSeldHist('${s.student_no}')">
		  ${s.student_no}, ${s.student_name} <i class="icon-search"></i>
		</a>
		</c:if>
		</td>
		<td nowrap>${s.cnt} / ${s.Select_Limit}</td>
		<td nowrap>${s.ext}</td>
		<td width="100%">
		<c:forEach items="${classes}" var="c">
			<c:if test="${c.Dtime_oid==s.Dtime_oid}">
			(${c.week})${c.begin}-${c.end} ${c.place},
			</c:if>
		</c:forEach>
		</td>
	</tr>	
	</c:forEach>
	
	
</table>