package action.seld;

public class AddSeldNextAction extends AddSeldBase{
	
	public String execute(){
		getSession().removeAttribute("css");
		return SUCCESS;
	}

}
