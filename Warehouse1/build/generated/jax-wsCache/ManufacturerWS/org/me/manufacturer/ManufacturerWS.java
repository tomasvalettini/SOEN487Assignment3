
package org.me.manufacturer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ManufacturerWS", targetNamespace = "http://manufacturer.me.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ManufacturerWS {


    /**
     * 
     * @param orderNumber
     * @param totalPrice
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "receivePayment", targetNamespace = "http://manufacturer.me.org/", className = "org.me.manufacturer.ReceivePayment")
    @ResponseWrapper(localName = "receivePaymentResponse", targetNamespace = "http://manufacturer.me.org/", className = "org.me.manufacturer.ReceivePaymentResponse")
    @Action(input = "http://manufacturer.me.org/ManufacturerWS/receivePaymentRequest", output = "http://manufacturer.me.org/ManufacturerWS/receivePaymentResponse")
    public boolean receivePayment(
        @WebParam(name = "orderNumber", targetNamespace = "")
        int orderNumber,
        @WebParam(name = "totalPrice", targetNamespace = "")
        float totalPrice);

    /**
     * 
     * @param productType
     * @return
     *     returns org.me.manufacturer.Product
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getProductInfo", targetNamespace = "http://manufacturer.me.org/", className = "org.me.manufacturer.GetProductInfo")
    @ResponseWrapper(localName = "getProductInfoResponse", targetNamespace = "http://manufacturer.me.org/", className = "org.me.manufacturer.GetProductInfoResponse")
    @Action(input = "http://manufacturer.me.org/ManufacturerWS/getProductInfoRequest", output = "http://manufacturer.me.org/ManufacturerWS/getProductInfoResponse")
    public Product getProductInfo(
        @WebParam(name = "productType", targetNamespace = "")
        String productType);

    /**
     * 
     * @param purchaseOrder
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "processPurchasePrder", targetNamespace = "http://manufacturer.me.org/", className = "org.me.manufacturer.ProcessPurchasePrder")
    @ResponseWrapper(localName = "processPurchasePrderResponse", targetNamespace = "http://manufacturer.me.org/", className = "org.me.manufacturer.ProcessPurchasePrderResponse")
    @Action(input = "http://manufacturer.me.org/ManufacturerWS/processPurchasePrderRequest", output = "http://manufacturer.me.org/ManufacturerWS/processPurchasePrderResponse")
    public boolean processPurchasePrder(
        @WebParam(name = "purchaseOrder", targetNamespace = "")
        PurchaseOrder purchaseOrder);

}
