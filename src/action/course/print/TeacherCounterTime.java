package action.course.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;

/**
 * 教師任教時數
 * @author John
 *
 */
public class TeacherCounterTime extends BasePrintXmlAction{
	
	public void print(HttpServletResponse response, List<Map>list) throws IOException{
		
		Date date=new Date();
		//response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
		xml2ods(response, getRequest(), date);
					
		
		PrintWriter out=response.getWriter();
		StringBuffer sb=new StringBuffer("SELECT sum(d.thour) total, e.cname FROM Dtime d LEFT OUTER JOIN empl e ON d.techid=e.idno WHERE d.Oid IN(");
		for(int i=0; i<list.size(); i++){
			sb.append("'"+list.get(i).get("Oid")+"',");
		}		
		sb.delete(sb.length()-1, sb.length());		
		sb.append(")GROUP BY d.techid");
		
		list=df.sqlGet(sb.toString());
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><style>body{background-color:#cccccc;}td{font-size:18px;color:#333333;font-family:新細明體}</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>");
		out.println("			<table bgcolor='#ffffff'><tr><td>");
		out.println("			<table border='1'>");
		out.println("<tr>");
		out.println("<td>");
		
		out.println("<table width='100%' border='1'>");
		out.println("<tr>");
		out.println("<td style='mso-number-format:\\@' nowrap>姓名</td>");
		out.println("<td style='mso-number-format:\\@' nowrap>時數</td>");
		out.println("<td style='mso-number-format:\\@' nowrap>姓名</td>");
		out.println("<td style='mso-number-format:\\@' nowrap>時數</td>");
		out.println("<td style='mso-number-format:\\@' nowrap>姓名</td>");
		out.println("<td style='mso-number-format:\\@' nowrap>時數</td>");
		out.println("<td style='mso-number-format:\\@' nowrap>姓名</td>");
		out.println("<td style='mso-number-format:\\@' nowrap>時數</td>");
		out.println("</tr>");
		int tmp=0;
		for(int i=0; i<list.size(); i++){			
			if(i>=tmp){
				tmp=tmp+4;
				out.println("<tr>");
			}
			if(list.get(i).get("cname")==null){
				out.println("<td style='mso-number-format:\\@'>未排定</td>");//name
			}else{
				out.println("<td style='mso-number-format:\\@'>"+list.get(i).get("cname")+"</td>");//name
			}
			
			out.println("<td align='center' style='mso-number-format:\\@'>"+list.get(i).get("total")+"</td>");//total thour			
			if(i>=tmp){
				out.println("</tr>");
			}
						
		}
		out.println("</table>");		
		
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println ("</body>");
		out.close();
		out.flush();		
	}

}
