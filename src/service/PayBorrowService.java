package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.input.InputMethodEvent;
import models.EquipmentEntity;
import models.PayBorrowEntity;
import models.db.DB;

public class PayBorrowService {
    private static PayBorrowService repo;

    public static PayBorrowService getRepo() {
        if (repo != null) {
            return repo;
        } else {
            return new PayBorrowService();
        }
    };

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

    public boolean save(PayBorrowEntity pay_borrow) throws SQLException {
        try {
            Statement stm = DB.getConnection().createStatement();
            ResultSet resultSet = stm.executeQuery("select count(*) as count from pay_borrow");
            if (resultSet.next()) {
                pay_borrow.setId(resultSet.getInt("count") + 1);
            }
            String insertSql = "INSERT INTO pay_borrow (id, fromDate, toDate, status, borrowReason, refuseReason, borrowerId) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(insertSql);
            preparedStmt.setInt(1, pay_borrow.getId());
            preparedStmt.setDate(2, pay_borrow.getFromDate());
            preparedStmt.setDate(3, pay_borrow.getToDate());
            preparedStmt.setString(4, pay_borrow.getStatus());
            preparedStmt.setString(5, pay_borrow.getBorrowReason());
            preparedStmt.setString(6, pay_borrow.getRefuseReason());
            preparedStmt.setInt(7, pay_borrow.getBorrowerId());
            preparedStmt.execute();
            int equipLenght = pay_borrow.getListEquipment().size();

            if (equipLenght > 0) {

                StringBuilder sql = new StringBuilder(
                        "INSERT IGNORE INTO pay_borrow_equipment (payBorrowId,equipmentId) VALUES ");
                int cnt = 0;
                for (EquipmentEntity equip : pay_borrow.getListEquipment()) {
                    sql.append("(" + pay_borrow.getId() + ",'" + equip.getId() + "')");
                    cnt += 1;
                    if (cnt < equipLenght) {
                        sql.append(",");
                    }
                }
                ;
                sql.append(";");

                System.err.println(sql.toString());
                Statement equipQuery = DB.getConnection().createStatement();
                String sqlCmd = sql.toString();
                equipQuery.execute(sqlCmd);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(PayBorrowEntity pay_borrow) throws SQLException {
        try {
            String deleteSql = "DELETE FROM pay_borrow WHERE id = ?";
            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(deleteSql);
            preparedStmt.setInt(1, pay_borrow.getId());
            preparedStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(PayBorrowEntity pay_borrow) throws SQLException {

        // Update operation may not be applicable for pay_borrow getTable() structure
        String sqlPayBorrow = "UPDATE pay_borrow " +
                "SET fromDate = ?, toDate = ?, status = ?, borrowReason = ?, refuseReason = ?, borrowerId = ? " +
                "WHERE id = ?";

        try {
            PreparedStatement statement = DB.getConnection().prepareStatement(sqlPayBorrow);
            statement.setDate(1, pay_borrow.getFromDate());
            statement.setDate(2, pay_borrow.getToDate());
            statement.setString(3, pay_borrow.getStatus());
            statement.setString(4, pay_borrow.getBorrowReason());
            statement.setString(5, pay_borrow.getRefuseReason());
            statement.setInt(6, pay_borrow.getBorrowerId());
            statement.setInt(7, pay_borrow.getId());

            statement.executeUpdate();
            int equipLenght = pay_borrow.getListEquipment().size();
            if (equipLenght > 0) {

                StringBuilder sql = new StringBuilder(
                        "INSERT IGNORE INTO pay_borrow_equipment (payBorrowId,equipmentId) VALUES ");
                int cnt = 0;
                for (EquipmentEntity equip : pay_borrow.getListEquipment()) {
                    sql.append("(" + pay_borrow.getId() + ",'" + equip.getId() + "')");
                    cnt += 1;
                    if (cnt < equipLenght) {
                        sql.append(",");
                    }
                }
                ;
                sql.append(";");

                System.err.println(sql.toString());
                Statement equipQuery = DB.getConnection().createStatement();
                String sqlCmd = sql.toString();
                equipQuery.execute(sqlCmd);
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }
    }

}
