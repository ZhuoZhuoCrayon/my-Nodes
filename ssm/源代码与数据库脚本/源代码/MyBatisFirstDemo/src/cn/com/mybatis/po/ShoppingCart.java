package cn.com.mybatis.po;

public class ShoppingCart {
	private int productId; 
	private String productName;
	private int number; 
	private double price; 
	private double totalAmount; 
	public ShoppingCart(){}
	public ShoppingCart(int productId, String productName, int number,
			double price, double totalAmount) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.number = number;
		this.price = price;
		this.totalAmount = totalAmount;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void init(){ 
		this.totalAmount=this.number*this.price; 
	} 
}
