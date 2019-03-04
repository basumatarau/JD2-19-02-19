package by.htp.basumatarau.normalCatalog.DAO.impl;


import by.htp.basumatarau.normalCatalog.DAO.IEntitySerializer;

public abstract class AbstractDAO {
	private static IEntitySerializer serializer = new EntitySerializerImpl();
	
	protected static IEntitySerializer getSerializer() {
		return serializer;
	}
}
