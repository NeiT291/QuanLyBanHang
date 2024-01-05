package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ImportProductController;
import dao.ImportProductDAO;
import dao.ProductDAO;
import model.ImportDetail;
import model.ImportProduct;
import model.Product;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ImportProductView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TF_Search;
	private JTable tableProduct;
	private DefaultTableModel model;
	private JTextField TF_Supplier;
	private boolean checkModify = false;
	private User user;

	public ImportProductView(User user) {
		this.user = user;
		this.Init();
	}
	public void Init() {
		
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Nhập hàng");
		setSize(1000, 600);
		
		ActionListener action = new ImportProductController(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LB_Search = new JLabel("Mã sản phẩm");
		LB_Search.setFont(new Font("Arial", Font.PLAIN, 18));
		LB_Search.setBounds(10, 10, 800, 22);
		contentPane.add(LB_Search);
		
		TF_Search = new JTextField();
		TF_Search.setFont(new Font("Arial", Font.PLAIN, 15));
		TF_Search.setBounds(10, 42, 800, 30);
		contentPane.add(TF_Search);
		TF_Search.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 152, 800, 402);
		contentPane.add(scrollPane);
		
		tableProduct = new JTable();
		tableProduct.setFocusable(false);
		tableProduct.setFont(new Font("Arial", Font.PLAIN, 15));
		tableProduct.setRowHeight(20);
		
		model = new DefaultTableModel();
		model.addColumn("Mã sản phẩm");
		model.addColumn("Sản phẩm");
		model.addColumn("Số lượng");
		model.addColumn("Đơn giá");
		tableProduct.setModel(model);
		scrollPane.setViewportView(tableProduct);
		
		JButton BTN_AddProduct = new JButton("Thêm sản phẩm");
		BTN_AddProduct.setFont(new Font("Arial", Font.PLAIN, 16));
		BTN_AddProduct.setBounds(820, 41, 156, 30);
		BTN_AddProduct.setActionCommand("AddProduct");
		BTN_AddProduct.addActionListener(action);
		contentPane.add(BTN_AddProduct);
		
		JButton BTN_ModifyProduct = new JButton("Sửa thông tin");
		BTN_ModifyProduct.setFont(new Font("Arial", Font.PLAIN, 16));
		BTN_ModifyProduct.setBounds(820, 152, 156, 30);
		BTN_ModifyProduct.setActionCommand("ModifyProduct");
		BTN_ModifyProduct.addActionListener(action);
		contentPane.add(BTN_ModifyProduct);
		
		TF_Supplier = new JTextField();
		TF_Supplier.setFont(new Font("Arial", Font.PLAIN, 15));
		TF_Supplier.setColumns(10);
		TF_Supplier.setBounds(10, 112, 800, 30);
		contentPane.add(TF_Supplier);
		
		JLabel LB_Supplier = new JLabel("Nhà cung cấp");
		LB_Supplier.setFont(new Font("Arial", Font.PLAIN, 18));
		LB_Supplier.setBounds(10, 82, 800, 22);
		contentPane.add(LB_Supplier);
		
		JButton BTN_DeleteProduct = new JButton("Xóa");
		BTN_DeleteProduct.setFont(new Font("Arial", Font.PLAIN, 16));
		BTN_DeleteProduct.setActionCommand("DeleteProduct");
		BTN_DeleteProduct.addActionListener(action);
		BTN_DeleteProduct.setBounds(820, 192, 156, 30);
		contentPane.add(BTN_DeleteProduct);
		
		JButton BTN_ImportProduct = new JButton("Nhập hàng");
		BTN_ImportProduct.setFont(new Font("Arial", Font.PLAIN, 16));
		BTN_ImportProduct.setActionCommand("ImportProduct");
		BTN_ImportProduct.addActionListener(action);
		BTN_ImportProduct.setBounds(820, 524, 156, 30);
		contentPane.add(BTN_ImportProduct);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void exitFunc() {
		this.dispose();
	}
	public void addProduct() {
		String IDProduct = TF_Search.getText();
		Product product = ProductDAO.getInstance().selectById(new Product(IDProduct));
		if(product.getIDProduct() == null) {
			new NewProductImport(this);
			return;
		}
		
		NewProductImport newProductImport = new NewProductImport(this);
		newProductImport.setTF_ID(product.getIDProduct());
		newProductImport.setTF_NameProduct(product.getNameProduct());
		newProductImport.setTF_Quantity(1);
		newProductImport.setTF_Price(product.getPrice());
	}

	public void modifyProduct() {
		int rowSelected = tableProduct.getSelectedRow();
		
		if(rowSelected == -1) {
			return;
		}
		checkModify = true;
		NewProductImport newProductImport = new NewProductImport(this);
		newProductImport.setTF_ID(tableProduct.getValueAt(rowSelected, 0).toString());
		newProductImport.setTF_NameProduct(tableProduct.getValueAt(rowSelected, 1).toString());
		newProductImport.setTF_Quantity(Integer.valueOf(tableProduct.getValueAt(rowSelected, 2).toString()));
		newProductImport.setTF_Price(Integer.valueOf(tableProduct.getValueAt(rowSelected, 3).toString()));
	}
	public void deleteProduct() {
		int rowSelected = tableProduct.getSelectedRow();
		
		if(rowSelected == -1) {
			return;
		}
		model.removeRow(rowSelected);
	}
	
	public void importProduct() {
		if(TF_Supplier.getText().length() == 0 || tableProduct.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Lỗi", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		int totalPrice = 0;
		
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	    String formattedDate = myDateObj.format(myFormatObj);
	    
		
		ImportProduct importProduct = new ImportProduct();
		importProduct.setIDImportProduct(formattedDate);
		importProduct.setIDUser(user.getId());
		importProduct.setSupplier(TF_Supplier.getText());
		
		ArrayList<ImportDetail> listProduct = new ArrayList<ImportDetail>();
		for(int i = 0; i< tableProduct.getRowCount();i++) {
			ImportDetail detail = new ImportDetail();
			detail.setIDImportProduct(importProduct.getIDImportProduct());
			detail.setIDProduct(tableProduct.getValueAt(i, 0).toString());
			detail.setNameProduct(tableProduct.getValueAt(i, 1).toString());
			detail.setQuantity(Integer.valueOf(tableProduct.getValueAt(i, 2).toString()));
			detail.setPrice(Integer.valueOf(tableProduct.getValueAt(i, 3).toString()));
			
			totalPrice += detail.getPrice();
			
			listProduct.add(detail);
		}
		
		importProduct.setListProduct(listProduct);
		importProduct.setTotalPrice(totalPrice);
		
		for (ImportDetail importDetail : listProduct) {
			Product product = ProductDAO.getInstance().selectById(new Product(importDetail.getIDProduct()));
			if(product.getIDProduct() == null) {
				product.setIDProduct(importDetail.getIDProduct());
				product.setNameProduct(importDetail.getNameProduct());
				product.setQuantity(importDetail.getQuantity());
				product.setPrice(importDetail.getPrice());
				ProductDAO.getInstance().insert(product);
			}else {
				product.setQuantity(importDetail.getQuantity());
				ProductDAO.getInstance().update(product);
			}
		}
		ImportProductDAO.getInstance().insert(importProduct);
		
		JOptionPane.showMessageDialog(null, "Nhập hàng thành công", "Thanh toán", JOptionPane.INFORMATION_MESSAGE);
		new PrintBillImport(user, importProduct);
	}
	
	public void addRowModelTable(Product product) {
		int quantity = product.getQuantity();
		for(int i = 0; i< tableProduct.getRowCount();i++) {
			if(tableProduct.getValueAt(i, 0).toString().equals(product.getIDProduct())) {
				if(checkModify) {
					tableProduct.setValueAt(quantity, i, 2);
					checkModify = false;
					return;
				}
				int newQuantity = Integer.valueOf(tableProduct.getValueAt(i, 2).toString()) + quantity;
				tableProduct.setValueAt(newQuantity, i, 2);
				return;
			}
		}
		model.addRow(new Object[] {product.getIDProduct(), product.getNameProduct(), quantity, product.getPrice()});
	}
	public void resetTable() {
		for(int i = tableProduct.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}
}
