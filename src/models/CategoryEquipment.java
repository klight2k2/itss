package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.db.DB;

public class CategoryEquipment extends BaseEntity {
    private int id;
    private String name;
    private String code;

    public CategoryEquipment(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public CategoryEquipment() {
        // Default constructor
    }

  



    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
