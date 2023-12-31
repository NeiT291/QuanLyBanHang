package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import model.User;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.Action;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

import controller.MainController;
import java.awt.Color;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private User user;
	
	
	public Main(User user) {
		this.user = user;
		this.Init();
	}
	public void Init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Quản lý bán hàng");
//		setResizable(false);
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
		JP_Info.setBounds(40, 55, 1168, 75);
		
		
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
		JP_Search.setBounds(40, 145, 1168, 75);
		
		JP_Search.setLayout(null);
		
		JLabel JL_SearchTitle = new JLabel("Tìm kiếm (Mã sản phẩm \\ barcode)");
		JL_SearchTitle.setBounds(0, 0, 364, 29);
		JL_SearchTitle.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		JP_Search.add(JL_SearchTitle);
		
		JTextField TF_Search = new JTextField();
		TF_Search.setFont(new Font("Roboto Mono", Font.PLAIN, 18));
		TF_Search.setBounds(0, 39, 1087, 36);
		JP_Search.add(TF_Search);
		TF_Search.setColumns(10);
		
		JButton BTN_Search = new JButton("O");
		BTN_Search.setBounds(1097, 36, 70, 39);
		JP_Search.add(BTN_Search);
		contentPane.add(JP_Search);
		
		JScrollPane SP_Order = new JScrollPane();
		SP_Order.setBounds(40, 230, 1168, 434);
		
		
		
		JTable tableProduct = new JTable();
		tableProduct.setFocusable(false);
		tableProduct.setFont(new Font("Roboto Mono Light", Font.PLAIN, 14));
		tableProduct.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"STT", "M\u00E3 s\u1EA3n ph\u1EA9m", "S\u1EA3n ph\u1EA9m", "\u0110\u01A1n gi\u00E1", "S\u1ED1 l\u01B0\u1EE3ng", "Th\u00E0nh ti\u1EC1n"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		SP_Order.setViewportView(tableProduct);

		contentPane.add(SP_Order);
		
		JPanel JP_Pay = new JPanel();
		JP_Pay.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JP_Pay.setBounds(40, 674, 1467, 125);
		JP_Pay.setLayout(null);
		
		JTextField TF_Sale = new JTextField();
		TF_Sale.setHorizontalAlignment(SwingConstants.RIGHT);
		TF_Sale.setFont(new Font("Arial", Font.PLAIN, 18));
		TF_Sale.setText("0");
		TF_Sale.setColumns(10);
		TF_Sale.setBounds(480, 75, 200, 40);
		JP_Pay.add(TF_Sale);
		
		JTextField TF_TotalPrice = new JTextField();
		TF_TotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		TF_TotalPrice.setBackground(Color.WHITE);
		TF_TotalPrice.setEditable(false);
		TF_TotalPrice.setFont(new Font("Arial", Font.PLAIN, 18));
		TF_TotalPrice.setText("0");
		TF_TotalPrice.setColumns(10);
		TF_TotalPrice.setBounds(249, 75, 200, 40);
		JP_Pay.add(TF_TotalPrice);
		
		JTextField TF_GuestCash = new JTextField();
		TF_GuestCash.setHorizontalAlignment(SwingConstants.RIGHT);
		TF_GuestCash.setFont(new Font("Arial", Font.PLAIN, 18));
		TF_GuestCash.setText("0");
		TF_GuestCash.setColumns(10);
		TF_GuestCash.setBounds(10, 75, 200, 40);
		JP_Pay.add(TF_GuestCash);
		
		JTextField TF_Change = new JTextField();
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
		
		JButton BTN_Transfer = new JButton("Chuyển khoản");
		BTN_Transfer.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 20));
		BTN_Transfer.setBounds(1218, 20, 239, 40);
		JP_Pay.add(BTN_Transfer);
		
		JButton BTN_Pay = new JButton("Thanh toán");
		BTN_Pay.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 20));
		BTN_Pay.setBounds(1218, 75, 239, 40);
		JP_Pay.add(BTN_Pay);
		
		JRadioButton RBTN_PrintBill = new JRadioButton("In hóa đơn");
		RBTN_PrintBill.setSelected(true);
		RBTN_PrintBill.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 16));
		RBTN_PrintBill.setBounds(1039, 82, 121, 37);
		JP_Pay.add(RBTN_PrintBill);
		contentPane.add(JP_Pay);
		
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
		BTN_Exit.setBounds(10, 544, 239, 55);
		BTN_Exit.addActionListener(action);
		
		JP_Func.add(BTN_Exit);
		
		JButton BTN_AddUser = new JButton("Thêm nhân viên");
		BTN_AddUser.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 20));
		BTN_AddUser.setFocusable(false);
		BTN_AddUser.setActionCommand("AddUser");
		BTN_AddUser.setBounds(10, 479, 239, 55);
		BTN_AddUser.addActionListener(action);
		JP_Func.add(BTN_AddUser);
		
		contentPane.add(JP_Func);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void exitProgram() {
		System.exit(0);
	}
	public void logOut() {
		this.user = null;
		new Login();
		this.dispose();
	}
	public void addUser() {
		new AddUser();
	}
	
}
