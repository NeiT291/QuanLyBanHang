package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Bill;
import model.BillDetail;

public class BillDAO implements DAOInterface<Bill>{
	
	public static BillDAO getInstance() {
		return new BillDAO();
	}
	@Override
	public int insert(Bill t) {
		int result = 0;
		try {
			Connection c = JDBCUtil.getConnection();
			
			String insertHoaDon = "INSERT INTO hoadon() VALUES(?, ?, ?, ?, ?)";
			
			PreparedStatement preSateInsertHD = c.prepareStatement(insertHoaDon);
			preSateInsertHD.setString(1, t.getIDBill());
			preSateInsertHD.setString(2, t.getIDUser());
			preSateInsertHD.setString(3, t.getDateTime());
			preSateInsertHD.setInt(4, t.getDiscount());
			preSateInsertHD.setInt(5, t.getTotalPrice());
			
			result = preSateInsertHD.executeUpdate();
			
			JDBCUtil.closeConnection(c);
			
			for (BillDetail billDetail : t.getListProduct()) {
				BillDetailDAO.getInstance().insert(billDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(Bill t) {
		return 0;
	}

	@Override
	public int delete(Bill t) {
		int result = 0;
		try {
			BillDetail billDetail = new BillDetail();
			billDetail.setIDBill(t.getIDBill());
			BillDetailDAO.getInstance().delete(billDetail);
			
			Connection c = JDBCUtil.getConnection();
			String deleteBill = "DELETE FROM hoadon WHERE IDHoaDon = ?";
			
			PreparedStatement preStatement = c.prepareStatement(deleteBill);
			
			preStatement.setString(1, t.getIDBill());
			
			result = preStatement.executeUpdate();
			
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<Bill> selectAll() {
		ArrayList<Bill> result = new ArrayList<Bill>();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM hoadon";
			PreparedStatement preStatement = c.prepareStatement(sql);

			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()) {
				Bill bill = new Bill();
				bill.setIDBill(rs.getString("IDHoaDon"));
				bill.setIDUser(rs.getString("IDNhanVien"));
				bill.setDateTime(rs.getString("ThoiGian"));
				bill.setDiscount(rs.getInt("GiamGia"));
				bill.setTotalPrice(rs.getInt("TongTien"));
				
				bill.setListProduct(BillDetailDAO.getInstance().selectByIDBill(new BillDetail(bill.getIDBill())));
				
				result.add(bill);
			}
			JDBCUtil.closeConnection(c);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Bill selectById(Bill t) {
		Bill result = new Bill();
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "SELECT * FROM hoadon WHERE IDHoaDon = ?";
			
			PreparedStatement preStatement = c.prepareStatement(sql);
			
			preStatement.setString(1, t.getIDBill());
			
			ResultSet rs = preStatement.executeQuery();
			
			while(rs.next()) {
				Bill bill = new Bill();
				bill.setIDBill(rs.getString("IDHoaDon"));
				bill.setIDUser(rs.getString("IDNhanVien"));
				bill.setDateTime(rs.getString("ThoiGian"));
				bill.setDiscount(rs.getInt("GiamGia"));
				bill.setTotalPrice(rs.getInt("TongTien"));
				
				bill.setListProduct(BillDetailDAO.getInstance().selectByIDBill(new BillDetail(bill.getIDBill())));
				
				result = bill;
			}
			JDBCUtil.closeConnection(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		BillDetail billDetail = new BillDetail();
		billDetail.setIDBill(result.getIDBill());
		ArrayList<BillDetail> listProduct = new ArrayList<BillDetail>();
		listProduct = BillDetailDAO.getInstance().selectByIDBill(billDetail);
		
		result.setListProduct(listProduct);
		return result;
	}

	@Override
	public ArrayList<Bill> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
