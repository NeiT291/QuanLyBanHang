package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.BillDetail;

public class BillDetailDAO implements DAOInterface<BillDetail>{
	
	public static BillDetailDAO getInstance() {
		return new BillDetailDAO();
	}
	@Override
	public int insert(BillDetail t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			
			String insertChiTietHoaDon = "INSERT INTO chitiethoadon() VALUES(?, ?, ?)";
			
			PreparedStatement preSateInsertHD = c.prepareStatement(insertChiTietHoaDon);
			preSateInsertHD.setString(1, t.getIDBill());
			preSateInsertHD.setString(2, t.getIDProduct());
			preSateInsertHD.setInt(3, t.getQuantity());
			
			result = preSateInsertHD.executeUpdate();
			
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(BillDetail t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(BillDetail t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "DELETE FROM chitiethoadon WHERE IDHoaDon = ?";
			PreparedStatement preStatement = c.prepareStatement(sql);
			
			preStatement.setString(1, t.getIDBill());
			
			result = preStatement.executeUpdate();
			
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<BillDetail> selectAll() {
		ArrayList<BillDetail> result = new ArrayList<BillDetail>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT chitiethoadon.IDHoaDon, chitiethoadon.IDHangHoa, hanghoa.TenHangHoa, hanghoa.DonGia, chitiethoadon.SoLuong  FROM chitiethoadon JOIN hanghoa WHERE chitiethoadon.IDHangHoa = hanghoa.IDHangHoa";
			PreparedStatement preStatement = c.prepareStatement(sql);

			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()) {
				BillDetail billDetail = new BillDetail();
				billDetail.setIDBill(rs.getString("IDHoaDon"));
				billDetail.setIDProduct(rs.getString("IDHangHoa"));
				billDetail.setNameProduct(rs.getString("TenHangHoa"));
				billDetail.setPrice(rs.getInt("DonGia"));
				billDetail.setQuantity(rs.getInt("SoLuong"));
				result.add(billDetail);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public BillDetail selectById(BillDetail t) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<BillDetail> selectByIDBill(BillDetail t) {
		ArrayList<BillDetail> result = new ArrayList<BillDetail>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT chitiethoadon.IDHoaDon, chitiethoadon.IDHangHoa, hanghoa.TenHangHoa, hanghoa.DonGia, chitiethoadon.SoLuong  FROM chitiethoadon JOIN hanghoa WHERE chitiethoadon.IDHangHoa = hanghoa.IDHangHoa AND IDHoaDon = ?";
			PreparedStatement preStatement = c.prepareStatement(sql);
			preStatement.setString(1, t.getIDBill());
			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()) {
				BillDetail billDetail = new BillDetail();
				billDetail.setIDBill(rs.getString("IDHoaDon"));
				billDetail.setIDProduct(rs.getString("IDHangHoa"));
				billDetail.setNameProduct(rs.getString("TenHangHoa"));
				billDetail.setPrice(rs.getInt("DonGia"));
				billDetail.setQuantity(rs.getInt("SoLuong"));
				result.add(billDetail);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public ArrayList<BillDetail> selectByCondition(String condition) {
		return null;
	}

}
