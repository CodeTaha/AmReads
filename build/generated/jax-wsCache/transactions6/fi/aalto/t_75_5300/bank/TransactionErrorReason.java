
package fi.aalto.t_75_5300.bank;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transactionErrorReason.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="transactionErrorReason">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="INVALID_AMOUNT"/>
 *     &lt;enumeration value="INVALID_CARD_NUMBER"/>
 *     &lt;enumeration value="CARD_EXPIRED"/>
 *     &lt;enumeration value="INTERNAL_SERVER_ERROR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "transactionErrorReason")
@XmlEnum
public enum TransactionErrorReason {

    INVALID_AMOUNT,
    INVALID_CARD_NUMBER,
    CARD_EXPIRED,
    INTERNAL_SERVER_ERROR;

    public String value() {
        return name();
    }

    public static TransactionErrorReason fromValue(String v) {
        return valueOf(v);
    }

}
