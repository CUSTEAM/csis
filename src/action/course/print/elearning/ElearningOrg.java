package action.course.print.elearning;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BaseAction;

public class ElearningOrg extends BaseAction{
	
	public void print(HttpServletResponse response) throws IOException{
		Date date=new Date();
		response.setContentType("application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-disposition","attachment;filename="+date.getTime()+".xls");				
		PrintWriter out=response.getWriter();		
		out.println("<html>");
		out.println("<head><style>body{background-color:#cccccc;}td{font-size:18px;color:#333333;font-family:新細明體;mso-number-format:\\@;}</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>");
		out.println("			<table bgcolor='#ffffff'><tr><td>");
		out.println("			<table border='1'>");
		// out.println("<c:if test='${not empty dtimeList}'>"); //???
		out.println("<tr>");
		out.println("<td>");

		out
				.println("			<table border='1' align='left' cellpadding='0' cellspacing='1' width='100%'>");
		out.println("");
		out.println("				<tr height='24'>");
		out.println("					<td>身份</td>");
		out.println("					<td>所屬組織代碼</td>");
		out.println("					<td>帳號</td>");
		out.println("					<td>姓名</td>");
		out.println("					<td>密碼</td>");
		out.println("					<td>性別</td>");
		out.println("					<td>電子郵件</td>");
		out.println("					<td>身分證字號</td>");

		out.println("				</tr>");
		List<Map>students = df.sqlGet("SELECT st.student_no, st.email, st.sex, st.idno, st.student_name, c.Oid "
						+ "FROM stmd st, Class c Where st.depart_class=c.ClassNo ORDER BY st.depart_class");
		for (int j = 0; j < students.size(); j++) {
			out.println("<tr height='24'>");
			out.println("<td>4</td>");
			out.println("<td>"+ students.get(j).get("Oid") + "</td>");
			out.println("<td style='mso-number-format:\\@'>"+ students.get(j).get("student_no") + "</td>");
			out.println("<td>"+ students.get(j).get("student_name") + "</td>");
			out.println("<td></td>");
			
			if(students.get(j).get("sex").toString().equals("1")){
				out.println("<td>男</td>");
			}else{
				out.println("<td>女</td>");
			}
			

			if (students.get(j).get("email") == null) {
				out.println("<td></td>");
			} else {
				out.println("<td>"+ students.get(j).get("email") + "</td>");
			}

			out.println("<td>"
					+ ((Map) students.get(j)).get("idno") + "</td>");
			out.println("</tr>");

		}

		out.print("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body>");

		out.println("</html>");
		out.close();
	}
}