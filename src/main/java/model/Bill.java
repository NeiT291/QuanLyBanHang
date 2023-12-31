package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Bill {
	private String IDBill;
	private String IDUser;
	private ArrayList<BillDetail> listProduct;
	private String dateTime;
	private int discount;
	private int totalPrice;
	
	public Bill() {
		this.dateTime = this.autoGenDateTime();
	}
	
	public Bill(String iDBill, String iDUser, ArrayList<BillDetail> listProduct, int discount, int totalPrice) {
		this.IDBill = iDBill;
		this.IDUser = iDUser;
		this.listProduct = listProduct;
		this.dateTime = this.autoGenDateTime();
		this.discount = discount;
		this.totalPrice = totalPrice;
	}

	public String getIDBill() {
		return IDBill;
	}
	public void setIDBill(String iDBill) {
		IDBill = iDBill;
	}
	public String getIDUser() {
		return IDUser;
	}
	public void setIDUser(String iDUser) {
		IDUser = iDUser;
	}
	
	public ArrayList<BillDetail> getListProduct() {
		return listProduct;
	}
	public void setListProduct(ArrayList<BillDetail> listProduct) {
		this.listProduct = listProduct;
	}

	public String getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String autoGenDateTime() {
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = myDateObj.format(myFormatObj);

		return formattedDate;
	}
	@Override
	public String toString() {
		return "Bill [IDBill=" + IDBill + ", IDUser=" + IDUser + ", listProduct=" + listProduct + ", dateTime="
				+ dateTime + ", discount=" + discount + ", totalPrice=" + totalPrice + "]";
	}
	
	
	
}
