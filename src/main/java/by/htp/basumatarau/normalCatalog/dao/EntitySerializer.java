package by.htp.basumatarau.normalCatalog.dao;

import by.htp.basumatarau.normalCatalog.dao.utl.generatedEntities.NewsCategory;
import by.htp.basumatarau.normalCatalog.dao.utl.generatedEntities.NewsSubCategory;
import by.htp.basumatarau.normalCatalog.exception.DAOException;

import java.io.Reader;
import java.io.Writer;
import java.util.List;

public interface EntitySerializer {
    List<NewsCategory> deserializeEntitiesFromXml(Reader xmlInput) throws DAOException;
    void serializeEntitiesToXml(Writer xmlOut, List<NewsCategory> newsCategories) throws DAOException;
    NewsCategory makeNewsCategory(List<NewsSubCategory> categoryContent, String categoryName);
}
