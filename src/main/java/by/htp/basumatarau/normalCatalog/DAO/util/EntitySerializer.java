package by.htp.basumatarau.normalCatalog.DAO.util;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import by.htp.basumatarau.normalCatalog.generatedEntities.ObjectFactory;
import by.htp.basumatarau.normalCatalog.generatedEntities.Book;
import by.htp.basumatarau.normalCatalog.generatedEntities.CD;
import by.htp.basumatarau.normalCatalog.generatedEntities.Movie;
import by.htp.basumatarau.normalCatalog.generatedEntities.News;
import by.htp.basumatarau.normalCatalog.generatedEntities.NewsItem;

public class EntitySerializer {
	private static final String entityPackageName = "by.htp.basumatarau.normalCatalog.generatedEntities";
	private static final ObjectFactory of = new ObjectFactory();
	
	public List<NewsItem> deserializeEntitiesFromXml(Reader xmlInput) {
		List<NewsItem> newsItems = new ArrayList<>();

		try {

			if(!xmlInput.ready()){
				return newsItems;
			}

			JAXBContext context = JAXBContext.newInstance(entityPackageName);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			News news = (News)unmarshaller.unmarshal(xmlInput); 
			newsItems = news.getNewsItem();

		} catch (JAXBException | IOException e) {
			System.out.println("JAXB failure " + e.getMessage());
			throw new RuntimeException(e);
		} 
		
		return newsItems;
	}
	
	public void serializeEntitiesToXml(Writer xmlOut, List<NewsItem> newsItems) {
		try {
			JAXBContext context = JAXBContext.newInstance(entityPackageName);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			News news = of.createNews();
			
			for(NewsItem item : newsItems) {
				news.getNewsItem().add(item);
			}
			marshaller.marshal(news, xmlOut);
		} catch (JAXBException e) {
			System.out.println("JAXB failure " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	public List<Object> getNewsItemContent(NewsItem newsItem){
		List<Object> result = new ArrayList<>();
		for(Object item : newsItem.getMovieOrBookOrCd()){
			result.add(item);
		}
		return result;
	}
	
	public NewsItem getNewsItem(List<Object> contents){
		NewsItem result = of.createNewsItem();
		for(Object item : contents) {
			result.getMovieOrBookOrCd().add(item);
		}
		return result;
	}
}
