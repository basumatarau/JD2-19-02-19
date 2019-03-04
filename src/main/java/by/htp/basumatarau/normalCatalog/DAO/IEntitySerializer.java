package by.htp.basumatarau.normalCatalog.DAO;

import by.htp.basumatarau.normalCatalog.DAO.utl.generatedEntities.NewsCategory;
import by.htp.basumatarau.normalCatalog.DAO.utl.generatedEntities.NewsSubCategory;

import java.io.Reader;
import java.io.Writer;
import java.util.List;

public interface IEntitySerializer {
    List<NewsCategory> deserializeEntitiesFromXml(Reader xmlInput);
    void serializeEntitiesToXml(Writer xmlOut, List<NewsCategory> newsCategories);
    NewsCategory makeNewsCategory(List<NewsSubCategory> categoryContent, String categoryName);
}
