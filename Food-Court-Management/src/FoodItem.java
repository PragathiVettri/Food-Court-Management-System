
public class FoodItem{

	private String fid;
	private String name;
	private double price;
	private int availableQuantity;

	public FoodItem(){};

	public FoodItem(String fid, String name, int availableQuantity,double price){
		this.fid = fid;
		this.name = name;
		this.availableQuantity= availableQuantity;
		this.price= price; 

	}
	
	void setFid(String fid){this.fid = fid;}
	
	String getFid(){return fid;}
	
	void setName(String name){this.name = name;}
	
	String getName(){ return name;}
	
	void setAvailableQuantity(int availableQuantity){this.availableQuantity = availableQuantity;}
	
	int getAvailableQuantity(){return availableQuantity;}
	
	void setPrice(double price){this.price = price;}
	
	double getPrice(){return price;}

}

