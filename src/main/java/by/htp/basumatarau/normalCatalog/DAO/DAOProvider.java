package by.htp.basumatarau.normalCatalog.DAO;

import by.htp.basumatarau.normalCatalog.DAO.impl.NewsDAO;
import by.htp.basumatarau.normalCatalog.generatedEntities.NewsItem;

public class DAOProvider {
	
	private static DAOProvider daoInstance;
	private NewsDAO news;
		
	public IDAO<NewsItem, String> getNewsDao(){
		return news;
	}
	
	private DAOProvider(){};
	public static DAOProvider getDAO() {
		if(daoInstance==null) {
			synchronized(DAOProvider.class) {
				if(daoInstance == null) {
					daoInstance = new DAOProvider();
					daoInstance.news = new NewsDAO();
				}
			}
		}
		return daoInstance;
	}
}
