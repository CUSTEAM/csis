package action.course.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;

/**
 * 教師任教時數
 * @author John
 *
 */
public class TeacherCounterCourse extends BasePrintXmlAction{
	
	public void print(HttpServletResponse response, List<Map>dtimeList, String Sterm) throws IOException{
		StringBuilder sb=new StringBuilder("SELECT d.Oid, c.chi_name, d.credit, d.thour, cl.ClassName, e.cname FROM Dtime d, Csno c, Class cl, empl e " +
		"WHERE d.Sterm='"+Sterm+"'AND d.cscode=c.cscode AND cl.ClassNo=d.depart_class AND e.idno=d.techid AND d.cscode NOT IN('50000', 'T0001', 'T0002') AND " +
		"d.Oid IN(");
		for(int i=0; i<dtimeList.size(); i++){
			sb.append("'"+dtimeList.get(i).get("Oid")+"',");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(") ORDER BY c.chi_name");		
		List<Map>newDtimeList=df.sqlGet(sb.toString());
		List<Map>list=new ArrayList();
		Map map;
		List<Map>tmp;
		for(int i=0; i<newDtimeList.size(); i++){
			map=new HashMap();
			map.put("chi_name", newDtimeList.get(i).get("chi_name"));
			map.put("credit", newDtimeList.get(i).get("credit"));
			map.put("thour", newDtimeList.get(i).get("thour"));
			map.put("ClassName", newDtimeList.get(i).get("ClassName"));
			StringBuffer teachers=new StringBuffer(newDtimeList.get(i).get("cname").toString());
			map.put("cname", newDtimeList.get(i).get("cname"));
			if(df.sqlGetInt("SELECT COUNT(*)FROM Dtime_teacher WHERE Dtime_oid='"+newDtimeList.get(i).get("Oid")+"'")>0){
				tmp=df.sqlGet("SELECT e.cname FROM Dtime_teacher dt, empl e "+"WHERE dt.teach_id=e.idno AND dt.Dtime_oid='"+newDtimeList.get(i).get("Oid")+"'");
				for(int j=0; j<tmp.size(); j++){
					teachers.append(", "+tmp.get(j).get("cname").toString());
				}
				map.put("cname", teachers);
			}	
			list.add(map);
		}
		Date date=new Date();
		xml2ods(response, getRequest(), date);
						
		PrintWriter out=response.getWriter();		
		out.println("<html>");
		out.println("<head><style>body{background-color:#cccccc;}td{font-size:18px;color:#333333;font-family:新細明體}</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table bgcolor='#ffffff'><tr><td>");
		out.println("<table border='1'><tr>");
		out.println("<td>科目名稱</td>");
		out.println("<td>學分</td>");
		out.println("<td>時數</td>");
		out.println("<td>開課班級</td>");
		out.println("<td>任課教師</td>");
		out.println("</tr>");
		for(int i=0; i<list.size(); i++){
			out.println("<tr>");
			out.println("<td style='mso-number-format:\\@' nowrap>"+list.get(i).get("chi_name")+"</td>");
			out.println("<td style='mso-number-format:\\@' nowrap>"+list.get(i).get("credit")+"</td>");
			out.println("<td style='mso-number-format:\\@' nowrap>"+list.get(i).get("thour")+"</td>");
			out.println("<td style='mso-number-format:\\@' nowrap>"+list.get(i).get("ClassName")+"</td>");
			out.println("<td style='mso-number-format:\\@' nowrap>"+list.get(i).get("cname")+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</td></tr></table>");
		out.close();
	}
}