package action.course.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;

public class List4Seld52 extends BasePrintXmlAction{
	
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
		out.println("");
		out.println("				<tr height='20'>");
		out.println("					<td align='center' bgcolor='#f0fcd7'>學年</td>");
		out.println("					<td align='center' bgcolor='#f0fcd7'>學期</td>");
		out.println("					<td align='center' bgcolor='#f0fcd7'>學校名稱</td>");
		out.println("					<td align='center' bgcolor='#f0fcd7'>學號</td>");
		out.println("					<td align='center' bgcolor='#f0fcd7'>學分數</td>");
		out.println("					<td align='center' bgcolor='#f0fcd7'>雙主修</td>");
		out.println("					<td align='center' bgcolor='#f0fcd7'>輔系</td>");
		out.println("					<td align='center' bgcolor='#f0fcd7'>學程</td>");
		out.println("					<td align='center' bgcolor='#f0fcd7'>校際選修</td>");
		out.println("				</tr>");

		StringBuilder sb=new StringBuilder("SELECT * FROM Seld s, Dtime d WHERE d.Oid=s.Dtime_oid AND d.Sterm = '"+ 
		term+ "' "+ "AND d.Oid IN(");
		
		for(int i=0; i<dtimeList.size(); i++){
			sb.append("'"+dtimeList.get(i).get("Oid")+"',");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(")");
		
		System.out.println(sb);
		List students = df.sqlGet(sb.toString());

		

		out.println("</table>");
		out.println("</body>");

		out.println("</html>");

		out.close();
	}

}
