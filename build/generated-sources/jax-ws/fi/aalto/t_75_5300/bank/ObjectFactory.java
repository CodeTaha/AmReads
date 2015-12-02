
package fi.aalto.t_75_5300.bank;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fi.aalto.t_75_5300.bank package. 
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

    private final static QName _MakeVisaTransactionResponse_QNAME = new QName("http://aalto.fi/t-75.5300/bank", "makeVisaTransactionResponse");
    private final static QName _MakeVisaTransaction_QNAME = new QName("http://aalto.fi/t-75.5300/bank", "makeVisaTransaction");
    private final static QName _TransactionException_QNAME = new QName("http://aalto.fi/t-75.5300/bank", "TransactionException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fi.aalto.t_75_5300.bank
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TransactionException }
     * 
     */
    public TransactionException createTransactionException() {
        return new TransactionException();
    }

    /**
     * Create an instance of {@link MakeVisaTransaction }
     * 
     */
    public MakeVisaTransaction createMakeVisaTransaction() {
        return new MakeVisaTransaction();
    }

    /**
     * Create an instance of {@link MakeVisaTransactionResponse }
     * 
     */
    public MakeVisaTransactionResponse createMakeVisaTransactionResponse() {
        return new MakeVisaTransactionResponse();
    }

    /**
     * Create an instance of {@link VisaCard }
     * 
     */
    public VisaCard createVisaCard() {
        return new VisaCard();
    }

    /**
     * Create an instance of {@link VisaTransaction }
     * 
     */
    public VisaTransaction createVisaTransaction() {
        return new VisaTransaction();
    }

    /**
     * Create an instance of {@link TransactionResult }
     * 
     */
    public TransactionResult createTransactionResult() {
        return new TransactionResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeVisaTransactionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://aalto.fi/t-75.5300/bank", name = "makeVisaTransactionResponse")
    public JAXBElement<MakeVisaTransactionResponse> createMakeVisaTransactionResponse(MakeVisaTransactionResponse value) {
        return new JAXBElement<MakeVisaTransactionResponse>(_MakeVisaTransactionResponse_QNAME, MakeVisaTransactionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeVisaTransaction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://aalto.fi/t-75.5300/bank", name = "makeVisaTransaction")
    public JAXBElement<MakeVisaTransaction> createMakeVisaTransaction(MakeVisaTransaction value) {
        return new JAXBElement<MakeVisaTransaction>(_MakeVisaTransaction_QNAME, MakeVisaTransaction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransactionException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://aalto.fi/t-75.5300/bank", name = "TransactionException")
    public JAXBElement<TransactionException> createTransactionException(TransactionException value) {
        return new JAXBElement<TransactionException>(_TransactionException_QNAME, TransactionException.class, null, value);
    }

}
