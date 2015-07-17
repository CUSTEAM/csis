<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="opencsTable">
<c:forEach items="${ot}" var="o">
<table>
	<tr>
		<td>
		<select name="cidno">
			<option value="">刪除</option>
			<option <c:if test="${o.Cidno eq '*'}">selected</c:if> value="*">所有校區</option>
			<c:forEach items="${allCampus}" var="c">
				<option <c:if test="${o.Cidno eq c.idno}">selected</c:if> value="${c.idno}">${c.name}</option>
			</c:forEach>
		</select>
		<select name="sidno">
			<option <c:if test="${o.Sidno eq '*'}">selected</c:if> value="*">所有學制</option>
			<c:forEach items="${allSchool}" var="c">
				<option <c:if test="${o.Sidno eq c.idno}">selected</c:if> value="${c.idno}">${c.name}</option>
			</c:forEach>
		</select>
		<select name="didno">			
			<option <c:if test="${o.Didno eq '*'}">selected</c:if> value="*">所有科系</option>
			<c:forEach items="${allDept}" var="c">
				<option <c:if test="${o.Didno eq c.idno}">selected</c:if> value="${c.idno}">${c.name}</option>
			</c:forEach>
		</select>
		<select name="grade">
			<option <c:if test="${o.Grade eq '*'}">selected</c:if> value="*">所有年級</option>
			<option <c:if test="${o.Grade eq '1'}">selected</c:if> value="1">1年級</option>
			<option <c:if test="${o.Grade eq '2'}">selected</c:if> value="2">2年級</option>
			<option <c:if test="${o.Grade eq '3'}">selected</c:if> value="3">3年級</option>
			<option <c:if test="${o.Grade eq '4'}">selected</c:if> value="4">4年級</option>
			<option <c:if test="${o.Grade eq '5'}">selected</c:if> value="5">5年級</option>
		</select>
		<select name="classes">
			<option <c:if test="${o.ClassNo eq '*'}">selected</c:if> value="*">所有班級</option>
			<option <c:if test="${o.ClassNo eq '1'}">selected</c:if> value="1">甲班</option>
			<option <c:if test="${o.ClassNo eq '2'}">selected</c:if> value="2">乙班</option>
			<option <c:if test="${o.ClassNo eq '3'}">selected</c:if> value="3">丙班</option>
			<option <c:if test="${o.ClassNo eq '4'}">selected</c:if> value="4">丁班</option>
		</select>
		</td>
	</tr>	
</table>


</c:forEach>
</div>
<div id="opencsTmp" style="display:none;">
<table>
	<tr>
		<td>
		<select name="cidno">
			<option value="">新增校區</option>
			<option value="*">所有校區</option>
			<c:forEach items="${allCampus}" var="c">
				<option value="${c.idno}">${c.name}</option>
			</c:forEach>
		</select>
		<select name="sidno">
			<option value="">新增學制</option>
			<option value="*">所有學制</option>
			<c:forEach items="${allSchool}" var="code5">
				<option value="${code5.idno}">${code5.name}</option>
			</c:forEach>
		</select>
		<select name="didno">
			<option value="">新增科系</option>
			<option value="*">所有科系</option>
			<c:forEach items="${allDept}" var="code5">
				<option value="${code5.idno}">${code5.name}</option>
			</c:forEach>
		</select>
		<select name="grade">
			<option value="">新增年級</option>
			<option value="*">所有年級</option>
			<option value="1">1年級</option>
			<option value="2">2年級</option>
			<option value="3">3年級</option>
			<option value="4">4年級</option>
			<option value="5">5年級</option>
		</select>
		<select name="classes">
			<option value="">新增班級</option>
			<option value="*">所有班級</option>
			<option value="1">甲班</option>
			<option value="2">乙班</option>
			<option value="3">丙班</option>
			<option value="4">丁班</option>
		</select>
		</td>
	</tr>	
</table>
</div>