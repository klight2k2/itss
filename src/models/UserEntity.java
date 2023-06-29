package models;

import java.sql.SQLException;
import java.util.List;

enum ROLE {
	TEACHER,
	ADMIN
}

public class UserEntity extends BaseEntity{
    private int ID;
    private String userName;
    private String password;
    private ROLE role;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPasswd() {
        return password;
    }

    public void setPasswd(String passwd) {
        this.password = passwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

	public ROLE getRole() {
		return role;
	}

	public void setRole(ROLE role) {
		this.role = role;
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
