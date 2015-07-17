package action.forbidScoreList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

/**
 * 報表2
 * @author John
 *
 */
public class Print {
	
	public void print(HttpServletResponse response, List<Map>list, List<Map>css, Map info) throws IOException{
		
		Date date=new Date();
		response.setContentType("text/html; charset=UTF-8");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition","attachment;filename="+date.getTime()+".xls");
		
		
		
			
		
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
		out.println ("  <LastPrinted>2012-12-21T02:00:00Z</LastPrinted>");
		out.println ("  <Created>2012-12-21T00:00:00Z</Created>");
		out.println ("  <LastSaved>2012-12-21T00:00:00Z</LastSaved>");
		out.println ("  <Version>14.00</Version>");
		out.println (" </DocumentProperties>");
		out.println (" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println ("  <AllowPNG/>");
		out.println (" </OfficeDocumentSettings>");
		out.println (" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println ("  <WindowHeight>11880</WindowHeight>");
		out.println ("  <WindowWidth>28035</WindowWidth>");
		out.println ("  <WindowTopX>360</WindowTopX>");
		out.println ("  <WindowTopY>105</WindowTopY>");
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
		out.println ("  <Style ss:ID='s76'>");
		out.println ("   <Alignment ss:Horizontal='Left' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='18'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		out.println ("  <Style ss:ID='s78'>");
		out.println ("   <Alignment ss:Horizontal='Left' ss:Vertical='Center'/>");
		out.println ("   <Borders/>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='18'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		
		
		
		out.println ("	<Style ss:ID='s63'>");
		out.println ("   <Alignment ss:Horizontal='Right' ss:Vertical='Center'/>");
		out.println ("   <Borders>");
		out.println ("    <Border ss:Position='Bottom' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("    <Border ss:Position='Top' ss:LineStyle='Dash' ss:Weight='1'/>");
		out.println ("   </Borders>");
		out.println ("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='18'");
		out.println ("    ss:Color='#000000'/>");
		out.println ("  </Style>");
		
		out.println (" </Styles>");	
		
		List<Map>cls;
		List<Map>stds;
		List<Map>dilgs;
		int row;
		
		String student_no;
		String student_name;		
		
		StringBuilder sb;		
		
		for(int i=0; i<list.size(); i++){
			cls=(List) list.get(i).get("cls");
			
			row=0;
			for(int x=0; x<cls.size(); x++){
				row=row+((List)cls.get(x).get("stds")).size();
			}
			
			if(row<1){
				continue;
			}
			
			out.println (" <Worksheet ss:Name='公佈"+list.get(i).get("SchoolNo")+"'>");			
			out.println ("  <Table ss:ExpandedColumnCount='5' ss:ExpandedRowCount='"+(row+cls.size())+"' x:FullColumns='1'");		
			
			out.println ("   x:FullRows='1' ss:StyleID='s78' ss:DefaultColumnWidth='20'");
			out.println ("   ss:DefaultRowHeight='25.5'>");
			
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='60'/>");		
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='300'/>");
			out.println ("   <Column ss:StyleID='s63' ss:AutoFitWidth='0' ss:Width='82'/>");
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='400'/>");
			
			int cnt;
			for(int j=0; j<cls.size(); j++){				
				stds=(List)cls.get(j).get("stds");
				if(stds.size()<1){
					continue;
				}
				out.println ("   <Row>");
				out.println ("    <Cell><Data ss:Type='String'>"+cls.get(j).get("ClassName")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>共 "+stds.size()+"筆</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>應到/未到</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>/細節</Data></Cell>");
				out.println ("   </Row>");				
				
				for(int k=0; k<stds.size(); k++){
					dilgs=(List)stds.get(k).get("dilgs");
					cnt=0;
					sb=new StringBuilder();
					for(int l=0; l<dilgs.size(); l++){
						cnt=cnt+Integer.parseInt(dilgs.get(l).get("cnt").toString());
						sb.append(dilgs.get(l).get("name")+":"+dilgs.get(l).get("cnt")+", ");
					}
					
					out.println ("   <Row>");
					student_no=stds.get(k).get("student_no").toString();
					student_name=stds.get(k).get("student_name").toString();
							
					out.println ("    <Cell><Data ss:Type='String'>"+student_no.substring(0, 3)+"＊＊＊"+student_no.substring(6)+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'>"+student_name.substring(0, 1)+"＊＊</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'>"+stds.get(k).get("ClassName")+stds.get(k).get("chi_name")+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'>"+(Integer.parseInt(stds.get(k).get("thour").toString())*18)+" / "+(cnt+Integer.parseInt(stds.get(k).get("elearn_dilg").toString()))+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'>/");
					
					if(!stds.get(k).get("elearn_dilg").toString().equals("0")){
						out.println (sb.toString()+"遠距:"+stds.get(k).get("elearn_dilg"));
					}else{
						out.println (sb.toString());
					}
					
					out.println ("    </Data></Cell>");
					out.println ("   </Row>");					
				}
			}
			
			out.println ("  </Table>");
			
			out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
			out.println ("   <PageSetup>");
			
			out.println ("    <Header x:Margin='0.3'");
			out.println ("     x:Data='&amp;L&amp; &#10;列印後異動依期末成績資料為準&amp;C&amp;24 "+list.get(i).get("name")+" "+info.get("school_year")+"學年第 "+info.get("school_term")+"學期 1/3缺課名單'/>");
			
			out.println ("    <Footer x:Margin='0.3'");
			out.println ("     x:Data='&amp;D-&amp;T&#10;第&amp;P頁 共&amp;N頁'/>");
			
			out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.7' x:Right='0.7' x:Top='0.75'/>");
			out.println ("   </PageSetup>");
			out.println ("   <FitToPage/>");
			out.println ("   <Print>");
			out.println ("    <FitHeight>0</FitHeight>");
			out.println ("    <ValidPrinterInfo/>");
			out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
			out.println ("    <Scale>52</Scale>");
			out.println ("    <HorizontalResolution>600</HorizontalResolution>");
			out.println ("    <VerticalResolution>600</VerticalResolution>");
			out.println ("   </Print>");
			out.println ("   <Selected/>");
			out.println ("   <Panes>");
			out.println ("    <Pane>");
			out.println ("     <Number>3</Number>");
			out.println ("     <ActiveRow>16</ActiveRow>");
			out.println ("     <ActiveCol>7</ActiveCol>");
			out.println ("    </Pane>");
			out.println ("   </Panes>");
			out.println ("   <ProtectObjects>False</ProtectObjects>");
			out.println ("   <ProtectScenarios>False</ProtectScenarios>");
			out.println ("  </WorksheetOptions>");
			out.println (" </Worksheet>");
		}		
		
		for(int i=0; i<list.size(); i++){
			cls=(List) list.get(i).get("cls");
			
			row=0;
			for(int x=0; x<cls.size(); x++){
				row=row+((List)cls.get(x).get("stds")).size();
			}
			
			if(row<1){
				continue;
			}
			
			out.println (" <Worksheet ss:Name='留存"+list.get(i).get("SchoolNo")+"'>");			
			out.println ("  <Table ss:ExpandedColumnCount='5' ss:ExpandedRowCount='"+(row+cls.size())+"' x:FullColumns='1'");		
			
			out.println ("   x:FullRows='1' ss:StyleID='s78' ss:DefaultColumnWidth='20'");
			out.println ("   ss:DefaultRowHeight='25.5'>");
			
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='60'/>");		
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='300'/>");
			out.println ("   <Column ss:StyleID='s63' ss:AutoFitWidth='0' ss:Width='82'/>");
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='400'/>");
			
			int cnt;
			for(int j=0; j<cls.size(); j++){				
				stds=(List)cls.get(j).get("stds");
				if(stds.size()<1){
					continue;
				}
				out.println ("   <Row>");
				out.println ("    <Cell><Data ss:Type='String'>"+cls.get(j).get("ClassName")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>共 "+stds.size()+"筆</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>應到/未到</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>/細節</Data></Cell>");
				out.println ("   </Row>");				
				
				for(int k=0; k<stds.size(); k++){
					dilgs=(List)stds.get(k).get("dilgs");
					cnt=0;
					sb=new StringBuilder();
					for(int l=0; l<dilgs.size(); l++){
						cnt=cnt+Integer.parseInt(dilgs.get(l).get("cnt").toString());
						sb.append(dilgs.get(l).get("name")+":"+dilgs.get(l).get("cnt")+", ");
					}
					
					out.println ("   <Row>");
					student_no=stds.get(k).get("student_no").toString();
					student_name=stds.get(k).get("student_name").toString();
							
					out.println ("    <Cell><Data ss:Type='String'>"+student_no+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'>"+student_name+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'>"+stds.get(k).get("ClassName")+","+stds.get(k).get("chi_name")+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'>"+(Integer.parseInt(stds.get(k).get("thour").toString())*18)+" / "+(cnt+Integer.parseInt(stds.get(k).get("elearn_dilg").toString()))+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'>/");					
					if(!stds.get(k).get("elearn_dilg").toString().equals("0")){
						out.println (sb.toString()+"遠距:"+stds.get(k).get("elearn_dilg"));
					}else{
						out.println (sb.toString());
					}
					
					out.println ("    </Data></Cell>");
					out.println ("   </Row>");					
				}
			}
			
			out.println ("  </Table>");
			
			out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
			out.println ("   <PageSetup>");
			
			out.println ("    <Header x:Margin='0.3'");
			out.println ("     x:Data='&amp;L&amp; &#10;單位留存用，請勿任意公佈&amp;C&amp;24 "+list.get(i).get("name")+" "+info.get("school_year")+"學年第 "+info.get("school_term")+"學期 1/3缺課名單(留存)'/>");
			
			out.println ("    <Footer x:Margin='0.3'");
			out.println ("     x:Data='&amp;L&amp; &#10;列印後異動依期末成績資料為準&amp;R&amp;D-&amp;T&#10;第&amp;P頁 共&amp;N頁'/>");			
			out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.7' x:Right='0.7' x:Top='0.75'/>");
			out.println ("   </PageSetup>");
			out.println ("   <FitToPage/>");
			out.println ("   <Print>");
			out.println ("    <FitHeight>0</FitHeight>");
			out.println ("    <ValidPrinterInfo/>");
			out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
			out.println ("    <Scale>52</Scale>");
			out.println ("    <HorizontalResolution>600</HorizontalResolution>");
			out.println ("    <VerticalResolution>600</VerticalResolution>");
			out.println ("   </Print>");
			out.println ("   <Selected/>");
			out.println ("   <Panes>");
			out.println ("    <Pane>");
			out.println ("     <Number>3</Number>");
			out.println ("     <ActiveRow>16</ActiveRow>");
			out.println ("     <ActiveCol>7</ActiveCol>");
			out.println ("    </Pane>");
			out.println ("   </Panes>");
			out.println ("   <ProtectObjects>False</ProtectObjects>");
			out.println ("   <ProtectScenarios>False</ProtectScenarios>");
			out.println ("  </WorksheetOptions>");
			out.println (" </Worksheet>");
		}		
		

		for(int i=0; i<css.size(); i++){			
			cls=(List) css.get(i).get("cls");			
			row=0;
			for(int x=0; x<cls.size(); x++){
				row=row+((List)cls.get(x).get("stds")).size();
			}
			
			if(row<1){
				continue;
			}
			out.println (" <Worksheet ss:Name='開課"+css.get(i).get("SchoolNo")+"'>");			
			out.println ("  <Table ss:ExpandedColumnCount='5' ss:ExpandedRowCount='"+(row+cls.size())+"' x:FullColumns='1'");		
			
			out.println ("   x:FullRows='1' ss:StyleID='s78' ss:DefaultColumnWidth='20'");
			out.println ("   ss:DefaultRowHeight='25.5'>");
			
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='120'/>");
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='60'/>");		
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='300'/>");
			out.println ("   <Column ss:StyleID='s63' ss:AutoFitWidth='0' ss:Width='82'/>");
			out.println ("   <Column ss:StyleID='s76' ss:AutoFitWidth='0' ss:Width='400'/>");
			
			int cnt;
			for(int j=0; j<cls.size(); j++){				
				stds=(List)cls.get(j).get("stds");
				if(stds.size()<1){
					continue;
				}
				out.println ("   <Row>");
				out.println ("    <Cell><Data ss:Type='String'>"+cls.get(j).get("ClassName")+"</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>共 "+stds.size()+"筆</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'></Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>應到/未到</Data></Cell>");
				out.println ("    <Cell><Data ss:Type='String'>/細節</Data></Cell>");
				out.println ("   </Row>");				
				
				for(int k=0; k<stds.size(); k++){
					dilgs=(List)stds.get(k).get("dilgs");
					cnt=0;
					sb=new StringBuilder();
					for(int l=0; l<dilgs.size(); l++){
						cnt=cnt+Integer.parseInt(dilgs.get(l).get("cnt").toString());
						sb.append(dilgs.get(l).get("name")+":"+dilgs.get(l).get("cnt")+", ");
					}
					
					out.println ("   <Row>");
					student_no=stds.get(k).get("student_no").toString();
					student_name=stds.get(k).get("student_name").toString();
							
					out.println ("    <Cell><Data ss:Type='String'>"+student_no+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'>"+student_name+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'>"+stds.get(k).get("ClassName")+","+stds.get(k).get("chi_name")+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'>"+(Integer.parseInt(stds.get(k).get("thour").toString())*18)+" / "+(cnt+Integer.parseInt(stds.get(k).get("elearn_dilg").toString()))+"</Data></Cell>");
					out.println ("    <Cell><Data ss:Type='String'>/");					
					if(!stds.get(k).get("elearn_dilg").toString().equals("0")){
						out.println (sb.toString()+"遠距:"+stds.get(k).get("elearn_dilg"));
					}else{
						out.println (sb.toString());
					}
					
					out.println ("    </Data></Cell>");
					out.println ("   </Row>");					
				}
			}
			
			out.println ("  </Table>");
			
			out.println ("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
			out.println ("   <PageSetup>");
			
			out.println ("    <Header x:Margin='0.3'");
			out.println ("     x:Data='&amp;L&amp; &#10;單位留存用，請勿任意公佈&amp;C&amp;24 "+css.get(i).get("name")+" "+info.get("school_year")+"學年第 "+info.get("school_term")+"學期 1/3缺課名單(留存)'/>");
			
			out.println ("    <Footer x:Margin='0.3'");
			out.println ("     x:Data='&amp;L&amp; &#10;列印後異動依期末成績資料為準&amp;R&amp;D-&amp;T&#10;第&amp;P頁 共&amp;N頁'/>");			
			out.println ("    <PageMargins x:Bottom='0.75' x:Left='0.7' x:Right='0.7' x:Top='0.75'/>");
			out.println ("   </PageSetup>");
			out.println ("   <FitToPage/>");
			out.println ("   <Print>");
			out.println ("    <FitHeight>0</FitHeight>");
			out.println ("    <ValidPrinterInfo/>");
			out.println ("    <PaperSizeIndex>9</PaperSizeIndex>");
			out.println ("    <Scale>52</Scale>");
			out.println ("    <HorizontalResolution>600</HorizontalResolution>");
			out.println ("    <VerticalResolution>600</VerticalResolution>");
			out.println ("   </Print>");
			out.println ("   <Selected/>");
			out.println ("   <Panes>");
			out.println ("    <Pane>");
			out.println ("     <Number>3</Number>");
			out.println ("     <ActiveRow>16</ActiveRow>");
			out.println ("     <ActiveCol>7</ActiveCol>");
			out.println ("    </Pane>");
			out.println ("   </Panes>");
			out.println ("   <ProtectObjects>False</ProtectObjects>");
			out.println ("   <ProtectScenarios>False</ProtectScenarios>");
			out.println ("  </WorksheetOptions>");
			out.println (" </Worksheet>");
		}	
		
			
		
		out.println ("</Workbook>");	
		out.close();
		
		
	}

}
