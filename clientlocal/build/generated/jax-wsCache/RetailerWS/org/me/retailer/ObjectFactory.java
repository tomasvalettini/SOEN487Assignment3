
package org.me.retailer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.me.retailer package. 
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

    private final static QName _ShipGoodsResponse_QNAME = new QName("http://retailer.me.org/", "shipGoodsResponse");
    private final static QName _ShipGoods_QNAME = new QName("http://retailer.me.org/", "shipGoods");
    private final static QName _GetCatalogResponse_QNAME = new QName("http://retailer.me.org/", "getCatalogResponse");
    private final static QName _GetCatalog_QNAME = new QName("http://retailer.me.org/", "getCatalog");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.me.retailer
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ShipGoodsResponse }
     * 
     */
    public ShipGoodsResponse createShipGoodsResponse() {
        return new ShipGoodsResponse();
    }

    /**
     * Create an instance of {@link GetCatalog }
     * 
     */
    public GetCatalog createGetCatalog() {
        return new GetCatalog();
    }

    /**
     * Create an instance of {@link GetCatalogResponse }
     * 
     */
    public GetCatalogResponse createGetCatalogResponse() {
        return new GetCatalogResponse();
    }

    /**
     * Create an instance of {@link ShipGoods }
     * 
     */
    public ShipGoods createShipGoods() {
        return new ShipGoods();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShipGoodsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://retailer.me.org/", name = "shipGoodsResponse")
    public JAXBElement<ShipGoodsResponse> createShipGoodsResponse(ShipGoodsResponse value) {
        return new JAXBElement<ShipGoodsResponse>(_ShipGoodsResponse_QNAME, ShipGoodsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShipGoods }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://retailer.me.org/", name = "shipGoods")
    public JAXBElement<ShipGoods> createShipGoods(ShipGoods value) {
        return new JAXBElement<ShipGoods>(_ShipGoods_QNAME, ShipGoods.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCatalogResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://retailer.me.org/", name = "getCatalogResponse")
    public JAXBElement<GetCatalogResponse> createGetCatalogResponse(GetCatalogResponse value) {
        return new JAXBElement<GetCatalogResponse>(_GetCatalogResponse_QNAME, GetCatalogResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCatalog }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://retailer.me.org/", name = "getCatalog")
    public JAXBElement<GetCatalog> createGetCatalog(GetCatalog value) {
        return new JAXBElement<GetCatalog>(_GetCatalog_QNAME, GetCatalog.class, null, value);
    }

}
