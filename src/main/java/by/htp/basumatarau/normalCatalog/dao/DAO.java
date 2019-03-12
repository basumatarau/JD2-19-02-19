package by.htp.basumatarau.normalCatalog.dao;

import by.htp.basumatarau.normalCatalog.dao.exception.DAOException;
import by.htp.basumatarau.normalCatalog.dao.util.criteria.Criteria;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, Id extends Serializable> {
	void persist(List<T> entity) throws DAOException;
	List<T> read(Id id) throws DAOException;
	List<T> readAll() throws DAOException;
	<R> List<T> lookUp(Criteria<R> criteria) throws DAOException;
}
