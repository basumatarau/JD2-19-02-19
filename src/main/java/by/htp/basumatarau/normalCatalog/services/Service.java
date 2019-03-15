package by.htp.basumatarau.normalCatalog.services;

import by.htp.basumatarau.normalCatalog.dao.util.criteria.Criteria;
import by.htp.basumatarau.normalCatalog.services.exception.ServiceException;

import java.io.File;
import java.io.PrintStream;

public interface Service {
	void save(File xmlIn) throws ServiceException;
	<R> void lookUp(PrintStream xmlOut, Criteria<R> criteria) throws ServiceException;
}