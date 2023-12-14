package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Product;

public class ProductDAO implements DAOInterface<Product>{
	
	public static ProductDAO getInstance() {
		return new ProductDAO();
	}
	@Override
	public int insert(Product t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			
			String insertHangHoa = "INSERT INTO hanghoa() VALUES(?, ?, ?, ?)";
			
			
			PreparedStatement preSateInsertHH = c.prepareStatement(insertHangHoa);
			preSateInsertHH.setString(1, t.getIDProduct());
			preSateInsertHH.setString(2, t.getNameProduct());
			preSateInsertHH.setInt(3, t.getQuantity());
			preSateInsertHH.setInt(4, t.getPrice());
			
			result = preSateInsertHH.executeUpdate();
			
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(Product t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String updateHangHoa = "UPDATE hanghoa SET TenHangHoa = ?, SoLuong = ?, DonGia = ? WHERE IDHangHoa = ?";
			
			PreparedStatement preSateInsertTK = c.prepareStatement(updateHangHoa);
			preSateInsertTK.setString(1, t.getNameProduct());
			preSateInsertTK.setInt(2, t.getQuantity());
			preSateInsertTK.setInt(3, t.getPrice());
			preSateInsertTK.setString(4, t.getIDProduct());
			
			result = preSateInsertTK.executeUpdate();
			
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(Product t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "DELETE FROM hanghoa WHERE IDHangHoa = ?";
			PreparedStatement preStatement = c.prepareStatement(sql);
			
			preStatement.setString(1, t.getIDProduct());
			
			result = preStatement.executeUpdate();
			
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Product> selectAll() {
		ArrayList<Product> result = new ArrayList<Product>();
		
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM hanghoa";
			PreparedStatement preStatement = c.prepareStatement(sql);

			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()) {
				Product product = new Product();
				product.setIDProduct(rs.getString("IDHangHoa"));
				product.setNameProduct(rs.getString("TenHangHoa"));
				product.setQuantity(rs.getInt("SoLuong"));
				product.setPrice(rs.getInt("DonGia"));
				result.add(product);
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}

	@Override
	public Product selectById(Product t) {
		Product result = new Product();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM hanghoa WHERE IDHangHoa = ?";
			
			PreparedStatement preStatement = c.prepareStatement(sql);
			
			preStatement.setString(1, t.getIDProduct());
			
			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()) {
				Product product = new Product();
				product.setIDProduct(rs.getString("IDHangHoa"));
				product.setNameProduct(rs.getString("TenHangHoa"));
				product.setQuantity(rs.getInt("SoLuong"));
				product.setPrice(rs.getInt("DonGia"));
				result = product;
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}

	@Override
	public ArrayList<Product> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
