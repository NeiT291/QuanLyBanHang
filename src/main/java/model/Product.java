package model;

public class Product {
	private String IDProduct;
	private String nameProduct;
	private int quantity;
	private int price;
	
	public Product() {
		
	}
	public Product(String iDProduct, String nameProduct, int quantity, int price) {
		this.IDProduct = iDProduct;
		this.nameProduct = nameProduct;
		this.quantity = quantity;
		this.price = price;
	}

	public String getIDProduct() {
		return IDProduct;
	}

	public void setIDProduct(String iDProduct) {
		IDProduct = iDProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [IDProduct=" + IDProduct + ", nameProduct=" + nameProduct + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	
}
