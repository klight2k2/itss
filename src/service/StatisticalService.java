package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import models.db.DB;

public class StatisticalService {
	public Map<String, Integer> countBorrowedEquipmentByCategory() throws SQLException {
	    Map<String, Integer> equipmentCounts = new HashMap<>();

	    String sql = "SELECT c.name AS category, COUNT(*) AS count " +
	                 "FROM pay_borrow_equipment pbe " +
	                 "JOIN pay_borrow pb ON pbe.payBorrowId = pb.id " +
	                 "JOIN equipment e ON pbe.equipmentId = e.id " +
	                 "JOIN equipment_category c ON e.equipmentCategoryId = c.id " +
	                 "WHERE pb.status = 'BORROWING'"+
	                 "GROUP BY c.name";

	    try (PreparedStatement statement = DB.getConnection().prepareStatement(sql)) {
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String category = resultSet.getString("category");
	            int count = resultSet.getInt("count");

	            equipmentCounts.put(category, count);
	        }
	    } catch (SQLException e) {
	        System.err.println("Got an exception!");
	        System.err.println(e.getMessage());
	    }
//	    print
	    for (Map.Entry<String, Integer> entry : equipmentCounts.entrySet()) {
	        String category = entry.getKey();
	        int count = entry.getValue();

	        System.out.println("Loại thiết bị: " + category);
	        System.out.println("Số lượng thiết bị được mượn: " + count);
	        System.out.println("--------------------");
	    }
	    return equipmentCounts;
	}
	
	public Map<String, Integer[]> getEquipmentStatsByCategory() throws SQLException {
	    Map<String, Integer[]> equipmentStats = new HashMap<>();

	    String sql = "SELECT c.name AS category, " +
	                 "       SUM(CASE WHEN e.status = 0 THEN 1 ELSE 0 END) AS brokenCount, " +
	                 "       SUM(CASE WHEN e.status = 1 THEN 1 ELSE 0 END) AS goodCount, " +
	                 "       SUM(CASE WHEN e.status = 2 THEN 1 ELSE 0 END) AS underRepairCount " +
	                 "FROM equipment e " +
	                 "JOIN equipment_category c ON e.equipmentCategoryId = c.id " +
	                 "GROUP BY c.name";

	    try (PreparedStatement statement = DB.getConnection().prepareStatement(sql)) {
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String category = resultSet.getString("category");
	            int brokenCount = resultSet.getInt("brokenCount");
	            int goodCount = resultSet.getInt("goodCount");
	            int underRepairCount = resultSet.getInt("underRepairCount");

	            Integer[] counts = {brokenCount, goodCount, underRepairCount};
	            equipmentStats.put(category, counts);
	        }
	    } catch (SQLException e) {
	        System.err.println("Got an exception!");
	        System.err.println(e.getMessage());
	    }
//	    for (Map.Entry<String, Integer[]> entry : equipmentStats.entrySet()) {
//	        String category = entry.getKey();
//	        Integer[] counts = entry.getValue();
//	        int brokenCount = counts[0];
//	        int goodCount = counts[1];
//	        int underRepairCount = counts[2];
//
//	        System.out.println("Category: " + category);
//	        System.out.println("Broken count: " + brokenCount);
//	        System.out.println("Good count: " + goodCount);
//	        System.out.println("Under repair count: " + underRepairCount);
//	        System.out.println("--------------------");
//	    }
	    return equipmentStats;
	}
	public Map<String, Integer> getStatsRoomStatus() throws SQLException {
	    Map<String, Integer> roomStatusCounts = new HashMap<>();

	    String sql = "SELECT status, COUNT(*) AS count FROM room GROUP BY status";

	    try (PreparedStatement statement = DB.getConnection().prepareStatement(sql)) {
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int status = resultSet.getInt("status");
	            int count = resultSet.getInt("count");

	            if (status == 0) {
	                roomStatusCounts.put("notUse", count);
	            } else if (status == 1) {
	                roomStatusCounts.put("use", count);
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Got an exception!");
	        System.err.println(e.getMessage());
	    }
	    for (Map.Entry<String, Integer> entry : roomStatusCounts.entrySet()) {
	        String status = entry.getKey();
	        int count = entry.getValue();

	        System.out.println("Số phòng học " + status + ": " + count);
	    }
	    return roomStatusCounts;
	}
	
	public int countRooms() throws SQLException {
	    String sql = "SELECT COUNT(*) AS count FROM room";

	    try (PreparedStatement statement = DB.getConnection().prepareStatement(sql)) {
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            int count = resultSet.getInt("count");
	            return count;
	        }
	    } catch (SQLException e) {
	        System.err.println("Got an exception!");
	        System.err.println(e.getMessage());
	    }

	    return 0;
	}

	public int countEquipment() throws SQLException {
	    String sql = "SELECT COUNT(*) AS count FROM equipment";

	    try (PreparedStatement statement = DB.getConnection().prepareStatement(sql)) {
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            int count = resultSet.getInt("count");
	            return count;
	        }
	    } catch (SQLException e) {
	        System.err.println("Got an exception!");
	        System.err.println(e.getMessage());
	    }

	    return 0;
	}

	
	public static void main(String[] args) {
		try {			
			Map<String, Integer> equipmentStats = new StatisticalService().countBorrowedEquipmentByCategory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
