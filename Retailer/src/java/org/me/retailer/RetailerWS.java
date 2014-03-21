/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.me.retailer;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceRef;
import org.me.manufacturer.ManufacturerWS_Service;
import org.me.manufacturer.Product;
import org.me.warehouse.*;
import org.me.warehouse.WarehouseWS_Service;

/**
 *
 * @author arnaud.rivard
 */
@WebService(serviceName = "RetailerWS")
public class RetailerWS {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/ec2-54-186-163-110.us-west-2.compute.amazonaws.com_8080/Manufacturer/ManufacturerWS.wsdl")
    private ManufacturerWS_Service service_Manufacturer1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/ec2-54-186-163-110.us-west-2.compute.amazonaws.com_8080/Warehouse1/WarehouseWS.wsdl")
    private WarehouseWS_Service service_Warehouse1;
    
    private List<OrderItem> itemsShipped;
    private List<OrderItem> itemsLeft;
    private List<OrderItem> itemsTemp;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "submitOrder")
    public List<OrderItem> submitOrder(@WebParam(name = "orderItemList") List<OrderItem> orderItemList) {
      //TODO ADD ITEM TO SHIP GOODS  shipGoods = new shipGoods; 
      itemsLeft = orderItemList;
      itemsTemp = new ArrayList();
      itemsShipped = new ArrayList();
      itemsTemp = shipGoods1(itemsLeft);
      itemsLeft.removeAll(itemsTemp);
      itemsShipped.addAll(itemsTemp);
//      itemsTemp = shipGoods2(itemsLeft);
//      itemsLeft.removeAll(itemsTemp);
//      itemsShipped.addAll(itemsTemp);
//      itemsTemp = shipGoods3(itemsLeft);
//      itemsLeft.removeAll(itemsTemp);
//      itemsShipped.addAll(itemsTemp);
    
     return itemsShipped;
     }
    @WebMethod(operationName = "getCatalog")
    public List<OrderItem> getCatalog(){
        List<OrderItem> catalog = new ArrayList();
        //CALL THE METHOD TO GET PRODUCTS
        //MANUFACTURER1
        //MANUFACTURER2
        //MANUFACTURER3
        return catalog;
    }
    
    private List<OrderItem> shipGoods1(List<OrderItem> items){
        org.me.warehouse.WarehouseWS port = service_Warehouse1.getWarehouseWSPort();
        return port.shipGoods(items);
    }
    
 /*  private List<OrderItem> shipGoods2(List<OrderItem> items){
        org.me.warehouse.WarehouseWS port = service.getWarehouseWSPort();
        return port.shipGoods(items);
    }
    private List<OrderItem> shipGoods3(List<OrderItem> items){
        org.me.warehouse.WarehouseWS port = service.getWarehouseWSPort();
        return port.shipGoods(items);
    }
  */

}
