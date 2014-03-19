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
        <h1>Get product info</h1>
        <%-- start web service invocation --%><hr/>
        <%
        try
        {
            org.me.manufacturer.ManufacturerWS_Service service = new org.me.manufacturer.ManufacturerWS_Service();
            org.me.manufacturer.ManufacturerWS port = service.getManufacturerWSPort();
             // TODO initialize WS operation arguments here
            java.lang.String productType = "DVD";
            // TODO process result here
            java.util.List<org.me.manufacturer.Product> result = port.getProductInfo(productType);

            for (int i = 0; i < result.size(); i++)
            {
                out.println("Product #" + (i + 1) + " " + result.get(i).getManufacturerName()+ "(" + result.get(i).getProductType() + ") ----- " + result.get(i).getUnitPrice() + "$ <br />");
            }

        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        %>
        <%-- end web service invocation --%><hr/>

        <h1>Process purchase order</h1>
        <%-- start web service invocation --%><hr/>
        <%
        try {
            org.me.manufacturer.ManufacturerWS_Service service = new org.me.manufacturer.ManufacturerWS_Service();
            org.me.manufacturer.ManufacturerWS port = service.getManufacturerWSPort();
             // TODO initialize WS operation arguments here
          
            org.me.manufacturer.Product prod = new org.me.manufacturer.Product();
            prod.setManufacturerName("Brand1");
            prod.setProductType("TV");
            prod.setUnitPrice(800);
            org.me.manufacturer.PurchaseOrder po = new org.me.manufacturer.PurchaseOrder();
            po.setOrderNum(111);
            po.setCustomerRef("tom");
            po.setProduct(prod);
            po.setQuantity(40);
            po.setUnitPrice(800);
            
            int quantity = 0;
            // TODO process result here
            boolean result = port.processPurchasePrder(po, quantity);
            
            out.println("Result = "+result);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        %>
        <%-- end web service invocation --%><hr/>

        <h1>Receive Payment</h1>
    </body>
</html>
