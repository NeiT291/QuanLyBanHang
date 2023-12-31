package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.ImportDetail;

public class ImportDetailDAO implements DAOInterface<ImportDetail> {
	public static ImportDetailDAO getInstance() {
		return new ImportDetailDAO();
	}

	@Override
	public int insert(ImportDetail t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			
			String insertChiTietHoaDon = "INSERT INTO chitietnhaphang() VALUES(?, ?, ?)";
			
			PreparedStatement preSateInsertHD = c.prepareStatement(insertChiTietHoaDon);
			preSateInsertHD.setString(1, t.getIDImportProduct());
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
	public int update(ImportDetail t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(ImportDetail t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "DELETE FROM chitietnhaphang WHERE IDNhapHang = ?";
			PreparedStatement preStatement = c.prepareStatement(sql);
			
			preStatement.setString(1, t.getIDImportProduct());
			
			result = preStatement.executeUpdate();
			
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<ImportDetail> selectAll() {
		ArrayList<ImportDetail> result = new ArrayList<ImportDetail>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT chitietnhaphang.IDNhapHang, chitietnhaphang.IDHangHoa, hanghoa.TenHangHoa, hanghoa.DonGia, chitietnhaphang.SoLuong  FROM chitietnhaphang JOIN hanghoa WHERE chitietnhaphang.IDHangHoa = hanghoa.IDHangHoa";
			PreparedStatement preStatement = c.prepareStatement(sql);

			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()) {
				ImportDetail importDetail = new ImportDetail();
				importDetail.setIDImportProduct(rs.getString("IDNhapHang"));
				importDetail.setIDProduct(rs.getString("IDHangHoa"));
				importDetail.setNameProduct(rs.getString("TenHangHoa"));
				importDetail.setQuantity(rs.getInt("SoLuong"));
				importDetail.setPrice(rs.getInt("DonGia"));
				result.add(importDetail);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ImportDetail selectById(ImportDetail t) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<ImportDetail> selectByIDImportProduct(ImportDetail t) {
		ArrayList<ImportDetail> result = new ArrayList<ImportDetail>();
		try {
			Connection c = JDBCUtil.getConnection();
			
			String sql = "SELECT chitietnhaphang.IDNhapHang, chitietnhaphang.IDHangHoa, hanghoa.TenHangHoa, hanghoa.DonGia, chitietnhaphang.SoLuong  FROM chitietnhaphang JOIN hanghoa WHERE chitietnhaphang.IDHangHoa = hanghoa.IDHangHoa AND IDNhapHang = ?";
			PreparedStatement preStatement = c.prepareStatement(sql);
			preStatement.setString(1, t.getIDImportProduct());
			
			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()) {
				ImportDetail importDetail = new ImportDetail();
				importDetail.setIDImportProduct(rs.getString("IDNhapHang"));
				importDetail.setIDProduct(rs.getString("IDHangHoa"));
				importDetail.setNameProduct(rs.getString("TenHangHoa"));
				importDetail.setQuantity(rs.getInt("SoLuong"));
				importDetail.setPrice(rs.getInt("DonGia"));
				result.add(importDetail);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public ArrayList<ImportDetail> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
