
package fi.aalto.t_75_5300.bank;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for visaTransaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="visaTransaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amountInCents" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="card" type="{http://aalto.fi/t-75.5300/bank}visaCard"/>
 *         &lt;element name="targetIBAN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "visaTransaction", propOrder = {
    "amountInCents",
    "card",
    "targetIBAN",
    "transactionMessage"
})
public class VisaTransaction {

    protected int amountInCents;
    @XmlElement(required = true)
    protected VisaCard card;
    @XmlElement(required = true)
    protected String targetIBAN;
    @XmlElement(required = true)
    protected String transactionMessage;

    /**
     * Gets the value of the amountInCents property.
     * 
     */
    public int getAmountInCents() {
        return amountInCents;
    }

    /**
     * Sets the value of the amountInCents property.
     * 
     */
    public void setAmountInCents(int value) {
        this.amountInCents = value;
    }

    /**
     * Gets the value of the card property.
     * 
     * @return
     *     possible object is
     *     {@link VisaCard }
     *     
     */
    public VisaCard getCard() {
        return card;
    }

    /**
     * Sets the value of the card property.
     * 
     * @param value
     *     allowed object is
     *     {@link VisaCard }
     *     
     */
    public void setCard(VisaCard value) {
        this.card = value;
    }

    /**
     * Gets the value of the targetIBAN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetIBAN() {
        return targetIBAN;
    }

    /**
     * Sets the value of the targetIBAN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetIBAN(String value) {
        this.targetIBAN = value;
    }

    /**
     * Gets the value of the transactionMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionMessage() {
        return transactionMessage;
    }

    /**
     * Sets the value of the transactionMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionMessage(String value) {
        this.transactionMessage = value;
    }

}
