<%@page import="java.text.DecimalFormat"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Retailer Services</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        
    </head>
    <body>
        <h1>Retailer</h1>
        <%-- start web service invocation --%><hr/>
        <%
        try
        {
            org.me.retailer.RetailerWS_Service service = new org.me.retailer.RetailerWS_Service();
            org.me.retailer.RetailerWS port = service.getRetailerWSPort();
            // TODO process result here
            org.me.warehouse.ProductList result = port.getCatalog();
            
            DecimalFormat df = new DecimalFormat();
            df.setMinimumFractionDigits(2);
            
            %>
            <div class='section'>
                <form action="ShipGoods" method="get">
                <table border="0">
                    <tr> 
            <%
            
            for (int i = 0; i < result.getProducts().size(); i++)
            {
                %>
                <td>
                <div class='itemContainer'>
                    <h2>
                        <input type="hidden" name="productName<% out.print(i+1); %>" value="<% out.print(result.getProducts().get(i).getProductName()); %>" />
                        <%
                            out.println(result.getProducts().get(i).getProductName());
                        %>
                    </h2>
                    <h3>
                        <input type="hidden" name="productUnitPrice<% out.print(i+1); %>" value="<% out.print(result.getProducts().get(i).getUnitPrice()); %>" />
                        <%
                            out.println(df.format(result.getProducts().get(i).getUnitPrice())  + "$");
                        %>
                    </h3>
                    <h4>
                        <input type="hidden" name="productManufacturerName<% out.print(i+1); %>" value="<% out.print(result.getProducts().get(i).getManufacturerName()); %>" />
                        <%
                            out.println(result.getProducts().get(i).getManufacturerName());
                        %>
                    </h4>
                    <h5>
                        <input type="hidden" name="productType<% out.print(i+1); %>" value="<% out.print(result.getProducts().get(i).getProductType()); %>" />
                        <%
                            out.println(result.getProducts().get(i).getProductType());
                        %>
                    </h5>
                    <input type="text" value="0" name="quantity<% out.print(i+1); %>" />
                </div>
                </td>
                <%
                if (((i + 1) % 3) == 0)
                {
                %>
                    </tr>
                    <tr>
                <%
                }
            }
            %>
                    </tr>
                    </table>
                    <input type="submit" value="Order Products" />
                </form>
                </div>
            <%
        }
        catch (Exception ex)
        {
            out.println(ex.getMessage());
        }
        %>
    </body>
</html>