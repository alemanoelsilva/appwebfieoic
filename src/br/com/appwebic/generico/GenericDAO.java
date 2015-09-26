package br.com.appwebic.generico;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T, I extends Serializable> {
	
	public String setCodigo(T objeto)throws Exception;  
    public void save(T objeto) throws Exception;  
    public void update(T objeto) throws SQLException;  
    public void delete(T objeto) throws SQLException;  
    public List<T> getLista(Class<T> objeto) throws SQLException;  

}
