package ai.svl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RunServlet extends HttpServlet
{
    private static final long serialVersionUID = -5864886627046557680L;

    @Override
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        Map<?, ?> parameters = request.getParameterMap();
        if (parameters.containsKey("FIND_IP")){
          forward = "/find_ip.jsp";
        } else if (parameters.containsKey("FIND_MAC")){
          forward = "/find_mac.jsp";
        } else if (parameters.containsKey("FIND_DESCR")){
          forward = "/find_descr.jsp";
        } else {
          forward = "/main.jsp";
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
      
    } 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        Map<?, ?> parameters = request.getParameterMap();
        if (parameters.containsKey("FIND_IP")){
          forward = "/find_ip.jsp";
        } else if (parameters.containsKey("FIND_MAC")){
          forward = "/find_mac.jsp";
        } else if (parameters.containsKey("FIND_DESCR")){
          forward = "/find_descr.jsp";
        } else {
          forward = "/main.jsp";
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
      
    } 

}
