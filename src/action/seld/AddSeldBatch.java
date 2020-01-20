package action.seld;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import model.Message;
import model.SeldBatch;

public class AddSeldBatch extends AddSeldBase{
	
	public String batchOid, limit;
	public File upload;
	
	public String execute() {
		
		request.setAttribute("allBatch", df.sqlGet("SELECT sb.*, e.cname, (SELECT COUNT(*)FROM Seld WHERE BatchOid=sb.Oid)as cnt FROM SeldBatch sb, empl e WHERE sb.idno=e.idno ORDER BY sb.Oid DESC"));
		
		return SUCCESS;
	}
	
	public String upload() throws IOException {
		
		
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Message msg=new Message();
		msg.setError("");
		msg.setMsg("");
		if(upload==null){			
			msg.setError("未指定檔案");
			this.savMessage(msg);
			return execute();
		}
		
		boolean check[]={false, false, false, false};
		
		FileInputStream fis = new FileInputStream(upload);
		//XSSFSheet sheet0, sheet1, sheet2, sheet3;
		XSSFSheet sheet;
		XSSFRow row;
		XSSFWorkbook xwb;
		try{
			xwb = new XSSFWorkbook(fis);
			sheet = xwb.getSheetAt(0);
			//sheet1 = xwb.getSheetAt(1);
			//sheet2 = xwb.getSheetAt(2);
			//sheet3 = xwb.getSheetAt(3);	
			//各個有資料的shit對應欄位			
		}catch(Exception e){
			msg.setError("請檢查檔案為.xlsx格式, xlsx中有1個頁面(SHEET)");
			this.savMessage(msg);
			return execute();
		}
		
		//批次單號
		SeldBatch b=new SeldBatch();
		b.setIdno(getSession().getAttribute("userid").toString());
		b.setSdate(new Timestamp(new Date().getTime()));
		df.update(b);
		StringBuilder err=new StringBuilder();		
		StringBuilder gost=new StringBuilder();
		int cnt=0;
		//以共用欄位為基準對應各欄位: 
		
		for(int i=(sheet.getFirstRowNum()+1); i<sheet.getPhysicalNumberOfRows(); i++) {
			
			row = sheet.getRow(i);	
			//System.out.println((readCellAsString(row.getCell(0))+", "+readCellAsString(row.getCell(1))));
			if(readCellAsString(row.getCell(1)).equals("")||readCellAsString(row.getCell(0)).equals("")) {				
				continue;
			}
			
			try {
				if(limit.equals("0")) {
					err.append(check( readCellAsString(row.getCell(1)), readCellAsString(row.getCell(0)), term, false));
				}else {
					err.append(check( readCellAsString(row.getCell(1)), readCellAsString(row.getCell(0)), term, true));
				}
				
			}catch(Exception e){
				e.printStackTrace();
				errHandler(b);
				msg.setError("發生錯誤, 請檢查課程編號存在");
				this.savMessage(msg);	
				fis.close();
				return execute();
			}
			
			
		}
		
		
		if(err.length()>0) {
			errHandler(b);
			msg.setError(err.toString());
			this.savMessage(msg);
			//fis.close();
			//新竹台北意見不同，新竹要求不得匯入，台北要求強制匯入
			//return execute();
		}	
		
		
		
		
		
		
		
		
		for(int i=(sheet.getFirstRowNum()+1); i<sheet.getPhysicalNumberOfRows(); i++) {
			
			row = sheet.getRow(i);	
			
			if(readCellAsString(row.getCell(1)).equals("")||readCellAsString(row.getCell(0)).equals("")) {				
				continue;
			}
			
			try{				
				df.exSql("INSERT INTO Seld(Dtime_oid, student_no, batchOid)VALUES("+readCellAsString(row.getCell(0))+", '"+readCellAsString(row.getCell(1))+"', "+b.getOid()+")");
				
			}catch(Exception e){				
				errHandler(b);
				msg.setError("建立失敗, 儲存發生問題");
				this.savMessage(msg);	
				fis.close();
				return execute();
			}
			cnt++;
			gost.append("'"+readCellAsString(row.getCell(1))+"',");
		}	
		
		fis.close();
		
		if(gost.length()>0) {
			gost.delete(gost.length()-1, gost.length());
			int e=df.sqlGetInt("SELECT COUNT(*)FROM stmd WHERE student_no IN("+gost+")");
			if((cnt-e)>0) {
				msg.setError((cnt-e)+"位無學籍選課, 每日資料重整時自動清理");
			}			
		}
		
		msg.setMsg("已儲存"+cnt+"筆資料");
		this.savMessage(msg);	
		return execute();		
	
	}
	
	public String del() {
		Message msg=new Message();
		
		try {
			if(getSession().getAttribute("userid").toString().equals(df.sqlGetStr("SELECT idno FROM SeldBatch WHERE Oid="+batchOid))) {
				df.exSql("DELETE FROM SeldBatch WHERE Oid="+batchOid);
				df.exSql("DELETE FROM Seld WHERE batchOid="+batchOid);				
				msg.setMsg("已刪除選課資料");
				this.savMessage(msg);	
			}else {
				msg.setError("無此筆選課資料的刪除權限");
				this.savMessage(msg);	
			}
		}catch(Exception e) {
			msg.setError("刪除失敗");
			this.savMessage(msg);	
		}
		
		
		return execute();
	}
	
	private void errHandler(SeldBatch b) {
		
		df.exSql("DELETE FROM Seld WHERE batchOid="+b.getOid());
		df.exSql("DELETE FROM SeldBatch WHERE Oid="+b.getOid());
		
	}
	
	/**
	 * excel欄位強制轉文字 for XLSX
	 * @param cell
	 * @return
	 */
	private String readCellAsString(XSSFCell cell) {
		if (cell == null) {
			return null;
		}
		final DecimalFormat df = new DecimalFormat("####################0.##########");

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_BLANK:
			return "";
		case HSSFCell.CELL_TYPE_BOOLEAN:
			return Boolean.valueOf(cell.getBooleanCellValue()).toString().trim();
		case HSSFCell.CELL_TYPE_NUMERIC:
			return df.format(cell.getNumericCellValue()).trim();
		case HSSFCell.CELL_TYPE_STRING:
			return cell.getStringCellValue().trim();
		case HSSFCell.CELL_TYPE_FORMULA:
			return cell.getCellFormula().trim();
		case HSSFCell.CELL_TYPE_ERROR:
			return Byte.toString(cell.getErrorCellValue());
		default:
			return "##POI## Unknown cell type";
		}
	}

}
