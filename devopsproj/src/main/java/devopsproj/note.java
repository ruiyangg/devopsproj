package devopsproj;

public class note{
	protected String name;
	 protected String description;
	 protected String target_date;
	 protected String accomplish;
	 
	public note(String name, String description, String target_date, String accomplish) {
	super();
	this.name = name;
	this.description = description;
	this.target_date= target_date;
	this.accomplish = accomplish;
	
	
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}
	public String gettarget_date() {
		return target_date;
	}
	public void settarget_date(String target_date) {
		this.target_date = target_date;
	}
	public String getaccomplish() {
		return accomplish;
	}
	public void setaccomplish(String accomplish) {
		this.accomplish = accomplish;
	}

}
