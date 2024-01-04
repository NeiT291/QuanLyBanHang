package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import dao.JDBCUtil;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class TransferBank extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Main main;
	private String stk;
	private String qrLocation;
	private String nameBank;
	public TransferBank(Main main) {
		loadShopInfo();
		this.main = main;
		this.Init();
	}
	public void Init() {
		setType(Type.POPUP);
		setSize(300, 450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LB_Title = new JLabel("Chuyển khoản");
		LB_Title.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Title.setFont(new Font("Arial", Font.PLAIN, 15));
		LB_Title.setBounds(10, 10, 280, 27);
		contentPane.add(LB_Title);
		
		ImageIcon imageIcon = new ImageIcon(TransferBank.class.getResource(qrLocation));
		Image image = imageIcon.getImage();
		
		JLabel LB_QR = new JLabel();
		LB_QR.setBounds(10, 71, 280, 280);
		image = image.getScaledInstance(LB_QR.getWidth(), LB_QR.getHeight(), java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		LB_QR.setIcon(imageIcon);
		contentPane.add(LB_QR);
		
		JLabel LB_Info = new JLabel("STK: " + stk);
		LB_Info.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Info.setFont(new Font("Arial", Font.PLAIN, 15));
		LB_Info.setBounds(10, 34, 280, 18);
		contentPane.add(LB_Info);
		
		JButton BTN_TransferSucess = new JButton("Chuyển khoản thành công");
		BTN_TransferSucess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(main.getTotalPrice() == 0) {
					JOptionPane.showMessageDialog(null, "Không có đơn hàng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}else {
					main.setGuestCash(main.getTotalPrice());
					main.pay();
					dispose();					
				}
			}
		});
		BTN_TransferSucess.setFocusable(false);
		BTN_TransferSucess.setFont(new Font("Arial", Font.PLAIN, 15));
		BTN_TransferSucess.setBounds(10, 370, 280, 30);
		contentPane.add(BTN_TransferSucess);
		
		JButton BTN_Exit = new JButton("Thoát");
		BTN_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		BTN_Exit.setFocusable(false);
		BTN_Exit.setFont(new Font("Arial", Font.PLAIN, 15));
		BTN_Exit.setBounds(10, 410, 280, 30);
		
		contentPane.add(BTN_Exit);
		
		JLabel LB_NameBank = new JLabel("Ngân hàng:");
		LB_NameBank.setText("Ngân hàng: " + nameBank);
		LB_NameBank.setHorizontalAlignment(SwingConstants.CENTER);
		LB_NameBank.setFont(new Font("Arial", Font.PLAIN, 15));
		LB_NameBank.setBounds(10, 50, 280, 18);
		contentPane.add(LB_NameBank);
		
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);
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
        
        stk = properties.getProperty("stk");
        qrLocation = properties.getProperty("qrlocation");
        nameBank = properties.getProperty("nameBank");
	}
}
