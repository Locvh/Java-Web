/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locVH.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LocVH
 */
public class UserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "Error.jsp";
    private static final String REGIST = "UserRegistController";
    private static final String ADD = "UserAddProductController";
    private static final String UPDATE = "UserUpdateProductController";
    private static final String REMOVE = "UserDeleteProductController";
    private static final String COMPLETE = "UserCompleteOrderController";
    private static final String SHOPPING = "ProductController";
    private static final String VIEWORDER = "UserViewOrderController";
    private static final String DELETEORDER = "UserDeleteOrderController";
    private static final String UPDATE_PROFILE = "UserUpdateProfileController";
    private static final String VIEW_DETAIL = "UserViewDetailController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("ROLE") == null) {
                float discountRate = 0;
                session.setAttribute("DiscountRate", discountRate);
            }

            String role = (String) session.getAttribute("ROLE");
            if (role == null) {
                role = "";
            }
            String action = request.getParameter("action");
            if (action.equals("Regist")) {
                url = REGIST;
            } else if (action.equals("Add to cart")) {
                url = ADD;
            } else if (action.equals("Update")) {
                url = UPDATE;
            } else if (action.equals("Delete")) {
                url = REMOVE;
            } else if (action.equals("Complete")) {
                url = COMPLETE;
            } else if (action.equals("Shopping")) {
                url = SHOPPING;
            } else if (action.equals("View History")) {
                url = VIEWORDER;
            } else if (action.equals("Delete History")) {
                url = DELETEORDER;
            } else if (action.equals("Update Profile")) {
                url = UPDATE_PROFILE;
            } else if (action.equals("View Detail")) {
                url = VIEW_DETAIL;
            } else {
                request.setAttribute("ERROR", "Your action is invalid!");
            }
        } catch (Exception e) {
            log("ERROR at UserUController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
