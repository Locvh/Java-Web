/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locVH.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locVH.dao.ProfileDAO;
import locVH.dao.RegistrationDAO;
import locVH.dto.ProfileDTO;

/**
 *
 * @author LocVH
 */
public class AdminDeleteUserController extends HttpServlet {

    
    private static final String ERROR = "Error.jsp";
    private static final String SUCCESS = "AdminUpdateUser.jsp";
    
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
            int userId = Integer.parseInt(request.getParameter("id"));
            ProfileDAO profileDAO = new ProfileDAO();

            if (profileDAO.delete(userId)) {
                RegistrationDAO registrationDAO = new RegistrationDAO();
                if (registrationDAO.delete(userId)) {
                    url = SUCCESS;
                    String search = request.getParameter("txtSearch");
                    ProfileDAO dao = new ProfileDAO();
                    List<ProfileDTO> list = dao.findByLikeName(search);
                    request.setAttribute("txtSearch", search);
                    request.setAttribute("INFO_SEARCH_USER", list);
                }
            } else {
                request.setAttribute("ERROR", "Xóa thất bại!");
            }

        } catch (Exception e) {
            log("ERROR at AdminDeleteUserController: " + e.getMessage());
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
