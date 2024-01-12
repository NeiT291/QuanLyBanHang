package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ManagerProductController;
import dao.ProductDAO;
import model.Product;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ManagerProduct extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TF_Search;
	private JTable tableProduct;
	private DefaultTableModel model;

	public ManagerProduct() {
		this.Init();
	}
	public void Init() {
		
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Quản lý hàng hóa");
		setSize(1000, 600);
		
		ActionListener action = new ManagerProductController(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LB_Search = new JLabel("Tìm kiếm (mã sản phẩm / tên sản phẩm)");
		LB_Search.setFont(new Font("Arial", Font.PLAIN, 18));
		LB_Search.setBounds(10, 10, 966, 22);
		contentPane.add(LB_Search);
		
		TF_Search = new JTextField();
		TF_Search.setFont(new Font("Arial", Font.PLAIN, 15));
		TF_Search.setBounds(10, 42, 800, 30);
		contentPane.add(TF_Search);
		TF_Search.setColumns(10);
		
		JButton BTN_Search = new JButton("Tìm kiếm");
		BTN_Search.setFont(new Font("Arial", Font.PLAIN, 16));
		BTN_Search.setBounds(820, 42, 156, 30);
		BTN_Search.setActionCommand("Search");
		BTN_Search.addActionListener(action);
		contentPane.add(BTN_Search);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 800, 471);
		contentPane.add(scrollPane);
		
		tableProduct = new JTable();
		tableProduct.setFocusable(false);
		tableProduct.setFont(new Font("Arial", Font.PLAIN, 15));
		tableProduct.setRowHeight(20);
		
		model = new DefaultTableModel();
		model.addColumn("STT");
		model.addColumn("Mã sản phẩm");
		model.addColumn("Sản phẩm");
		model.addColumn("Số lượng");
		model.addColumn("Đơn giá");
		tableProduct.setModel(model);
		reload();
		scrollPane.setViewportView(tableProduct);
		
		JButton BTN_AddProduct = new JButton("Thêm sản phẩm");
		BTN_AddProduct.setFont(new Font("Arial", Font.PLAIN, 16));
		BTN_AddProduct.setBounds(820, 122, 156, 30);
		BTN_AddProduct.setActionCommand("AddProduct");
		BTN_AddProduct.addActionListener(action);
		contentPane.add(BTN_AddProduct);
		
		JButton BTN_ModifyProduct = new JButton("Sửa thông tin");
		BTN_ModifyProduct.setFont(new Font("Arial", Font.PLAIN, 16));
		BTN_ModifyProduct.setBounds(820, 162, 156, 30);
		BTN_ModifyProduct.setActionCommand("ModifyProduct");
		BTN_ModifyProduct.addActionListener(action);
		contentPane.add(BTN_ModifyProduct);
		
		JButton BTN_Reload = new JButton("Tải lại danh sách");
		BTN_Reload.setFont(new Font("Arial", Font.PLAIN, 16));
		BTN_Reload.setBounds(820, 82, 156, 30);
		BTN_Reload.setActionCommand("Reload");
		BTN_Reload.addActionListener(action);
		contentPane.add(BTN_Reload);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void exitFunc() {
		this.dispose();
	}
	public void addProduct() {
		new AddProduct(this);
	}
	public void modifyProduct() {
		int rowSelected = tableProduct.getSelectedRow();
		
		if(rowSelected == -1) {
			return;
		}
		
		String IDProduct = tableProduct.getValueAt(rowSelected, 1).toString();
		Product product = ProductDAO.getInstance().selectById(new Product(IDProduct));

		new ModifyProduct(this, product);
		
	}
	public void searchProduct() {
		String infoSearch = TF_Search.getText();
		
		String [] listString = infoSearch.split(" ");
		infoSearch = "";
		for (String string : listString) {
			if(string.isEmpty()) {
				continue;
			}
			infoSearch += string + " ";
		}
		infoSearch = infoSearch.trim();

		Product productSearch = new Product();
		productSearch.setIDProduct(infoSearch);
		productSearch.setNameProduct(infoSearch);
		
		Product product;
		product = ProductDAO.getInstance().selectById(productSearch);
		
		if(product.getIDProduct() == null) {
			ArrayList<Product> listProduct = ProductDAO.getInstance().selectByName(productSearch);
			if(listProduct.size() == 0) {
				JOptionPane.showMessageDialog(null, "Không có sản phẩm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);	
			}
			for(int i = tableProduct.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			int count = 0;
			for (Product i : listProduct) {
				count++;
				model.addRow(new Object[] {count, i.getIDProduct(), i.getNameProduct(), i.getQuantity(), i.getPrice()});
			}
		}else {
			for(int i = tableProduct.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			
			model.addRow(new Object[] {1, product.getIDProduct(), product.getNameProduct(), product.getQuantity(), product.getPrice()});
		}
	}
	public void reload() {
		for(int i = tableProduct.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
		
		ArrayList<Product> listProduct = new ArrayList<Product>();
		listProduct = ProductDAO.getInstance().selectAll();
		int count = 0;
		for (Product product : listProduct) {
			count++;
			model.addRow(new Object[] {count, product.getIDProduct(), product.getNameProduct(), product.getQuantity(), product.getPrice()});
		}
	}
}
