package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.db.DB;

enum PayBorrowStatus {
	PENDING,
	BORROWING,
	PAID

}

public class PayBorrowEntity extends BaseEntity {
	private int id;
	private java.sql.Date fromDate;
	private java.sql.Date toDate;
	private String status;
	private String borrowReason;
	private String refuseReason;
	private int borrowerId;

	public PayBorrowEntity(int id, java.sql.Date fromDate, java.sql.Date toDate, String status,
			String borrowReason, String refuseReason, int borrowerId) {
		this.id = id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.status = status;
		this.borrowReason = borrowReason;
		this.refuseReason = refuseReason;
		this.borrowerId = borrowerId;
	}

	public PayBorrowEntity() {
		// Default constructor
	}

	@Override
	public List<PayBorrowEntity> getAll() throws SQLException {
		try {
			Statement stm = DB.getConnection().createStatement();
			ResultSet res = stm.executeQuery("SELECT * FROM pay_borrow");
			ArrayList<PayBorrowEntity> payBorrows = new ArrayList<>();
			while (res.next()) {
				PayBorrowEntity payBorrow = new PayBorrowEntity(
						res.getInt("id"),
						res.getDate("fromDate"),
						res.getDate("toDate"),
						res.getString("status"),
						res.getString("borrowReason"),
						res.getString("refuseReason"),
						res.getInt("borrowerId"));
				payBorrows.add(payBorrow);
			}
			return payBorrows;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean save() throws SQLException {
		try {
			String insertSql = "INSERT INTO pay_borrow (id, fromDate, toDate, status, borrowReason, refuseReason, borrowerId) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insertSql);
			preparedStmt.setInt(1, this.id);
			preparedStmt.setDate(2, this.fromDate);
			preparedStmt.setDate(3, this.toDate);
			preparedStmt.setString(4, this.status);
			preparedStmt.setString(5, this.borrowReason);
			preparedStmt.setString(6, this.refuseReason);
			preparedStmt.setInt(7, this.borrowerId);
			preparedStmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete() throws SQLException {
		try {
			String deleteSql = "DELETE FROM pay_borrow WHERE id = ?";
			PreparedStatement preparedStmt = DB.getConnection().prepareStatement(deleteSql);
			preparedStmt.setInt(1, this.id);
			preparedStmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update() throws SQLException {
		// Update operation may not be applicable for this table structure
		String sql = "UPDATE pay_borrow " +
                "SET fromDate = ?, toDate = ?, status = ?, borrowReason = ?, refuseReason = ?, borrowerId = ? " +
                "WHERE id = ?";
	   try (PreparedStatement statement = DB.getConnection().prepareStatement(sql)) {
	       statement.setDate(1, this.fromDate);
	       statement.setDate(2, this.toDate);
	       statement.setString(3, this.status);
	       statement.setString(4, this.borrowReason);
	       statement.setString(5, this.refuseReason);
	       statement.setInt(6, this.borrowerId);
	       statement.setInt(7, this.id);
	
	       int rowsAffected = statement.executeUpdate();
	       return rowsAffected > 0;
	   } catch (SQLException e) {
	       System.err.println("Got an exception!");
	       System.err.println(e.getMessage());
	       return false;
	   }
	}

	// Getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(java.sql.Date fromDate) {
		this.fromDate = fromDate;
	}

	public java.sql.Date getToDate() {
		return toDate;
	}

	public void setToDate(java.sql.Date toDate) {
		this.toDate = toDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBorrowReason() {
		return borrowReason;
	}

	public void setBorrowReason(String borrowReason) {
		this.borrowReason = borrowReason;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public int getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(int borrowerId) {
		this.borrowerId = borrowerId;
	}
}
