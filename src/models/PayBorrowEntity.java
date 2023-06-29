package models;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

enum PayBorrowStatus{
	PENDING,
	BORROWING,
	PAID	
	
}

public class PayBorrowEntity extends BaseEntity {
	private  Date fromDate;
	private Date toDate;
	private UserEntity borrower;
	private String borrowReason ;
	private String refuseReason;
	private  PayBorrowStatus status;
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public UserEntity getBorrower() {
		return borrower;
	}

	public void setBorrower(UserEntity borrower) {
		this.borrower = borrower;
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

	
	
	public PayBorrowEntity() {
		// TODO Auto-generated constructor stub
	}

	public PayBorrowStatus getStatus() {
		return status;
	}

	public void setStatus(PayBorrowStatus status) {
		this.status = status;
	}

	@Override
	public List getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
