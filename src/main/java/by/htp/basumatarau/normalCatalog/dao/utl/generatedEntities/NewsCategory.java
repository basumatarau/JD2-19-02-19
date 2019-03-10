
package by.htp.basumatarau.normalCatalog.dao.utl.generatedEntities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NewsCategory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NewsCategory"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence maxOccurs="unbounded"&gt;
 *         &lt;element name="newsSubCategory" type="{http://by.htp.basumatarau.normalCatalog/news}NewsSubCategory"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute ref="{http://by.htp.basumatarau.normalCatalog/news}categoryName"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NewsCategory", propOrder = {
    "newsSubCategory"
})
public class NewsCategory {

    @XmlElement(required = true)
    protected List<NewsSubCategory> newsSubCategory;
    @XmlAttribute(name = "categoryName", namespace = "http://by.htp.basumatarau.normalCatalog/news")
    protected String categoryName;

    /**
     * Gets the value of the newsSubCategory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the newsSubCategory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNewsSubCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NewsSubCategory }
     * 
     * 
     */
    public List<NewsSubCategory> getNewsSubCategory() {
        if (newsSubCategory == null) {
            newsSubCategory = new ArrayList<NewsSubCategory>();
        }
        return this.newsSubCategory;
    }

    /**
     * Gets the value of the categoryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the value of the categoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
    }

}
