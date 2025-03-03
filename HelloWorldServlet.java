import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Extending HttpServlet to create an HTTP-based servlet
public class HelloWorldServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html"); // Set response type to HTML
        PrintWriter out = response.getWriter(); // Get PrintWriter to write response
        out.println("<html><body>");
        out.println("<h2>Hello, World! This is a Java Servlet.</h2>");
        out.println("</body></html>");
    }
}