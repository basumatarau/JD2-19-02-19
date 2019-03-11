package by.htp.basumatarau.normalCatalog.dao;

import by.htp.basumatarau.normalCatalog.dao.util.generatedEntities.NewsCategory;
import by.htp.basumatarau.normalCatalog.dao.util.generatedEntities.NewsSubCategory;
import by.htp.basumatarau.normalCatalog.dao.exception.DAOException;

import java.io.PrintStream;
import java.io.Reader;
import java.util.List;

public interface EntitySerializer {
    List<NewsCategory> deserializeEntitiesFromXml(Reader xmlInput) throws DAOException;
    void serializeEntitiesToXml(List<NewsCategory> newsCategories, PrintStream xmlOut) throws DAOException;
    NewsCategory makeNewsCategory(List<NewsSubCategory> categoryContent, String categoryName);
}
