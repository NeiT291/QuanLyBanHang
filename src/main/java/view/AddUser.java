package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.JLabel;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JRadioButton;

public class AddUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	public AddUser() {
		this.Init();
	}
	public void Init() {
		setTitle("Thêm nhân viên");
		setSize(600, 700);
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
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 15));
		textField.setEditable(false);
		textField.setText(formattedDate);
		textField.setBounds(178, 14, 398, 40);
		JP_Info.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(178, 64, 398, 40);
		JP_Info.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(178, 118, 398, 40);
		JP_Info.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(178, 172, 398, 40);
		JP_Info.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(178, 338, 398, 40);
		JP_Info.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(178, 388, 398, 40);
		JP_Info.add(textField_5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		comboBox.setBounds(178, 225, 78, 40);
		JP_Info.add(comboBox);
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setFormatForDatesCommonEra("yyyy-MM-dd");
		dateSettings.setFontValidDate(new Font("Roboto Mono SemiBold", Font.PLAIN, 15));
		dateSettings.setVisibleTodayButton(false);
		dateSettings.setVisibleClearButton(false);
		
		DatePicker datePicker = new DatePicker(dateSettings);
		datePicker.setDateToToday();
		datePicker.getComponentDateTextField().setEditable(false);

		datePicker.setLocation(178, 280);
		datePicker.setSize(200, 40);
		
		JP_Info.add(datePicker);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Quản lý");
		rdbtnNewRadioButton.setFont(new Font("Arial", Font.PLAIN, 18));
		rdbtnNewRadioButton.setBounds(178, 452, 87, 21);
		JP_Info.add(rdbtnNewRadioButton);
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
