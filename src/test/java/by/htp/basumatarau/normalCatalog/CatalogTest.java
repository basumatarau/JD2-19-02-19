package by.htp.basumatarau.normalCatalog;

import by.htp.basumatarau.normalCatalog.dao.util.criteria.Criteria;
import by.htp.basumatarau.normalCatalog.dao.util.criteria.LookUpOpts;
import by.htp.basumatarau.normalCatalog.services.ServiceFactory;
import by.htp.basumatarau.normalCatalog.services.exception.ServiceException;
import org.junit.Test;

import by.htp.basumatarau.normalCatalog.services.Service;

import java.io.*;

public class CatalogTest {
	private final ServiceFactory serviceFactory = new ServiceFactory();
	//private static final String DATABASE;
	private static final String TEST_XML_INPUT;

	static {
		//DATABASE = "DATABASE.xml";
		TEST_XML_INPUT = "testXmlData.xml";
	}

	@Test
	public void serviceTest() throws ServiceException {
		System.out.println("-------------service save method test---------------");
		Service service = serviceFactory.getNewsService();
		service.save(new File(TEST_XML_INPUT));
	}

	@Test
	public void serviceLookUpTest() throws ServiceException {
		System.out.println("-------------service look-up method test---------------");
		Service service = serviceFactory.getNewsService();

		System.out.println("\n\n\t\t\tlookup query (match any): \" --issue '2017' --name 'Logan' \"\n");
		service.lookUp(System.out, new Criteria<>(LookUpOpts.class, Criteria.Matcher.MATCH_ALL)
				.add(LookUpOpts.BY_CATEGORY, "movie")
				.add(LookUpOpts.BY_NEWS_NAME, "Logan")
				.add(LookUpOpts.BY_DATE_OF_ISSUE, "2017")
		);


		System.out.println("\n\n\t\t\tlookup query (match all): \" --issue '2017' --name 'Logan' \"\n");
		service.lookUp(System.out, new Criteria<>(LookUpOpts.class, Criteria.Matcher.MATCH_ANY)
				.add(LookUpOpts.BY_CATEGORY, "movie")
				.add(LookUpOpts.BY_NEWS_NAME, "Logan")
				.add(LookUpOpts.BY_DATE_OF_ISSUE, "2017")
		);


	}

}
