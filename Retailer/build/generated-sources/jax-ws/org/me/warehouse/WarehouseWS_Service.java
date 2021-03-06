
package org.me.warehouse;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WarehouseWS", targetNamespace = "http://warehouse.me.org/", wsdlLocation = "http://ec2-54-186-163-110.us-west-2.compute.amazonaws.com:8080/Warehouse1/WarehouseWS?wsdl")
public class WarehouseWS_Service
    extends Service
{

    private final static URL WAREHOUSEWS_WSDL_LOCATION;
    private final static WebServiceException WAREHOUSEWS_EXCEPTION;
    private final static QName WAREHOUSEWS_QNAME = new QName("http://warehouse.me.org/", "WarehouseWS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://ec2-54-186-163-110.us-west-2.compute.amazonaws.com:8080/Warehouse1/WarehouseWS?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WAREHOUSEWS_WSDL_LOCATION = url;
        WAREHOUSEWS_EXCEPTION = e;
    }

    public WarehouseWS_Service() {
        super(__getWsdlLocation(), WAREHOUSEWS_QNAME);
    }

    public WarehouseWS_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), WAREHOUSEWS_QNAME, features);
    }

    public WarehouseWS_Service(URL wsdlLocation) {
        super(wsdlLocation, WAREHOUSEWS_QNAME);
    }

    public WarehouseWS_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WAREHOUSEWS_QNAME, features);
    }

    public WarehouseWS_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WarehouseWS_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WarehouseWS
     */
    @WebEndpoint(name = "WarehouseWSPort")
    public WarehouseWS getWarehouseWSPort() {
        return super.getPort(new QName("http://warehouse.me.org/", "WarehouseWSPort"), WarehouseWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WarehouseWS
     */
    @WebEndpoint(name = "WarehouseWSPort")
    public WarehouseWS getWarehouseWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://warehouse.me.org/", "WarehouseWSPort"), WarehouseWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WAREHOUSEWS_EXCEPTION!= null) {
            throw WAREHOUSEWS_EXCEPTION;
        }
        return WAREHOUSEWS_WSDL_LOCATION;
    }

}
