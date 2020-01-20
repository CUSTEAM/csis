package action.course.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;

public class List4Dtime32 extends BasePrintXmlAction{
	
	public void print(HttpServletResponse response, List<Map>dtimeList, String year, String term) throws IOException{
		
		Date date=new Date();
		xml2ods(response, getRequest(), date);	
		PrintWriter out=response.getWriter();
		
		out.println("<html>");
		out.println("<head><style>body{background-color:#cccccc;}td{font-size:18px;color:#333333;font-family:新細明體}</style>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("			<table bgcolor='#ffffff'><tr><td>");
		out.println("			<table border='1'>");
		// out.println("<c:if test='${not empty dtimeList}'>"); //???
		out.println("	<tr>");
		out.println("		<td>");

		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		out.println("			<table border='1'>");
		out.println("				<tr height='20'>");
		out.println("					<td align='center'>學年</td>");
		out.println("					<td align='center'>學期</td>");
		out.println("					<td align='center'>開課學院</td>");
		out.println("					<td align='center'>開課系所</td>");
		out.println("					<td align='center'>學制</td>");
		out.println("					<td align='center'>學期課號</td>");
		out.println("					<td align='center'>課程名稱</td>");
		out.println("					<td align='center'>修別</td>");
		out.println("					<td align='center'>講授時數(每週)</td>");
		out.println("					<td align='center'>實習時數(每週)</td>");
		out.println("					<td align='center'>總學分數</td>");
		out.println("					<td align='center'>授課教師</td>");
		out.println("					<td align='center'>修課人數</td>");
		out.println("					<td align='center'>外語授課</td>");
		out.println("					<td align='center'>授課語言</td>");
		out.println("					<td align='center'>專業課程</td>");
		out.println("				</tr>");
		
		StringBuilder sb=new StringBuilder("SELECT cc.name as schName, c.DeptNo,c.CampusNo, c.SchoolNo, c.ClassNo,c.graduate, d.thour, d.credit,e.unit as idno2, IFNULL(e.cname,'')as cname, cs.chi_name, "
				+ "d.cscode, d.techid, cdo.name as opt2, d.thour, d.credit, (SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid)as cnt FROM "
				+ "Dtime d ,empl e, CODE_COLLEGE cc,"
				+ "CODE_DTIME_OPT cdo, Csno cs, Class c WHERE cc.id=c.InstNo AND d.techid=e.idno AND c.ClassNo=d.depart_class AND cs.cscode=d.cscode AND "
				+ "d.opt=cdo.id AND d.Oid IN(");
		for(int i=0; i<dtimeList.size(); i++){
			sb.append("'"+dtimeList.get(i).get("Oid")+"',");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(")");
		
		dtimeList=df.sqlGet(sb.toString());
		
		List<Map>pecode9=df.sqlGet("SELECT * FROM CODE_PE9");
		List<Map>pecode11=df.sqlGet("SELECT * FROM CODE_PE11");
		String tmpPcode111, tmpPcode112,departClass,tmpDepartClass;
		for (int i = 0; i < dtimeList.size(); i++) {
			departClass=dtimeList.get(i).get("ClassNo").toString();
			tmpDepartClass = departClass.toString().substring(0, 3);
			// TODO 尚未定義學期劃分方法
			out.println("				<tr height='20'>");
			out.println("					<td align='center'>"+year+"</td>");
			out.println("					<td align='center'>"+term+"</td>");
			// 開課學院
			out.println("					<td align='center'>"+dtimeList.get(i).get("schName")+"</td>");
			// 開課系所
			out.println("					<td align='center'>");
			for (int k = 0; k < pecode9.size(); k++) {
				if (pecode9.get(k).get("id1").toString().equals(dtimeList.get(i).get("DeptNo").toString())) {
					out.println(pecode9.get(k).get("id"));
				}
			}
			out.println("</td>");
			// 學制
			out.println("					<td align='center'>");
			// 取得授課學制
			for (int l = 0; l < pecode11.size(); l++) {
				//System.out.println(pecode11.get(l));
				if (pecode11.get(l).get("id1").toString().equals(dtimeList.get(i).get("SchoolNo").toString())) {
					tmpPcode111 = pecode11.get(l).get("id1").toString();
					//tmpPcode112 = pecode11.get(l).get("id2").toString();
					//if (tmpPcode111.equals(tmpDepartClass)||tmpPcode112.equals(tmpDepartClass)) {
						out.println(pecode11.get(l).get("id"));
					//}
				}
			}
			out.print("</td>");
			// 課程代碼
			out.println("					<td align='center'>"+dtimeList.get(i).get("cscode") + "</td>");
			// 課程名稱
			out.println("					<td>"+dtimeList.get(i).get("chi_name") + "</td>");
			// 修別
			out.println("					<td align='center'>"+dtimeList.get(i).get("opt2") + "</td>");
			// 時數
			out.println("					<td align='center'>"+dtimeList.get(i).get("thour") + "</td>");
			// 實習時數
			out.println("					<td align='center'>0</td>");
			// 總學分數
			out.println("					<td align='center'>"+dtimeList.get(i).get("credit") + "</td>");
			// 授課教師
			out.println("					<td align='center'>"+dtimeList.get(i).get("cname") + "</td>");
			// 修課人數
			out.println("					<td align='center'>"+dtimeList.get(i).get("cnt") + "</td>");
			// 外語授課
			out.println("					<td align='center'>");
			//if (dtimeList.get(i).get("chi_name").toString().indexOf("日文") > 0) {
				//System.out.println("Japan");
			//}
			String language = "國語";
			if ((dtimeList.get(i).get("chi_name").toString().indexOf("日文") >= 0 || dtimeList.get(i).get("chi_name").toString().indexOf("日語") >= 0)) {
				language = "日語";
				out.print("是</td>");
			} else if ((dtimeList.get(i)).get("chi_name").toString().indexOf("英文") >= 0 || dtimeList.get(i).get("chi_name").toString().indexOf("英語") >= 0) {
				language = "英語";
				out.print("是</td>");
			} else {
				out.print("否</td>");
			}
			// 授課語言
			out.println("					<td align='center'>"+language + "</td>");
			// 專業課程
			out.println("					<td align='center'></td>");
		}

		out.println("	</tr>");
		out.println("</table></td></tr></table>");
		out.println("</body>");
		out.println("</html>");

		out.close();
		
	}

}
