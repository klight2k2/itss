package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.db.DB;

public class CategoryEquipmentEntity extends BaseEntity {
    private int id;
    private String name;
    private String code;

    public CategoryEquipmentEntity(String name, String code) {
        super();
        this.name = name;
        this.code = code;
    }

    public CategoryEquipmentEntity(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public CategoryEquipmentEntity() {
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
@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object object) {
		CategoryEquipmentEntity category= (CategoryEquipmentEntity) object;
		if(category==null) return false;
		return category.getId()==this.getId();
	}
}
