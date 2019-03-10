package by.htp.basumatarau.normalCatalog.dao.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import by.htp.basumatarau.normalCatalog.dao.EntitySerializer;
import by.htp.basumatarau.normalCatalog.dao.utl.generatedEntities.*;
import by.htp.basumatarau.normalCatalog.exception.DAOException;

public class EntitySerializerImpl implements EntitySerializer {
	private static final String entityPackageName = "by.htp.basumatarau.normalCatalog.dao.utl.generatedEntities";
	private static final ObjectFactory of = new ObjectFactory();
	
	public List<NewsCategory> deserializeEntitiesFromXml(Reader xmlInput) throws DAOException {
		List<NewsCategory> newsItems = new ArrayList<>();
		try {
			if(!xmlInput.ready()){
				return newsItems;
			}
			JAXBContext context = JAXBContext.newInstance(entityPackageName);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			News news = (News)unmarshaller.unmarshal(xmlInput); 
			newsItems = news.getNewsCategory();
		} catch (JAXBException | IOException e) {
			System.out.println("serialization failure " + e.getMessage());
			throw new DAOException(e);
		} 
		
		return newsItems;
	}
	
	public void serializeEntitiesToXml(Writer xmlOut, List<NewsCategory> newsItems) throws DAOException {
		try {
			JAXBContext context = JAXBContext.newInstance(entityPackageName);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			News news = of.createNews();
			
			for(NewsCategory item : newsItems) {
				news.getNewsCategory().add(item);
			}
			marshaller.marshal(news, xmlOut);
		} catch (JAXBException e) {
			System.out.println("JAXB failure " + e.getMessage());
			throw new DAOException(e);
		}
	}
	
	public NewsCategory makeNewsCategory(List<NewsSubCategory> categoryContent, String categoryName){
		NewsCategory result = of.createNewsCategory();
		for(NewsSubCategory item : categoryContent) {
			result.getNewsSubCategory().add(item);
		}
		result.setCategoryName(categoryName);
		return result;
	}
}
