
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.AccountService;


public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
                
        //getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        
         if(request.getParameter("logout") != null){
             session.invalidate();
             String logout = "You have successfully logged out.";
             request.setAttribute("logout", logout);
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return;
        }
             
             String userName = (String) session.getAttribute("username");
             
             if(userName != null) {
                 response.sendRedirect("home");
                 return;
             }
             
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return;
                
       
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String name = request.getParameter("username");
        String passwd = request.getParameter("password");
         
         
                         
        if(name == null || name.equals("") || passwd == null || passwd.equals("")){
           
            String error = "Enter a valid username and password";
            request.setAttribute("error", error);
            request.setAttribute("username", name);
            request.setAttribute("password", passwd);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        AccountService check = new AccountService();
         
        if(check.login(name, passwd) != null) {
            session.setAttribute("username", name);
            response.sendRedirect("home");
            return;
        } else {
            String invalid = "Invalid login";
            request.setAttribute("invalid", invalid);
            request.setAttribute("username", name);
            request.setAttribute("password", passwd);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        } 
    }
}
