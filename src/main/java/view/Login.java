package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import dao.UserDAO;
import model.User;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JPasswordField;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField TF_Username;
	private JPasswordField PF_Password;
	public User currentUser = new User();
	public Login() {
		this.Init();
	}
	
	public void Init() {
		setTitle("Đăng nhập");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Action action = new LoginController(this);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TF_Username = new JTextField();
		TF_Username.setFont(new Font("Roboto Mono", Font.PLAIN, 12));
		TF_Username.setBounds(31, 78, 380, 25);
		TF_Username.setColumns(10);
		contentPane.add(TF_Username);
		
		JLabel LB_Username = new JLabel("Tài khoản");
		LB_Username.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 12));
		LB_Username.setBounds(31, 46, 65, 25);
		contentPane.add(LB_Username);
		
		JLabel LB_Password = new JLabel("Mật khẩu");
		LB_Password.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 12));
		LB_Password.setBounds(31, 103, 58, 25);
		contentPane.add(LB_Password);
		
		PF_Password = new JPasswordField();
		PF_Password.setEchoChar('*');
		PF_Password.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 12));
		PF_Password.setBounds(32, 138, 379, 25);
		contentPane.add(PF_Password);
		
		JButton BTN_Login = new JButton("ĐĂNG NHẬP");
		BTN_Login.setActionCommand("Login");
		BTN_Login.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 14));
		BTN_Login.setBounds(148, 186, 137, 36);
		BTN_Login.addActionListener(action);
		
		contentPane.add(BTN_Login);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public User login() {
		
		char[] pass = PF_Password.getPassword();
		currentUser.setUsername(TF_Username.getText());
		currentUser.setPassword(String.valueOf(pass));
		User userInDB = UserDAO.getInstance().selectByUsernameAndPassword(currentUser);
		if(userInDB == null) {
			JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu !!!","Lỗi", JOptionPane.ERROR_MESSAGE);
		}else {
			currentUser = UserDAO.getInstance().selectById(userInDB);
			new Main(currentUser);
			this.dispose();
		}
		return currentUser;
	}
}
