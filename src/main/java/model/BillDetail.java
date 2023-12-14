package model;


public class BillDetail {
	
	private String IDBill;
	private String IDProduct;
	private String nameProduct;
	private int quantity;
	
	public BillDetail() {
	}
	public BillDetail(String iDBill) {
		this.IDBill = iDBill;
	}
	public BillDetail(String iDBill, String iDProduct, String nameProduct, int quantity) {
		this.IDBill = iDBill;
		this.IDProduct = iDProduct;
		this.nameProduct = nameProduct;
		this.quantity = quantity;
	}

	public String getIDBill() {
		return IDBill;
	}

	public void setIDBill(String iDBill) {
		IDBill = iDBill;
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

	@Override
	public String toString() {
		return "BillDetail [IDBill=" + IDBill + ", IDProduct=" + IDProduct + ", nameProduct=" + nameProduct
				+ ", quantity=" + quantity + "]";
	}
	
	
}
