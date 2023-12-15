package view;

import javax.swing.JPanel;
import model.User;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import java.awt.Rectangle;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;

public class SellTab extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField TF_Search;
	private JTable table;

	public SellTab(User user) {
		setBackground(Color.WHITE);
		setSize(1920, 1080);
		setLayout(null);
		
		
		JPanel JP_Info = new JPanel();
		JP_Info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		JP_Info.setBounds(260, 55, 1395, 75);
		add(JP_Info);
		
		JLabel LB_Info_Role = new JLabel();
		LB_Info_Role.setBounds(1155, 0, 240, 75);
		if(user.isAdmin()) {
			LB_Info_Role.setText("Chức vụ: Quản lý");
		}else {
			LB_Info_Role.setText("Chức vụ: Nhân viên");
		}
		JP_Info.setLayout(null);
		LB_Info_Role.setHorizontalAlignment(SwingConstants.LEFT);
		LB_Info_Role.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 20));
		JP_Info.add(LB_Info_Role);
		
		JLabel LB_Info_FullName = new JLabel();
		LB_Info_FullName.setBounds(342, 0, 800, 75);
		LB_Info_FullName.setText("Họ và tên: " + user.getFullName());
		LB_Info_FullName.setHorizontalAlignment(SwingConstants.LEFT);
		LB_Info_FullName.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 20));
		JP_Info.add(LB_Info_FullName);
		
		JLabel LB_Info_ID = new JLabel();
		LB_Info_ID.setBounds(10, 0, 246, 75);
		JP_Info.add(LB_Info_ID);
		LB_Info_ID.setText("Mã nhân viên: " + user.getId());
		LB_Info_ID.setHorizontalAlignment(SwingConstants.LEFT);
		LB_Info_ID.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 20));
		
		JButton BTN_LogOut = new JButton("Đăng xuất");
		BTN_LogOut.setActionCommand("Logout");
		BTN_LogOut.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 25));
		BTN_LogOut.setBounds(1675, 55, 200, 75);
		add(BTN_LogOut);
		
		JPanel JP_Search = new JPanel();
		JP_Search.setBounds(40, 145, 1615, 75);
		add(JP_Search);
		JP_Search.setLayout(null);
		
		JLabel JL_SearchTitle = new JLabel("Tìm kiếm (Mã sản phẩm \\ barcode)");
		JL_SearchTitle.setBounds(0, 0, 364, 29);
		JL_SearchTitle.setFont(new Font("Roboto Mono SemiBold", Font.PLAIN, 18));
		JP_Search.add(JL_SearchTitle);
		
		TF_Search = new JTextField();
		TF_Search.setFont(new Font("Roboto Mono", Font.PLAIN, 18));
		TF_Search.setBounds(0, 39, 1535, 36);
		JP_Search.add(TF_Search);
		TF_Search.setColumns(10);
		
		JButton BTN_Search = new JButton("O");
		BTN_Search.setBounds(1545, 36, 70, 39);
		JP_Search.add(BTN_Search);
		JScrollPane SP_Order = new JScrollPane();
		SP_Order.setBounds(40, 230, 1615, 593);
		
		
		add(SP_Order);
		
		table = new JTable();
		table.setFont(new Font("Roboto Mono Light", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"STT", "M\u00E3 s\u1EA3n ph\u1EA9m", "S\u1EA3n ph\u1EA9m", "\u0110\u01A1n gi\u00E1", "S\u1ED1 l\u01B0\u1EE3ng", "Th\u00E0nh ti\u1EC1n"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(2);
		table.getColumnModel().getColumn(0).setMinWidth(1);
		table.getColumnModel().getColumn(1).setPreferredWidth(24);
		table.getColumnModel().getColumn(2).setPreferredWidth(213);
		table.getColumnModel().getColumn(3).setPreferredWidth(28);
		table.getColumnModel().getColumn(4).setPreferredWidth(25);
		SP_Order.setViewportView(table);
	}
}
