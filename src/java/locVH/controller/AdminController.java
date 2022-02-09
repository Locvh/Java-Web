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
public class AdminController extends HttpServlet {

    private static final String ERROR = "Error.jsp";
    private static final String SEARCH_USER = "AdminSearchController";
    private static final String DELETE_USER = "AdminDeleteUserController";    
    private static final String VIEW_HISTORY = "AdminViewHistoryController";   
    private static final String VIEW_DETAIL = "AdminViewDetailController";
    private static final String ADD_NEW_PRODUCT = "AdminAddNewProductController";   
    private static final String SEARCH_PRODUCT = "AdminSearchProductController";
    private static final String EDIT = "AdminEditProductController";  
    private static final String DELETE_PRODUCT = "AdminDeleteProductController";   
    private static final String UPDATE_PRODUCT = "AdminUpdateProductsController";
    private static final String DELETE_HISTORY = "AdminDeleteHistoryController";
    private static final String ADD_NEW_CATEGORY = "AdminAddNewCategoryController";
    private static final String CATEGORY = "AdminCategoryController";
    private static final String REMOVE = "AdminDeleteProductCateroryController";
    private static final String COMPLETECATEGORY = "AdminCompleteCategoryController";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
       try {
            HttpSession session = request.getSession();
            String role = (String) session.getAttribute("ROLE");
            if (role == null) {
                role = "";
            }
            String action = request.getParameter("action");
            if (action.equals("Search User")  && role.equals("admin")) {
                url = SEARCH_USER;
            } else if (action.equals("Delete User") && role.equals("admin")) {
                url = DELETE_USER;
            }else if (action.equals("Load History") && role.equals("admin")) {
                url = VIEW_HISTORY;
            } else if (action.equals("Delete Product") && role.equals("admin")) {
                url = DELETE_PRODUCT;
            } else if (action.equals("Edit") && role.equals("admin")) {
                url = EDIT;
            } else if (action.equals("Add New") && role.equals("admin")) {
                url = ADD_NEW_PRODUCT;                
            } else if (action.equals("View Detail") && role.equals("admin")) {
                url = VIEW_DETAIL;
            } else if (action.equals("Update Product") && role.equals("admin")) {
                url = UPDATE_PRODUCT;
            } else if (action.equals("Search Product") && role.equals("admin")) {
                url = SEARCH_PRODUCT;
            } else if (action.equals("Delete History") && role.equals("admin")) {
                url = DELETE_HISTORY;
            } else if (action.equals("Add to category") && role.equals("admin")) {
                url = ADD_NEW_CATEGORY;           
            } else if (action.equals("category") && role.equals("admin")) {
                url = CATEGORY;
            }else if (action.equals("Delete")&& role.equals("admin")) {
                url = REMOVE;
            } else if (action.equals("Complete")&& role.equals("admin")) {
                url = COMPLETECATEGORY;
            }
            else {
                request.setAttribute("ERROR", "Your action is invalid!");
            }

        } catch (Exception e) {
            log("ERROR at AdminController: " + e.getMessage());
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
