package test;

import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.KeyStroke;
import javax.swing.UIManager;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dao.BillDAO;
import dao.BillDetailDAO;
import dao.JDBCUtil;
import dao.ProductDAO;
import dao.UserDAO;
import model.Bill;
import model.BillDetail;
import model.Product;
import model.User;
import view.Login;
import view.Main;
import view.PrintBill;

public class testJDBC {

	public static void main(String[] args) {
//		Date date = Date.valueOf("2000-3-3");
//		User test = new User(10, "GiapTien", "123", "GT", true, date, "0123123", "BG",false);
//		UserDAO.getInstance().insert(test);
		
//		User user = new User();
//		user.setUsername("GiapTien");
//		user.setPassword("123");
//		System.out.println(UserDAO.getInstance().selectByUsernameAndPassword(user).toString());
		
//		Product product = new Product("2", "Raucau1", 120, 10000);
//		ProductDAO.getInstance().insert(product);
//		ProductDAO.getInstance().delete(new Product("2", null, 0, 0));
		
//		UserDAO.getInstance().delete(new User(3, null, null, null, false, null, null, null, false));
//		System.out.println(ProductDAO.getInstance().selectById(new Product("2", null, 0, 0)).toString()); 
//		ArrayList<BillDetail> test = new ArrayList<BillDetail>();
//		Bill bill = new Bill("4",3,test, 2200, 50000);
//		BillDAO.getInstance().insert(bill);
//		System.out.println(BillDAO.getInstance().selectById(bill).toString());
		
//		Bill bill = new Bill();
//		bill.setIDBill("3");
//		System.out.println(BillDAO.getInstance().selectById(bill).toString());
//		
//		Connection c = JDBCUtil.getConnection();
//		JDBCUtil.printInfo(c);
//		JDBCUtil.closeConnection(c);
//		Date date = Date.valueOf("2023-2-3");
//		User user = new User("2023712123020", "giaptien2", "123", "Giáp Hoàng Việt Tiến", true, date, "123456789", "BG", true);
//		UserDAO.getInstance().insert(user);
		new Login();
	}

}
