/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.me.manufacturer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

/**
 *
 * @author deven.collette
 */
@WebService(serviceName = "ManufacturerWS")
public class ManufacturerWS
{
    @Resource private WebServiceContext wsc;
    
    private boolean produce(String productName, int quantity)
    {
        return ((quantity<=100)&&
                ((productName.equalsIgnoreCase("TV"))||
                (productName.equalsIgnoreCase("CAM"))||
                (productName.equalsIgnoreCase("DVD")))); 
    }
    
    private static String getValue(String tag, Element element) 
    {
            NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
            Node node = (Node) nodes.item(0);
            return node.getNodeValue();
    }
    
    /**
     * Web service operation
     * @param productType
     * @return 
     */
    @WebMethod(operationName = "getProductInfo")
    public Product getProductInfo(@WebParam(name = "productType") String productType)
    {        
        Product prod = new Product();
        
        try
        { 
            DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder dombuilder;
            dombuilder = domfac.newDocumentBuilder();
            MessageContext ctxt = wsc.getMessageContext();
            ServletContext req = (ServletContext) ctxt.get(ctxt.SERVLET_CONTEXT);
            String path = req.getRealPath("WEB-INF");
            InputStream is = new FileInputStream(path + "/productInfo.xml");
            Document doc;
            doc = dombuilder.parse(is);
            Element titleInfo = doc.getDocumentElement();
            NodeList products = titleInfo.getElementsByTagName("product");
            
            for (int i = 0; i < products.getLength(); i++) 
            {
                Node product = products.item(i);
                if (product.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element element = (Element) product;
                    if(element.getAttribute("name").equals("productType"))
                    {
                        prod.setManufacturerName(getValue("manufacturerName", element));
                        prod.setProductType(getValue("productType", element));
                        prod.setUnitPrice(Float.parseFloat(getValue("unitPrice", element)));
                    }
                }
            }
        }
        catch (Exception e)
        {
                System.out.println("Caught Exception: ");
                e.printStackTrace();		
        }
        return productList;
    }

    /**
     * Web service operation
     * @param orderNumber
     * @param totalPrice
     * @return 
     */
    @WebMethod(operationName = "receivePayment")
    public boolean receivePayment(@WebParam(name = "orderNumber") int orderNumber, @WebParam(name = "totalPrice") float totalPrice)
    {
        ProcessOrder po = new ProcessOrder(wsc);
        
        return po.processPayment(orderNumber, totalPrice);
    }

    /**
     * Web service operation
     * @param po
     * @param quantity
     * @return 
     */
    @WebMethod(operationName = "processPurchasePrder")
    public boolean processPurchasePrder(@WebParam(name = "purchaseOrder") PurchaseOrder po, @WebParam(name = "quantity") int quantity) {
        boolean processed = false;
        try { 
            DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder dombuilder;
            dombuilder = domfac.newDocumentBuilder();
            MessageContext ctxt = wsc.getMessageContext();
            ServletContext req = (ServletContext) ctxt.get(ctxt.SERVLET_CONTEXT);
            String path = req.getRealPath("WEB-INF");
            InputStream is = new FileInputStream(path + "/productInfo.xml");
            Document doc;
            doc = dombuilder.parse(is);
            Element titleInfo = doc.getDocumentElement();
            NodeList products = titleInfo.getElementsByTagName("product");
            for (int i = 0; i < products.getLength(); i++) 
            {
                Node product = products.item(i);

                if (product.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element element = (Element) product;
                    if( getValue("manufacturerName", element).equals(po.getProduct().getManufacturerName()) &&
                        getValue("productType", element).equals(po.getProduct().getProductType()) &&   
                        Float.parseFloat(getValue("unitPrice", element))<= po.getProduct().getUnitPrice())
                    {
                        processed = produce(po.getProduct().getProductType(), quantity);
                        break;
                    }
                }
            }
            
            if (processed)
            {
                ProcessOrder prodOr = new ProcessOrder(wsc);
                prodOr.process(po);
            }
        }
        catch (Exception e)
        {
                System.out.println("Caught Exception: ");
                e.printStackTrace();		
        }
        return processed;
    }
}
