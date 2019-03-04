package by.htp.basumatarau.normalCatalog.DAO;

import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.List;

public interface IDAO<T, Id extends Serializable> {
	void persist(List<T> entity, Writer xmlOut);
	List<T> read(Reader xmlIn);
	List<T> lookUp(Reader xmlIn, String criteria);
}
