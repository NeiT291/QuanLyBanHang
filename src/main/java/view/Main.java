package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import model.Bill;
import model.BillDetail;
import model.Product;
import model.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

import controller.MainController;
import dao.BillDAO;
import dao.ProductDAO;

import java.awt.Color;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private User user;
	private JTextField TF_Search;
	private JTable tableProduct;
	private DefaultTableModel model;
	private int numberProduct = 0;
	private JTextField TF_TotalPrice;
	private JTextField TF_GuestCash;
	private JTextField TF_Sale;
	private JTextField TF_Change;
	private JRadioButton RBTN_PrintBill;
	
	public Main(User user) {
		this.user = user;
		this.Init();
	}
	public void Init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Quản lý bán hàng");
		setResizable(false);
		setSize(1920,1080);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Action action = new MainController(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel JP_Info = new JPanel();
		JP_Info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JP_Info.setBounds(40, 55, 1198, 75);
		
		
		JLabel LB_Info_Role = new JLabel();
		LB_Info_Role.setBounds(894, 0, 261, 75);
		if(user.isAdmin()) {
			LB_Info_Role.setText("Chức vụ: Quản lý");
		}else {
			LB_Info_Role.setText("Chức vụ: Nhân viên");
		}
		JP_Info.setLayout(null);
		LB_Info_Role.setHorizontalAlignment(SwingConstants.LEFT);
		LB_Info_Role.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		JP_Info.add(LB_Info_Role);
		
		JLabel LB_Info_FullName = new JLabel();
		LB_Info_FullName.setBounds(393, 0, 481, 75);
		LB_Info_FullName.setText("Họ và tên: " + user.getFullName());
		LB_Info_FullName.setHorizontalAlignment(SwingConstants.LEFT);
		LB_Info_FullName.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		JP_Info.add(LB_Info_FullName);
		
		JLabel LB_Info_ID = new JLabel();
		LB_Info_ID.setBounds(10, 0, 373, 75);
		JP_Info.add(LB_Info_ID);
		LB_Info_ID.setText("Mã nhân viên: " + user.getId());
		LB_Info_ID.setHorizontalAlignment(SwingConstants.LEFT);
		LB_Info_ID.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		contentPane.add(JP_Info);
		
		JPanel JP_Search = new JPanel();
		JP_Search.setBounds(40, 145, 1060, 75);
		
		JP_Search.setLayout(null);
		
		JLabel JL_SearchTitle = new JLabel("Tìm kiếm (Mã sản phẩm \\ barcode)");
		JL_SearchTitle.setBounds(0, 0, 364, 29);
		JL_SearchTitle.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		JP_Search.add(JL_SearchTitle);
		
		TF_Search = new JTextField();
		TF_Search.setFont(new Font("Roboto Mono", Font.PLAIN, 18));
		TF_Search.setBounds(0, 39, 1060, 36);
		JP_Search.add(TF_Search);
		TF_Search.setColumns(10);
		contentPane.add(JP_Search);
		
		JScrollPane SP_Order = new JScrollPane();
		SP_Order.setBounds(40, 230, 1060, 434);
		
		tableProduct = new JTable();
		tableProduct.setFocusable(false);
		tableProduct.setFont(new Font("Arial", Font.PLAIN, 15));
		tableProduct.setRowHeight(20);
		
		model = new DefaultTableModel();
		model.addColumn("STT");
		model.addColumn("Mã sản phẩm");
		model.addColumn("Sản phẩm");
		model.addColumn("Đơn giá");
		model.addColumn("Số lượng");
		model.addColumn("Thành tiền");
		tableProduct.setModel(model);
		SP_Order.setViewportView(tableProduct);
		contentPane.add(SP_Order);
		
		JPanel JP_Pay = new JPanel();
		JP_Pay.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JP_Pay.setBounds(40, 674, 1467, 125);
		JP_Pay.setLayout(null);
		
		TF_Sale = new JTextField();
		TF_Sale.setHorizontalAlignment(SwingConstants.RIGHT);
		TF_Sale.setFont(new Font("Arial", Font.PLAIN, 18));
		TF_Sale.setText("0");
		TF_Sale.setColumns(10);
		TF_Sale.setBounds(480, 75, 200, 40);
		JP_Pay.add(TF_Sale);
		
		TF_TotalPrice = new JTextField();
		TF_TotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		TF_TotalPrice.setBackground(Color.WHITE);
		TF_TotalPrice.setEditable(false);
		TF_TotalPrice.setFont(new Font("Arial", Font.PLAIN, 18));
		TF_TotalPrice.setText("0");
		TF_TotalPrice.setColumns(10);
		TF_TotalPrice.setBounds(249, 75, 200, 40);
		JP_Pay.add(TF_TotalPrice);
		
		TF_GuestCash = new JTextField();
		TF_GuestCash.setHorizontalAlignment(SwingConstants.RIGHT);
		TF_GuestCash.setFont(new Font("Arial", Font.PLAIN, 18));
		TF_GuestCash.setText("0");
		TF_GuestCash.setColumns(10);
		TF_GuestCash.setBounds(10, 75, 200, 40);
		JP_Pay.add(TF_GuestCash);
		
		TF_Change = new JTextField();
		TF_Change.setHorizontalAlignment(SwingConstants.RIGHT);
		TF_Change.setBackground(Color.WHITE);
		TF_Change.setEditable(false);
		TF_Change.setFont(new Font("Arial", Font.PLAIN, 18));
		TF_Change.setText("0");
		TF_Change.setColumns(10);
		TF_Change.setBounds(704, 75, 200, 40);
		JP_Pay.add(TF_Change);
		
		JLabel LB_GuestCash = new JLabel("Tiền khách đưa");
		LB_GuestCash.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		LB_GuestCash.setBounds(10, 39, 172, 26);
		JP_Pay.add(LB_GuestCash);
		
		JLabel LB_TotalPrice = new JLabel("Tổng tiền");
		LB_TotalPrice.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		LB_TotalPrice.setBounds(249, 39, 172, 26);
		JP_Pay.add(LB_TotalPrice);
		
		JLabel LB_Sale = new JLabel("Giảm giá");
		LB_Sale.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		LB_Sale.setBounds(480, 39, 172, 26);
		JP_Pay.add(LB_Sale);
		
		JLabel LB_Change = new JLabel("Tiền thừa");
		LB_Change.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		LB_Change.setBounds(704, 39, 172, 26);
		JP_Pay.add(LB_Change);
		
		JButton BTN_TransferBank = new JButton("Chuyển khoản");
		BTN_TransferBank.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 20));
		BTN_TransferBank.setBounds(1218, 20, 239, 40);
		BTN_TransferBank.setActionCommand("TransferBank");
		BTN_TransferBank.addActionListener(action);
		JP_Pay.add(BTN_TransferBank);
		
		JButton BTN_Pay = new JButton("Thanh toán");
		BTN_Pay.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 20));
		BTN_Pay.setBounds(1218, 75, 239, 40);
		BTN_Pay.setActionCommand("Pay");
		BTN_Pay.addActionListener(action);
		JP_Pay.add(BTN_Pay);
		
		RBTN_PrintBill = new JRadioButton("In hóa đơn");
		RBTN_PrintBill.setSelected(true);
		RBTN_PrintBill.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 16));
		RBTN_PrintBill.setBounds(1084, 85, 128, 37);
		JP_Pay.add(RBTN_PrintBill);
		contentPane.add(JP_Pay);
		
		JButton BTN_CalculatePrice = new JButton();
		BTN_CalculatePrice.setBounds(914, 75, 119, 40);
		JP_Pay.add(BTN_CalculatePrice);
		BTN_CalculatePrice.setText("Tính tiền");
		BTN_CalculatePrice.setFont(new Font("Arial", Font.PLAIN, 14));
		BTN_CalculatePrice.setActionCommand("CalculatePrice");
		BTN_CalculatePrice.addActionListener(action);
		
		JPanel JP_Func = new JPanel();
		JP_Func.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JP_Func.setBounds(1248, 55, 259, 609);
		
		JP_Func.setLayout(null);
		
		JButton BTN_LogOut = new JButton("Đăng xuất");
		BTN_LogOut.setBounds(10, 10, 239, 55);
		BTN_LogOut.setActionCommand("Logout");
		BTN_LogOut.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 25));
		BTN_LogOut.setFocusable(false);
		BTN_LogOut.addActionListener(action);
		JP_Func.add(BTN_LogOut);
		
		JButton BTN_Exit = new JButton("Thoát");
		BTN_Exit.setActionCommand("Exit");
		BTN_Exit.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 25));
		BTN_Exit.setFocusable(false);
		BTN_Exit.setBounds(10, 556, 239, 43);
		BTN_Exit.addActionListener(action);
		
		JP_Func.add(BTN_Exit);
		
		contentPane.add(JP_Func);
		
		JButton BTN_ImportProduct = new JButton("Nhập hàng");
		BTN_ImportProduct.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 20));
		BTN_ImportProduct.setFocusable(false);
		BTN_ImportProduct.setActionCommand("ImportProduct");
		BTN_ImportProduct.addActionListener(action);
		BTN_ImportProduct.setBounds(10, 491, 239, 55);
		JP_Func.add(BTN_ImportProduct);
		
		JButton BTN_ManagerUser = new JButton("Quản lý nhân viên");
		BTN_ManagerUser.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 20));
		BTN_ManagerUser.setFocusable(false);
		BTN_ManagerUser.setActionCommand("ManagerUser");
		BTN_ManagerUser.setBounds(10, 90, 239, 55);
		BTN_ManagerUser.addActionListener(action);
		JP_Func.add(BTN_ManagerUser);
		if(!user.isAdmin()) {
			BTN_ManagerUser.setVisible(false);;
		}
		
		JButton BTN_ManagerProduct = new JButton("Quản lý kho hàng");
		BTN_ManagerProduct.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 20));
		BTN_ManagerProduct.setFocusable(false);
		BTN_ManagerProduct.setBounds(10, 426, 239, 55);
		BTN_ManagerProduct.setActionCommand("ManagerProduct");
		BTN_ManagerProduct.addActionListener(action);
		JP_Func.add(BTN_ManagerProduct);
		
		JPanel JP_TableFunc = new JPanel();
		JP_TableFunc.setBounds(1110, 185, 128, 479);
		contentPane.add(JP_TableFunc);
		JP_TableFunc.setLayout(null);
		
		JButton BTN_DeleteProduct = new JButton();
		BTN_DeleteProduct.setBounds(0, 86, 128, 30);
		BTN_DeleteProduct.setText("Xóa");
		BTN_DeleteProduct.setFont(new Font("Arial", Font.PLAIN, 14));
		BTN_DeleteProduct.setActionCommand("DeleteProductInTable");
		BTN_DeleteProduct.addActionListener(action);
		JP_TableFunc.add(BTN_DeleteProduct);
		
		JButton BTN_ModifyProduct = new JButton();
		BTN_ModifyProduct.setBounds(0, 46, 128, 30);
		BTN_ModifyProduct.setText("Sửa");
		BTN_ModifyProduct.setFont(new Font("Arial", Font.PLAIN, 14));
		BTN_ModifyProduct.setActionCommand("ModifyProductInTable");
		BTN_ModifyProduct.addActionListener(action);
		JP_TableFunc.add(BTN_ModifyProduct);
		
		JButton BTN_AddProduct = new JButton();
		BTN_AddProduct.setBounds(0, 0, 128, 36);
		BTN_AddProduct.setText("Thêm");
		BTN_AddProduct.setFont(new Font("Arial", Font.PLAIN, 14));
		BTN_AddProduct.setActionCommand("AddProductToTable");
		BTN_AddProduct.addActionListener(action);
		JP_TableFunc.add(BTN_AddProduct);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void exitProgram() {
		int check = JOptionPane.showConfirmDialog(null, "Thoát chương trình", "Thông báo", JOptionPane.YES_NO_OPTION);
		if(check == 0) {
			System.exit(0);			
		}
	}
	public void logOut() {
		this.user = null;
		new Login();
		this.dispose();
	}
	public void addProductToTable() {
		String IDProduct = TF_Search.getText();
		Product product = ProductDAO.getInstance().selectById(new Product(IDProduct));

		if(product.getIDProduct() == null) {
			JOptionPane.showMessageDialog(null, "Không có sản phẩm này");
			return;
		}
		
		int productCount = Integer.valueOf(JOptionPane.showInputDialog(null, "Số lượng sản phẩm", "Thông báo", JOptionPane.QUESTION_MESSAGE, null, null, "1").toString());
		
		for(int i = 0; i < tableProduct.getRowCount(); i++) {
			if(tableProduct.getValueAt(i, 1).toString().equals(product.getIDProduct())) {
				int newProductCount = Integer.valueOf(tableProduct.getValueAt(i, 4).toString()) + productCount;
				tableProduct.setValueAt(newProductCount, i, 4);
				tableProduct.setValueAt(product.getPrice() * newProductCount, i, 5);
				calculatePrice();
				return;
			}
		}
		numberProduct++;
		model.addRow(new Object[] {numberProduct, product.getIDProduct(), product.getNameProduct(), product.getPrice(), productCount, product.getPrice() * productCount});
		calculatePrice();
	}
	public void modifyProductInTable() {
		int selectedRow = tableProduct.getSelectedRow();
		
		if(selectedRow == -1) {
			return;
		}
		int productCount = Integer.valueOf(JOptionPane.showInputDialog(null, "Số lượng sản phẩm", "Thông báo", JOptionPane.QUESTION_MESSAGE, null, null, "1").toString());
		tableProduct.setValueAt(productCount, tableProduct.getSelectedRow(), 4);
		
		String IDProduct = tableProduct.getValueAt(selectedRow, 1).toString();
		Product product = ProductDAO.getInstance().selectById(new Product(IDProduct));
		tableProduct.setValueAt(product.getPrice() * productCount, tableProduct.getSelectedRow(), 5);
		calculatePrice();
	}
	public void deleteProductInTable() {
		int selectedRow = tableProduct.getSelectedRow();
		
		if(selectedRow == -1) {
			return;
		}
		model.removeRow(selectedRow);
		calculatePrice();
	}
	public void calculatePrice() {
		int totalPrice = 0;
		for(int i = 0; i < tableProduct.getRowCount(); i++) {
			totalPrice += Integer.valueOf(tableProduct.getValueAt(i, 5).toString());
		}
		
		int guestCash = Integer.valueOf(TF_GuestCash.getText());
		int sale = Integer.valueOf(TF_Sale.getText());
		int change = guestCash - (totalPrice - sale);
		TF_TotalPrice.setText(String.valueOf(totalPrice));
		TF_Change.setText(String.valueOf(change));
	}
	public void pay() {
		try {
			calculatePrice();
			if(Integer.valueOf(TF_Change.getText()) < 0) {
				JOptionPane.showMessageDialog(null, "Khách đưa thiếu tiền", "Thanh toán", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if(Integer.valueOf(TF_TotalPrice.getText()) == 0) {
				JOptionPane.showMessageDialog(null, "Không có đơn hàng", "Thanh toán", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			Bill bill = new Bill();
			
			LocalDateTime myDateObj = LocalDateTime.now();
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		    String formattedDate = myDateObj.format(myFormatObj);
		    
			bill.setIDBill(formattedDate);
			bill.setIDUser(user.getId());
			bill.setDiscount(Integer.valueOf(TF_Sale.getText()));
			bill.setTotalPrice(Integer.valueOf(TF_TotalPrice.getText()));
			
			ArrayList<BillDetail> listProduct = new ArrayList<BillDetail>();
			for(int i = 0; i < tableProduct.getRowCount(); i++) {
				BillDetail billDetail = new BillDetail();
				billDetail.setIDBill(bill.getIDBill());
				billDetail.setIDProduct(tableProduct.getValueAt(i, 1).toString());
				billDetail.setNameProduct(tableProduct.getValueAt(i, 2).toString());
				billDetail.setPrice(Integer.valueOf(tableProduct.getValueAt(i, 3).toString()));
				billDetail.setQuantity(Integer.valueOf(tableProduct.getValueAt(i, 4).toString()));
				listProduct.add(billDetail);
				
				if(!updateQuantityProduct(i, tableProduct.getValueAt(i, 1).toString())) {
					TF_GuestCash.setText("0");
					return;
				};
			}
			bill.setListProduct(listProduct);
			
			BillDAO.getInstance().insert(bill);
			
			printBill(bill);
			JOptionPane.showMessageDialog(null, "Thanh toán thành công", "Thanh toán", JOptionPane.INFORMATION_MESSAGE);
			
			resetTable();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Chương trình lỗi");
		}
		
	}
	public void transferBank() {
		new TransferBank(this);
	}
	public boolean updateQuantityProduct(int row, String IDProduct) {
		Product product = ProductDAO.getInstance().selectById(new Product(IDProduct));
		int quantityProductInBill = Integer.valueOf(tableProduct.getValueAt(row, 4).toString());
		int newQuantity = product.getQuantity() - quantityProductInBill;
		if(newQuantity < 0) {
			JOptionPane.showMessageDialog(null, "Số lượng " + product.getNameProduct() + " còn: " + product.getQuantity(), "Thanh toán", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
		product.setQuantity(newQuantity);
		ProductDAO.getInstance().update(product);
		return true;
	}
	public void printBill(Bill bill) {
		if(RBTN_PrintBill.isSelected()) {
			new PrintBill(user, bill);	
		}
	}
	public void resetTable() {
		numberProduct = 0;
		for(int i = tableProduct.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
		TF_GuestCash.setText("0");
		TF_Sale.setText("0");
		calculatePrice();
	}
	
	public void managerUser() {
		new ManagerUser();
	}
	public void managerProduct() {
		new ManagerProduct();
	}
	public void importProduct() {
		new ImportProductView(user);
	}
	public int getTotalPrice() {
		return Integer.valueOf(TF_TotalPrice.getText());
	}
	public void setGuestCash(int number) {
		TF_GuestCash.setText(String.valueOf(number));
	}
}
