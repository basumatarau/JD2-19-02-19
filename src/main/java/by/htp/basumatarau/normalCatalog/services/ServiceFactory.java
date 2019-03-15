package by.htp.basumatarau.normalCatalog.services;

import by.htp.basumatarau.normalCatalog.services.impl.NewsServiceImpl;

public class ServiceFactory {

    public ServiceFactory(){
    }

    public Service getNewsService() {
        return new NewsServiceImpl();
    }
}
