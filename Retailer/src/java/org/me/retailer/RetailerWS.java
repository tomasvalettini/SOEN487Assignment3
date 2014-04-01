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
//    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Warehouse3/Warehouse3WS.wsdl")
//    private Warehouse3WS_Service service_2;
//    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Warehouse2/Warehouse2WS.wsdl")
//    private Warehouse2WS_Service service_1;
//    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Warehouse1/WarehouseWS.wsdl")
//    private WarehouseWS_Service service;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/ec2-54-186-163-110.us-west-2.compute.amazonaws.com_8080/Warehouse3/Warehouse3WS.wsdl")
    private Warehouse3WS_Service service_2;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/ec2-54-186-163-110.us-west-2.compute.amazonaws.com_8080/Warehouse2/Warehouse2WS.wsdl")
    private Warehouse2WS_Service service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/ec2-54-186-163-110.us-west-2.compute.amazonaws.com_8080/Warehouse1/WarehouseWS.wsdl")
    private WarehouseWS_Service service;
    
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
        
        OrderList completeOrder = items;
        ShippedList temp, sl;
        temp = port.shipGoods(items);
        
        System.out.println("Process shipped one!");
        for (int i = 0; i < completeOrder.getItems().size(); i++)
        {
            for (int j = 0; j < temp.getItems().size(); j++)
            {
                if (completeOrder.getItems().get(i).getProduct().getManufacturerName().equals(temp.getItems().get(j).getProduct().getManufacturerName()) &&
                    completeOrder.getItems().get(i).getProduct().getProductName().equals(temp.getItems().get(j).getProduct().getProductName()) &&
                    completeOrder.getItems().get(i).getProduct().getProductType().equals(temp.getItems().get(j).getProduct().getProductType()) &&
                    completeOrder.getItems().get(i).getProduct().getUnitPrice() == temp.getItems().get(j).getProduct().getUnitPrice())
                {
                    if (completeOrder.getItems().get(i).getQuantity() == temp.getItems().get(j).getQuantity())
                    {
                        completeOrder.getItems().remove(i);
                    }
                    else
                    {
                        completeOrder.getItems().get(i).setQuantity(completeOrder.getItems().get(i).getQuantity() - temp.getItems().get(j).getQuantity());
                    }
                }
            }
        }
        
        sl = temp;
        
        if (completeOrder.getItems().isEmpty())
        {
            return sl;
        }
        
        temp = port2.shipGoods(completeOrder);
        
        for (int i = 0; i < completeOrder.getItems().size(); i++)
        {
            for (int j = 0; j < temp.getItems().size(); j++)
            {
                if (completeOrder.getItems().get(i).getProduct().getManufacturerName().equals(temp.getItems().get(j).getProduct().getManufacturerName()) &&
                    completeOrder.getItems().get(i).getProduct().getProductName().equals(temp.getItems().get(j).getProduct().getProductName()) &&
                    completeOrder.getItems().get(i).getProduct().getProductType().equals(temp.getItems().get(j).getProduct().getProductType()) &&
                    completeOrder.getItems().get(i).getProduct().getUnitPrice() == temp.getItems().get(j).getProduct().getUnitPrice())
                {
                    if (completeOrder.getItems().get(i).getQuantity() == temp.getItems().get(j).getQuantity())
                    {
                        completeOrder.getItems().remove(i);
                    }
                    else
                    {
                        completeOrder.getItems().get(i).setQuantity(completeOrder.getItems().get(i).getQuantity() - temp.getItems().get(j).getQuantity());
                    }
                }
            }
        }
        
        for (int i = 0; i < sl.getItems().size(); i++)
        {
            for (int j = 0; j < temp.getItems().size(); j++)
            {
                if (sl.getItems().get(i).getProduct().getManufacturerName().equals(temp.getItems().get(j).getProduct().getManufacturerName()) &&
                    sl.getItems().get(i).getProduct().getProductName().equals(temp.getItems().get(j).getProduct().getProductName()) &&
                    sl.getItems().get(i).getProduct().getProductType().equals(temp.getItems().get(j).getProduct().getProductType()) &&
                    sl.getItems().get(i).getProduct().getUnitPrice() == temp.getItems().get(j).getProduct().getUnitPrice())
                {
                    sl.getItems().get(i).setQuantity(sl.getItems().get(i).getQuantity() + temp.getItems().get(j).getQuantity());
                }
            }
        }
        
        if (completeOrder.getItems().isEmpty())
        {
            return sl;
        }
        
        temp = port3.shipGoods(completeOrder);
        
        for (int i = 0; i < completeOrder.getItems().size(); i++)
        {
            for (int j = 0; j < temp.getItems().size(); j++)
            {
                if (completeOrder.getItems().get(i).getProduct().getManufacturerName().equals(temp.getItems().get(j).getProduct().getManufacturerName()) &&
                    completeOrder.getItems().get(i).getProduct().getProductName().equals(temp.getItems().get(j).getProduct().getProductName()) &&
                    completeOrder.getItems().get(i).getProduct().getProductType().equals(temp.getItems().get(j).getProduct().getProductType()) &&
                    completeOrder.getItems().get(i).getProduct().getUnitPrice() == temp.getItems().get(j).getProduct().getUnitPrice())
                {
                    if (completeOrder.getItems().get(i).getQuantity() == temp.getItems().get(j).getQuantity())
                    {
                        completeOrder.getItems().remove(i);
                    }
                    else
                    {
                        completeOrder.getItems().get(i).setQuantity(completeOrder.getItems().get(i).getQuantity() - temp.getItems().get(j).getQuantity());
                    }
                }
            }
        }
        
        for (int i = 0; i < sl.getItems().size(); i++)
        {
            for (int j = 0; j < temp.getItems().size(); j++)
            {
                if (sl.getItems().get(i).getProduct().getManufacturerName().equals(temp.getItems().get(j).getProduct().getManufacturerName()) &&
                    sl.getItems().get(i).getProduct().getProductName().equals(temp.getItems().get(j).getProduct().getProductName()) &&
                    sl.getItems().get(i).getProduct().getProductType().equals(temp.getItems().get(j).getProduct().getProductType()) &&
                    sl.getItems().get(i).getProduct().getUnitPrice() == temp.getItems().get(j).getProduct().getUnitPrice())
                {
                    sl.getItems().get(i).setQuantity(sl.getItems().get(i).getQuantity() + temp.getItems().get(j).getQuantity());
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