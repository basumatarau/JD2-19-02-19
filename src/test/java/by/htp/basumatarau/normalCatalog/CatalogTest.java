package by.htp.basumatarau.normalCatalog;

import by.htp.basumatarau.normalCatalog.exception.ServiceException;
import by.htp.basumatarau.normalCatalog.services.impl.NewsServiceImpl;
import org.junit.Test;

import by.htp.basumatarau.normalCatalog.services.NewsService;

import java.io.*;

public class CatalogTest {

	private static final String DATABASE;

	static {
		DATABASE = "DATABASE.xml";
	}

	@Test
	public void serviceTest() throws ServiceException {
		System.out.println("-------------service saveNews method test---------------");
		NewsService service = new NewsServiceImpl();

		service.saveNews(new File(DATABASE));
	}
	
	//usage:  --name 'search word' --provider 'search word' --issue 'search word' --body 'search word' --category 'search word'
	@Test
	public void serviceLookUpTest() throws ServiceException {
		System.out.println("-------------service look-up method test---------------");
		NewsService service = new NewsServiceImpl();

		System.out.println("\n\n\t\t\tlookup query: \" --issue '2017' --name 'Logan' \"\n");
		System.out.println(
				service.lookUpNews(" --issue '2017' --name 'Logan' ")
		);

		System.out.println("\n\n\t\t\tlookup query: \" --body 'experience about the design' \"\n");
		System.out.println(
				service.lookUpNews(" --body 'experience about the design' ")
		);

		System.out.println("\n\n\t\t\tlookup query: \" --provider 'Scribner (September 30, 2004)' \"\n");
		System.out.println(
				service.lookUpNews(" --provider 'Scribner (September 30, 2004)' ")
		);

		System.out.println("\n\n\t\t\tlookup query: \" --category 'movie' \"\n");
		System.out.println(
				service.lookUpNews(" --category 'movie' ")
		);

	}

}
