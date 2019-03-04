package by.htp.basumatarau.normalCatalog.DAO.impl;

import by.htp.basumatarau.normalCatalog.DAO.IDAO;
import by.htp.basumatarau.normalCatalog.DAO.utl.generatedEntities.NewsCategory;

public class DAOProvider {
	
	private static DAOProvider daoInstance;
	private IDAO<NewsCategory, String> news;
		
	public IDAO<NewsCategory, String> getNewsDao(){
		return news;
	}
	
	private DAOProvider(){};
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
