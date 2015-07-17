<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="table table-hover">
	<tr>
		<td class="text-info" nowrap></td>
		<td class="text-info" nowrap>課程編號</td>
		<td class="text-info" nowrap>開課班級</td>
		<td class="text-info" nowrap>課程名稱</td>
		<td class="text-info" nowrap>選別</td>
		<td class="text-info" nowrap>學分</td>
		<td class="text-info" nowrap>時數</td>
		<td class="text-info" nowrap>學號姓名</td>
		<td class="text-info" nowrap>已選/上限</td>
		<td class="text-info" nowrap>實習費</td>
		<td class="text-info" nowrap>(星期)節次 地點</td>
	</tr>
	<c:forEach items="${dtime_selds}" var="s">
	<tr>
		<td class="text-info" nowrap>
		
		<button class="btn btn-danger btn-small" name="method:deleteSeldByDtime" onClick="javascript: return(confirm('確定刪除?')); void('')" 
		onMouseOver="$('#Oid').val('${s.Oid}'), $('#delNo').val('${s.student_no}')" type="submit">刪除</button>
		</td>		
		<td class="text-info" nowrap>${s.Dtime_oid}</td>
		<td class="text-info" nowrap>${s.ClassName}</td>
		<td class="text-info" nowrap>${s.chi_name}</td>
		<td class="text-info">${s.opt}</td>
		<td class="text-info">${s.credit}</td>
		<td class="text-info">${s.thour}</td>
		<td class="text-info" nowrap>
		<c:if test="${!empty s.student_no}"><a href="#seldInfo" data-toggle="modal" onClick="getSeldHist('${s.student_no}')">
		${s.student_no}, ${s.student_name} <i class="icon-search"></i></a>
		</c:if>
		</td>
		<td class="text-info" nowrap>${s.cnt} / ${s.Select_Limit}</td>
		<td class="text-info" nowrap>${s.ext}</td>
		<td class="text-info" width="100%">
		<c:forEach items="${classes}" var="c">
			<c:if test="${c.Dtime_oid==s.Dtime_oid}">
			(${c.week})${c.begin}-${c.end} ${c.place},
			</c:if>
		</c:forEach>
		</td>
	</tr>	
	</c:forEach>
	
	
</table>