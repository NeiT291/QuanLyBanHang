package model;

public class ImportDetail {
	private String IDImportProduct;
	private String IDProduct;
	private String nameProduct;
	private int quantity;
	private int price;

	public ImportDetail() {

	}

	public ImportDetail(String IDImportProduct) {
		this.IDImportProduct  = IDImportProduct;
	}

	public ImportDetail(String iDImportProduct, String iDProduct, String nameProduct, int quantity, int price) {
		IDImportProduct = iDImportProduct;
		IDProduct = iDProduct;
		this.nameProduct = nameProduct;
		this.quantity = quantity;
		this.price = price;
	}

	public String getIDImportProduct() {
		return IDImportProduct;
	}

	public void setIDImportProduct(String iDImportProduct) {
		IDImportProduct = iDImportProduct;
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
		return "ImportDetail [IDImportProduct=" + IDImportProduct + ", IDProduct=" + IDProduct + ", nameProduct="
				+ nameProduct + ", quantity=" + quantity + ", price=" + price + "]";
	}
	
}
