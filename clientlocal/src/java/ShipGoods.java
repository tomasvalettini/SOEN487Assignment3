import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import org.me.manufacturer.Product;
import org.me.retailer.RetailerWS_Service;
import org.me.warehouse.OrderItem;
import org.me.warehouse.OrderList;
import org.me.warehouse.ShippedList;

public class ShipGoods extends HttpServlet 
{
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/Retailer/RetailerWS.wsdl")
    private RetailerWS_Service service;
    private final int NUM_OF_PRODUCTS = 9;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        try (PrintWriter out = response.getWriter())
        {
            OrderList items = new OrderList();
            String msg = "";

            for (int i = 0; i < NUM_OF_PRODUCTS; i++)
            {
                int q = Integer.parseInt(request.getParameter("quantity"+(i+1)));

                if (q != 0)
                {
                    OrderItem oi = new OrderItem();
                    Product p = new Product();

                    p.setManufacturerName(request.getParameter("productManufacturerName"+(i+1)));
                    p.setProductName(request.getParameter("productName"+(i+1)));
                    p.setProductType(request.getParameter("productType"+(i+1)));
                    out.println(request.getParameter("productUnitPrice"+(i+1)));
                    p.setUnitPrice(Float.parseFloat(request.getParameter("productUnitPrice"+(i+1))));

                    oi.setProduct(p);
                    oi.setQuantity(q);
                    items.getItems().add(oi);
                }
            }
            
            ShippedList sl = null;

            if (!items.getItems().isEmpty())
            {
                sl = shipGoods(items);
            }

            if (sl != null && !sl.getItems().isEmpty())
            {
                for (OrderItem oi : sl.getItems())
                {
                    msg += oi.getQuantity() + " " + oi.getProduct().getProductName() + " were shipped!";
                }
            }
            else
            {
                msg += "No items were shipped :(";
            }
        
            response.sendRedirect("index.jsp");
            /* TODO output your page here. You may use following sample code. */
            out.println(msg);
            
            
        }
        catch (Exception ex)
        {
            String s = "";
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

    private ShippedList shipGoods(OrderList items)
    {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.me.retailer.RetailerWS port = service.getRetailerWSPort();
        return port.shipGoods(items);
    }
}