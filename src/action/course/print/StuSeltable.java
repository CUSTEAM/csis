package action.course.print;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;





import action.BaseAction;

/**
 * 通識課表
 * 課亂開根本沒屁用
 * @author John
 *
 */
public class StuSeltable extends BaseAction{
	
	public void print(HttpServletResponse response, List<Map>dtimeList, String year,String thisTerm, String term) throws IOException{
		
		Date date=new Date();
		response.setContentType("application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-disposition","attachment;filename="+date.getTime()+".xls");				
		PrintWriter out=response.getWriter();
		
		//System.out.println(dtimeList);
		
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
		out.println ("  <LastPrinted>2014-04-22T08:51:42Z</LastPrinted>");
		out.println ("  <Created>2014-04-22T06:58:49Z</Created>");
		out.println ("  <LastSaved>2014-04-22T08:13:28Z</LastSaved>");
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
		out.println ("  <Style ss:ID='s82'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		
		out.println ("  <Style ss:ID='s83'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Left' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Right' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='8'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		
		out.println ("  <Style ss:ID='s124'>");
		out.println ("   <Alignment ss:Horizontal='Left' ss:Vertical='Bottom'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='18'");
		out.println ("    ss:Color='#000000' ss:Bold='1'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s125'>");
		out.println ("   <Alignment ss:Vertical='Bottom'/>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='18'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s127'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='14'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s128'>");
		out.println ("   <Alignment ss:Vertical='Center'/>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s129'>");
		out.println ("   <Alignment ss:Horizontal='Right' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Continuous' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='微軟正黑體' x:CharSet='136' x:Family='Swiss' ss:Size='12'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("   <Interior/>");
		out.println ("   <NumberFormat ss:Format='@'/>");
		out.println ("  </Style>");
		out.println (" </Styles>");
		
		List<Map>stds, selds;
		int cnt,thour;
		float credit;
		for(int i=0; i<dtimeList.size(); i++){
			
			stds=df.sqlGet("SELECT student_no, student_name FROM stmd WHERE depart_class='"+dtimeList.get(i).get("ClassNo")+"' ORDER BY student_no");
			if(stds.size()<1)continue;
			
			out.println (" <Worksheet ss:Name='"+dtimeList.get(i).get("ClassNo")+"'>");
			out.println ("  <Table ss:ExpandedColumnCount='6' ss:ExpandedRowCount='"+50*30+"' x:FullColumns='1'");
			out.println ("   x:FullRows='1' ss:StyleID='s128' ss:DefaultColumnWidth='54'");
			out.println ("   ss:DefaultRowHeight='15.75'>");
			out.println ("   <Column ss:StyleID='s128' ss:AutoFitWidth='0' ss:Width='93.75'/>");
			out.println ("   <Column ss:StyleID='s128' ss:AutoFitWidth='0' ss:Width='252.25'/>");
			out.println ("   <Column ss:StyleID='s128' ss:AutoFitWidth='0' ss:Width='33.75' ss:Span='2'/>");
			out.println ("   <Column ss:Index='6' ss:StyleID='s128' ss:AutoFitWidth='0' ss:Width='144.5'/>");
			
			for(int j=0; j<stds.size(); j++){
				
				
				selds=df.sqlGet("SELECT c.ClassName, d.Oid, cs.chi_name, cdo.name as opt, d.thour, d.credit FROM Dtime d, "
						+ "Seld s, Csno cs, Class c, CODE_DTIME_OPT cdo WHERE cdo.id=d.opt AND d.Oid=s.Dtime_oid AND "
						+ "d.cscode=cs.cscode AND c.ClassNo=d.depart_class AND d.Sterm='"+term+"' AND s.student_no='"+stds.get(j).get("student_no")+"'");
				
				//if(selds.size()>15)System.out.println(stds.get(j));
				
				out.println ("   <Row ss:AutoFitHeight='0' ss:Height='15' ss:StyleID='s125'>");
				out.println ("    <Cell ss:MergeAcross='5' ss:MergeDown='1' ss:StyleID='s124'><Data");
				if(!upgrade){
					out.println ("      ss:Type='String'>"+year+"學年第 "+term+"學期選課清單</Data></Cell>");
				}else{
					out.println ("      ss:Type='String'>"+year+"學年第 "+term+"學期升級後選課清單</Data></Cell>");
				}
				
				out.println ("   </Row>");
				out.println ("   <Row ss:AutoFitHeight='0' ss:Height='10.5' ss:StyleID='s125'/>");
				out.println ("   <Row ss:AutoFitHeight='0' ss:Height='18'>");				
				out.println ("    <Cell ss:MergeAcross='5' ss:StyleID='s127'><Data ss:Type='String'>"+dtimeList.get(i).get("ClassName")+" "+stds.get(j).get("student_no")+" "+stds.get(j).get("student_name")+"同學</Data></Cell>");
				out.println ("   </Row>");
				out.println ("   <Row ss:AutoFitHeight='0'>");
				out.println ("    <Cell ss:StyleID='s82'><Data ss:Type='String'>開課班級</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s82'><Data ss:Type='String'>課程名稱</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s82'><Data ss:Type='String'>選別</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s82'><Data ss:Type='String'>學分</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s82'><Data ss:Type='String'>時數</Data></Cell>");
				out.println ("    <Cell ss:StyleID='s82'><Data ss:Type='String'>星期(節次)地點</Data></Cell>");
				out.println ("   </Row>");
				
				thour=0;credit=0f;
				for(int k=0; k<12; k++){
					try{						
						thour+=Integer.parseInt(selds.get(k).get("thour").toString());
						credit+=Float.parseFloat(selds.get(k).get("credit").toString());
						writeData(out, selds.get(k), df.sqlGet("SELECT * FROM Dtime_class WHERE Dtime_oid="+selds.get(k).get("Oid")));
					}catch(Exception e){
						writeEmpty(out);
					}
				}				
				
				if(selds.size()>12){
					out.println ("   <Row ss:AutoFitHeight='0' ss:Height='16.5'>");					
					out.println ("    <Cell ss:StyleID='s83'><Data ss:Type='String'>");
					for(int k=12; k<selds.size(); k++){
						thour+=Integer.parseInt(selds.get(k).get("thour").toString());
						credit+=Float.parseFloat(selds.get(k).get("credit").toString());
						out.print(selds.get(k).get("Oid")+""+selds.get(k).get("chi_name")+", ");
					}
					out.println ("    </Data></Cell>");					
					out.println ("    <Cell ss:StyleID='s83'/>");
					out.println ("    <Cell ss:StyleID='s83'/>");
					out.println ("    <Cell ss:StyleID='s83'/>");
					out.println ("    <Cell ss:StyleID='s83'/>");
					out.println ("    <Cell ss:StyleID='s83'/>");
					out.println ("   </Row>");
				}else{
					out.println ("   <Row ss:AutoFitHeight='0'>");
					out.println ("    <Cell ss:StyleID='s82'/>");
					out.println ("    <Cell ss:StyleID='s82'/>");
					out.println ("    <Cell ss:StyleID='s82'/>");
					out.println ("    <Cell ss:StyleID='s82'/>");
					out.println ("    <Cell ss:StyleID='s82'/>");
					out.println ("    <Cell ss:StyleID='s82'/>");
					out.println ("   </Row>");
				}			
				
				out.println ("   <Row ss:AutoFitHeight='0' ss:Height='16.5'>");
				out.println ("    <Cell ss:MergeAcross='5' ss:StyleID='s129'><Data ss:Type='String'>"+selds.size()+"門課 "+thour+"小時 "+credit+"學分</Data></Cell>");
				out.println ("   </Row>");				
			}
			
			out.println ("  </Table>");
			out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
			out.println ("   <PageSetup>");
			out.println ("    <Header x:Margin='0.2'/>");
			out.println ("    <Footer x:Margin='0.3'/>");
			out.println ("    <PageMargins x:Bottom='0.3' x:Left='0.25' x:Right='0.25' x:Top='0.2'/>");
			out.println ("   </PageSetup>");
			out.println ("   <Unsynced/>");
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
			out.println ("     <ActiveRow>8</ActiveRow>");
			out.println ("     <ActiveCol>1</ActiveCol>");
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
		out.flush();
	}
	
	private void writeData(PrintWriter out, Map seld, List<Map>place){
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:StyleID='s82'><Data ss:Type='String'>"+seld.get("ClassName")+"</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s82'><Data ss:Type='String'>"+seld.get("Oid")+seld.get("chi_name")+"</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s82'><Data ss:Type='String'>"+seld.get("opt")+"</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s82'><Data ss:Type='String'>"+seld.get("credit")+"</Data></Cell>");
		out.println ("    <Cell ss:StyleID='s82'><Data ss:Type='String'>"+seld.get("thour")+"</Data></Cell>");
		
		if(place.size()>0){
			StringBuilder sb=new StringBuilder();
			for(int i=0; i<place.size(); i++){
				sb.append(bl.getWeekOfDay4Zh(Integer.parseInt(place.get(i).get("week").toString()), null)+"("+place.get(i).get("begin")+"~"+place.get(i).get("end")+"),");
			}
			sb.delete(sb.length()-1, sb.length());
			sb.append(place.get(0).get("place"));
			out.println ("    <Cell ss:StyleID='s82'><Data ss:Type='String'>"+sb+"</Data></Cell>");
		}else{
			out.println ("    <Cell ss:StyleID='s82'><Data ss:Type='String'>未排定</Data></Cell>");
		}
		out.println ("   </Row>");
		
	}
	
	private void writeEmpty(PrintWriter out){
		out.println ("   <Row ss:AutoFitHeight='0'>");
		out.println ("    <Cell ss:StyleID='s82'/>");
		out.println ("    <Cell ss:StyleID='s82'/>");
		out.println ("    <Cell ss:StyleID='s82'/>");
		out.println ("    <Cell ss:StyleID='s82'/>");
		out.println ("    <Cell ss:StyleID='s82'/>");
		out.println ("    <Cell ss:StyleID='s82'/>");
		out.println ("   </Row>");
	}

}
