package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import model.User;
import javax.swing.JLabel;
import java.awt.Font;

public class Main extends JFrame {

	/**
	 * 
	 */
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
		setResizable(false);
		setSize(1920,1080);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane jTabbedPane = new JTabbedPane();
		jTabbedPane.setFont(new Font("Roboto Mono", Font.PLAIN, 16));
		jTabbedPane.setSize(1920,1080);
		jTabbedPane.setFocusable(false);
		
		contentPane.add(jTabbedPane);
		
		SellTab sellTab = new SellTab(user);
		jTabbedPane.addTab("Bán hàng",sellTab);
		SellTab sellTab2 = new SellTab(user);
		jTabbedPane.addTab("Bán hàng2",sellTab2);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
