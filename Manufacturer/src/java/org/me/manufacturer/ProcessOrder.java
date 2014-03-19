/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.me.manufacturer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author tomas.valettini
 */
public class ProcessOrder
{
    private static ProcessOrder instance = null;
    private String ORDERS_XML = "purchaseOrder.xml";
    private String path;
    
    public ProcessOrder()
    {
        
    }

    public ProcessOrder(WebServiceContext wsc)
    {
        MessageContext ctxt = wsc.getMessageContext();
        ServletContext req = (ServletContext) ctxt.get(ctxt.SERVLET_CONTEXT);
        path = req.getRealPath("WEB-INF");
    }
    
    private static String getValue(String tag, Element element) 
    {
            NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
            Node node = (Node) nodes.item(0);
            return node.getNodeValue();
    }
    
    public void process(PurchaseOrder po)
    {
        //process xml bitch
        // Processing the purchase order, needs to be added to the xml file        
        File file = new File(path + "/" + ORDERS_XML);
        
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new FileReader(file));

            Document doc = db.parse(is);

            Element root = doc.getDocumentElement();
            Element e = doc.createElement("order");

            // Convert the purchase order to an XML format
            String keys[] = {"orderNum","customerRef","product","quantity","unitPrice"};
            String values[] = {Integer.toString(po.getOrderNum()), po.getCustomerRef(), po.getProduct().getProductType(), Integer.toString(po.getQuantity()), Float.toString(po.getUnitPrice())};
            
            for(int i=0;i<keys.length;i++)
            {
                Element tmp = doc.createElement(keys[i]);
                tmp.setTextContent(values[i]);
                e.appendChild(tmp);
            }
            
            // Set the status to submitted
            Element status = doc.createElement("status");
            status.setTextContent("submitted");
            e.appendChild(status);

            // Set the order total
            Element total = doc.createElement("orderTotal");
            float orderTotal = po.getQuantity() * po.getUnitPrice();
            total.setTextContent(Float.toString(orderTotal));
            e.appendChild(total);

            // Write the content all as a new element in the root
            root.appendChild(e);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer m = tf.newTransformer();
            DOMSource source = new DOMSource(root);
            StreamResult result = new StreamResult(file);
            m.transform(source, result);

        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean processPayment(int orderNumber, float totalPrice)
    {
        boolean paid = false;
        
        try
        {
            DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder dombuilder;
            dombuilder = domfac.newDocumentBuilder();
            InputStream is = new FileInputStream(path + "/" + ORDERS_XML);
            Document doc;
            doc = dombuilder.parse(is);
            Element titleInfo = doc.getDocumentElement();
            NodeList products = titleInfo.getElementsByTagName("order");
            for (int i = 0; i < products.getLength(); i++) 
            {
                Node product = products.item(i);
                if (product.getNodeType() == Node.ELEMENT_NODE) 
                {
                    Element element = (Element) product;
                    if( Integer.parseInt(getValue("orderNum", element)) == (orderNumber) &&
                        Float.parseFloat(getValue("orderTotal", element)) == (totalPrice))
                    {
                        element.getElementsByTagName("status").item(0).setTextContent("paid");
                        paid = true;
                    }
                }
            }
        }
        catch (Exception e)
        {
                System.out.println("Caught Exception: ");
                e.printStackTrace();		
        }
        
        return paid;
    }
}
