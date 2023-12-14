package model;

import java.sql.Date;

public class User {
	private int id;
	private String username;
	private String password;
	private String fullName;
	private boolean sex;
	private Date birthDay;
	private String phone;
	private String address;
	private boolean isAdmin;
	
	public User() {
		
	}

	public User(int id, String username, String password, String fullName, boolean sex, Date birthDay, String phone, String address, boolean isAdmin) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.sex = sex;
		this.birthDay = birthDay;
		this.phone = phone;
		this.address = address;
		this.isAdmin = isAdmin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", fullName=" + fullName
				+ ", sex=" + sex + ", birthDay=" + birthDay + ", phone=" + phone + ", address=" + address + ", isAdmin="
				+ isAdmin + "]";
	}

}
