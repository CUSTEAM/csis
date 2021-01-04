package action.course.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BasePrintXmlAction;

public class List4211 extends BasePrintXmlAction{
	
	public void print(HttpServletResponse response, String cno, String sno, String dno, String gno, String zno, String year, String term) throws IOException{
		
		Date date=new Date();
		
		xml2ods(response, getRequest(), date);
		
		PrintWriter out=response.getWriter();
		
		
		out.println ("<?xml version='1.0'?>");
		out.println ("<?mso-application progid='Excel.Sheet'?>");
		out.println ("<Workbook xmlns='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:o='urn:schemas-microsoft-com:office:office'");
		out.println (" xmlns:x='urn:schemas-microsoft-com:office:excel'");
		out.println (" xmlns:ss='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:html='http://www.w3.org/TR/REC-html40'>");
		out.println (" <DocumentProperties xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <Author>John</Author>");
		out.println ("  <LastAuthor>John</LastAuthor>");
		out.println ("  <Created>2019-09-11T00:46:59Z</Created>");
		out.println ("  <Version>15.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <AllowPNG/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>12315</WindowHeight>");
		out.println ("  <WindowWidth>29010</WindowWidth>");
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
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s69'>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println (" </Styles>");
		out.println (" <Worksheet ss:Name='工作表1'>");
		out.println ("  <Table ss:ExpandedColumnCount='6' ss:ExpandedRowCount='999' x:FullColumns='1'");
		out.println ("   x:FullRows='1' ss:StyleID='s69' ss:DefaultColumnWidth='54'");
		out.println ("   ss:DefaultRowHeight='15.75'>");
		out.println ("   <Column ss:StyleID='s69' ss:Width='73.5'/>");
		out.println ("   <Column ss:StyleID='s69' ss:AutoFitWidth='0' ss:Width='84.75'/>");
		out.println ("   <Column ss:StyleID='s69' ss:AutoFitWidth='0' ss:Width='150'/>");
		out.println ("   <Column ss:StyleID='s69' ss:AutoFitWidth='0' ss:Width='112.5'/>");
		out.println ("   <Column ss:StyleID='s69' ss:AutoFitWidth='0' ss:Width='61.5'/>");
		out.println ("   <Column ss:StyleID='s69' ss:AutoFitWidth='0' ss:Width='105.75'/>");
		out.println ("   <Row ss:Height='16.5' ss:StyleID='s68'>");
		out.println ("    <Cell><Data ss:Type='String'>學年度/學期</Data></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>學院</Data></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>系所</Data></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>學制</Data></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>學生人數</Data></Cell>");
		out.println ("    <Cell><Data ss:Type='String'>曾修程式設計人數</Data></Cell>");
		out.println ("   </Row>");
		
		StringBuilder sb=new StringBuilder("SELECT cc.name as inst_name, cs.name as school_name, "
		+ "cd.name as dept_name, c.ClassNo,SUM((SELECT COUNT(*)FROM stmd WHERE depart_class=c.ClassNo))"
		+ "as stds,SUM((SELECT COUNT(*)FROM stmd st, ScoreHist sh, Csno csn WHERE sh.cscode=csn.cscode AND "
		//+ "(csn.chi_name LIKE'%程式%'OR csn.chi_name LIKE'%計算機%'OR csn.chi_name LIKE'%軟體%'OR csn.chi_name LIKE'%邏輯%') AND "
		+ "(csn.chi_name LIKE'%計算機%'OR csn.chi_name LIKE'%程式%') AND "
		//+"c.ClassName NOT LIKE '%產%'AND c.ClassName NOT LIKE '%軍%' AND "
		+ "sh.score>=80 AND st.student_no=sh.student_no AND "
		+ "st.depart_class=c.ClassNo))as ptds FROM Class c, CODE_SCHOOL cs, CODE_DEPT cd, CODE_COLLEGE cc "
		+ "WHERE c.SchoolNo=cs.id AND cd.id=c.DeptNo AND cc.id=c.InstNo AND c.stds>0 ");
		if(!cno.equals(""))sb.append("AND c.CampusNo='"+cno+"'");
		if(!sno.equals(""))sb.append("AND c.SchoolNo='"+sno+"'");
		if(!dno.equals(""))sb.append("AND c.DeptNo='"+dno+"'");
		if(!gno.equals(""))sb.append("AND c.Grade='"+gno+"'");
		if(!zno.equals(""))sb.append("AND c.SeqNo='"+zno+"'");
		
		sb.append("GROUP BY c.InstNo, c.SchoolNo, c.DeptNo ORDER BY c.InstNo, c.SchoolNo, c.DeptNo");
		//System.out.println(sb.toString());
		List<Map>list=df.sqlGet(sb.toString());
		int ptds, stds;
		for(int i=0; i<list.size(); i++) {
			stds=Integer.parseInt(String.valueOf(list.get(i).get("stds")));
			ptds=Integer.parseInt(String.valueOf(list.get(i).get("ptds")));
			out.println ("   <Row>");
			out.println ("    <Cell><Data ss:Type='String'>"+year+"-"+term+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("inst_name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("dept_name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+list.get(i).get("school_name")+"</Data></Cell>");
			out.println ("    <Cell><Data ss:Type='String'>"+stds+"</Data></Cell>");
			if(ptds>=stds) {
				out.println ("    <Cell><Data ss:Type='Number'>"+stds+"</Data></Cell>");
			}else {
				out.println ("    <Cell><Data ss:Type='Number'>"+ptds+"</Data></Cell>");
			}
			
			out.println ("   </Row>");
			
		}
		
		
		
		
		
		
		out.println ("  </Table>");
		out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("   <PageSetup>");
		out.println ("    <Header x:Margin='0.3'/>");
		out.println ("    <Footer x:Margin='0.3'/>");
		out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.25' x:Right='0.25' x:Top='0.75'/>");
		out.println ("   </PageSetup>");
		out.println ("   <Print>");
		out.println ("    <ValidPrinterInfo/>");
		out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println ("    <HorizontalResolution>-1</HorizontalResolution>");
		out.println ("    <VerticalResolution>-1</VerticalResolution>");
		out.println ("   </Print>");
		out.println ("   <Selected/>");
		out.println ("   <ProtectObjects>False</ProtectObjects>");
		out.println ("   <ProtectScenarios>False</ProtectScenarios>");
		out.println ("  </WorksheetOptions>");
		out.println (" </Worksheet>");
		out.println ("</Workbook>");
		out.println ("");
		out.close();
		out.flush();
	}

}
