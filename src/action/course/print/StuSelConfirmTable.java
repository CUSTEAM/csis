package action.course.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;

public class StuSelConfirmTable extends BasePrintXmlAction{
	
	public void print(HttpServletResponse response, List<Map>dtimeList, String year, String thisTerm, String term) throws IOException{
		
		Date date=new Date();
		xml2ods(response, getRequest(), date);
						
		PrintWriter out=response.getWriter();
		
		boolean upgrade=false;
		if(thisTerm.equals("2")&&term.equals("1")){
			year=String.valueOf(Integer.parseInt(year)+1);
			upgrade=true;
		}
		
		out.println ("<?xml version='1.0'?>");
		out.println ("<?mso-application progid='Excel.Sheet'?>");
		out.println ("<Workbook xmlns='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:o='urn:schemas-microsoft-com:office:office'");
		out.println (" xmlns:x='urn:schemas-microsoft-com:office:excel'");
		out.println (" xmlns:ss='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:html='http://www.w3.org/TR/REC-html40'>");
		out.println (" <DocumentProperties xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <Author>shawn</Author>");
		out.println ("  <LastAuthor>shawn</LastAuthor>");
		out.println ("  <LastPrinted>2014-04-28T02:36:14Z</LastPrinted>");
		out.println ("  <Created>2014-04-28T02:22:25Z</Created>");
		out.println ("  <LastSaved>2014-04-28T02:40:51Z</LastSaved>");
		out.println ("  <Version>15.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <AllowPNG/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>11880</WindowHeight>");
		out.println ("  <WindowWidth>28800</WindowWidth>");
		out.println ("  <WindowTopX>0</WindowTopX>");
		out.println ("  <WindowTopY>0</WindowTopY>");
		out.println ("  <ProtectStructure>False</ProtectStructure>");
		out.println ("  <ProtectWindows>False</ProtectWindows>");
		out.println (" </ExcelWorkbook>");
		out.println (" <Styles>");
		out.println ("  <Style ss:ID='Default' ss:Name='Normal'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat/>");
		out.println ("   <Protection/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s68'>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s76'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println (" </Styles>");
		
		
		
		StringBuilder sb=new StringBuilder("SELECT c.ClassNo, c.ClassName FROM Class c, Dtime d WHERE c.Type='P'AND c.ClassNo=d.depart_class AND d.Oid IN(");
		if(dtimeList.size()<1)return;
		for(int i=0; i<dtimeList.size(); i++){
			sb.append("'"+dtimeList.get(i).get("Oid")+"',");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(")GROUP BY c.ClassNo ORDER BY c.ClassNo");
		
		dtimeList=df.sqlGet(sb.toString());
		
		List<Map>stds;
		
		for(int i=0; i<dtimeList.size(); i++){
			
			stds=df.sqlGet("SELECT st.student_no, st.student_name, SUM(d.credit)as credit,"
					+ "SUM(d.thour)as thour, COUNT(*)as cnt FROM stmd st,Seld s,Dtime d WHERE "
					+ "d.Sterm='"+term+"'AND st.student_no=s.student_no AND s.Dtime_oid=d.Oid AND "
					+ "st.depart_class='"+dtimeList.get(i).get("ClassNo")+"' GROUP BY st.student_no ORDER BY st.student_no");
			
			
			out.println (" <Worksheet ss:Name='"+dtimeList.get(i).get("ClassNo")+"'>");
			out.println ("  <Table ss:ExpandedColumnCount='6' ss:ExpandedRowCount='"+stds.size()+10+"' x:FullColumns='1'");
			out.println ("   x:FullRows='1' ss:StyleID='s68' ss:DefaultColumnWidth='54'");
			out.println ("   ss:DefaultRowHeight='15.75'>");
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='90' ss:Span='1'/>");
			out.println ("   <Column ss:Index='3' ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='75'");
			out.println ("    ss:Span='2'/>");
			out.println ("   <Column ss:Index='6' ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='188.25'/>");
			
			
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>學號</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>姓名</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>課程數</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>時數</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>學分</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>簽名</Data></Cell>");
			out.println ("   </Row>");
			for(int j=0; j<stds.size(); j++){
				out.println ("   <Row>");
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("student_no")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("student_name")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("cnt")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("thour")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>"+stds.get(j).get("credit")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
				out.println ("   </Row>");
			}
			
			out.println ("  </Table>");
			out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
			out.println ("   <PageSetup>");
			if(!upgrade){
				out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;微軟正黑體,標準&quot;&amp;20 "+year+"學年第"+term+"學期"+dtimeList.get(i).get("ClassName")+"選課簽收單'/>");
			}else{
				out.println ("    <Header x:Margin='0.3' x:Data='&amp;C&amp;&quot;微軟正黑體,標準&quot;&amp;20 "+year+"學年第"+term+"學期"+dtimeList.get(i).get("ClassName")+"升級後選課簽收單'/>");
			}
			
			out.println ("    <Footer x:Margin='0.2'/>");
			out.println ("    <PageMargins x:Bottom='0.2' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
			out.println ("   </PageSetup>");
			out.println ("   <Print>");
			out.println ("    <ValidPrinterInfo/>");
			out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
			out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
			out.println ("    <VerticalResolution>-1</VerticalResolution>");
			out.println ("   </Print>");
			out.println ("   <Selected/>");
			out.println ("   <Panes>");
			out.println ("    <Pane>");
			out.println ("     <Number>3</Number>");
			out.println ("     <ActiveRow>5</ActiveRow>");
			out.println ("     <ActiveCol>2</ActiveCol>");
			out.println ("    </Pane>");
			out.println ("   </Panes>");
			out.println ("   <ProtectObjects>False</ProtectObjects>");
			out.println ("   <ProtectScenarios>False</ProtectScenarios>");
			out.println ("  </WorksheetOptions>");
			out.println (" </Worksheet>");
			
			
		}
		
		
		out.println ("</Workbook>");
		out.println ("");
		
	}
		
		
		
		
}
