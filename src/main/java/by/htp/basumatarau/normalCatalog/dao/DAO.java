package by.htp.basumatarau.normalCatalog.dao;

import by.htp.basumatarau.normalCatalog.exception.DAOException;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, Id extends Serializable> {
	void persist(List<T> entity) throws DAOException;
	List<T> read(Id id) throws DAOException;
	List<T> readAll() throws DAOException;
	List<T> lookUp(String criteria) throws DAOException;
}
