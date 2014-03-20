<%-- 
    Document   : index
    Created on : Mar 19, 2014, 2:19:14 PM
    Author     : tomas.valettini
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ship Motherfucker!!</h1>
        <%-- start web service invocation --%><hr/>
        <%
        try {
            org.me.warehouse.WarehouseWS_Service service = new org.me.warehouse.WarehouseWS_Service();
            org.me.warehouse.WarehouseWS port = service.getWarehouseWSPort();
             // TODO initialize WS operation arguments here
            org.me.manufacturer.Product p = new org.me.manufacturer.Product();
            p.setProductName("Brand1TV");
            p.setManufacturerName("Brand1");
            p.setProductType("TV");
            p.setUnitPrice(1400f);
            
            org.me.warehouse.OrderItem oi = new org.me.warehouse.OrderItem();
            oi.setProduct(p);
            oi.setQuantity(50);
            
            java.util.List<org.me.warehouse.OrderItem> items = null;
            items.add(oi);
            // TODO process result here
            java.util.List<org.me.warehouse.OrderItem> result = port.shipGoods(items);
            
            for (int i = 0; i < result.size(); i++)
            {
                System.out.print(result.get(i).getProduct().getProductName() + " // " + result.get(i).getQuantity() + "<br />");
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        %>
        <%-- end web service invocation --%><hr/>

        
    </body>
</html>
