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
import locVH.dao.ProfileDAO;
import locVH.dao.RegistrationDAO;
import locVH.dto.ProfileDTO;
import locVH.dto.RegistrationErrorObject;
import locVH.models.Cart;

/**
 *
 * @author LocVH
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "Error.jsp";
    private static final String ADMIN = "AdminMain.jsp";
    private static final String USER = "UserMain.jsp";
    private static final String INVALID = "Login.jsp";
    private static final String VIEWCART = "ShoppingCart.jsp";

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
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            boolean valid = true;
            RegistrationErrorObject errorObj = new RegistrationErrorObject();
            RegistrationDAO registrationDAO = new RegistrationDAO();
            ProfileDAO profileDAO = new ProfileDAO();

            if (username.equals("")) {
                valid = false;
                errorObj.setUsernameError("Username không được trống");
            }

            if (password.equals("")) {
                valid = false;
                errorObj.setPasswordError("Password không được trống");
            }

            if (valid) {
                RegistrationDAO dao = new RegistrationDAO();
                String role = dao.checkLogin(username, password);

                if (role.equals("failed")) {
                    request.setAttribute("ERROR", "Invalid Username or Password!");
                } else {                    
                    session.setAttribute("USER", username);
                    session.setAttribute("ROLE", role);
                    if (role.equals("admin")) {
                        url = ADMIN;
                    } else if (role.equals("user")) {
                        url = USER;
                        int userId = registrationDAO.findUserId(username);
                        ProfileDTO dto = profileDAO.findInfo(userId);
                        session.setAttribute("fullname", dto.getCustomerName());
                        session.setAttribute("DOB", dto.getDOB());
                        session.setAttribute("sex", dto.getSex());
                        session.setAttribute("email", dto.getEmail());
                        session.setAttribute("phoneNumber", dto.getPhoneNumber());
                        session.setAttribute("address", dto.getAddress());
                        if (session.getAttribute("CONTINUE") != null) {
                            Cart shoppingCart = (Cart) session.getAttribute("cart");
                            shoppingCart.setCustomerName(username);
                            url = VIEWCART;
                        } else {
                            url = USER;
                        }
                    }else {
                        request.setAttribute("ERROR", "Your role is invalid!");
                    }
                }
            } else {
                url = INVALID;
                request.setAttribute("INVALID", errorObj);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log("ERROR at LoginController: " + e.getMessage());
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
