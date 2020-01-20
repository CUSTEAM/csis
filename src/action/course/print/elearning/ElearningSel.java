package action.course.print.elearning;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;

public class ElearningSel extends BasePrintXmlAction{
	
	public void print(HttpServletResponse response, List<Map>dtimeList, String schoolYear, String schoolTerm) throws IOException{
		Date date=new Date();
		xml2ods(response, getRequest(), date);
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><style>body{background-color:#cccccc;}td{font-size:18px;color:#333333;font-family:新細明體}</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>");
		out.println("			<table bgcolor='#ffffff'><tr><td>");
		out.println("			<table border='1'>");
		out.println("<tr>");
		out.println("<td>");
		out.println("			<table border='1'>");
		out.println("				<tr height='20'>");
		out.println("					<td align='center'>科目代碼</td>");
		out.println("					<td align='center'>學生帳號</td>");
		out.println("					<td align='center'>必選修</td>");

		out.println("				</tr>");
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		if (c.get(Calendar.MONTH) > 7 && c.get(Calendar.MONTH) < 8) {
			schoolYear = schoolYear + 1;
		}
		
		for (int i = 0; i < dtimeList.size(); i++) {

			List students = df.sqlGet("SELECT d.Oid, cs.Oid as csnOid, cl.Oid as clOid, s.student_no "
							+ "FROM Dtime d, Seld s, Csno cs, Class cl WHERE d.Oid=s.Dtime_oid AND "
							+ "d.cscode=cs.cscode AND cl.ClassNo=d.depart_class AND d.Oid="
							+ ((Map) dtimeList.get(i)).get("oid"));

			for (int j = 0; j < students.size(); j++) {
				
				out.println("<tr height='20'>");
				
				// 舊版以班代+課代以獲得最大長度
				// out.println("<td
				// align='center'>"+schoolYear+schoolTerm+((Map)students.get(j)).get("csnOid")+((Map)students.get(j)).get("clOid")+"</td>");
				String Oid = ((Map) students.get(j)).get("Oid").toString();
				int tmp = Oid.length();

				try {
					for (int k = 0; k < 5 - tmp; k++) {
						Oid = "0" + Oid;
					}
				} catch (Exception e) {
					Oid = "ERROR";
				}

				out.println("<td align='center'>" + schoolYear + schoolTerm
						+ Oid + "</td>");
				out.println("<td align='center' style='mso-number-format:\\@'>"
						+ ((Map) students.get(j)).get("student_no") + "</td>");

				out.println("<td align='center'>1</td>");
				out.println("</tr>");

			}

		}
		out.println("</table>");
		out.print("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body>");

		out.println("</html>");
		out.close();
		
		
	}
}