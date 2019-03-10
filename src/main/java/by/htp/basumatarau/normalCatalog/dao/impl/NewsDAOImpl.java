package by.htp.basumatarau.normalCatalog.dao.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.htp.basumatarau.normalCatalog.dao.DAO;
import by.htp.basumatarau.normalCatalog.dao.EntitySerializer;
import by.htp.basumatarau.normalCatalog.dao.utl.generatedEntities.*;
import by.htp.basumatarau.normalCatalog.exception.DAOException;

public class NewsDAOImpl implements DAO<NewsCategory, String> {
	private static String DATABASE = "DATABASE.xml";
	private static EntitySerializer serializer = new EntitySerializerImpl();

	private enum LOOK_UP_OPTS {
		BY_NEWS_NAME, BY_PROVIDER, BY_DATE_OF_ISSUE, BY_NEWS_BODY, BY_CATEGORY;
        private String lookUpWord;
        public boolean isMatched = false;

        public String getLookUpWord() {
            return lookUpWord;
        }

        public void setLookUpWord(String lookUpWord) {
            this.lookUpWord = lookUpWord;
        }

		public static boolean allCriteriaMatches(EnumSet<LOOK_UP_OPTS> opts){
			boolean result = true;
			for (LOOK_UP_OPTS opt: opts) {
				if(!opt.isMatched){
					result = false;
				}
				opt.isMatched = false;
			}

			return result;
		}
		public static boolean anyCriteriaMatches(EnumSet<LOOK_UP_OPTS> opts){
			boolean result = false;
        	for (LOOK_UP_OPTS opt: opts) {
				if(opt.isMatched){
					result = true;
					opt.isMatched = false;
					break;
				}
			}
			return result;
		}
    }
	
	@Override
	public void persist(List<NewsCategory> entities) throws DAOException {
		entities.addAll(readAll());

		try (FileWriter fileWriter = new FileWriter(DATABASE)) {
			serializer.serializeEntitiesToXml(fileWriter, entities);
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

	//criteria pattern:  --name 'search word' --provider 'search word' --issue 'search word' --body 'search word' --category 'search word'
	private EnumSet<LOOK_UP_OPTS> optionsResolver(String criteria){
		String regexpRequest = "^((((\\s*--name\\s*)(\\s*[\"'](.*?)[\"']\\s*)?)|((\\s*--provider\\s*)(\\s*[\"'](.*?)[\"']\\s*)?)|((\\s*--issue\\s*)(\\s*[\"'](.*?)[\"']\\s*)?)|((\\s*--body\\s*)(\\s*[\"'](.*?)[\"']\\s*)?)|((\\s*--category\\s*)(\\s*[\"'](.*?)[\"']\\s*)?)){1,5})";
        LOOK_UP_OPTS.BY_NEWS_NAME.setLookUpWord("default");
		EnumSet<LOOK_UP_OPTS> result = EnumSet.of(LOOK_UP_OPTS.BY_NEWS_NAME);

		Matcher matcher = Pattern.compile(regexpRequest).matcher(criteria);
		if(matcher.find()) {
		    result.remove(LOOK_UP_OPTS.BY_NEWS_NAME);
			if(matcher.group(4) != null) {
                LOOK_UP_OPTS.BY_NEWS_NAME.setLookUpWord(matcher.group(6));
				result.add(LOOK_UP_OPTS.BY_NEWS_NAME);
			}
			if(matcher.group(8) != null) {
                LOOK_UP_OPTS.BY_PROVIDER.setLookUpWord(matcher.group(10));
				result.add(LOOK_UP_OPTS.BY_PROVIDER);
			}
			if(matcher.group(12) != null) {
                LOOK_UP_OPTS.BY_DATE_OF_ISSUE.setLookUpWord(matcher.group(14));
				result.add(LOOK_UP_OPTS.BY_DATE_OF_ISSUE);
			}
			if(matcher.group(16) != null) {
                LOOK_UP_OPTS.BY_NEWS_BODY.setLookUpWord(matcher.group(18));
				result.add(LOOK_UP_OPTS.BY_NEWS_BODY);
			}
			if(matcher.group(20) != null) {
                LOOK_UP_OPTS.BY_CATEGORY.setLookUpWord(matcher.group(22));
                result.add(LOOK_UP_OPTS.BY_CATEGORY);
            }
		}
		return result;
	}
	
	@Override
	public List<NewsCategory> lookUp(String criteria) throws DAOException {
		List<NewsCategory> lookUpBase;

		try {
			lookUpBase = serializer.deserializeEntitiesFromXml(new FileReader(DATABASE));
		}catch (IOException e){
			throw new DAOException(e);
		}

		EnumSet<LOOK_UP_OPTS> opts = optionsResolver(criteria);

		List<NewsCategory> lookUpResponse = new ArrayList<>();

		for(NewsCategory category : lookUpBase) {
			List<NewsSubCategory> matchedContent = new ArrayList<>();
			for(NewsSubCategory newsItem : category.getNewsSubCategory()) {

				if(opts.contains(LOOK_UP_OPTS.BY_NEWS_NAME)) {
					if (newsItem.getNewsName().contains(LOOK_UP_OPTS.BY_NEWS_NAME.getLookUpWord())) {
						LOOK_UP_OPTS.BY_NEWS_NAME.isMatched = true;
					}
				}
				if(opts.contains(LOOK_UP_OPTS.BY_PROVIDER)) {
					if (newsItem.getProvider().getValue().contains(LOOK_UP_OPTS.BY_PROVIDER.getLookUpWord())) {
						LOOK_UP_OPTS.BY_PROVIDER.isMatched = true;
					}
				}
				if(opts.contains(LOOK_UP_OPTS.BY_NEWS_BODY)) {
					if (newsItem.getNewsBody().contains(LOOK_UP_OPTS.BY_NEWS_BODY.getLookUpWord())) {
						LOOK_UP_OPTS.BY_NEWS_BODY.isMatched = true;
					}
				}
				if(opts.contains(LOOK_UP_OPTS.BY_DATE_OF_ISSUE)) {
					if (newsItem.getDateOfIssue().contains(LOOK_UP_OPTS.BY_DATE_OF_ISSUE.getLookUpWord())) {
						LOOK_UP_OPTS.BY_DATE_OF_ISSUE.isMatched = true;
					}
				}
				if(opts.contains(LOOK_UP_OPTS.BY_CATEGORY)) {
					if (category.getCategoryName().contains(LOOK_UP_OPTS.BY_CATEGORY.getLookUpWord())) {
						LOOK_UP_OPTS.BY_CATEGORY.isMatched = true;
					}
				}

				if(LOOK_UP_OPTS.allCriteriaMatches(opts)) {
					matchedContent.add(newsItem);
				}
			}
			if(!matchedContent.isEmpty()) {
				lookUpResponse.add(serializer.makeNewsCategory(matchedContent, category.getCategoryName()));
			}
		}
		return lookUpResponse;
	}

}
