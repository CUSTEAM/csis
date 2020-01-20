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
public class CourseCounterTeacher extends BasePrintXmlAction{
	
	public void print(HttpServletResponse response, List<Map>dtimeList, String Sterm) throws IOException{
		
		Date date=new Date();
		xml2ods(response, getRequest(), date);
		PrintWriter out=response.getWriter();
		
		StringBuffer strbuf=new StringBuffer("SELECT d.techid, e.cname FROM Dtime d, empl e WHERE " +
		//"d.Sterm='"+sterm+"' AND e.idno=d.techid AND d.Oid IN(");
		"e.idno=d.techid AND d.Oid IN(");
		for(int i=0; i<dtimeList.size(); i++){
			strbuf.append("'"+((Map)dtimeList.get(i)).get("oid")+"',");
		}
		strbuf.delete(strbuf.length()-1, strbuf.length());
		strbuf.append(") GROUP BY d.techid");
		
		List cscodeList=df.sqlGet(strbuf.toString());
		
		out.println("<html>");
		out.println("<head><style>body{background-color:#cccccc;}td{font-size:18px;color:#333333;font-family:新細明體}</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table bgcolor='#ffffff'><tr><td>");
		for(int i=0; i<cscodeList.size(); i++){		
			
			
			List myCs=df.sqlGet("SELECT cl.ClassName, c.chi_name FROM " +
					"((Dtime d LEFT OUTER JOIN Class cl ON d.depart_class=cl.ClassNo)LEFT OUTER JOIN " +
					"Csno c ON d.cscode=c.cscode) WHERE d.Sterm='"+Sterm+"' AND d.techid='"+((Map)cscodeList.get(i)).get("techid")+"'");
			
			out.println("<table border='1' align='left'>");
			out.println("<tr>");
			out.println("<td colspan='2' style='mso-number-format:\\@' nowrap>教師姓名:"+((Map)cscodeList.get(i)).get("cname")+"</td>");
			out.println("</tr>");
			
			int tmp=0;
			
			for(int j=0; j<myCs.size(); j++){
				
				if(j>=tmp){
					tmp=tmp+2;
				out.println("<tr>");
				}
				
				out.println("<td align='right' style='mso-number-format:\\@' nowrap>"+((Map)myCs.get(j)).get("ClassName")+"</td>");
				out.println("<td align='left' style='mso-number-format:\\@' nowrap>"+((Map)myCs.get(j)).get("chi_name")+"</td>");
				
				if(j>=tmp){					
					out.println("</tr>");
				}
			}
			
			out.println("</table>");
			
			
		}
		
		out.close();
		
		
	}

}
