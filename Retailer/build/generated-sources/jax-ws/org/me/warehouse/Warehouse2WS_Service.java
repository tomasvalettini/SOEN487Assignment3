
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
@WebServiceClient(name = "Warehouse2WS", targetNamespace = "http://warehouse.me.org/", wsdlLocation = "http://ec2-54-186-163-110.us-west-2.compute.amazonaws.com:8080/Warehouse2/Warehouse2WS?wsdl")
public class Warehouse2WS_Service
    extends Service
{

    private final static URL WAREHOUSE2WS_WSDL_LOCATION;
    private final static WebServiceException WAREHOUSE2WS_EXCEPTION;
    private final static QName WAREHOUSE2WS_QNAME = new QName("http://warehouse.me.org/", "Warehouse2WS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://ec2-54-186-163-110.us-west-2.compute.amazonaws.com:8080/Warehouse2/Warehouse2WS?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WAREHOUSE2WS_WSDL_LOCATION = url;
        WAREHOUSE2WS_EXCEPTION = e;
    }

    public Warehouse2WS_Service() {
        super(__getWsdlLocation(), WAREHOUSE2WS_QNAME);
    }

    public Warehouse2WS_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), WAREHOUSE2WS_QNAME, features);
    }

    public Warehouse2WS_Service(URL wsdlLocation) {
        super(wsdlLocation, WAREHOUSE2WS_QNAME);
    }

    public Warehouse2WS_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WAREHOUSE2WS_QNAME, features);
    }

    public Warehouse2WS_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Warehouse2WS_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Warehouse2WS
     */
    @WebEndpoint(name = "Warehouse2WSPort")
    public Warehouse2WS getWarehouse2WSPort() {
        return super.getPort(new QName("http://warehouse.me.org/", "Warehouse2WSPort"), Warehouse2WS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Warehouse2WS
     */
    @WebEndpoint(name = "Warehouse2WSPort")
    public Warehouse2WS getWarehouse2WSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://warehouse.me.org/", "Warehouse2WSPort"), Warehouse2WS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WAREHOUSE2WS_EXCEPTION!= null) {
            throw WAREHOUSE2WS_EXCEPTION;
        }
        return WAREHOUSE2WS_WSDL_LOCATION;
    }

}
