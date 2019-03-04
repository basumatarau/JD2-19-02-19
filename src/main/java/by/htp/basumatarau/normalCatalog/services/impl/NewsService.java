package by.htp.basumatarau.normalCatalog.services.impl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.htp.basumatarau.normalCatalog.DAO.IDAO;
import by.htp.basumatarau.normalCatalog.DAO.impl.DAOProvider;
import by.htp.basumatarau.normalCatalog.DAO.utl.generatedEntities.*;
import by.htp.basumatarau.normalCatalog.services.INewsService;

public class NewsService implements INewsService {
    private IDAO<NewsCategory, String> newsDao = DAOProvider.getDAO().getNewsDao();

    /* (non-Javadoc)
     * @see by.htp.basumatarau.normalCatalog.services.INewsService#saveNews(java.io.File, java.io.File)
     */
    @Override
    public void saveNews(File xmlIn, File xmlOut) {
        List<NewsCategory> readItems = new ArrayList<>();

        try (FileReader tmpIn = new FileReader(xmlOut)) {
            readItems.addAll(newsDao.read(tmpIn));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileReader tmpXmlIn = new FileReader(xmlIn);
             FileWriter tmpXmlOut = new FileWriter(xmlOut)) {
            readItems.addAll(newsDao.read(tmpXmlIn));
            newsDao.persist(readItems, tmpXmlOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //usage:  --name 'search word' --provider 'search word' --issue 'search word' --body 'search word'
    @Override
    public void lookUpNews(String criteria, File xmlIn, File xmlOut) {
        try (FileWriter tmpXmlOut = new FileWriter(xmlOut)) {
            List<NewsCategory> result = newsDao.lookUp(new FileReader(xmlIn), criteria);

            if (!result.isEmpty()) {
                newsDao.persist(result, tmpXmlOut);
            }

        } catch (IOException e) {
            System.out.println("service failure");
            throw new RuntimeException(e);
        }

    }

}
