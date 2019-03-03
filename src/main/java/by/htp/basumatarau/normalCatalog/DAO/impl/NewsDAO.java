package by.htp.basumatarau.normalCatalog.DAO.impl;

import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.htp.basumatarau.normalCatalog.DAO.AbstractDAO;
import by.htp.basumatarau.normalCatalog.DAO.IDAO;
import by.htp.basumatarau.normalCatalog.generatedEntities.Book;
import by.htp.basumatarau.normalCatalog.generatedEntities.CD;
import by.htp.basumatarau.normalCatalog.generatedEntities.Movie;
import by.htp.basumatarau.normalCatalog.generatedEntities.NewsItem;

public class NewsDAO extends AbstractDAO implements IDAO<NewsItem, String> {

	private enum LOOK_UP_OPTION{
		BY_NEWS_NAME, BY_PROVIDER, BY_DATE_OF_ISSUE, BY_NEWS_BODY
	}
	
	@Override
	public void persist(List<NewsItem> entities, Writer xmlOut) {
		getSerializer().serializeEntitiesToXml(xmlOut, entities);
	}

	@Override
	public List<NewsItem> read(Reader xmlIn) {
		List<NewsItem> result = getSerializer().deserializeEntitiesFromXml(xmlIn);
		return result;
	}
	
	//usage:  --name  --provider --issue --body 'search word'
	private EnumSet<LOOK_UP_OPTION> optionsResolver(String criteria){
		String regexpRequest = "^(((\\s*--name\\s*)|(\\s*--provider\\s*)|(\\s*--issue\\s*)|(\\s*--body\\s*)){1,4})(\\s*[\"'](.*)[\"']\\s*)";
		EnumSet<LOOK_UP_OPTION> result = EnumSet.of(LOOK_UP_OPTION.BY_NEWS_NAME);
		if(criteria.matches(regexpRequest)) {
			result.remove(LOOK_UP_OPTION.BY_NEWS_NAME);
			Matcher matcher = Pattern.compile(regexpRequest).matcher(criteria);
			matcher.find();
			if(matcher.group(1).contains("name")) {
				result.add(LOOK_UP_OPTION.BY_NEWS_NAME);
			}
			if(matcher.group(1).contains("provider")) {
				result.add(LOOK_UP_OPTION.BY_PROVIDER);
			}
			if(matcher.group(1).contains("issue")) {
				result.add(LOOK_UP_OPTION.BY_DATE_OF_ISSUE);
			}
			if(matcher.group(1).contains("body")) {
				result.add(LOOK_UP_OPTION.BY_NEWS_BODY);
			}
		}
		return result;
	}
	private String resolveSearchWord(String criteria) {
		String regexpRequest = "^(((\\s*--name\\s*)|(\\s*--provider\\s*)|(\\s*--issue\\s*)|(\\s*--body\\s*)){1,4})(\\s*[\"'](.*)[\"']\\s*)";
		String result = "";
		Matcher matcher = Pattern.compile(regexpRequest).matcher(criteria);
		System.out.println(criteria);
		if(matcher.find() && matcher.group(8) != null) {
			result = matcher.group(8);
		}
		return result;
	}
	
	@Override
	public List<NewsItem> lookUp(Reader xmlInput, String criteria) {
		List<NewsItem> lookUpBase = getSerializer().deserializeEntitiesFromXml(xmlInput);
		EnumSet<LOOK_UP_OPTION> opts = optionsResolver(criteria);
		List<NewsItem> lookUpResponse = new ArrayList<>();
		String searchWord = resolveSearchWord(criteria);
		
		System.out.println(searchWord);
		
		for(NewsItem ni : lookUpBase) {
			List<Object> content = getSerializer().getNewsItemContent(ni);
			List<Object> matchedContent = new ArrayList<>();
			for(Object item : content) {
				boolean matched = false;
				if(item instanceof Movie) {
					Movie m = ((Movie) item);
					if(opts.contains(LOOK_UP_OPTION.BY_NEWS_NAME)) {
						if (m.getNewsName().contains(searchWord)) {
							matched = true;
						}else {
							matched = false;
						}
					}
					if(opts.contains(LOOK_UP_OPTION.BY_PROVIDER)) {
						if (m.getProvider().getValue().contains(searchWord)) {
							matched = true;
						}else {
							matched = false;
						}
					}
					if(opts.contains(LOOK_UP_OPTION.BY_NEWS_BODY)) {
						if (m.getNewsBody().contains(searchWord)) {
							matched = true;
						}else {
							matched = false;
						}
					} 
					if(opts.contains(LOOK_UP_OPTION.BY_DATE_OF_ISSUE)) {
						if (m.getDateOfIssue().contains(searchWord)) {
							matched = true;
						}else {
							matched = false;
						}
					}
				}else if(item instanceof Book) {
					Book b = ((Book) item);
					if(opts.contains(LOOK_UP_OPTION.BY_NEWS_NAME)) {
						if (b.getNewsName().contains(searchWord)) {
							matched = true;
						}else {
							matched = false;
						}
					}
					if(opts.contains(LOOK_UP_OPTION.BY_PROVIDER)) {
						if (b.getProvider().getValue().contains(searchWord)) {
							matched = true;
						}else {
							matched = false;
						}
					}
					if(opts.contains(LOOK_UP_OPTION.BY_NEWS_BODY)) {
						if (b.getNewsBody().contains(searchWord)) {
							matched = true;
						}else {
							matched = false;
						}
					} 
					if(opts.contains(LOOK_UP_OPTION.BY_DATE_OF_ISSUE)) {
						if (b.getDateOfIssue().contains(searchWord)) {
							matched = true;
						}else {
							matched = false;
						}
					}
				}else if(item instanceof CD) {
					CD c = ((CD) item);
					if(opts.contains(LOOK_UP_OPTION.BY_NEWS_NAME)) {
						if (c.getNewsName().contains(searchWord)) {
							matched = true;
						}else {
							matched = false;
						}
					}
					if(opts.contains(LOOK_UP_OPTION.BY_PROVIDER)) {
						if (c.getProvider().getValue().contains(searchWord)) {
							matched = true;
						}else {
							matched = false;
						}
					}
					if(opts.contains(LOOK_UP_OPTION.BY_NEWS_BODY)) {
						if (c.getNewsBody().contains(searchWord)) {
							matched = true;
						}else {
							matched = false;
						}
					} 
					if(opts.contains(LOOK_UP_OPTION.BY_DATE_OF_ISSUE)) {
						if (c.getDateOfIssue().contains(searchWord)) {
							matched = true;
						}else {
							matched = false;
						}
					}
				}
				if(matched) {
					matchedContent.add(item);
				}
			}
			if(!matchedContent.isEmpty()) {
				lookUpResponse.add(getSerializer().getNewsItem(matchedContent, ni.getCategory()));
			}
			matchedContent.clear();
		}
		return lookUpResponse;
	}

}
