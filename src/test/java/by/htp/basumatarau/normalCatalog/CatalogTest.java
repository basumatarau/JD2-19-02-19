package by.htp.basumatarau.normalCatalog;

import by.htp.basumatarau.normalCatalog.dao.util.criteria.Criteria;
import by.htp.basumatarau.normalCatalog.dao.util.criteria.LookUpOpts;
import by.htp.basumatarau.normalCatalog.services.exception.ServiceException;
import by.htp.basumatarau.normalCatalog.services.impl.NewsServiceImpl;
import org.junit.Test;

import by.htp.basumatarau.normalCatalog.services.NewsService;

import java.io.*;

public class CatalogTest {

	//private static final String DATABASE;
	private static final String TEST_XML_INPUT;

	static {
		//DATABASE = "DATABASE.xml";
		TEST_XML_INPUT = "testXmlData.xml";
	}

	@Test
	public void serviceTest() throws ServiceException {
		System.out.println("-------------service saveNews method test---------------");
		NewsService service = new NewsServiceImpl();
		service.saveNews(new File(TEST_XML_INPUT));
	}

	@Test
	public void serviceLookUpTest() throws ServiceException {
		System.out.println("-------------service look-up method test---------------");
		NewsService service = new NewsServiceImpl();

		System.out.println("\n\n\t\t\tlookup query (match any): \" --issue '2017' --name 'Logan' \"\n");
		service.lookUpNews(System.out, new Criteria(Criteria.CriteriaType.MATCH_ANY)
				.add(LookUpOpts.BY_CATEGORY, "movie")
				.add(LookUpOpts.BY_NEWS_NAME, "Logan")
				.add(LookUpOpts.BY_DATE_OF_ISSUE, "2017")
		);

		System.out.println("\n\n\t\t\tlookup query (match all): \" --issue '2017' --name 'Logan' \"\n");
		service.lookUpNews(System.out, new Criteria(Criteria.CriteriaType.MATCH_ALL)
				.add(LookUpOpts.BY_CATEGORY, "movie")
				.add(LookUpOpts.BY_NEWS_NAME, "Logan")
				.add(LookUpOpts.BY_DATE_OF_ISSUE, "2017")
		);
	}

}
