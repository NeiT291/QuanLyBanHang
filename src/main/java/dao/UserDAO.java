package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.User;

public class UserDAO implements DAOInterface<User> {

	public static UserDAO getInstance() {
		return new UserDAO();
	}

	@Override
	public int insert(User t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			
			String insertTaiKhoan = "INSERT INTO taikhoan() VALUES(?, ?, ?, ?)";
			String insertThongTinNhanVien = "INSERT INTO thongtinnhanvien() VALUES(?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preSateInsertTK = c.prepareStatement(insertTaiKhoan);
			preSateInsertTK.setString(1, t.getId());
			preSateInsertTK.setString(2, t.getUsername());
			preSateInsertTK.setString(3, t.getPassword());
			preSateInsertTK.setBoolean(4, t.isAdmin());
			
			PreparedStatement preSateInsertTTNV = c.prepareStatement(insertThongTinNhanVien);
			preSateInsertTTNV.setString(1, t.getId());
			preSateInsertTTNV.setString(2, t.getFullName());
			preSateInsertTTNV.setBoolean(3, t.isSex());
			preSateInsertTTNV.setDate(4,t.getBirthDay());
			preSateInsertTTNV.setString(5, t.getPhone());
			preSateInsertTTNV.setString(6, t.getAddress());
			
			result = preSateInsertTK.executeUpdate() + preSateInsertTTNV.executeUpdate();
			
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(User t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String updateTaiKhoan = "UPDATE taikhoan SET Username = ?, Password = ? WHERE IDNhanVien = ?";
			String updateThongTinNhanVien = "UPDATE thongtinnhanvien SET HoVaTen = ?, GioiTinh = ?, NgayThangNamSinh = ?, SDT = ?, QueQuan = ? WHERE IDNhanVien = ?";
			
			PreparedStatement preSateInsertTK = c.prepareStatement(updateTaiKhoan);
			preSateInsertTK.setString(1, t.getUsername());
			preSateInsertTK.setString(2, t.getPassword());
			preSateInsertTK.setString(3, t.getId());
			
			PreparedStatement preSateInsertTTNV = c.prepareStatement(updateThongTinNhanVien);
			preSateInsertTTNV.setString(1, t.getFullName());
			preSateInsertTTNV.setBoolean(2, t.isSex());
			preSateInsertTTNV.setDate(3,t.getBirthDay());
			preSateInsertTTNV.setString(4, t.getPhone());
			preSateInsertTTNV.setString(5, t.getAddress());
			preSateInsertTTNV.setString(6, t.getId());
			
			result = preSateInsertTK.executeUpdate() + preSateInsertTTNV.executeUpdate();
			
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(User t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String deleteTK = "DELETE FROM taikhoan WHERE IDNhanVien = ?";
			String deleteTTNV = "DELETE FROM thongtinnhanvien WHERE IDNhanVien = ?";
			PreparedStatement preSateDeleteTTNV = c.prepareStatement(deleteTTNV);
			PreparedStatement preSateDeleteTK = c.prepareStatement(deleteTK);
			
			preSateDeleteTK.setString(1, t.getId());
			preSateDeleteTTNV.setString(1, t.getId());
			result = preSateDeleteTTNV.executeUpdate() + preSateDeleteTK.executeUpdate();
			
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<User> selectAll() {
		ArrayList<User> result = new ArrayList<User>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT taikhoan.IDNhanVien, taikhoan.Username, taikhoan.Password, taikhoan.isAdmin, thongtinnhanvien.HoVaTen, thongtinnhanvien.GioiTinh, thongtinnhanvien.NgayThangNamSinh, thongtinnhanvien.SDT, thongtinnhanvien.QueQuan"
					+ " FROM taikhoan"
					+ " JOIN thongtinnhanvien ON taikhoan.IDNhanVien = thongtinnhanvien.IDNhanVien";
			PreparedStatement preStatement = c.prepareStatement(sql);

			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getString("IDNhanVien"));
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setFullName(rs.getString("HoVaTen"));
				user.setSex(rs.getBoolean("GioiTinh"));
				user.setBirthDay(rs.getDate("NgayThangNamSinh"));
				user.setPhone(rs.getString("SDT"));
				user.setAddress(rs.getString("QueQuan"));
				user.setAdmin(rs.getBoolean("isAdmin"));
				result.add(user);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}

	@Override
	public User selectById(User t) {
		User result = new User();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT taikhoan.IDNhanVien, taikhoan.Username, taikhoan.Password, taikhoan.isAdmin, thongtinnhanvien.HoVaTen, thongtinnhanvien.GioiTinh, thongtinnhanvien.NgayThangNamSinh, thongtinnhanvien.SDT, thongtinnhanvien.QueQuan"
					+ " FROM taikhoan"
					+ " JOIN thongtinnhanvien ON taikhoan.IDNhanVien = thongtinnhanvien.IDNhanVien"
					+ " WHERE taikhoan.IDNhanvien = ?";
			
			PreparedStatement preStatement = c.prepareStatement(sql);
			
			preStatement.setString(1, t.getId());
			
			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getString("IDNhanVien"));
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setFullName(rs.getString("HoVaTen"));
				user.setSex(rs.getBoolean("GioiTinh"));
				user.setBirthDay(rs.getDate("NgayThangNamSinh"));
				user.setPhone(rs.getString("SDT"));
				user.setAddress(rs.getString("QueQuan"));
				user.setAdmin(rs.getBoolean("isAdmin"));
				result = user;
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}
	public User selectByUsernameAndPassword(User t) {
		User result = null;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM taikhoan WHERE Username = ? AND Password = ?";
			
			PreparedStatement preStatement = c.prepareStatement(sql);
			
			preStatement.setString(1, t.getUsername());
			preStatement.setString(2, t.getPassword());

			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getString("IDNhanVien"));
				user.setUsername(rs.getString("Username"));
				user.setPassword(rs.getString("Password"));
				user.setAdmin(rs.getBoolean("isAdmin"));
				result = user;
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}

	@Override
	public ArrayList<User> selectByCondition(String condition) {
		ArrayList<User> result = new ArrayList<User>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM taikhoan WHERE " + condition;
			PreparedStatement preStatement = c.prepareStatement(sql);
			
			System.out.println(preStatement);
			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()) {
				result.add(new User());
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}

}
