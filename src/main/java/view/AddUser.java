package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import dao.UserDAO;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Color;


public class AddUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TF_ID;
	private JTextField TF_Username;
	private JTextField TF_Password;
	private JTextField TF_FullName;
	private JComboBox<String> sexChooser;
	private DatePicker datePicker;
	private JTextField TF_Phone;
	private JTextField TF_Andress;
	private JRadioButton RBTN_IsAdmin;
	public AddUser() {
		this.Init();
	}
	public void Init() {
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Thêm nhân viên");
		setSize(600, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel JP_Info = new JPanel();
		JP_Info.setBounds(0, 0, 586, 494);
		contentPane.add(JP_Info);
		JP_Info.setLayout(null);
		
		JLabel LB_ID = new JLabel("Mã nhân viên");
		LB_ID.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_ID.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		LB_ID.setBounds(20, 10, 146, 44);
		JP_Info.add(LB_ID);
		
		JLabel LB_ID_1 = new JLabel("Tên đăng nhập");
		LB_ID_1.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_ID_1.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		LB_ID_1.setBounds(20, 64, 146, 44);
		JP_Info.add(LB_ID_1);
		
		JLabel LB_ID_2 = new JLabel("Mật khẩu");
		LB_ID_2.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_ID_2.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		LB_ID_2.setBounds(20, 118, 146, 44);
		JP_Info.add(LB_ID_2);
		
		JLabel LB_ID_3 = new JLabel("Họ và tên");
		LB_ID_3.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_ID_3.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		LB_ID_3.setBounds(20, 172, 146, 44);
		JP_Info.add(LB_ID_3);
		
		JLabel LB_ID_3_1 = new JLabel("Giới tính");
		LB_ID_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_ID_3_1.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		LB_ID_3_1.setBounds(20, 226, 146, 44);
		JP_Info.add(LB_ID_3_1);
		
		JLabel LB_ID_3_2 = new JLabel("Ngày sinh");
		LB_ID_3_2.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_ID_3_2.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		LB_ID_3_2.setBounds(20, 280, 146, 44);
		JP_Info.add(LB_ID_3_2);
		
		JLabel LB_ID_3_3 = new JLabel("Số điện thoại");
		LB_ID_3_3.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_ID_3_3.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		LB_ID_3_3.setBounds(20, 334, 146, 44);
		JP_Info.add(LB_ID_3_3);
		
		JLabel LB_ID_3_4 = new JLabel("Quê quán");
		LB_ID_3_4.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_ID_3_4.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		LB_ID_3_4.setBounds(20, 388, 146, 44);
		JP_Info.add(LB_ID_3_4);
		
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	    String formattedDate = myDateObj.format(myFormatObj);
		
		TF_ID = new JTextField();
		TF_ID.setBackground(Color.WHITE);
		TF_ID.setFont(new Font("Arial", Font.PLAIN, 16));
		TF_ID.setEditable(false);
		TF_ID.setBounds(178, 14, 390, 40);
		JP_Info.add(TF_ID);
		TF_ID.setColumns(10);
		
		TF_Username = new JTextField();
		TF_Username.setFont(new Font("Arial", Font.PLAIN, 16));
		TF_Username.setColumns(10);
		TF_Username.setBounds(178, 64, 390, 40);
		JP_Info.add(TF_Username);
		
		TF_Password = new JTextField();
		TF_Password.setFont(new Font("Arial", Font.PLAIN, 16));
		TF_Password.setColumns(10);
		TF_Password.setBounds(178, 118, 390, 40);
		JP_Info.add(TF_Password);
		
		TF_FullName = new JTextField();
		TF_FullName.setFont(new Font("Arial", Font.PLAIN, 16));
		TF_FullName.setColumns(10);
		TF_FullName.setBounds(178, 172, 390, 40);
		JP_Info.add(TF_FullName);
		
		TF_Phone = new JTextField();
		TF_Phone.setFont(new Font("Arial", Font.PLAIN, 16));
		TF_Phone.setColumns(10);
		TF_Phone.setBounds(178, 338, 390, 40);
		JP_Info.add(TF_Phone);
		
		TF_Andress = new JTextField();
		TF_Andress.setFont(new Font("Arial", Font.PLAIN, 16));
		TF_Andress.setColumns(10);
		TF_Andress.setBounds(178, 388, 390, 40);
		JP_Info.add(TF_Andress);
		
		sexChooser = new JComboBox<String>();
		sexChooser.setFont(new Font("Arial", Font.PLAIN, 18));
		sexChooser.setModel(new DefaultComboBoxModel<String>(new String[] {"Nam", "Nữ"}));
		sexChooser.setBounds(178, 235, 78, 28);
		JP_Info.add(sexChooser);
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setFormatForDatesCommonEra("yyyy-MM-dd");
		dateSettings.setFontValidDate(new Font("Arial", Font.PLAIN, 20));
		dateSettings.setVisibleTodayButton(false);
		dateSettings.setVisibleClearButton(false);
		
		datePicker = new DatePicker(dateSettings);
		datePicker.getComponentDateTextField().setFont(new Font("Arial", Font.PLAIN, 16));
		datePicker.setDateToToday();
		datePicker.getComponentDateTextField().setEditable(false);

		datePicker.setLocation(178, 280);
		datePicker.setSize(200, 40);
		
		JP_Info.add(datePicker);
		
		RBTN_IsAdmin = new JRadioButton("Quản lý");
		RBTN_IsAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		RBTN_IsAdmin.setFont(new Font("Arial", Font.PLAIN, 18));
		RBTN_IsAdmin.setBounds(167, 445, 95, 21);
		RBTN_IsAdmin.setFocusable(false);
		JP_Info.add(RBTN_IsAdmin);
		if(RBTN_IsAdmin.isSelected()) {
			TF_ID.setText("1" + formattedDate);
		}else {
			TF_ID.setText("0" + formattedDate);
		}
		
		JButton BTN_AddUser = new JButton("Thêm");
		BTN_AddUser.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 20));
		BTN_AddUser.setBounds(230, 504, 101, 41);
		BTN_AddUser.setFocusable(false);
		contentPane.add(BTN_AddUser);
		BTN_AddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUser();
				
			}
		});
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void addUser() {
		if(!vaildate()) {
			JOptionPane.showMessageDialog(null, "Hãy nhập đủ thông tin !!!","Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
		boolean sex = false;
		if(sexChooser.getSelectedItem() == "Nam") {
			sex = true;
		}
		Date birthDay = Date.valueOf(datePicker.getDate());
		User user = new User(TF_ID.getText(), TF_Username.getText(), TF_Password.getText(), TF_FullName.getText(), sex, birthDay, TF_Phone.getText(), TF_Andress.getText(), RBTN_IsAdmin.isSelected());
		int result = UserDAO.getInstance().insert(user);
		if(result > 0) {
			JOptionPane.showMessageDialog(null, "Thêm thành công","Thông báo", JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		}
	}
	public boolean vaildate() {
		if(TF_Username.getText().length() == 0) {
			return false;
		}
		if(TF_Password.getText().length() == 0) {
			return false;
		}
		if(TF_FullName.getText().length() == 0) {
			return false;
		}
		if(TF_Phone.getText().length() == 0) {
			return false;
		}
		if(TF_Andress.getText().length() == 0) {
			return false;
		}
		return true;
	}
}
