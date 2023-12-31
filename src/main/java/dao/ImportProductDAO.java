package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.ImportDetail;
import model.ImportProduct;

public class ImportProductDAO implements DAOInterface<ImportProduct>{
	public static ImportProductDAO getInstance() {
		return new ImportProductDAO();
	}
	@Override
	public int insert(ImportProduct t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			
			String insertHoaDon = "INSERT INTO nhaphang() VALUES(?, ?, ?, ?, ?)";
			
			PreparedStatement preSateInsertHD = c.prepareStatement(insertHoaDon);
			preSateInsertHD.setString(1, t.getIDImportProduct());
			preSateInsertHD.setString(2, t.getIDUser());
			preSateInsertHD.setString(3, t.getDateTime());
			preSateInsertHD.setInt(4, t.getTotalPrice());
			
			result = preSateInsertHD.executeUpdate();
			
			JDBCUtil.closeConnection(c);
			
			for (ImportDetail importDetail : t.getListProduct()) {
				ImportDetailDAO.getInstance().insert(importDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(ImportProduct t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(ImportProduct t) {
		int result = 0;
		try {
			ImportDetail importDetail = new ImportDetail();
			importDetail.setIDImportProduct(t.getIDImportProduct());
			ImportDetailDAO.getInstance().delete(importDetail);
			
			Connection c = JDBCUtil.getConnection();
			String deleteBill = "DELETE FROM nhaphang WHERE IDNhapHang = ?";
			
			PreparedStatement preStatement = c.prepareStatement(deleteBill);
			
			preStatement.setString(1, t.getIDImportProduct());
			
			result = preStatement.executeUpdate();
			
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<ImportProduct> selectAll() {
		ArrayList<ImportProduct> result = new ArrayList<ImportProduct>();
		try {
			Connection c = JDBCUtil.getConnection();
			
			String sql = "SELECT * FROM nhaphang";
			PreparedStatement preStatement = c.prepareStatement(sql);

			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()) {
				ImportProduct importProduct = new ImportProduct();
				importProduct.setIDImportProduct(rs.getString("IDNhapHang"));
				importProduct.setIDUser(rs.getString("IDNhanVien"));
				importProduct.setDateTime(rs.getString("ThoiGian"));
				importProduct.setTotalPrice(rs.getInt("TongTien"));
				
				importProduct.setListProduct(ImportDetailDAO.getInstance().selectByIDImportProduct(new ImportDetail(importProduct.getIDImportProduct())));
				
				result.add(importProduct);
			}
			JDBCUtil.closeConnection(c);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ImportProduct selectById(ImportProduct t) {
		ImportProduct result = new ImportProduct();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM nhaphang WHERE IDNhapHang = ?";
			
			PreparedStatement preStatement = c.prepareStatement(sql);
			
			preStatement.setString(1, t.getIDImportProduct());
			
			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()) {
				ImportProduct importProduct = new ImportProduct();
				importProduct.setIDImportProduct(rs.getString("IDNhapHang"));
				importProduct.setIDUser(rs.getString("IDNhanVien"));
				importProduct.setDateTime(rs.getString("ThoiGian"));
				importProduct.setTotalPrice(rs.getInt("TongTien"));
				
				importProduct.setListProduct(ImportDetailDAO.getInstance().selectByIDImportProduct(new ImportDetail(importProduct.getIDImportProduct())));
				
				result = (importProduct);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ImportDetail importDetail = new ImportDetail();
		importDetail.setIDImportProduct(result.getIDImportProduct());
		ArrayList<ImportDetail> listProduct = new ArrayList<ImportDetail>();
		listProduct = ImportDetailDAO.getInstance().selectByIDImportProduct(importDetail);
		
		result.setListProduct(listProduct);
		return result;
	}

	@Override
	public ArrayList<ImportProduct> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
