package action.course.print.elearning;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BaseAction;

public class ElearningCou extends BaseAction{
	
	public void print(HttpServletResponse response, List<Map>dtimeList, String schoolYear, String schoolTerm) throws IOException{
		Date date=new Date();
		response.setContentType("application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-disposition","attachment;filename="+date.getTime()+".xls");				
		PrintWriter out=response.getWriter();			
		
		out.println("<html>");
		out.println("<head><style>body{background-color:#cccccc;}td{font-size:18px;color:#333333;font-family:新細明體}</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>");
		out.println("			<table bgcolor='#ffffff'><tr><td>");
		out.println("			<table border='1'>");
		out.println("");
		out.println("				<tr height='24'>");
		out.println("					<td align='center'>科目代碼</td>");
		out.println("					<td align='center'>科目名稱</td>");
		out.println("					<td align='center'>本校科目代碼</td>");
		out.println("					<td align='center'>本校班級代碼</td>");
		out.println("					<td align='center'>開課班級</td>");

		out.println("					<td align='center'>授課教師</td>");
		out.println("					<td align='center'>代理教師</td>");
		out.println("					<td align='center'>開放日期</td>");
		out.println("					<td align='center'>結束日期</td>");
		out.println("					<td align='center'>科目簡介</td>");

		out.println("				</tr>");
		
		//System.out.println(getContext().getAttribute("DBbackup"));
		
		//String schoolYear = (String) getContext().getAttribute("school_year");
		//String schoolTerm = (String) getContext().getAttribute("school_term");
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());

		if (c.get(Calendar.MONTH) > 7 && c.get(Calendar.MONTH) < 8) {
			schoolYear = schoolYear + 1;
		}
		

		for (int i = 0; i < dtimeList.size(); i++) {

			List<Map>courses = df.sqlGet("SELECT d.depart_class, d.cscode, d.Oid, e.cname, c.Oid as csnOid, c.chi_name, cl.Oid as clOid, d.techid, cl.ClassName "
							+ "FROM Csno c, Class cl, Dtime d LEFT OUTER JOIN empl e ON d.techid=e.idno WHERE cl.ClassNo=d.depart_class AND "
							+ "d.cscode=c.cscode AND d.Oid="
							+ dtimeList.get(i).get("oid"));

			for (int j = 0; j < courses.size(); j++) {
				out.println("<tr height='24'>");String Oid = ((Map) courses.get(j)).get("Oid").toString();
				int tmp = Oid.length();
				try {
					for (int k = 0; k < 5 - tmp; k++) {
						Oid = "0" + Oid;
					}
				} catch (Exception e) {
					Oid = "ERROR";
				}

				out.println("<td align='center'>" + schoolYear + schoolTerm+ Oid + "</td>");
				out.println("<td align='left'>"+ courses.get(j).get("chi_name") + "("+ courses.get(j).get("ClassName") + ")</td>");
				out.println("<td align='center'>"+ courses.get(j).get("cscode") + "</td>");
				out.println("<td align='center'>"+ courses.get(j).get("depart_class") + "</td>");
				out.println("<td align='center'>"+ courses.get(j).get("clOid") + "</td>");
				if (courses.get(j).get("cname") != null) {
					out.println("<td align='center'>"+ courses.get(j).get("cname") + "</td>");
				} else {
					out.println("<td align='center'></td>");
				}

				out.println("<td align='center'></td>");
				out.println("<td align='center'></td>");
				out.println("<td align='center'></td>");
				out.println("<td align='center'></td>");
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