package by.htp.basumatarau.normalCatalog.dao.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import by.htp.basumatarau.normalCatalog.dao.EntitySerializer;
import by.htp.basumatarau.normalCatalog.dao.util.generatedEntities.*;
import by.htp.basumatarau.normalCatalog.dao.exception.DAOException;

public class EntitySerializerImpl implements EntitySerializer {
	private static final String entityPackageName = "by.htp.basumatarau.normalCatalog.dao.util.generatedEntities";
	private static final ObjectFactory of = new ObjectFactory();

	@Override
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


	@Override
	public void serializeEntitiesToXml(List<NewsCategory> newsItems, PrintStream xmlOut) throws DAOException {
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
			throw new DAOException("JAXB serializer failure ", e);
		}
	}

	@Override
	public NewsCategory makeNewsCategory(List<NewsSubCategory> categoryContent, String categoryName){
		NewsCategory result = of.createNewsCategory();
		for(NewsSubCategory item : categoryContent) {
			result.getNewsSubCategory().add(item);
		}
		result.setCategoryName(categoryName);
		return result;
	}
}
