package action.course.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BaseAction;

public class List4DtimeG extends BaseAction{
	
	public void print(HttpServletResponse response, List<Map>dtimeList, String year, String term) throws IOException{
		
		Date date=new Date();
		response.setContentType("application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-disposition","attachment;filename="+date.getTime()+".xls");				
		PrintWriter out=response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta http-equiv=application/vnd.ms-excel; charset=UTF-8><style>body{background-color:#cccccc;}td{font-size:18px;color:#333333;font-family:新細明體}</style>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("			<table bgcolor='#ffffff'><tr><td>");
		out.println("			<table border='1'>");
		out.println("<tr>");
		
		out.println("<td align='center'>課程名稱</td>");
		out.println("<td align='center'>英文課程名稱</td>");
		out.println("<td align='center'>科系</td>");
		out.println("<td align='center'>科系英文名稱</td>");
		out.println("<td align='center'>學程名稱</td>");
		out.println("<td align='center'>組別</td>");
		out.println("<td align='center'>年級</td>");
		out.println("<td align='center'>班級</td>");
		out.println("<td align='center'>開課老師</td>");
		out.println("<td align='center'>報部文號</td>");
		out.println("<td align='center'>學分數</td>");
		out.println("<td align='center'>開課時數</td>");
		out.println("<td align='center'>實習時數</td>");
		out.println("<td align='center'>課程摘要</td>");
		out.println("<td align='center'>課程大綱</td>");
		out.println("<td align='center'>課程連結</td>");
		out.println("<td align='center'>備註</td>");
		out.println("<td align='center'>填表人</td>");
		out.println("<td align='center'>男</td>");
		out.println("<td align='center'>女</td>");
		out.println("<td align='center'>授課語言1</td>");
		out.println("<td align='center'>授課語言2</td>");
		out.println("<td align='center'>證照1</td>");
		out.println("<td align='center'>證照2</td>");
		out.println("<td align='center'>部別碼</td>");
		out.println("<td align='center'>學制碼 </td>");
		out.println("<td align='center'>部校訂碼</td>");
		out.println("<td align='center'>半全年碼</td>");
		out.println("<td align='center'>科目類別</td>");
		out.println("<td align='center'>教學型態</td>");
		out.println("<td align='center'>必選修碼</td>");
		out.println("<td align='center'>師資來源碼</td>");
		out.println("<td align='center'>全英語教學碼</td>");	
		out.println("<td align='center'>班級代碼</td>");
		out.println("<td align='center'>課程代碼</td>");

/*		out.println("<td>課程編碼ID</td>");
		out.println("<td align='center'>年度</td>");
		out.println("<td align='center'>學期</td>");
		out.println("<td align='center'>職類</td>");
		out.println("<td align='center'>已選人數</td>");
		out.println("<td align='center'>填表時間</td>");
		out.println("<td align='center'>上課地點</td>");
		out.println("<td align='center'>教育部承辦人</td>");
		out.println("<td align='center'>學校承辦人</td>");
		out.println("<td align='center'>科系代碼</td>");
		out.println("<td align='center'>管轄單位</td>");
		out.println("<td align='center'>回文文號</td>");*/

		out.println("</tr>");

		StringBuilder sb=new StringBuilder("SELECT cd.ename, d.Oid, d.elearning, cs.eng_name, c.Grade, c.DeptNo, cd.name as DeptName,c.CampusNo, c.SchoolNo, "
				+ "c.ClassNo, c.ShortName, c.graduate, d.thour, d.credit, (SELECT unit FROM empl WHERE idno=d.techid)as idno2, (SELECT cname FROM empl WHERE idno=d.techid)as cname, cs.chi_name, "
				+ "d.cscode, d.techid as techid, cdo.name as opt2, d.thour, d.credit, (SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid)as cnt FROM "
				+ "Dtime d ,CODE_DEPT cd,"
				+ "CODE_DTIME_OPT cdo, Csno cs, Class c WHERE cd.id=c.DeptNo AND c.ClassNo=d.depart_class AND cs.cscode=d.cscode AND "
				+ "d.opt=cdo.id AND d.Oid IN(");
		for(int i=0; i<dtimeList.size(); i++){
			sb.append("'"+dtimeList.get(i).get("Oid")+"',");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(") ORDER BY d.depart_class,d.cscode");

		String tmpPcode111, tmpPcode112,departClass,tmpDepartClass;
		dtimeList=df.sqlGet(sb.toString());
		List<Map>pecode9=df.sqlGet("SELECT * FROM CODE_PE9");
		List<Map>pecode11=df.sqlGet("SELECT * FROM CODE_PE11");
		
		String allYear,cscode, schoolName,schoolType;
		boolean practical = false;
		List<Map>csGroup;
		
		List<Map>dtimeTeacher;
		for (int i = 0; i < dtimeList.size(); i++) {

			if (!dtimeList.get(i).get("cscode").toString().equals("50000")//排除班會
					&& !dtimeList.get(i).get("cscode").toString().equals("T0001")//排除通識
					&& !dtimeList.get(i).get("cscode").toString().equals("T0002")) {

				String schoolTerm; // 學期轉型
				if (term.equals("1")) {
					schoolTerm = "1";
				} else {
					schoolTerm = "2";
				}

				// 部制
				schoolName = dtimeList.get(i).get("SchoolNo").toString();
				schoolType = "";
				if (schoolName.equals("12")) {
					schoolName = "0";
					schoolType = "1002";
				} else if (schoolName.equals("15")) {
					schoolType = "1003";
					schoolName = "0";
				} else if (schoolName.equals("1G")) {
					schoolType = "1006";
					schoolName = "0";
				} else if (schoolName.equals("22")) {
					schoolType = "1002";
					schoolName = "1";
				} else if (schoolName.equals("32")) {
					schoolName = "2";
					schoolType = "1002";
				} else if (schoolName.equals("42")) {
					schoolType = "1001";
					schoolName = "0";
				} else if (schoolName.equals("52")) {
					schoolType = "1001";
					schoolName = "1";
				} else if (schoolName.equals("54")) {
					schoolType = "1005";
					schoolName = "1";
				} else if (schoolName.equals("64")) {
					schoolType = "1005";
					schoolName = "0";
				} else if (schoolName.equals("72")) {
					schoolType = "1001";
					schoolName = "2";
				} else if (schoolName.equals("82")) {
					schoolType = "1001";
					schoolName = "1";
				} else if (schoolName.equals("8G")) {
					schoolType = "1006";
					schoolName = "1";
				} else if (schoolName.equals("92")) {
					schoolType = "1002";
					schoolName = "1";
				}

				out.println("<tr>");
				
				out.print("<td>" + dtimeList.get(i).get("chi_name")+ "</td>");              // 課程名稱
				out.print("<td nowrap>"+ dtimeList.get(i).get("eng_name") + "</td>");       // 英文課程名稱
				out.print("<td align='center'>"+ dtimeList.get(i).get("DeptName")+"</td>"); // 科系
				out.print("<td align='center'>"+dtimeList.get(i).get("ename")+"</td>");     // 科系英文名稱
				
				// 學程名稱
				csGroup = df.sqlGet("SELECT cg.cname FROM CsGroupSet cgs, CsGroup cg WHERE "
				+ "cgs.group_oid=cg.Oid AND cgs.cscode='"+ dtimeList.get(i).get("cscode")+"'GROUP BY cg.Oid");
				sb = new StringBuilder();
				if (csGroup.size() > 0) {
					for (int j = 0; j < csGroup.size(); j++) { sb.append(csGroup.get(j).get("cname") + ", "); }
				}out.print("<td align='left'>" + sb + "</td>");
				
				out.print("<td align='center'></td>");                                      // 組別
				out.print("<td align='center'>"+ dtimeList.get(i).get("Grade")+ "</td>");   // 年級
				out.print("<td align='center'>"+ dtimeList.get(i).get("ShortName").toString().charAt(dtimeList.get(i).get("ShortName").toString().length()-1) + "</td>"); // 班級

				// 開課老師
				sb = new StringBuilder(); 
				dtimeTeacher =df.sqlGet("SELECT e.cname FROM empl e, Dtime_teacher d WHERE e.idno=d.teach_id AND d.Dtime_oid='"+ dtimeList.get(i).get("Oid") + "'");
				if (dtimeTeacher.size() > 0) {
					for (int j = 0; j < dtimeTeacher.size(); j++) {
						sb.append(dtimeTeacher.get(j).get("cname")+ ", ");
					}
					if(dtimeList.get(i).get("cname")==null){
						out.print("<td align='center'>"+ sb.toString() + "</td>");
					}else{
						out.print("<td align='center'>"+ dtimeList.get(i).get("cname") + ", "+ sb.toString() + "</td>");
					}
				} else {
					if(dtimeList.get(i).get("cname")==null){
						out.print("<td align='center'></td>");
					}else{
						out.print("<td align='center'>"+ dtimeList.get(i).get("cname")+ "</td>");
					}
				}
				
				// 報部文號
				out.print("<td align='center'></td>");
				
				// 學分數
				out.print("<td align='center'>"+dtimeList.get(i).get("credit") + "</td>");
				
				// 上課時數				
				out.print("<td align='center'>"+ dtimeList.get(i).get("thour") + "</td>"); 
				practical = false;
				
				// 實習時數
				if (dtimeList.get(i).get("chi_name").toString().indexOf("實習") == -1) {    
					out.print("<td align='center'>0</td>");
				} else {
					out.print("<td align='center'>"+ Float.parseFloat(dtimeList.get(i).get("thour").toString())  + "</td>");
					practical = true;
				}
		
				out.print("<td nowrap>http://ap.cust.edu.tw/CIS/Print/teacher/IntorDoc.do?Oid="+ dtimeList.get(i).get("Oid") + "</td>"); // 課程摘要
				out.print("<td nowrap>http://ap.cust.edu.tw/CIS/Print/teacher/SylDoc.do?Oid="+   dtimeList.get(i).get("Oid") + "</td>"); // 課程大綱
				out.print("<td nowrap>http://ap.cust.edu.tw/CIS/Print/teacher/IntorDoc.do?Oid="+ dtimeList.get(i).get("Oid") + "</td>"); // 課程連結

				// 備註
				out.print("<td align='center'></td>");
				// 填表人
				out.print("<td align='center'></td>");
				// 男
				out.println("<td align='center'>"+ df.sqlGetStr("SELECT COUNT(*)FROM Seld s, Dtime d, stmd st WHERE "
						+ "s.Dtime_oid=d.Oid AND s.student_no=st.student_no AND st.sex='1' AND d.Oid='"
						+ dtimeList.get(i).get("Oid") + "'")+ "</td>");
				// 女
				out.println("<td align='center'>"+ df.sqlGetStr("SELECT COUNT(*)FROM Seld s, Dtime d, stmd st WHERE "+ "s.Dtime_oid=d.Oid AND s.student_no=st.student_no AND st.sex='2' AND d.Oid='"+ dtimeList.get(i).get("Oid") + "'")+ "</td>");
				
				String language = "國語";
				if ((dtimeList.get(i)).get("chi_name").toString().indexOf("日文") >= 0 || dtimeList.get(i).get("chi_name").toString().indexOf("日語") >= 0) {
					language = "日語";
				} else if ((dtimeList.get(i)).get("chi_name").toString().indexOf("英文") >= 0 || dtimeList.get(i).get("chi_name").toString().indexOf("英語") >= 0) {
					language = "英語";
				}
				// 授課語言1
				out.print("<td align='center' nowrap>" + language + "</td>");
				String language1 = "";
				if (!language.equals("國語")) {
					language1 = "國語";
				}
				// 授課語言2
				out.print("<td align='center' nowrap>" + language1 + "</td>");

				out.print("<td align='center'></td>"); // 證照1
				out.print("<td align='center'></td>"); // 證照2
	
				out.print("<td align='center'>" + schoolName + "</td>");// 部別代碼
				out.print("<td align='center'>" + schoolType + "</td>");// 學制代碼
				out.print("<td align='center'>1</td>");// 部校訂碼

				/*allYear = "0";// 有連號的課程為全年
				cscode = dtimeList.get(i).get("cscode").toString();
				if (cscode.charAt(cscode.length() - 1) != '0') {
					allYear = "1";
				}*/
				
				out.print("<td align='center'>0</td>");// 半全年碼(20141016全設定為零)
				out.print("<td align='center'></td>"); // 科目類別碼
				
				// 教學型態碼
				String type = "1";
				if (dtimeList.get(i).get("elearning").toString().equals("1")) {
					type = "4";
				}
				if (dtimeList.get(i).get("elearning").equals("2")) {
					type = "8";//輔助教學
				}
				if (practical) {
					type = "5";//實習
				}
				out.print("<td align='center'>" + type + "</td>"); 
				
				// 必選修碼
				if (dtimeList.get(i).get("opt2").equals("必修")) {
					out.print("<td align='center'>0</td>");
				} else {
					out.print("<td align='center'>1</td>");
				}
				
				// 師資來源碼
				if(dtimeList.get(i).get("techid")!=null){
					if (df.sqlGetStr("SELECT unit FROM empl WHERE idno='"+dtimeList.get(i).get("techid")+"'").equals("0")) {
						out.println("<td align='center'>2</td>");//通識老師
					} else {
						out.println("<td align='center'>1</td>");//專業老師
					}
				}else{
					out.println("<td align='center'></td>");
				}
				
				out.print("<td align='center'>0</td>"); // 全英語教學碼
				
				/*out.print("<td>" + dtimeList.get(i).get("Oid")+ "</td>");// 課程OID
				out.print("<td align='center'>" + year+ "</td>");// 學年
				out.print("<td align='center'>" + schoolTerm + "</td>");// 學期
				
				out.print("<td align='center'>");// 職類
				for (int k = 0; k < pecode9.size(); k++) {
					if (pecode9.get(k).get("id1").toString().equals(dtimeList.get(i).get("DeptNo").toString())) {
						out.println(pecode9.get(k).get("id"));
					}
				}
				out.print("</td>");



				// 已選人數
				out.print("<td align='center'>"+ dtimeList.get(i).get("cnt")+ "</td>"); 
				out.print("<td align='center'></td>"); // 填表時間
				// 上課地點
				if (dtimeList.get(i).get("CampusNo").toString().equals("2")) {
					out.print("<td align='center' nowrap>新竹校區</td>");
				}else{
					out.print("<td align='center' nowrap>台北校區</td>");
				}				
				
				out.print("<td align='center'>汪佳佩</td>");// 教育部承辦人
				out.print("<td align='center'></td>");    // 學校承辦人
				out.print("<td align='center'></td>");    // 科系代碼				
				out.print("<td align='center'>技職司</td>");// 管轄單位
				out.print("<td align='center'></td>");    // 回文文號 */
				
				out.print("<td align='center'>'"+dtimeList.get(i).get("ClassNo").toString()+"</td>");
				out.print("<td align='center'>"+dtimeList.get(i).get("cscode").toString()+"</td>");
				
				
				//System.out.println("Select dp.engname From dept dp, Dtime d, Class c " +"Where d.depart_class=c.ClassNo And c.Dept=dp.no " +
						//"  And d.Oid='"+ dtimeList.get(i).get("Oid") + "'");
				

				
				
				
				out.println("	</tr>");
			}
		}

		out.println("</table></td></tr></table>");
		out.println("</body>");
		out.println("</html>");
		out.close();
		
	}
	
	

}
