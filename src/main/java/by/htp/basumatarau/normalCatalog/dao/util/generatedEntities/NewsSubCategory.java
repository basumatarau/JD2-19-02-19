
package by.htp.basumatarau.normalCatalog.dao.util.generatedEntities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;


/**
 * <p>Java class for NewsSubCategory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NewsSubCategory"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;group ref="{http://by.htp.basumatarau.normalCatalog/news}newsContent"/&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NewsSubCategory", propOrder = {
    "id",
    "newsName",
    "provider",
    "dateOfIssue",
    "newsBody"
})
public class NewsSubCategory {

    protected int id;
    @XmlElement(required = true)
    protected String newsName;
    @XmlElement(required = true)
    protected Provider provider;
    @XmlElement(required = true)
    protected String dateOfIssue;
    @XmlElement(required = true)
    protected String newsBody;
    @XmlAttribute(name = "name", required = true)
    protected String name;

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

    /**
     * Gets the value of the newsName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewsName() {
        return newsName;
    }

    /**
     * Sets the value of the newsName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewsName(String value) {
        this.newsName = value;
    }

    /**
     * Gets the value of the provider property.
     * 
     * @return
     *     possible object is
     *     {@link Provider }
     *     
     */
    public Provider getProvider() {
        return provider;
    }

    /**
     * Sets the value of the provider property.
     * 
     * @param value
     *     allowed object is
     *     {@link Provider }
     *     
     */
    public void setProvider(Provider value) {
        this.provider = value;
    }

    /**
     * Gets the value of the dateOfIssue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateOfIssue() {
        return dateOfIssue;
    }

    /**
     * Sets the value of the dateOfIssue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateOfIssue(String value) {
        this.dateOfIssue = value;
    }

    /**
     * Gets the value of the newsBody property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewsBody() {
        return newsBody;
    }

    /**
     * Sets the value of the newsBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewsBody(String value) {
        this.newsBody = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    @Override
    public String toString(){
        return "subCategory: " + this.getName()
                + ", issued: " + this.getDateOfIssue()
                + ", provided " + this.getProvider()
                + ", news title: " + this.getNewsName()
                + ", article: " + this.getNewsBody();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((newsName == null) ? 0 : newsName.hashCode());
        result = prime * result + ((provider == null) ? 0 : provider.hashCode());
        result = prime * result + ((dateOfIssue == null) ? 0 : dateOfIssue.hashCode());
        result = prime * result + ((newsBody == null) ? 0 : newsBody.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsSubCategory that = (NewsSubCategory) o;
        return id == that.id &&
                Objects.equals(newsName, that.newsName) &&
                Objects.equals(provider, that.provider) &&
                Objects.equals(dateOfIssue, that.dateOfIssue) &&
                Objects.equals(newsBody, that.newsBody) &&
                Objects.equals(name, that.name);
    }
}
