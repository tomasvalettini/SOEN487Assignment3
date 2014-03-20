package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>JSP Page</title>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <h1>Get product info</h1>\r\n");
      out.write("        ");
      out.write("<hr/>\r\n");
      out.write("        ");

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
        
      out.write("\r\n");
      out.write("        ");
      out.write("<hr/>\r\n");
      out.write("\r\n");
      out.write("        <h1>Process purchase order</h1>\r\n");
      out.write("        ");
      out.write("<hr/>\r\n");
      out.write("        ");

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
        
      out.write("\r\n");
      out.write("        ");
      out.write("<hr/>\r\n");
      out.write("\r\n");
      out.write("        <h1>Receive Payment</h1>\r\n");
      out.write("        ");
      out.write("<hr/>\r\n");
      out.write("        ");

        try {
            org.me.manufacturer.ManufacturerWS_Service service = new org.me.manufacturer.ManufacturerWS_Service();
            org.me.manufacturer.ManufacturerWS port = service.getManufacturerWSPort();
             // TODO initialize WS operation arguments here
            int orderNumber = 111;
            float totalPrice = 32000.0f;
            // TODO process result here
            boolean result = port.receivePayment(orderNumber, totalPrice);
            out.println("Result = "+result);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        
      out.write("\r\n");
      out.write("        ");
      out.write("<hr/>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
