package by.htp.basumatarau.normalCatalog.services;

import java.io.File;

public interface INewsService {

	void saveNews(File xmlIn, File xmlOut);
	void lookUpNews(String criteria, File xmlIn, File xmlOut);

}