package by.htp.basumatarau.normalCatalog.dao.exception;

public class DAOException extends Exception {
    public DAOException(){
        super();
    }

    public DAOException(Exception e){
        super(e);
    }

    public DAOException(String msg, Exception e){
        super(msg, e);
    }
}

