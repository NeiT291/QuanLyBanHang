package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField tF_Username;
	private JTextField tF_Password;
	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.Init();
		setVisible(true);
	}
	public void Init() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");			
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Đăng nhập");
		setLocationRelativeTo(null);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tF_Username = new JTextField();
		tF_Username.setFont(new Font("Roboto Mono", Font.PLAIN, 12));
		tF_Username.setBounds(31, 71, 369, 25);
		contentPane.add(tF_Username);
		tF_Username.setColumns(10);
		
		tF_Password = new JTextField();
		tF_Password.setFont(new Font("Roboto Mono", Font.PLAIN, 12));
		tF_Password.setColumns(10);
		tF_Password.setBounds(31, 127, 369, 25);
		contentPane.add(tF_Password);
		
		JButton btnNewButton = new JButton("ĐĂNG NHẬP");
		btnNewButton.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 14));
		btnNewButton.setBounds(148, 186, 137, 36);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Tài khoản");
		lblNewLabel.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 12));
		lblNewLabel.setBounds(31, 46, 65, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu");
		lblMtKhu.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 12));
		lblMtKhu.setBounds(31, 103, 58, 25);
		contentPane.add(lblMtKhu);
	}
	public User getUsernameAndPassword() {
		User user = new User();
		user.setUsername(tF_Username.getText());
		user.setPassword(tF_Password.getText());
		
		return user;
	}
}
