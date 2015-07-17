package action.course.print;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import action.BaseAction;

public class ExamPacketFace extends BaseAction{
	
	public void print(HttpServletResponse response, List<Map>dtimeList, String year, String term) throws IOException{		
		Date date=new Date();
		//response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
		response.setContentType("application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-disposition","attachment;filename="+date.getTime()+".xls");				
		PrintWriter out=response.getWriter();
		
		
		/*out.println ("<?xml version='1.0'?>");
		out.println ("<?mso-application progid='Excel.Sheet'?>");
		out.println ("<Workbook xmlns='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:o='urn:schemas-microsoft-com:office:office'");
		out.println (" xmlns:x='urn:schemas-microsoft-com:office:excel'");
		out.println (" xmlns:ss='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println (" xmlns:html='http://www.w3.org/TR/REC-html40'>");
		out.println (" <DocumentProperties xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <Author>shawn</Author>");
		out.println ("  <LastAuthor>shawn</LastAuthor>");
		out.println ("  <LastPrinted>2014-04-28T00:20:38Z</LastPrinted>");
		out.println ("  <Created>2014-04-25T08:34:08Z</Created>");
		out.println ("  <LastSaved>2014-04-28T00:21:19Z</LastSaved>");
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
		out.println ("  <Style ss:ID='s16'>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='16'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s21'>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='20'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s24'>");
		out.println ("   <Alignment ss:Horizontal='Right' ss:Vertical='Center'/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='20'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s25'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='20'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s26'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='20'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s27'>");
		out.println ("   <Alignment ss:Horizontal='Left' ss:Vertical='Top' ss:WrapText='1'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='18'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s29'>");
		out.println ("   <Alignment ss:Horizontal='Center' ss:Vertical='Center'/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='24'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("  </Style>");
		out.println (" </Styles>");
		
		
		StringBuilder sb=new StringBuilder("SELECT d.Oid, c.ClassNo, c.ClassName, e.cname,cs.chi_name, "
				+ "(SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid)as stu_select FROM Dtime d "
				+ "LEFT OUTER JOIN empl e ON e.idno=d.techid, Csno cs, Class c WHERE "
				+ "d.cscode=cs.cscode AND d.depart_class=c.ClassNo AND d.Oid IN(");
		if(dtimeList.size()<1)return;
		for(int i=0; i<dtimeList.size(); i++){
			sb.append("'"+dtimeList.get(i).get("Oid")+"',");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(") ORDER BY c.ClassNo");
		
		dtimeList=df.sqlGet(sb.toString());
		
		List<Map>stds;
		int cnt;
		for(int i=0; i<dtimeList.size(); i++){
			
			out.println (" <Worksheet ss:Name='"+dtimeList.get(i).get("Oid")+"'>");
			out.println ("  <Table ss:ExpandedColumnCount='6' ss:ExpandedRowCount='21' x:FullColumns='1'");
			out.println ("   x:FullRows='1' ss:StyleID='s16' ss:DefaultColumnWidth='54'");
			out.println ("   ss:DefaultRowHeight='21'>");
			out.println ("   <Column ss:StyleID='s16' ss:Width='161.25' ss:Span='4'/>");
			out.println ("   <Row ss:Height='32.25'>");
			out.println ("    <Cell ss:MergeAcross='5' ss:StyleID='s29'><Data ss:Type='String'>"+year+"學年第"+term+"學期試卷封袋</Data></Cell>");
			out.println ("   </Row>");
			out.println ("   <Row ss:AutoFitHeight='0' ss:Height='105'>");
			out.println ("    <Cell ss:MergeAcross='5' ss:StyleID='s27'><Data ss:Type='String'>考試班級: "+dtimeList.get(i).get("ClassName")+"&#10;任課教師: "+dtimeList.get(i).get("cname")+"&#10;考試科目: "+dtimeList.get(i).get("chi_name")+" &#10;實到人數: ____ / "+dtimeList.get(i).get("stu_select")+" </Data></Cell>");
			out.println ("   </Row>");
			
			stds=df.sqlGet("SELECT st.student_no, st.student_name FROM Seld s, stmd st WHERE s.student_no=st.student_no AND s.Dtime_oid="+dtimeList.get(i).get("Oid"));
			cnt=0;
			for(int j=0; j<15; j++){
				
				out.println ("   <Row>");
				for(int k=0; k<5; k++){
					try{
						out.println ("    <Cell><Data ss:Type='String'>□"+stds.get(cnt).get("student_no")+", "+stds.get(cnt).get("student_name")+"</Data></Cell>");
						cnt++;
					}catch(Exception e){
						out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
					}
					
				}
				out.println ("   </Row>");				
			}
			
			
			
			
			
			
			
			out.println ("   <Row ss:Index='18' ss:Height='27.75' ss:StyleID='s21'>");
			out.println ("    <Cell ss:StyleID='s24'><Data ss:Type='String'>加考學生</Data></Cell>");
			out.println ("    <Cell ss:MergeAcross='3' ss:StyleID='s25'/>");
			out.println ("   </Row>");
			out.println ("   <Row ss:Height='27.75' ss:StyleID='s21'>");
			out.println ("    <Cell ss:StyleID='s24'><Data ss:Type='String'>缺考學生</Data></Cell>");
			out.println ("    <Cell ss:MergeAcross='3' ss:StyleID='s26'/>");
			out.println ("   </Row>");
			out.println ("   <Row ss:Height='27.75' ss:StyleID='s21'>");
			out.println ("    <Cell ss:StyleID='s24'><Data ss:Type='String'>違規學生</Data></Cell>");
			out.println ("    <Cell ss:MergeAcross='3' ss:StyleID='s26'/>");
			out.println ("   </Row>");
			out.println ("   <Row ss:Height='27.75' ss:StyleID='s21'>");
			out.println ("    <Cell ss:StyleID='s24'><Data ss:Type='String'>監考教師</Data></Cell>");
			out.println ("    <Cell ss:MergeAcross='3' ss:StyleID='s26'/>");
			out.println ("   </Row>");
			out.println ("  </Table>");
			out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
			out.println ("   <PageSetup>");
			out.println ("    <Layout x:Orientation='Landscape'/>");
			out.println ("    <Header x:Margin='0.2'/>");
			out.println ("    <Footer x:Margin='0.2'/>");
			out.println ("    <PageMargins x:Bottom='0.2' x:Left='0.25' x:Right='0.17' x:Top='0.2'/>");
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
			out.println ("     <ActiveRow>11</ActiveRow>");
			out.println ("     <ActiveCol>1</ActiveCol>");
			out.println ("    </Pane>");
			out.println ("   </Panes>");
			out.println ("   <ProtectObjects>False</ProtectObjects>");
			out.println ("   <ProtectScenarios>False</ProtectScenarios>");
			out.println ("  </WorksheetOptions>");
			out.println (" </Worksheet>");
			
		}		
		out.println ("</Workbook>");
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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
		out.println ("  <LastPrinted>2014-05-16T08:31:17Z</LastPrinted>");
		out.println ("  <Created>2014-05-16T06:30:38Z</Created>");
		out.println ("  <LastSaved>2014-05-16T08:38:44Z</LastSaved>");
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
		out.println ("  <Style ss:ID='s201'>");
		out.println ("   <Font ss:FontName='細明體' x:CharSet='136' x:Family='Swiss' ss:Size='11'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s202'>");
		out.println ("   <Alignment ss:Horizontal='Right' ss:Vertical='Center'/>");
		out.println ("   <Font ss:FontName='細明體' x:CharSet='136' x:Family='Swiss' ss:Size='12'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s203'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='細明體' x:CharSet='136' x:Family='Swiss' ss:Size='11'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s205'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='細明體' x:CharSet='136' x:Family='Swiss' ss:Size='11'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s207'>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='細明體' x:CharSet='136' x:Family='Swiss' ss:Size='11'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s211'>");
		out.println ("   <Font ss:FontName='細明體' x:CharSet='136' x:Family='Swiss' ss:Color='#000000'/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s217'>");
		out.println ("   <Alignment ss:Horizontal='Left' ss:Vertical='Top' ss:WrapText='1'/>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='細明體' x:CharSet='136' x:Family='Swiss' ss:Size='16'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("  </Style>");
		out.println (" </Styles>");
		
		
		StringBuilder sb=new StringBuilder("SELECT d.Oid, c.ClassNo, c.ClassName, e.cname,cs.chi_name, "
				+ "(SELECT COUNT(*)FROM Seld WHERE Dtime_oid=d.Oid)as stu_select FROM Dtime d "
				+ "LEFT OUTER JOIN empl e ON e.idno=d.techid, Csno cs, Class c WHERE "
				+ "d.cscode=cs.cscode AND d.depart_class=c.ClassNo AND d.Oid IN(");
		if(dtimeList.size()<1)return;
		for(int i=0; i<dtimeList.size(); i++){
			sb.append("'"+dtimeList.get(i).get("Oid")+"',");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(") ORDER BY c.ClassNo");
		
		dtimeList=df.sqlGet(sb.toString());
		
		Date dates=new Date();
		Calendar c=Calendar.getInstance();
		c.setTime(dates);
		int month=c.get(Calendar.MONTH)+1;
		String title="期中 / 期未";
		switch(month){
			case 10:
				title="期中";
			break;
			case 11:
				title="期中";
			break;
			case 3:
				title="期中";
			break;
			case 4:
				title="期中";
			break;
			case 12:
				title="期末";
			break;
			case 1:
				title="期末";
			break;
			case 5:
				title="期末";
			break;
			case 6:
				title="期末";
			break;
		}
		
		List<Map>stds;
		int cnt;
		for(int i=0; i<dtimeList.size(); i++){
			
			
			out.println (" <Worksheet ss:Name='"+dtimeList.get(i).get("Oid")+"'>");
			out.println ("  <Table ss:ExpandedColumnCount='6' ss:ExpandedRowCount='35' x:FullColumns='1'");
			out.println ("   x:FullRows='1' ss:StyleID='s201' ss:DefaultColumnWidth='97.5'");
			out.println ("   ss:DefaultRowHeight='15'>");
			/*
			out.println ("    <Cell ss:MergeAcross='5' ss:StyleID='s29'><Data ss:Type='String'>"+year+"學年第"+term+"學期試卷封袋</Data></Cell>");
			out.println ("   </Row>");
			out.println ("   <Row ss:AutoFitHeight='0' ss:Height='105'>");
			out.println ("    <Cell ss:MergeAcross='5' ss:StyleID='s27'><Data ss:Type='String'>考試班級: "+dtimeList.get(i).get("ClassName")+"&#10;任課教師: 
			"+dtimeList.get(i).get("cname")+"&#10;考試科目: "+dtimeList.get(i).get("chi_name")+
			" &#10;實到人數: ____ / "+dtimeList.get(i).get("stu_select")+" </Data></Cell>");
			
			*/
			out.println ("   <Row ss:AutoFitHeight='0' ss:Height='69.75'>");
			out.println ("    <Cell ss:MergeAcross='5' ss:StyleID='s217'><Data ss:Type='String'>"+year+"學年第 "+term+"學期 "+dtimeList.get(i).get("ClassName")+
			" "+title+"考卷封袋&#10;考試科目: "+dtimeList.get(i).get("chi_name")+
			" 課程編號: "+dtimeList.get(i).get("Oid")+" 任課教師: "+dtimeList.get(i).get("cname")+
			"&#10;應到人數: "+dtimeList.get(i).get("stu_select")+" 實到人數: ____ </Data></Cell>");
			out.println ("   </Row>");
			
			
			stds=df.sqlGet("SELECT st.student_no, st.student_name FROM Seld s, stmd st WHERE s.student_no=st.student_no AND s.Dtime_oid="+dtimeList.get(i).get("Oid")+" ORDER BY st.student_no");
			cnt=0;
			for(int j=0; j<18; j++){
				out.println ("   <Row ss:AutoFitHeight='0' ss:StyleID='s211'>");
				for(int k=0; k<6; k++){
					try{
						//out.println ("    <Cell><Data ss:Type='String'>□"+stds.get(cnt).get("student_no")+", "+stds.get(cnt).get("student_name")+"</Data></Cell>");
						out.println ("    <Cell><Data ss:Type='String'>"+stds.get(cnt).get("student_no")+", "+stds.get(cnt).get("student_name")+"</Data></Cell>");
						cnt++;
					}catch(Exception e){
						out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
					}
				}
				out.println ("   </Row>");				
			}
			
			if(stds.size()>108){
				for(int j=0; j<((stds.size()-108)/6)+1; j++){
					out.println ("   <Row ss:AutoFitHeight='0' ss:StyleID='s211'>");
					for(int k=0; k<6; k++){
						try{out.println ("    <Cell><Data ss:Type='String'>"+stds.get(cnt).get("student_no")+", "+stds.get(cnt).get("student_name")+"</Data></Cell>");
							cnt++;
						}catch(Exception e){
							out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
						}
					}
					out.println ("   </Row>");				
				}
			}
			
			out.println ("   <Row ss:AutoFitHeight='0' ss:Height='18.75'>");
			out.println ("    <Cell ss:StyleID='s202'><Data ss:Type='String'>缺考學生</Data></Cell>");
			out.println ("    <Cell ss:MergeAcross='4' ss:StyleID='s203'/>");
			out.println ("   </Row>");
			out.println ("   <Row ss:AutoFitHeight='0' ss:Height='18.75'>");
			out.println ("    <Cell ss:StyleID='s202'><Data ss:Type='String'>加考學生</Data></Cell>");
			out.println ("    <Cell ss:MergeAcross='4' ss:StyleID='s205'/>");
			out.println ("   </Row>");
			out.println ("   <Row ss:AutoFitHeight='0' ss:Height='18.75'>");
			out.println ("    <Cell ss:StyleID='s202'><Data ss:Type='String'>違規學生</Data></Cell>");
			out.println ("    <Cell ss:MergeAcross='3' ss:StyleID='s205'/>");
			out.println ("    <Cell ss:StyleID='s207'/>");
			out.println ("   </Row>");
			out.println ("   <Row ss:AutoFitHeight='0' ss:Height='18.75'>");
			out.println ("    <Cell ss:StyleID='s202'><Data ss:Type='String'>監考教師</Data></Cell>");
			out.println ("    <Cell ss:MergeAcross='4' ss:StyleID='s205'/>");
			out.println ("   </Row>");
			out.println ("  </Table>");
			out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
			out.println ("   <PageSetup>");
			out.println ("    <Header x:Margin='0.2'/>");
			out.println ("    <Footer x:Margin='0.2'/>");
			out.println ("    <PageMargins x:Bottom='0.2' x:Left='0.17' x:Right='0.17' x:Top='0.2'/>");
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
			out.println ("     <RangeSelection>R1C1:R1C6</RangeSelection>");
			out.println ("    </Pane>");
			out.println ("   </Panes>");
			out.println ("   <ProtectObjects>False</ProtectObjects>");
			out.println ("   <ProtectScenarios>False</ProtectScenarios>");
			out.println ("  </WorksheetOptions>");
			out.println (" </Worksheet>");
			
			
			
		}
		
		
		
		out.println ("</Workbook>");
		out.println ("");
		
		
		
		out.close();
		
		
	}

}
