package by.htp.basumatarau.normalCatalog.services.impl;

import java.io.*;
import java.util.List;

import by.htp.basumatarau.normalCatalog.dao.DAO;
import by.htp.basumatarau.normalCatalog.dao.EntitySerializer;
import by.htp.basumatarau.normalCatalog.dao.impl.DAOProvider;
import by.htp.basumatarau.normalCatalog.dao.impl.EntitySerializerImpl;
import by.htp.basumatarau.normalCatalog.dao.utl.generatedEntities.*;
import by.htp.basumatarau.normalCatalog.exception.DAOException;
import by.htp.basumatarau.normalCatalog.exception.ServiceException;

public class NewsServiceImpl implements by.htp.basumatarau.normalCatalog.services.NewsService {
    private DAO<NewsCategory, String> newsDao = DAOProvider.getDAO().getNewsDao();
    private static EntitySerializer serializer = new EntitySerializerImpl();

    @Override
    public void saveNews(File xmlIn) throws ServiceException {
        try (FileReader tmpXmlIn = new FileReader(xmlIn)) {
            List<NewsCategory> entities = serializer.deserializeEntitiesFromXml(tmpXmlIn);
            newsDao.persist(entities);
        } catch (IOException | DAOException e) {
            throw new ServiceException(e);
        }
    }

    //usage:  --name 'search word' --provider 'search word' --issue 'search word' --body 'search word'
    @Override
    public StringWriter lookUpNews(String criteria) throws ServiceException {
        StringWriter stringWriter = new StringWriter();
        try {
            List<NewsCategory> newsCategories = newsDao.lookUp(criteria);
            serializer.serializeEntitiesToXml(stringWriter, newsCategories);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        stringWriter.flush();
        return stringWriter;
    }

}
