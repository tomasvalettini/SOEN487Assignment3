package org.me.warehouse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.me.manufacturer.Product;
import org.me.manufacturer.PurchaseOrder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@WebService(serviceName = "WarehouseWS")
public class WarehouseWS
{
    @Resource private WebServiceContext wsc;
    private static int orderNum=0;
    private static int THRESHOLD = 20;
    private final int REPLENISH_AMOUNT = 100;
    private final String SELF = "Warehouse_Uno";
    
    private String getValue(String tag, Element element) 
    {
            NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
            Node node = (Node) nodes.item(0);
            return node.getNodeValue();
    }
     
    private void replenish()
    {
        MessageContext ctxt = wsc.getMessageContext();
        ServletContext req = (ServletContext) ctxt.get(ctxt.SERVLET_CONTEXT);
        String path = req.getRealPath("WEB-INF");
        path += "/inventory.xml";
        File file = new File(path);
        
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new FileReader(file));
            Document doc = db.parse(is);
            NodeList product = doc.getElementsByTagName("item");
            
            for (int i = 0; i < product.getLength(); i++)
            {
                Element xmlItem = (Element) product.item(i);

                if (Integer.parseInt(getValue("quantity", xmlItem)) < THRESHOLD) 
                {
                    System.out.println("BELOW THRESHOLD");
                    String h = xmlItem.getAttribute("name");
                    System.out.println("PRODUCT TO ORDER: " + h);
                    AbstractManufacturer am = ManufacturerFactory.getInstance().getManufacturer(h);
                    System.out.println(am.getClass());
                    Product p = am.getProductInfo(h);
                    System.out.println("PRODUCT INFO: " + p.getManufacturerName());
                     System.out.println("IS P NULL: " + (p == null));
                     
                    if (p != null)
                    {
                        System.out.println("PRODUCT INFO: " + p.getManufacturerName());
                        PurchaseOrder po = new PurchaseOrder();
                        po.setOrderNum(orderNum++);
                        po.setCustomerRef(SELF);
                        po.setProduct(p);
                        po.setQuantity(REPLENISH_AMOUNT);
                        po.setUnitPrice(p.getUnitPrice() + 5);
                        
                        if (am.processPurchasePrder(po))
                        {
                            System.out.println("IN IF");
                            xmlItem.getElementsByTagName("quantity").item(0).setTextContent(String.valueOf(Integer.parseInt(getValue("quantity", xmlItem)) + REPLENISH_AMOUNT));
                            am.receivePayment(po.getOrderNum(), po.getQuantity() * po.getUnitPrice());
                            
                        }
                    }
                }
            }
            
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer m = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(path);
            m.transform(source, result);
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    @WebMethod(operationName = "shipGoods")
    public ShippedList shipGoods(@WebParam(name = "items") OrderList items)
    {
        ArrayList<OrderItem> shippedItems = new ArrayList<>();
        ArrayList<OrderItem> itemList = items.getItems();
        
        try
        { 
            DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder dombuilder;
            dombuilder = domfac.newDocumentBuilder();
            MessageContext ctxt = wsc.getMessageContext();
            ServletContext req = (ServletContext) ctxt.get(ctxt.SERVLET_CONTEXT);
            String path = req.getRealPath("WEB-INF");
            InputStream is = new FileInputStream(path + "/inventory.xml");
            Document doc;
            doc = dombuilder.parse(is);
            Element root = doc.getDocumentElement();
            Element titleInfo = doc.getDocumentElement();
            NodeList inventory = titleInfo.getElementsByTagName("item");
            System.out.println("PATH OF XML: " + path + "/inventory.xml");
            
            for(int t=0;t<itemList.size();t++)
            {
                System.out.println("IN LOOP: " + t);
                OrderItem tmp = (OrderItem) itemList.get(t);
               System.out.println("TMP PRODUCT: " + tmp.getProduct().getProductName());
                for(int i=0;i<inventory.getLength();i++)
                {
                    
                    System.out.println("IN XML FOR LOOP");
                    Element InventoryItem = (Element) inventory.item(i);
                    
                     System.out.println("TMP PRODUCT: " + tmp.getProduct().getProductName() + " XML Attribute: " + InventoryItem.getAttribute("name"));
                    if(tmp.getProduct().getProductName().equals(InventoryItem.getAttribute("name")))
                    {
                        System.out.println("PRODUCT NAME MATCH");
                        System.out.println("XML QTY: " + getValue("quantity", InventoryItem) + " " + "TMP QTY: " + tmp.getQuantity());
                        int newQuantity = Integer.parseInt(getValue("quantity", InventoryItem)) - tmp.getQuantity();
                         
                        System.out.println("NEW QTY: " + newQuantity);
                        if(newQuantity >= 0)
                        {
                            InventoryItem.getElementsByTagName("quantity").item(0).setTextContent(Integer.toString(newQuantity));
                            shippedItems.add(tmp);

                        }
                        else
                        {
                            tmp.setQuantity(Integer.parseInt(getValue("quantity", InventoryItem)));
                            shippedItems.add(tmp);
                            InventoryItem.getElementsByTagName("quantity").item(0).setTextContent("0");
                        }
                        
                        
                        break;
                    }
                }
            }
                    
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer m = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(path + "/inventory.xml");
            m.transform(source, result);
            
        }
        catch (Exception e)
        {
                System.out.println("Caught Exception: ");
                e.printStackTrace();		
        }

        
        replenish();

        return new ShippedList(shippedItems);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getProducts")
    public ProductList getProducts() 
    {
        ProductList list = new ProductList();
        ArrayList<Product> prods = new ArrayList<Product>();
        try
        {
            DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder dombuilder;
            dombuilder = domfac.newDocumentBuilder();
            MessageContext ctxt = wsc.getMessageContext();
            ServletContext req = (ServletContext) ctxt.get(ctxt.SERVLET_CONTEXT);
            String path = req.getRealPath("WEB-INF");
            InputStream is = new FileInputStream(path + "/inventory.xml");
            Document doc;
            doc = dombuilder.parse(is);
            Element root = doc.getDocumentElement();
            Element titleInfo = doc.getDocumentElement();
            NodeList inventory = titleInfo.getElementsByTagName("item");
            System.out.println("PATH OF XML: " + path + "/inventory.xml");
            
           
            NodeList nodes = doc.getElementsByTagName("item");
            
            // Loop through and print out all of the title elements
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                Product p = new Product();
                p.setManufacturerName(getValue("manufacturerName", element));
                p.setProductType(getValue("productType", element));
                p.setUnitPrice(Float.valueOf(getValue("unitPrice", element)));
                p.setProductName(element.getAttribute("name"));
                prods.add(p);
            }
            list.setProducts(prods);
         
        }
        catch (Exception e)
        {
                e.printStackTrace();		
        }
        return list;
    }
}
