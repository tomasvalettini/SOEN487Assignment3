
package org.me.warehouse;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.me.warehouse package. 
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

    private final static QName _ShipGoodsResponse_QNAME = new QName("http://warehouse.me.org/", "shipGoodsResponse");
    private final static QName _ShipGoods_QNAME = new QName("http://warehouse.me.org/", "shipGoods");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.me.warehouse
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
     * Create an instance of {@link ShipGoods }
     * 
     */
    public ShipGoods createShipGoods() {
        return new ShipGoods();
    }

    /**
     * Create an instance of {@link OrderItem }
     * 
     */
    public OrderItem createOrderItem() {
        return new OrderItem();
    }

    /**
     * Create an instance of {@link OrderList }
     * 
     */
    public OrderList createOrderList() {
        return new OrderList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShipGoodsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://warehouse.me.org/", name = "shipGoodsResponse")
    public JAXBElement<ShipGoodsResponse> createShipGoodsResponse(ShipGoodsResponse value) {
        return new JAXBElement<ShipGoodsResponse>(_ShipGoodsResponse_QNAME, ShipGoodsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShipGoods }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://warehouse.me.org/", name = "shipGoods")
    public JAXBElement<ShipGoods> createShipGoods(ShipGoods value) {
        return new JAXBElement<ShipGoods>(_ShipGoods_QNAME, ShipGoods.class, null, value);
    }

}
