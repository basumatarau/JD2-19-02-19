
package by.htp.basumatarau.normalCatalog.generatedEntities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CD complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CD"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;group ref="{http://by.htp.basumatarau.normalCatalog/news}newsContent"/&gt;
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CD", propOrder = {
    "newsName",
    "provider",
    "dateOfIssue",
    "newsBody"
})
public class CD {

    @XmlElement(required = true)
    protected String newsName;
    @XmlElement(required = true)
    protected Provider provider;
    @XmlElement(required = true)
    protected String dateOfIssue;
    @XmlElement(required = true)
    protected String newsBody;
    @XmlAttribute(name = "id", required = true)
    protected int id;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((newsBody == null) ? 0 : newsBody.hashCode());
        result = prime * result + ((provider == null) ? 0 : provider.hashCode());
        result = prime * result + ((dateOfIssue == null) ? 0 : dateOfIssue.hashCode());
        result = prime * result + ((newsBody == null) ? 0 : newsBody.hashCode());
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movie other = (Movie) obj;
        if (newsName == null) {
            if (other.newsName != null)
                return false;
        } else if (!newsName.equals(other.newsName))
            return false;
        if (provider == null) {
            if (other.provider != null)
                return false;
        } else if (!provider.equals(other.provider))
            return false;
        if (dateOfIssue == null) {
            if (other.dateOfIssue != null)
                return false;
        } else if (!dateOfIssue.equals(other.dateOfIssue))
            return false;
        if (newsBody == null) {
            if (other.newsBody != null)
                return false;
        } else if (!newsBody.equals(other.newsBody))
            return false;
        if (id != other.id)
            return false;
        return true;
    }
}
