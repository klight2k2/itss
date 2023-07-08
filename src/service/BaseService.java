package service;

import java.sql.SQLException;
import java.util.List;

public abstract class BaseService<T> {
	abstract public List<T> getAll() throws SQLException;
	
	abstract public boolean save(T model) throws SQLException;
	
	abstract public boolean delete(T model) throws SQLException;
	
	abstract public boolean update(T model) throws SQLException;
}
