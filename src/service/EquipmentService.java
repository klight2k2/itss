package service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.EquipmentEntity;
import models.RoomEntity;
import models.db.DB;

public class EquipmentService {
    private static EquipmentService repo;

    public static EquipmentService getRepo() {
        if (repo != null) {
            return repo;
        } else {
            return new EquipmentService();
        }
    };

    public List<EquipmentEntity> getAll() throws SQLException {
        try {
            Statement stm = DB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("select * from equipment");
            ArrayList<EquipmentEntity> medium = new ArrayList<>();
            while (res.next()) {
                EquipmentEntity equipment = new EquipmentEntity(res.getInt("equipmentCategoryId"), res.getString("id"),
                        res.getString("name"),
                        res.getInt("status"), res.getDate("mfg"), res.getDate("yearOfUse"),
                        res.getInt("numberOfRepairs"),
                        res.getString("note"));
                medium.add(equipment);
            }
            return medium;
        } catch (SQLException e) {
            // TODO: handle exception
            return null;
        }

    }

    public boolean save(EquipmentEntity equipment) throws SQLException {
        try {
            String insert_sqlString = "INSERT IGNORE INTO equipment (equipmentCategoryId, id, name, status, mfg, yearOfUse, numberOfRepairs, note)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";
            Statement statement = DB.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select count(e.equipmentCategoryId) as count, ec.code from equipment e, equipment_category ec "+
            		"where e.equipmentCategoryId = ec.id and e.equipmentCategoryId = " + equipment.getEquipmentCategoryId() +
            		" group by ec.code");
            resultSet.next();
            int count = resultSet.getInt("count")+1;
            equipment.setId(resultSet.getString("code") + String.format("%03d", count));
            System.out.println(equipment.getId());
            System.out.println(resultSet.getInt("count"));
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
            System.out.println(preparedStmt);
            preparedStmt.setInt(1, equipment.getEquipmentCategoryId());
            preparedStmt.setString(2, equipment.getId());
            preparedStmt.setString(3, equipment.getName());
            preparedStmt.setInt(4, equipment.getStatus());
            preparedStmt.setDate(5, equipment.getMfg());
            preparedStmt.setDate(6, equipment.getYearOfUse());
            preparedStmt.setInt(7, equipment.getNumberOfRepairs());
            preparedStmt.setString(8, equipment.getNote());
            preparedStmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean delete(EquipmentEntity equipment) throws SQLException {
        // TODO Auto-generated method stub
        try {
            String insert_sqlString = "DELETE FROM equipment WHERE id = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
            preparedStmt.setString(1, equipment.getId());
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            // TODO: handle exception
            return false;
        }
    }

    public boolean update(EquipmentEntity equipment) throws SQLException {
        // TODO Auto-generated method stub
        try {
            String insert_sqlString = "UPDATE equipment SET equipmentCategoryId = ?, id = ?, name = ?, status = ?, mfg = ?, yearOfUse = ?, numberOfRepairs = ?, note = ? WHERE id = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
            preparedStmt.setInt(1, equipment.getEquipmentCategoryId());
            preparedStmt.setString(2, equipment.getId());
            preparedStmt.setString(3, equipment.getName());
            preparedStmt.setInt(4, equipment.getStatus());
            preparedStmt.setDate(5, equipment.getMfg());
            preparedStmt.setDate(6, equipment.getYearOfUse());
            preparedStmt.setInt(7, equipment.getNumberOfRepairs());
            preparedStmt.setString(8, equipment.getNote());
            preparedStmt.setString(9, equipment.getId());
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }

    }

    public List<EquipmentEntity> getAllEquipmentInRoom(int roomId) throws SQLException {
        try {
            Statement stm = DB.getConnection().createStatement();
            String insert_sqlString = "select * from room_equipment where roomId = " + roomId + ";";
            ResultSet res = stm.executeQuery(insert_sqlString);
            String sql = "SELECT * FROM equipment WHERE id IN (";
            while (res.next()) {
                sql += "'" + res.getString("equipmentId") + "'";
                sql += ",";
            }
            if (sql.charAt(sql.length() - 1) == ',') {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ")";
            if (sql.charAt(sql.length() - 2) != '(') {
                res = stm.executeQuery(sql);
            }
            ArrayList<EquipmentEntity> medium = new ArrayList<>();
            while (res.next()) {
                EquipmentEntity equipment = new EquipmentEntity(res.getInt("equipmentCategoryId"), res.getString("id"),
                        res.getString("name"),
                        res.getInt("status"), res.getDate("mfg"), res.getDate("yearOfUse"),
                        res.getInt("numberOfRepairs"),
                        res.getString("note"));
                medium.add(equipment);
            }
            return medium;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }

    }

    public EquipmentEntity getEquipmentById(String equipmentId) {
        String sql = "SELECT * FROM equipment WHERE id = ?";

        try (PreparedStatement statement = DB.getConnection().prepareStatement(sql)) {
            statement.setString(1, equipmentId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                EquipmentEntity equipment = new EquipmentEntity(
                        resultSet.getInt("equipmentCategoryId"),
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("status"),
                        resultSet.getDate("mfg"),
                        resultSet.getDate("yearOfUse"),
                        resultSet.getInt("numberOfRepairs"),
                        resultSet.getString("note"));
                return equipment;
            } else {
                return null; // Thiết bị không tồn tại
            }
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<EquipmentEntity> getAllEquipmentNoUse() throws SQLException {
        try {
            Statement stm = DB.getConnection().createStatement();
            String insert_sqlString = "select * from equipment where equipment.id not in (select equipmentId from pay_borrow_equipment where payBorrowId in (select id from pay_borrow where status=\"borrowing\")) and equipment.id not in (select equipmentId from room_equipment) ";
            ResultSet res = stm.executeQuery(insert_sqlString);

            ArrayList<EquipmentEntity> medium = new ArrayList<>();
            while (res.next()) {
                EquipmentEntity equipment = new EquipmentEntity(res.getInt("equipmentCategoryId"), res.getString("id"),
                        res.getString("name"),
                        res.getInt("status"), res.getDate("mfg"), res.getDate("yearOfUse"),
                        res.getInt("numberOfRepairs"),
                        res.getString("note"));
                medium.add(equipment);
            }
            return medium;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }

    }

     public List<EquipmentEntity> getEquipmentBorrowed(int payBorrowId) throws SQLException {
        try {
            String insert_sqlString = "select equipment.* from equipment,equipment_category  where equipment.id in (select equipmentId from pay_borrow_equipment where payBorrowId= "+payBorrowId+" ) and equipment_category.id = equipment.equipmentCategoryId";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insert_sqlString);
            ResultSet res = preparedStmt.executeQuery(insert_sqlString);
           
            ArrayList<EquipmentEntity> medium = new ArrayList<>();
            while (res.next()) {
                            System.out.println("lloi dau"+  res.getString("name"));

                EquipmentEntity equipment = new EquipmentEntity(res.getInt("equipmentCategoryId"), res.getString("id"),
                        res.getString("name"),
                        res.getInt("status"), res.getDate("mfg"), res.getDate("yearOfUse"),
                        res.getInt("numberOfRepairs"),
                        res.getString("note"));
                medium.add(equipment);
            }
            return medium;
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("lloi dau");
            e.printStackTrace();
            return null;
        }
    }
    public RoomEntity getRoomByEquipment(String equipmentId) {
		try {				
			Statement stm = DB.getConnection().createStatement();
			ResultSet resultSet = stm.executeQuery("SELECT r.* FROM room r, room_equipment re where r.id = re.roomId and re.equipmentId = " + equipmentId);
			if (resultSet.next()) {
				return new RoomEntity(
						resultSet.getInt("id"),
                        resultSet.getBoolean("status"),
                        resultSet.getString("name")
                        );
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
    
//    public static void main(String[] args) {
//		EquipmentService aEquipmentService = new EquipmentService();
//		try {
//			aEquipmentService.save(new EquipmentEntity(6,"Ban",1,Date.valueOf("2023-07-03"),Date.valueOf("2023-07-03"),0,"note1"));
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
}
