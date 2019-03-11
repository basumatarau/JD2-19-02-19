package by.htp.basumatarau.normalCatalog.services;

import by.htp.basumatarau.normalCatalog.dao.util.criteria.Criteria;
import by.htp.basumatarau.normalCatalog.services.exception.ServiceException;

import java.io.File;
import java.io.PrintStream;

public interface NewsService {
	void saveNews(File xmlIn) throws ServiceException;
	void lookUpNews(PrintStream xmlOut, Criteria criteria) throws ServiceException;
}