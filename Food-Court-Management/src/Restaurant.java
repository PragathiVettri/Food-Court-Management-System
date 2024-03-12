
public class Restaurant  {

	private String rid;
	private String name;
	private int noOfTables;
	
	public Restaurant(){};
	public Restaurant( String rid ,String name,int noOfTables){
		this.rid = rid;
		this.name = name;
		this.noOfTables=noOfTables;
	}

	public void setName(String name){this.name = name;}

	public String getName(){return name;}

	public void setRid(String rid){this.rid = rid;}
	
	public String getRid(){return rid;}
	
	public void setNoOfTables(int noOfTables){this.noOfTables=noOfTables;}
	
	public int getNoOfTables(){return noOfTables;}

}