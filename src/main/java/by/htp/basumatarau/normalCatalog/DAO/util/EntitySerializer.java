package by.htp.basumatarau.normalCatalog.DAO.util;

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
			JAXBContext context = JAXBContext.newInstance(entityPackageName);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			News news = (News)unmarshaller.unmarshal(xmlInput); 
			for(Object newsItemNode : news.getNewsItem()) {
				
				if(!newsItemNode.getClass().equals(NewsItem.class)) {
					Node nodeDataNewsItem = (Node) newsItemNode;
					NodeList nList = ((Node) newsItemNode).getChildNodes();
					NewsItem ni = of.createNewsItem();
					ni.setId(Integer.parseInt(
									nodeDataNewsItem.getAttributes().getNamedItem("id").getNodeValue()
									)
							);
					for(int j = 0; j< nList.getLength(); j++ ) {
						String nodeName = nList.item(j).getNodeName();
						if(nodeName.equals("movie")) {
							JAXBElement<Movie> m = unmarshaller.unmarshal(nList.item(j), Movie.class);
							ni.getMovieOrBookOrCd().add(of.createNewsItemMovie(m.getValue()));
						}else if(nodeName.equals("book")) {
							JAXBElement<Book> b = unmarshaller.unmarshal(nList.item(j), Book.class);
							ni.getMovieOrBookOrCd().add(of.createNewsItemBook(b.getValue()));
						}else if(nodeName.equals("cd")) {
							JAXBElement<CD> c = unmarshaller.unmarshal(nList.item(j), CD.class);
							ni.getMovieOrBookOrCd().add(of.createNewsItemCd(c.getValue()));
						}
					}
					newsItems.add(ni);
				}else {
					newsItems.add((NewsItem)newsItemNode);
				}
			}
		} catch (JAXBException e) {
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
				NewsItem nI = of.createNewsItem();
				for(JAXBElement<?> jbe : item.getMovieOrBookOrCd()) {
					if(jbe.getName().toString().equals("movie")) {
						nI.getMovieOrBookOrCd().add(of.createNewsItemMovie(jbe.getValue()));
					}else if(jbe.getName().toString().equals("book")) {
						nI.getMovieOrBookOrCd().add(of.createNewsItemBook(jbe.getValue()));
					}else if(jbe.getName().toString().equals("cd")) {
						nI.getMovieOrBookOrCd().add(of.createNewsItemCd(jbe.getValue()));
					}
				}
				news.getNewsItem().add(nI);
			}
			marshaller.marshal(news, xmlOut);
		} catch (JAXBException e) {
			System.out.println("JAXB failure " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	public List<Object> getNewsItemContent(NewsItem newsItem){
		List<Object> result = new ArrayList<>();
		for(JAXBElement<?> jbe : newsItem.getMovieOrBookOrCd()){
			result.add(jbe.getValue());
		}
		return result;
	}
	
	public NewsItem getNewsItem(List<Object> contents){
		NewsItem result = of.createNewsItem();
		for(Object item : contents) {
			if(item instanceof Movie) {
				result.getMovieOrBookOrCd().add(of.createNewsItemMovie(item));
			}else if(item instanceof Book) {
				result.getMovieOrBookOrCd().add(of.createNewsItemBook(item));
			}else if(item instanceof CD) {
				result.getMovieOrBookOrCd().add(of.createNewsItemCd(item));
			}
		}
		return result;
	}
}
