package action.course.print.elearning;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;

public class ElearningPer extends BasePrintXmlAction{
	
	public void print(HttpServletResponse response) throws IOException{
		Date date=new Date();
		xml2ods(response, getRequest(), date);
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
		out.println("				<tr height='24'>");
		out.println("					<td>組織代碼</td>");
		out.println("					<td>組織名稱</td>");
		out.println("					<td>組織主管帳號</td>");
		out.println("					<td>組織代理人帳號</td>");
		out.println("					<td>上層組織代碼</td>");

		out.println("				</tr>");
		List<Map>classes = df.sqlGet("SELECT cl.Oid, cl.ClassName FROM stmd st, Class cl WHERE st.depart_class=cl.ClassNo GROUP BY st.depart_class");
		for (int j = 0; j < classes.size(); j++) {			
			out.println("<tr height='24'>");
			out.println("<td>"+classes.get(j).get("Oid") + "</td>");
			out.println("<td>"+classes.get(j).get("ClassName") + "</td>");
			out.println("<td></td>");
			out.println("<td></td>");
			out.println("<td></td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</html>");
		out.close();
	}
}