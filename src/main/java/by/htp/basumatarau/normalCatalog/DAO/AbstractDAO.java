package by.htp.basumatarau.normalCatalog.DAO;

import by.htp.basumatarau.normalCatalog.DAO.util.EntitySerializer;

public abstract class AbstractDAO {
	private static EntitySerializer serializer = new EntitySerializer();
	
	protected static EntitySerializer getSerializer() {
		return serializer;
	}
}
