package by.htp.basumatarau.normalCatalog;

import org.junit.Test;

import by.htp.basumatarau.normalCatalog.services.INewsService;
import by.htp.basumatarau.normalCatalog.services.impl.NewsService;
import by.htp.basumatarau.normalCatalog.DAO.impl.EntitySerializerImpl;
import by.htp.basumatarau.normalCatalog.DAO.utl.generatedEntities.*;
import by.htp.basumatarau.normalCatalog.DAO.utl.generatedEntities.Provider;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CatalogTest {

	private static final String DATABASE;
	private static final String UNMARSHALLING_TEST_OUT;
	private static final String SERVICE_TEST_OUT;
	private static final String LOOKUP_TEST_OUT;

	static {
		DATABASE = "DATABASE.xml";
		UNMARSHALLING_TEST_OUT = "UNMARSHALLING_TEST_OUT.xml";
		SERVICE_TEST_OUT = "SERVICE_TEST_OUT.xml";
		LOOKUP_TEST_OUT = "LOOKUP_TEST_OUT.xml";
	}
	
	@Test
	public void marshallingTest() throws FileNotFoundException {
		System.out.println("-------------marshalling test-------------");
		List<NewsCategory> categories = new EntitySerializerImpl().deserializeEntitiesFromXml(new FileReader(DATABASE));
		for(NewsCategory category : categories) {
			System.out.println(category.getCategoryName());
			for(NewsSubCategory subCategory : category.getNewsSubCategory()) {
				System.out.println(subCategory.getNewsName());
			}
		}
	}
	
	@Test
	public void unmarshallingTest() throws IOException {
		System.out.println("-------------unmarshalling test---------------");
		ObjectFactory of = new ObjectFactory();
		NewsCategory newsCategory = of.createNewsCategory();
		NewsSubCategory newsSubCategory = of.createNewsSubCategory();
		newsSubCategory.setId(25);
		newsSubCategory.setName("for kids");
		newsSubCategory.setDateOfIssue(new SimpleDateFormat("dd/mm/yyyy").format(new Date()));
		newsSubCategory.setNewsBody("some news body");
		newsSubCategory.setNewsName("movie premiere");
		Provider prov = of.createProvider();
		prov.setAuthor("author");
		prov.setValue("provider name");
		newsSubCategory.setProvider(prov);
		newsCategory.setCategoryName("movie");
		newsCategory.getNewsSubCategory().add(newsSubCategory);

		System.out.println(UNMARSHALLING_TEST_OUT);
		
		new EntitySerializerImpl().serializeEntitiesToXml(new FileWriter(UNMARSHALLING_TEST_OUT), Arrays.asList(newsCategory));
	}
	
	@Test
	public void serviceTest() {
		System.out.println("-------------service test---------------");
		INewsService service = new NewsService();
		service.saveNews(new File(DATABASE), new File(SERVICE_TEST_OUT));
	}
	
	//usage:  --name 'search word' --provider 'search word' --issue 'search word' --body 'search word' --category 'search word'
	@Test
	public void serviceLookUpTest() {
		System.out.println("-------------look-up test---------------");
		INewsService service = new NewsService();


		System.out.println("\n\nlookup query: \" --issue '2017' --name 'Logan' \"");
		service.lookUpNews(" --issue '2017' --name 'Logan' ", new File(DATABASE), new File(LOOKUP_TEST_OUT));
		displayTxtFile(LOOKUP_TEST_OUT);


		System.out.println("\n\nlookup query: \" --body 'experience about the design' \"");
		service.lookUpNews(" --body 'experience about the design' ", new File(DATABASE), new File(LOOKUP_TEST_OUT));
		displayTxtFile(LOOKUP_TEST_OUT);

		System.out.println("\n\nlookup query: \" --provider 'Scribner (September 30, 2004)' \"");
		service.lookUpNews(" --provider 'Scribner (September 30, 2004)' ", new File(DATABASE), new File(LOOKUP_TEST_OUT));
		displayTxtFile(LOOKUP_TEST_OUT);

		System.out.println("\n\nlookup query: \" --category 'movie' \"");
		service.lookUpNews(" --category 'movie' ", new File(DATABASE), new File(LOOKUP_TEST_OUT));
		displayTxtFile(LOOKUP_TEST_OUT);
	}

	private static void displayTxtFile(String strPath){
		try{
			Path path = Paths.get(strPath);
			for(String line : Files.readAllLines(path, StandardCharsets.UTF_8)){
				System.out.println(line);
			}
		}catch (IOException e){
			throw new RuntimeException(e);
		}
	}

}
