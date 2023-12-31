package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ImportProduct {
	private String IDImportProduct;
	private String IDUser;
	private ArrayList<ImportDetail> listProduct;
	private String dateTime;
	private int totalPrice;

	public ImportProduct() {
		this.dateTime = this.autoGenDateTime();
	}

	public ImportProduct(String iDImportProduct, String iDUser, ArrayList<ImportDetail> listProduct, String dateTime,
			int totalPrice) {
		super();
		IDImportProduct = iDImportProduct;
		IDUser = iDUser;
		this.listProduct = listProduct;
		this.dateTime = dateTime;
		this.totalPrice = totalPrice;
	}

	public String getIDImportProduct() {
		return IDImportProduct;
	}

	public void setIDImportProduct(String iDImportProduct) {
		IDImportProduct = iDImportProduct;
	}

	public String getIDUser() {
		return IDUser;
	}

	public void setIDUser(String iDUser) {
		IDUser = iDUser;
	}

	public ArrayList<ImportDetail> getListProduct() {
		return listProduct;
	}

	public void setListProduct(ArrayList<ImportDetail> listProduct) {
		this.listProduct = listProduct;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
}
