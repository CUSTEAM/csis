package action.license;

import action.BaseAction;
import model.LicenseCode;
import model.Message;

public class LicenseCodeManagerAction extends BaseAction{
	
	public String licenseCode, Code, Name, Locale, Level, Type, DeptName, DelCode;
	
	
	public String execute() {	
		
		
		return SUCCESS;
	}
	
	
	public String searchMix() {
		if(licenseCode.trim().length()>0)
		request.setAttribute("licenses", df.sqlGet("SELECT l.*, c.name as TypeName, (SELECT COUNT(*)FROM StdSkill WHERE LicenseCode=l.Code)as cnt FROM LicenseCode l, CODE_LICENSE c WHERE c.id=l.Type AND l.Code='"+licenseCode.substring(0, licenseCode.indexOf(","))+"'"));
		
		return SUCCESS;
	}
	
	public String search() {
		
		StringBuilder sb=new StringBuilder("SELECT l.*, c.name as TypeName, (SELECT COUNT(*)FROM StdSkill WHERE LicenseCode=l.Code)as cnt FROM LicenseCode l, CODE_LICENSE c WHERE c.id=l.Type");
		
		if(!Code.trim().equals(""))sb.append(" AND l.Code ='"+Code+"'");
		if(!Name.trim().equals(""))sb.append(" AND l.Name LIKE'%"+Name+"%'");
		if(!Locale.trim().equals(""))sb.append(" AND l.Locale ='"+Locale+"'");
		if(!Level.trim().equals(""))sb.append(" AND l.Level LIKE'"+Level+"'");
		if(!Type.trim().equals(""))sb.append(" AND l.Type ='"+Type+"'");
		if(!DeptName.trim().equals(""))sb.append(" AND l.DeptName LIKE'%"+DeptName+"%'");
		System.out.println(sb);
		request.setAttribute("licenses", df.sqlGet(sb.toString()));
		return SUCCESS;
	}
	
	public String add() {
		
		if(!Code.trim().equals("") && !Name.trim().equals("")&& !Type.trim().equals("")&& !Locale.trim().equals("")) {
			LicenseCode l=new LicenseCode();
			l.setCode(Code);
			//l.setCreator(Creator);
			l.setDeptName(DeptName);
			//l.setLastModified(lastModified);
			l.setLevel(Level);
			l.setLocale(Locale);
			l.setName(Name);
			l.setType(Type);
			df.update(l);
		}else {
			Message msg=new Message();
			msg.setError("資料不完整");
			this.savMessage(msg);
		}
		
		
		return search();
	}
	
	public String del() {
		Message msg=new Message();
		if(df.sqlGetInt("SELECT COUNT(*)FROM StdSkill WHERE LicenseCode='"+DelCode+"'")<1) {
			df.exSql("DELETE FROM LicenseCode WHERE Code='"+DelCode+"'");
			msg.setError("已刪除");
			
		}else {
			msg.setError("學生已獲得不可刪除");
		}
		this.savMessage(msg);
		return search();
	}
}
