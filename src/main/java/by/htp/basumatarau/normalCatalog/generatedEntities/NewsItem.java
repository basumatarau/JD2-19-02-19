
package by.htp.basumatarau.normalCatalog.generatedEntities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NewsItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NewsItem"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence maxOccurs="unbounded"&gt;
 *         &lt;choice&gt;
 *           &lt;element name="movie" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *           &lt;element name="book" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *           &lt;element name="cd" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NewsItem", propOrder = {
    "movieOrBookOrCd"
})
public class NewsItem {

    @XmlElementRefs({
        @XmlElementRef(name = "movie", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "book", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "cd", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<Object>> movieOrBookOrCd;
    @XmlAttribute(name = "id", required = true)
    protected int id;

    /**
     * Gets the value of the movieOrBookOrCd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the movieOrBookOrCd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMovieOrBookOrCd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * 
     */
    public List<JAXBElement<Object>> getMovieOrBookOrCd() {
        if (movieOrBookOrCd == null) {
            movieOrBookOrCd = new ArrayList<JAXBElement<Object>>();
        }
        return this.movieOrBookOrCd;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

}
