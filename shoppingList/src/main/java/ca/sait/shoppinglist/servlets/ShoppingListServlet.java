package ca.sait.shoppinglist.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kayleen
 */
public class ShoppingListServlet extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getQueryString();
        HttpSession session = request.getSession();
        String name = (String)session.getAttribute("name");
        if(name==null ||name.isBlank()){
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        } else if (query!=null && query.contains("logout")){
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
        else {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        }
        
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if(action!=null && session.getAttribute("name")==null){
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        } else if (action!=null && action.equals("add")){
            String item = request.getParameter("item");
            ArrayList<String> itemList = (ArrayList<String>)session.getAttribute("itemList");
            itemList.add(item);
            session.setAttribute("itemList", itemList);
        } else if (action!=null && action.equals("delete")){
            String item = request.getParameter("item");
            ArrayList<String> itemList = (ArrayList<String>)session.getAttribute("itemList");
            itemList.remove(item);
            session.setAttribute("itemList", itemList);
        }
        else {
            String name = request.getParameter("name");
            session.setAttribute("name", name);
            ArrayList<String> itemList = new ArrayList<>();
            session.setAttribute("itemList", itemList);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
    }

}
