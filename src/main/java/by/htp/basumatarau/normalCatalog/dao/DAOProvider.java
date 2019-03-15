package by.htp.basumatarau.normalCatalog.dao;

import by.htp.basumatarau.normalCatalog.dao.impl.NewsDAOImpl;
import by.htp.basumatarau.normalCatalog.dao.util.generatedEntities.NewsCategory;

public class DAOProvider {
	
	private static DAOProvider daoInstance;
	private DAO<NewsCategory, String> news;
		
	public DAO<NewsCategory, String> getNewsDao(){
		return news;
	}
	
	private DAOProvider(){}
	public static DAOProvider getDAO() {
		if(daoInstance==null) {
			synchronized(DAOProvider.class) {
				if(daoInstance == null) {
					daoInstance = new DAOProvider();
					daoInstance.news = new NewsDAOImpl();
				}
			}
		}
		return daoInstance;
	}
}
