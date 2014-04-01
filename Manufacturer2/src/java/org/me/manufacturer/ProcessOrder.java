/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.me.manufacturer;

import java.io.File;
import java.io.FileReader;
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

    public boolean processPayment(int orderNum, float totalPrice)
    {
        File file = new File(path + "/" + ORDERS_XML);
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new FileReader(file));

            Document doc = db.parse(is);
            Document newDoc = db.newDocument();
            Element root = newDoc.createElement("orderList");

            boolean flag = false;
            NodeList orders = doc.getElementsByTagName("order");
            int j = orders.getLength();
            // Loops through each element and checks if it is paid
            for(int i=0;i<orders.getLength();i++){
                Element order = (Element) orders.item(i);
                if(getValue("orderNum", order).equals(String.valueOf(orderNum))){
                    Float f = Float.valueOf(getValue("orderTotal", order)).floatValue();
                    boolean b = (Float.valueOf(getValue("orderTotal", order)).floatValue() == totalPrice);
                    if(Float.valueOf(getValue("orderTotal", order)).floatValue() == totalPrice){

                        // update the xml here

                        order.getElementsByTagName("status").item(0).setTextContent("paid");

                        flag = true;
                    }
                }
                Node newNode = newDoc.importNode(order, true);
                root.appendChild(newNode);
            }
            // Write the updated content to the xml file
            Node docRoot = newDoc.importNode(root,true);
            newDoc.appendChild(docRoot);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(newDoc);

            //StreamResult result = new StreamResult(System.out);
            StreamResult result = new StreamResult(path + "/" + ORDERS_XML);
            transformer.transform(source, result);
            return flag;
        } catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}
