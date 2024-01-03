package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ProductDAO;
import model.Product;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;


public class ModifyProduct extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TF_ID;
	private JTextField TF_NameProduct;
	private JTextField TF_Quantity;
	private JTextField TF_Price;
	private ManagerProduct managerProduct;
	private Product product;
	
	public ModifyProduct(ManagerProduct managerProduct, Product product) {
		this.managerProduct = managerProduct;
		this.product = product;
		this.Init();
	}
	public void Init() {
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Thêm Sản phẩm");
		setSize(600, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel JP_Info = new JPanel();
		JP_Info.setBounds(0, 0, 586, 222);
		contentPane.add(JP_Info);
		JP_Info.setLayout(null);
		
		JLabel LB_ID = new JLabel("Mã sản phẩm");
		LB_ID.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_ID.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		LB_ID.setBounds(20, 10, 146, 44);
		JP_Info.add(LB_ID);
		
		JLabel LB_ID_1 = new JLabel("Tên sản phẩm");
		LB_ID_1.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_ID_1.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		LB_ID_1.setBounds(20, 64, 146, 44);
		JP_Info.add(LB_ID_1);
		
		JLabel LB_ID_2 = new JLabel("Số lượng");
		LB_ID_2.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_ID_2.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		LB_ID_2.setBounds(20, 118, 146, 44);
		JP_Info.add(LB_ID_2);
		
		JLabel LB_ID_3 = new JLabel("Đơn giá");
		LB_ID_3.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_ID_3.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		LB_ID_3.setBounds(20, 172, 146, 44);
		JP_Info.add(LB_ID_3);
		
		TF_ID = new JTextField();
		TF_ID.setEditable(false);
		TF_ID.setText(product.getIDProduct());
		TF_ID.setBackground(Color.WHITE);
		TF_ID.setFont(new Font("Arial", Font.PLAIN, 16));
		TF_ID.setBounds(178, 14, 390, 40);
		JP_Info.add(TF_ID);
		TF_ID.setColumns(10);
		
		TF_NameProduct = new JTextField();
		TF_NameProduct.setText(product.getNameProduct());
		TF_NameProduct.setFont(new Font("Arial", Font.PLAIN, 16));
		TF_NameProduct.setColumns(10);
		TF_NameProduct.setBounds(178, 64, 390, 40);
		JP_Info.add(TF_NameProduct);
		
		TF_Quantity = new JTextField();
		TF_Quantity.setText(String.valueOf(product.getQuantity()));
		TF_Quantity.setFont(new Font("Arial", Font.PLAIN, 16));
		TF_Quantity.setColumns(10);
		TF_Quantity.setBounds(178, 118, 390, 40);
		JP_Info.add(TF_Quantity);
		
		TF_Price = new JTextField();
		TF_Price.setText(String.valueOf(product.getPrice()));
		TF_Price.setFont(new Font("Arial", Font.PLAIN, 16));
		TF_Price.setColumns(10);
		TF_Price.setBounds(178, 172, 390, 40);
		JP_Info.add(TF_Price);
		
		JButton BTN_Save = new JButton("Lưu");
		BTN_Save.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 20));
		BTN_Save.setBounds(235, 222, 101, 41);
		BTN_Save.setFocusable(false);
		BTN_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyProduct();
			}
		});
		contentPane.add(BTN_Save);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void modifyProduct() {
		if(!vaildate()) {
			JOptionPane.showMessageDialog(null, "Hãy nhập lại thông tin !!!","Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Product product = new Product(TF_ID.getText(), TF_NameProduct.getText(), Integer.valueOf(TF_Quantity.getText()), Integer.valueOf(TF_Price.getText()));
		int result = ProductDAO.getInstance().update(product);
		if(result > 0) {
			JOptionPane.showMessageDialog(null, "Lưu thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
			managerProduct.reload();
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "Lưu thất bại","Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public boolean vaildate() {
		if(TF_NameProduct.getText().length() == 0) {
			return false;
		}
		if(TF_Quantity.getText().length() == 0) {
			return false;
		}
		if(TF_Price.getText().length() == 0) {
			return false;
		}
		return true;
	}
	
}
