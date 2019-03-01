package by.htp.basumatarau.normalCatalog.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.htp.basumatarau.normalCatalog.DAO.DAOProvider;
import by.htp.basumatarau.normalCatalog.generatedEntities.NewsItem;

public class NewsService implements INewsService {
	private DAOProvider DAOInstance = DAOProvider.getDAO();
	
	/* (non-Javadoc)
	 * @see by.htp.basumatarau.normalCatalog.services.INewsService#saveNews(java.io.File, java.io.File)
	 */
	@Override
	public void saveNews(File xmlIn, File xmlOut) {
		List<NewsItem> readItems = new ArrayList<>();
		
		try (FileReader tmpIn = new FileReader(xmlOut)){
			readItems.addAll(DAOInstance.getNewsDao().read(tmpIn));
		}catch(IOException e) {
			throw new RuntimeException(e);
		}

		try (FileReader tmpXmlIn = new FileReader(xmlIn);
			FileWriter tmpXmlOut = new FileWriter(xmlOut)){
			readItems.addAll(DAOInstance.getNewsDao().read(tmpXmlIn));
			DAOInstance.getNewsDao().persist(readItems, tmpXmlOut);
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	//usage:  --name  --provider --issue --body 'search word'
	@Override
	public void lookUpNews(String criteria, File xmlIn, File xmlOut) {
		List<NewsItem> result = new ArrayList<>();
		try {
			result = DAOInstance.getNewsDao().lookUp(new FileReader(xmlIn), criteria);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		if(!result.isEmpty()) {
			try(FileWriter tmpXmlOut = new FileWriter(xmlOut)){
				DAOInstance.getNewsDao().persist(result, tmpXmlOut);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
}
