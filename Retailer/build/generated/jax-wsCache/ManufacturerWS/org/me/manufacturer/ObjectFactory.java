
package org.me.manufacturer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.me.manufacturer package. 
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

    private final static QName _ReceivePayment_QNAME = new QName("http://manufacturer.me.org/", "receivePayment");
    private final static QName _ReceivePaymentResponse_QNAME = new QName("http://manufacturer.me.org/", "receivePaymentResponse");
    private final static QName _GetProductInfo_QNAME = new QName("http://manufacturer.me.org/", "getProductInfo");
    private final static QName _ProcessPurchasePrderResponse_QNAME = new QName("http://manufacturer.me.org/", "processPurchasePrderResponse");
    private final static QName _GetProductInfoResponse_QNAME = new QName("http://manufacturer.me.org/", "getProductInfoResponse");
    private final static QName _ProcessPurchasePrder_QNAME = new QName("http://manufacturer.me.org/", "processPurchasePrder");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.me.manufacturer
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProcessPurchasePrder }
     * 
     */
    public ProcessPurchasePrder createProcessPurchasePrder() {
        return new ProcessPurchasePrder();
    }

    /**
     * Create an instance of {@link GetProductInfoResponse }
     * 
     */
    public GetProductInfoResponse createGetProductInfoResponse() {
        return new GetProductInfoResponse();
    }

    /**
     * Create an instance of {@link ProcessPurchasePrderResponse }
     * 
     */
    public ProcessPurchasePrderResponse createProcessPurchasePrderResponse() {
        return new ProcessPurchasePrderResponse();
    }

    /**
     * Create an instance of {@link GetProductInfo }
     * 
     */
    public GetProductInfo createGetProductInfo() {
        return new GetProductInfo();
    }

    /**
     * Create an instance of {@link ReceivePayment }
     * 
     */
    public ReceivePayment createReceivePayment() {
        return new ReceivePayment();
    }

    /**
     * Create an instance of {@link ReceivePaymentResponse }
     * 
     */
    public ReceivePaymentResponse createReceivePaymentResponse() {
        return new ReceivePaymentResponse();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link PurchaseOrder }
     * 
     */
    public PurchaseOrder createPurchaseOrder() {
        return new PurchaseOrder();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceivePayment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manufacturer.me.org/", name = "receivePayment")
    public JAXBElement<ReceivePayment> createReceivePayment(ReceivePayment value) {
        return new JAXBElement<ReceivePayment>(_ReceivePayment_QNAME, ReceivePayment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceivePaymentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manufacturer.me.org/", name = "receivePaymentResponse")
    public JAXBElement<ReceivePaymentResponse> createReceivePaymentResponse(ReceivePaymentResponse value) {
        return new JAXBElement<ReceivePaymentResponse>(_ReceivePaymentResponse_QNAME, ReceivePaymentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manufacturer.me.org/", name = "getProductInfo")
    public JAXBElement<GetProductInfo> createGetProductInfo(GetProductInfo value) {
        return new JAXBElement<GetProductInfo>(_GetProductInfo_QNAME, GetProductInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessPurchasePrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manufacturer.me.org/", name = "processPurchasePrderResponse")
    public JAXBElement<ProcessPurchasePrderResponse> createProcessPurchasePrderResponse(ProcessPurchasePrderResponse value) {
        return new JAXBElement<ProcessPurchasePrderResponse>(_ProcessPurchasePrderResponse_QNAME, ProcessPurchasePrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manufacturer.me.org/", name = "getProductInfoResponse")
    public JAXBElement<GetProductInfoResponse> createGetProductInfoResponse(GetProductInfoResponse value) {
        return new JAXBElement<GetProductInfoResponse>(_GetProductInfoResponse_QNAME, GetProductInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessPurchasePrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://manufacturer.me.org/", name = "processPurchasePrder")
    public JAXBElement<ProcessPurchasePrder> createProcessPurchasePrder(ProcessPurchasePrder value) {
        return new JAXBElement<ProcessPurchasePrder>(_ProcessPurchasePrder_QNAME, ProcessPurchasePrder.class, null, value);
    }

}
