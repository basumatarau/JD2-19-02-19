
package by.htp.basumatarau.normalCatalog.generatedEntities;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the by.htp.basumatarau.normalCatalog.generatedEntities package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _NewsItemMovie_QNAME = new QName("", "movie");
    private final static QName _NewsItemBook_QNAME = new QName("", "book");
    private final static QName _NewsItemCd_QNAME = new QName("", "cd");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: by.htp.basumatarau.normalCatalog.generatedEntities
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link News }
     * 
     */
    public News createNews() {
        return new News();
    }

    /**
     * Create an instance of {@link NewsItem }
     * 
     */
    public NewsItem createNewsItem() {
        return new NewsItem();
    }

    /**
     * Create an instance of {@link Movie }
     * 
     */
    public Movie createMovie() {
        return new Movie();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link CD }
     * 
     */
    public CD createCD() {
        return new CD();
    }

    /**
     * Create an instance of {@link Provider }
     * 
     */
    public Provider createProvider() {
        return new Provider();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "movie", scope = NewsItem.class)
    public JAXBElement<Object> createNewsItemMovie(Object value) {
        return new JAXBElement<Object>(_NewsItemMovie_QNAME, Object.class, NewsItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "book", scope = NewsItem.class)
    public JAXBElement<Object> createNewsItemBook(Object value) {
        return new JAXBElement<Object>(_NewsItemBook_QNAME, Object.class, NewsItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "cd", scope = NewsItem.class)
    public JAXBElement<Object> createNewsItemCd(Object value) {
        return new JAXBElement<Object>(_NewsItemCd_QNAME, Object.class, NewsItem.class, value);
    }

}
