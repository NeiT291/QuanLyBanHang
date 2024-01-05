package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ManagerUserController;
import dao.UserDAO;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ManagerUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TF_Search;
	private JTable tableUser;
	private DefaultTableModel model;

	public ManagerUser() {
		this.Init();
	}
	public void Init() {
		
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Quản lý nhân viên");
		setSize(1000, 600);
		
		ActionListener action = new ManagerUserController(this);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LB_Search = new JLabel("Tìm kiếm (mã nhân viên / username)");
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
		
		tableUser = new JTable();
		tableUser.setFocusable(false);
		tableUser.setFont(new Font("Arial", Font.PLAIN, 15));
		tableUser.setRowHeight(20);
		
		model = new DefaultTableModel();
		model.addColumn("STT");
		model.addColumn("Mã nhân viên");
		model.addColumn("Username");
		model.addColumn("Họ và tên");
		model.addColumn("Giới tính");
		model.addColumn("Ngày sinh");
		model.addColumn("SĐT");
		model.addColumn("Quê quán");
		model.addColumn("Quản lý");
		tableUser.setModel(model);
		reload();
		scrollPane.setViewportView(tableUser);
		
		JButton BTN_AddUser = new JButton("Thêm nhân viên");
		BTN_AddUser.setFont(new Font("Arial", Font.PLAIN, 16));
		BTN_AddUser.setBounds(820, 122, 156, 30);
		BTN_AddUser.setActionCommand("AddUser");
		BTN_AddUser.addActionListener(action);
		contentPane.add(BTN_AddUser);
		
		JButton BTN_ModifyUser = new JButton("Sửa thông tin");
		BTN_ModifyUser.setFont(new Font("Arial", Font.PLAIN, 16));
		BTN_ModifyUser.setBounds(820, 162, 156, 30);
		BTN_ModifyUser.setActionCommand("ModifyUser");
		BTN_ModifyUser.addActionListener(action);
		contentPane.add(BTN_ModifyUser);
		
		JButton BTN_DeleteUser = new JButton("Xóa nhân viên");
		BTN_DeleteUser.setFont(new Font("Arial", Font.PLAIN, 16));
		BTN_DeleteUser.setBounds(820, 202, 156, 30);
		BTN_DeleteUser.setActionCommand("DeleteUser");
		BTN_DeleteUser.addActionListener(action);
		contentPane.add(BTN_DeleteUser);
		
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
	public void addUser() {
		new AddUser(this);
	}
	public void deleteUser() {
		int rowSelected = tableUser.getSelectedRow();
		
		if(rowSelected == -1) {
			return;
		}
		
		String IDUser = tableUser.getValueAt(rowSelected, 1).toString();
		User user = new User(IDUser);
		if(JOptionPane.showConfirmDialog(null, "Xác nhận xóa nhân viên", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE ) == 0) {
			int check = UserDAO.getInstance().delete(user);
			if(check > 0) {
				JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);				
			}else {
				JOptionPane.showMessageDialog(null, "Xóa thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);	
			}
		}
		
		reload();
	}
	public void modifyUser() {
		int rowSelected = tableUser.getSelectedRow();
		
		if(rowSelected == -1) {
			return;
		}
		
		String IDUser = tableUser.getValueAt(rowSelected, 1).toString();
		User user = UserDAO.getInstance().selectById(new User(IDUser));
		new ModifyUser(this, user);
	}
	public void searchUser() {
		String infoSearch = TF_Search.getText();
		
		User userSearch = new User();
		userSearch.setId(infoSearch);
		userSearch.setUsername(infoSearch);
		
		User user;
		user = UserDAO.getInstance().selectById(userSearch);
		if(user.getId() == null) {
			user = UserDAO.getInstance().selectByUsername(userSearch);
		}
		
		if(user.getId() == null) {
			JOptionPane.showMessageDialog(null, "Không có nhân viên này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);	
		}else {
			for(int i = tableUser.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			
			String sex = "Nữ";
			if(user.isSex()) {
				sex = "Nam";
			}
			model.addRow(new Object[] {1, user.getId(), user.getUsername(), user.getFullName(), sex, user.getBirthDay(), user.getPhone(), user.getAddress(), user.isAdmin()});
		}
	}
	public void reload() {
		for(int i = tableUser.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
		
		ArrayList<User> listUser = new ArrayList<User>();
		listUser = UserDAO.getInstance().selectAll();
		int count = 0;
		for (User user : listUser) {
			count++;
			String sex = "Nữ";
			if(user.isSex()) {
				sex = "Nam";
			}
			model.addRow(new Object[] {count, user.getId(), user.getUsername(), user.getFullName(), sex, user.getBirthDay(), user.getPhone(), user.getAddress(), user.isAdmin()});
		}
	}
}
