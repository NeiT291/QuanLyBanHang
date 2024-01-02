package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.JDBCUtil;
import model.ImportDetail;
import model.ImportProduct;
import model.User;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JScrollPane;

public class PrintBillImport extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private ImportProduct importProduct;
	private User user;
	private JLabel LB_NameShop;
	private JLabel LB_AndressShop;
	private JLabel LB_NameUser;
	private JLabel LB_Supplier;
	private JLabel LB_IDBill;
	private JLabel LB_Date;
	private JLabel LB_TotalNumber;
	
	public PrintBillImport(User user, ImportProduct importProduct) {
		this.user = user;
		this.importProduct = importProduct;
		this.Init();
	}
	public void Init() {
		setBackground(Color.WHITE);
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Bill");
		setResizable(false);
		setSize(600, 850);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		Color color = new Color(0, 0, 0, 0);
		panel.setBackground(color);
		panel.setBounds(0, 0, 600, 850);
		contentPane.add(panel);
		
		LB_NameShop = new JLabel("CỬA HÀNG:");
		LB_NameShop.setBackground(Color.WHITE);
		LB_NameShop.setFont(new Font("Arial", Font.PLAIN, 18));
		LB_NameShop.setBounds(10, 19, 580, 30);
		contentPane.add(LB_NameShop);
		
		LB_AndressShop = new JLabel("ĐỊA CHỈ:");
		LB_AndressShop.setFont(new Font("Arial", Font.PLAIN, 18));
		LB_AndressShop.setBounds(10, 50, 580, 30);
		contentPane.add(LB_AndressShop);
		
		JLabel lblNewLabel_1 = new JLabel("HÓA ĐƠN NHẬP HÀNG");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 90, 580, 42);
		contentPane.add(lblNewLabel_1);
		
		LB_Date = new JLabel("Ngày: ");
		LB_Date.setFont(new Font("Arial", Font.PLAIN, 18));
		LB_Date.setBounds(10, 142, 580, 22);
		contentPane.add(LB_Date);
		
		LB_NameUser = new JLabel("Nhân viên:");
		LB_NameUser.setFont(new Font("Arial", Font.PLAIN, 18));
		LB_NameUser.setBounds(10, 169, 580, 22);
		contentPane.add(LB_NameUser);
		
		JLabel LB_TotalPrice = new JLabel("Tổng Tiền:");
		LB_TotalPrice.setFont(new Font("Arial", Font.PLAIN, 18));
		LB_TotalPrice.setBounds(10, 732, 86, 22);
		contentPane.add(LB_TotalPrice);
		
		LB_TotalNumber = new JLabel("0");
		LB_TotalNumber.setText(String.valueOf(importProduct.getTotalPrice()));
		LB_TotalNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_TotalNumber.setFont(new Font("Arial", Font.PLAIN, 18));
		LB_TotalNumber.setBounds(299, 732, 291, 22);
		contentPane.add(LB_TotalNumber);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 257, 580, 465);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		
		model = new DefaultTableModel();
		model.addColumn("STT");
		model.addColumn("Mã sản phẩm");
		model.addColumn("Sản phẩm");
		model.addColumn("Đơn giá");
		model.addColumn("Số lượng");
		model.addColumn("Thành tiền");
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		LB_IDBill = new JLabel("Mã bill:");
		LB_IDBill.setFont(new Font("Arial", Font.PLAIN, 18));
		LB_IDBill.setBounds(10, 225, 580, 22);
		contentPane.add(LB_IDBill);
		
		LB_Supplier = new JLabel("Nhà cung cấp: ");
		LB_Supplier.setText(importProduct.getSupplier());
		LB_Supplier.setFont(new Font("Arial", Font.PLAIN, 18));
		LB_Supplier.setBounds(10, 195, 580, 22);
		contentPane.add(LB_Supplier);
		
		loadShopInfo();
		addBillToTable();
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);
	}
	public void addBillToTable() {
		LB_Date.setText("Ngày: " + importProduct.getDateTime());
		LB_NameUser.setText("Nhân viên: " + user.getFullName());
		LB_Supplier.setText("Nhà cung cấp: " + importProduct.getSupplier());
		LB_IDBill.setText("Mã Bill: " + importProduct.getIDImportProduct());
		LB_TotalNumber.setText(String.valueOf(importProduct.getTotalPrice()));
		
		ArrayList<ImportDetail> listProduct = importProduct.getListProduct();

		int count = 0;
		for (ImportDetail importDetail : listProduct) {
			count++;
			model.addRow(new Object[] {count, importDetail.getIDProduct(), importDetail.getNameProduct(), importDetail.getPrice(), importDetail.getQuantity(), importDetail.getPrice() * importDetail.getQuantity()});
		}
	}
	public void loadShopInfo() {
		final String SHOP_CONFIG = "\\shop.properties";
		Properties properties = new Properties();
        InputStream inputStream = null;
        
        try {
            inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream(SHOP_CONFIG);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        String nameShop = properties.getProperty("name");
        String andressShop = properties.getProperty("andress");
        
        LB_NameShop.setText("Cửa hàng: " + nameShop);
        LB_AndressShop.setText("Địa chỉ: " + andressShop);
	}
}
