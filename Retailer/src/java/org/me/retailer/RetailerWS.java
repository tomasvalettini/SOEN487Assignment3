package org.me.retailer;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceRef;
import org.me.manufacturer.Product;
import org.me.warehouse.*;
import org.me.warehouse.WarehouseWS_Service;
import org.me.warehouse.Warehouse2WS;
import org.me.warehouse.Warehouse2WS_Service;
import org.me.warehouse.Warehouse3WS;
import org.me.warehouse.Warehouse3WS_Service;

@WebService(serviceName = "RetailerWS")
public class RetailerWS
{
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Warehouse3/Warehouse3WS.wsdl")
    private Warehouse3WS_Service service_2;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Warehouse2/Warehouse2WS.wsdl")
    private Warehouse2WS_Service service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Warehouse1/WarehouseWS.wsdl")
    private WarehouseWS_Service service;
//    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/ec2-54-186-163-110.us-west-2.compute.amazonaws.com_8080/Warehouse3/Warehouse3WS.wsdl")
//    private Warehouse3WS_Service service_2;
//    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/ec2-54-186-163-110.us-west-2.compute.amazonaws.com_8080/Warehouse2/Warehouse2WS.wsdl")
//    private Warehouse2WS_Service service_1;
//    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/ec2-54-186-163-110.us-west-2.compute.amazonaws.com_8080/Warehouse1/WarehouseWS.wsdl")
//    private WarehouseWS_Service service;
    
    @WebMethod(operationName = "getCatalog")
    public ProductList getCatalog()
    {
        WarehouseWS port = service.getWarehouseWSPort();
        Warehouse2WS port2 = service_1.getWarehouse2WSPort();
        Warehouse3WS port3 = service_2.getWarehouse3WSPort();
        
        ProductList plFinal = port.getProducts();
        ProductList pl2 = port2.getProducts();
        ProductList pl3 = port3.getProducts();
        
        //merging list of items
        boolean match = false;
        
        for (Product p2 : pl2.getProducts())
        {
            for (Product p : plFinal.getProducts())
            {
                if (p.getManufacturerName().equals(p2.getManufacturerName()) &&
                    p.getProductName().equals(p2.getProductName()) &&
                    p.getProductType().equals(p2.getProductType()) &&
                    p.getUnitPrice() == p2.getUnitPrice())
                {
                    match = true;
                }
            }
            
            if (match)
            {
                match = false;
            }
            else
            {
                plFinal.getProducts().add(p2);
            }
        }
        
        for (Product p3 : pl3.getProducts())
        {
            for (Product p : plFinal.getProducts())
            {
                if (p.getManufacturerName().equals(p3.getManufacturerName()) &&
                    p.getProductName().equals(p3.getProductName()) &&
                    p.getProductType().equals(p3.getProductType()) &&
                    p.getUnitPrice() == p3.getUnitPrice())
                {
                    match = true;
                }
            }
            
            if (match)
            {
                match = false;
            }
            else
            {
                plFinal.getProducts().add(p3);
            }
        }
        
        return plFinal;
    }
    
    @WebMethod(operationName = "shipGoods")
    public ShippedList shipGoods(@WebParam(name = "items") OrderList items)
    {
        //TODO write your implementation code here:
        WarehouseWS port = service.getWarehouseWSPort();
        Warehouse2WS port2 = service_1.getWarehouse2WSPort();
        Warehouse3WS port3 = service_2.getWarehouse3WSPort();
        
        ShippedList sl = port.shipGoods(items);
        
        for (int i = 0; i < items.getItems().size(); i++)
        {
            System.out.println("Shipped goods quantity (" + sl.getItems().get(i).getProduct().getProductName() + "): " + sl.getItems().get(i).getQuantity());
            System.out.println("items to ship quantity: (" + items.getItems().get(i).getProduct().getProductName() + ")" + items.getItems().get(i).getQuantity());
            if (sl.getItems().get(i).getQuantity() == items.getItems().get(i).getQuantity())
            {
                items.getItems().remove(i);
            }
            else
            {
                //items shipped will always be less than the ordered ones!!
                items.getItems().get(i).setQuantity(items.getItems().get(i).getQuantity() - sl.getItems().get(i).getQuantity());
            }
        }
        
        if (items.getItems().isEmpty())
        {
            return sl;
        }
        else
        {
            ShippedList sl2 = port2.shipGoods(items);
        
            for (int i = 0; i < items.getItems().size(); i++)
            {
                if (sl2.getItems().get(i).getQuantity() == items.getItems().get(i).getQuantity())
                {
                    items.getItems().remove(i);
                }
                else
                {
                    //items shipped will always be less than the ordered ones!!
                    items.getItems().get(i).setQuantity(items.getItems().get(i).getQuantity() - sl2.getItems().get(i).getQuantity());
                }
                
                //adding the quantity to the item that were left from the previous warehouse
                //double for loop to do this :(
                for (int j = 0; j < sl.getItems().size(); j++)
                {
                    for (int k = 0; k < sl2.getItems().size(); k++)
                    {
                        if (sl.getItems().get(i).getProduct().getManufacturerName().equals(sl2.getItems().get(k).getProduct().getManufacturerName()) &&
                            sl.getItems().get(i).getProduct().getProductName().equals(sl2.getItems().get(k).getProduct().getProductName()) &&
                            sl.getItems().get(i).getProduct().getProductType().equals(sl2.getItems().get(k).getProduct().getProductType()) &&
                            sl.getItems().get(i).getProduct().getUnitPrice() == sl2.getItems().get(k).getProduct().getUnitPrice())
                        {
                            sl.getItems().get(i).setQuantity(sl.getItems().get(i).getQuantity() + sl2.getItems().get(k).getQuantity());
                        }
                    }
                }
            }
            
            if (items.getItems().isEmpty())
            {
                return sl;
            }
            else
            {
                ShippedList sl3 = port3.shipGoods(items);
        
                for (int i = 0; i < items.getItems().size(); i++)
                {
                    if (sl3.getItems().get(i).getQuantity() == items.getItems().get(i).getQuantity())
                    {
                        items.getItems().remove(i);
                    }
                    else
                    {
                        //items shipped will always be less than the ordered ones!!
                        items.getItems().get(i).setQuantity(items.getItems().get(i).getQuantity() - sl3.getItems().get(i).getQuantity());
                    }

                    //adding the quantity to the item that were left from the previous warehouse
                    //double for loop to do this :(
                    for (int j = 0; j < sl.getItems().size(); j++)
                    {
                        for (int k = 0; k < sl3.getItems().size(); k++)
                        {
                            if (sl.getItems().get(i).getProduct().getManufacturerName().equals(sl3.getItems().get(k).getProduct().getManufacturerName()) &&
                                sl.getItems().get(i).getProduct().getProductName().equals(sl3.getItems().get(k).getProduct().getProductName()) &&
                                sl.getItems().get(i).getProduct().getProductType().equals(sl3.getItems().get(k).getProduct().getProductType()) &&
                                sl.getItems().get(i).getProduct().getUnitPrice() == sl3.getItems().get(k).getProduct().getUnitPrice())
                            {
                                sl.getItems().get(i).setQuantity(sl.getItems().get(i).getQuantity() + sl3.getItems().get(k).getQuantity());
                            }
                        }
                    }
                }
            }
        }
        
        /*
        ship from 1st warehouse
        then if what was shipped does not match the orderList,
        ship from next warehouse and repeat! 
        */
        return sl;
    }
}