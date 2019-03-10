
package by.htp.basumatarau.normalCatalog.dao.utl.generatedEntities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence maxOccurs="unbounded"&gt;
 *         &lt;element name="newsCategory" type="{http://by.htp.basumatarau.normalCatalog/news}NewsCategory"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "newsCategory"
})
@XmlRootElement(name = "news")
public class News {

    @XmlElement(required = true)
    protected List<NewsCategory> newsCategory;

    /**
     * Gets the value of the newsCategory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the newsCategory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    makeNewsCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NewsCategory }
     * 
     * 
     */
    public List<NewsCategory> getNewsCategory() {
        if (newsCategory == null) {
            newsCategory = new ArrayList<NewsCategory>();
        }
        return this.newsCategory;
    }

}
