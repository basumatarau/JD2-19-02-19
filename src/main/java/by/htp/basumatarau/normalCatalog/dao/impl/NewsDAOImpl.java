package by.htp.basumatarau.normalCatalog.dao.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import by.htp.basumatarau.normalCatalog.dao.DAO;
import by.htp.basumatarau.normalCatalog.dao.EntitySerializer;
import by.htp.basumatarau.normalCatalog.dao.util.criteria.Criteria;
import by.htp.basumatarau.normalCatalog.dao.util.criteria.LookUpOpts;
import by.htp.basumatarau.normalCatalog.dao.util.generatedEntities.*;
import by.htp.basumatarau.normalCatalog.dao.exception.DAOException;

import static by.htp.basumatarau.normalCatalog.dao.util.criteria.LookUpOpts.*;

public class NewsDAOImpl implements DAO<NewsCategory, String> {
	private static String DATABASE = "DATABASE.xml";
	private static EntitySerializer serializer = new EntitySerializerImpl();

	@Override
	public void persist(List<NewsCategory> entities) throws DAOException {
		List<NewsCategory> newsCategories = readAll();
		for (NewsCategory newsCategory : newsCategories) {
			for (NewsCategory entity : entities) {
				if(newsCategory.getCategoryName().equals(entity.getCategoryName())){
					newsCategory.getNewsSubCategory().addAll(entity.getNewsSubCategory());
				}
			}
		}
		try (PrintStream xmlOut = new PrintStream(new File(DATABASE))) {
			serializer.serializeEntitiesToXml(newsCategories, xmlOut);
		}catch (IOException e){
			throw new DAOException(e);
		}
	}

	@Override
	public List<NewsCategory> read(String id) throws DAOException {
		List<NewsCategory> result;
		try (FileReader fileReader = new FileReader(DATABASE)){
			result = serializer.deserializeEntitiesFromXml(fileReader);
		}catch (IOException e){
			throw new DAOException(e);
		}
		for (NewsCategory newsCategory : result) {
			removeNotMatchingSubCategories(newsCategory, id);
		}
		return result;
	}

	private void removeNotMatchingSubCategories(NewsCategory newsCategory, String id) {
		for (NewsSubCategory newsSubCategory : newsCategory.getNewsSubCategory()) {
			if(!id.equals(newsSubCategory.getId() + "")){
				newsCategory.getNewsSubCategory().remove(newsSubCategory);
			}
		}
	}

	@Override
	public List<NewsCategory> readAll() throws DAOException {
		List<NewsCategory> result;
		try (FileReader fileReader = new FileReader(DATABASE)){
			result = serializer.deserializeEntitiesFromXml(fileReader);
		}catch (IOException e){
			throw new DAOException(e);
		}
		return result;
	}


	@Override
	public <R> List<NewsCategory> lookUp(Criteria<R> criteria) throws DAOException {
		List<NewsCategory> lookUpBase;

		try {
			lookUpBase = serializer.deserializeEntitiesFromXml(new FileReader(DATABASE));
		}catch (IOException e){
			throw new DAOException(e);
		}
		List<NewsCategory> lookUpResponse = new ArrayList<>();

		for(NewsCategory category : lookUpBase) {
			List<NewsSubCategory> matchedContent
					= selectMatchedContent(criteria, category);
			if(!matchedContent.isEmpty()) {
				lookUpResponse.add(serializer.makeNewsCategory(matchedContent, category.getCategoryName()));
			}
		}
		return lookUpResponse;
	}

	private List<NewsSubCategory> selectMatchedContent(Criteria<?> crit, NewsCategory category)
			throws DAOException {

		if(!crit.getCriteriaMatchType().equals(LookUpOpts.class)){
			throw new DAOException("criteria mismatch");
		}

		//generic type has been checked in the previous statement
		//DAOException on type mismatch prevents ClassCastException to be thrown below
		@SuppressWarnings("unchecked")
		Criteria<LookUpOpts> criteria = (Criteria<LookUpOpts>) crit;

		List<NewsSubCategory> matchedContent = new ArrayList<>();
		for(NewsSubCategory newsItem : category.getNewsSubCategory()) {

			String searchWord;
			if((searchWord = criteria.get(BY_NEWS_NAME))!=null) {
				if (newsItem.getNewsName().contains(searchWord)) {
					criteria.matched(BY_NEWS_NAME);
				}else{
					criteria.notMatched(BY_NEWS_NAME);
				}
			}
			if((searchWord = criteria.get(BY_PROVIDER))!=null) {
				if (newsItem.getProvider().getValue().contains(searchWord)) {
					criteria.matched(BY_PROVIDER);
				}else{
					criteria.notMatched(BY_PROVIDER);
				}
			}
			if((searchWord = criteria.get(BY_NEWS_BODY))!=null) {
				if (newsItem.getNewsBody().contains(searchWord)) {
					criteria.matched(BY_NEWS_BODY);
				}else{
					criteria.notMatched(BY_NEWS_BODY);
				}
			}
			if((searchWord = criteria.get(BY_DATE_OF_ISSUE))!=null) {
				if (newsItem.getDateOfIssue().contains(searchWord)) {
					criteria.matched(BY_DATE_OF_ISSUE);
				}else{
					criteria.notMatched(BY_DATE_OF_ISSUE);
				}
			}
			if((searchWord = criteria.get(BY_CATEGORY))!=null) {
				if (category.getCategoryName().contains(searchWord)) {
					criteria.matched(BY_CATEGORY);
				}else {
					criteria.notMatched(BY_CATEGORY);
				}
			}
			if(criteria.satisfied()) {
				matchedContent.add(newsItem);
			}
		}
		return matchedContent;
	}

}
