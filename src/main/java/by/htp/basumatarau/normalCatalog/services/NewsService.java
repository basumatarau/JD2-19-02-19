package by.htp.basumatarau.normalCatalog.services;

import by.htp.basumatarau.normalCatalog.exception.ServiceException;

import java.io.File;
import java.io.StringWriter;

public interface NewsService {

	void saveNews(File xmlIn) throws ServiceException;
	StringWriter lookUpNews(String criteria) throws ServiceException;

}