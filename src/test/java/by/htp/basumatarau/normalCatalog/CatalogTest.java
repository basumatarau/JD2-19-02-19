package by.htp.basumatarau.normalCatalog;

import org.junit.Test;

import by.htp.basumatarau.normalCatalog.services.INewsService;
import by.htp.basumatarau.normalCatalog.services.NewsService;
import by.htp.basumatarau.normalCatalog.DAO.impl.EntitySerializer;
import by.htp.basumatarau.normalCatalog.generatedEntities.Movie;
import by.htp.basumatarau.normalCatalog.generatedEntities.NewsItem;
import by.htp.basumatarau.normalCatalog.generatedEntities.ObjectFactory;
import by.htp.basumatarau.normalCatalog.generatedEntities.Provider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CatalogTest {
	private static final String testXmlFile;
	private static final String testXmlOut;
	private static final String marshTestXmlOut;
	private static final String searshTestXmlOut;
	
	
	static {
		testXmlFile = "testXmlFile.xml";
		testXmlOut = "testXmlOut.xml";
		marshTestXmlOut = "marshTestXmlOut.xml";
		searshTestXmlOut = "searchXmlOut.xml";
	}
	
	@Test
	public void marshallingTest() throws FileNotFoundException {
		System.out.println("-------------marshalling test-------------");
		List<NewsItem> newsItems = new EntitySerializer().deserializeEntitiesFromXml(new FileReader(testXmlFile));
		for(NewsItem newsItem : newsItems) {
			System.out.println(newsItem.getCategory());
			for(Object elem : newsItem.getMovieOrBookOrCd()) {
				System.out.println(elem);
			}
		}
	}
	
	@Test
	public void unmarshallingTest() throws IOException {
		System.out.println("-------------unmarshalling test---------------");
		ObjectFactory of = new ObjectFactory();
		Movie movie = of.createMovie();
		movie.setDateOfIssue("date");
		movie.setId(25);
		movie.setNewsBody("newsBody");
		movie.setNewsName("movie_name");
		Provider prov = of.createProvider();
		prov.setAuthor("author");
		prov.setValue("providerValue");
		movie.setProvider(prov);
		NewsItem ni = of.createNewsItem();
		ni.getMovieOrBookOrCd().add(movie);
		ni.setCategory("kids");
	
		System.out.println(marshTestXmlOut);
		
		new EntitySerializer().serializeEntitiesToXml(new FileWriter("marshTestXmlOut.xml"), Arrays.asList(ni));
	}
	
	@Test
	public void serviceTest() {
		INewsService service = new NewsService();
		service.saveNews(new File(testXmlFile), new File(testXmlOut));
	}
	
	//usage:  --name  --provider --issue --body 'search word'
	@Test
	public void serviceLookUpTest() {
		System.out.println("-------------look-up test---------------");
		INewsService service = new NewsService();
		service.lookUpNews(" --issue '2017' ", new File(testXmlFile), new File(searshTestXmlOut));
		service.lookUpNews(" --body 'experience about the design' ", new File(testXmlFile), new File(searshTestXmlOut));
		service.lookUpNews(" --provider 'Scribner (September 30, 2004)' ", new File(testXmlFile), new File(searshTestXmlOut));
	}
}
