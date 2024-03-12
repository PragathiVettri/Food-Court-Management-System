
public class Appetiziers extends FoodItem{

	private String size;

	public Appetiziers(){
		this("fid" ," name", 0, 0.0 , "size");
	}

	public Appetiziers(String fid , String name,int availableQuantity, double price , String size ){
		super(fid,name, availableQuantity,price);
		this.size = size;
	}

	public void setSize(String size){this.size = size;}
	public String getSize(){return size;}

}