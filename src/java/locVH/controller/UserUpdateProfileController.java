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

/**
 *
 * @author LocVH
 */
public class UserUpdateProfileController extends HttpServlet {

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
    private static final String SUCCESS = "UserUpdateProfile.jsp";
    private static final String INVALID = "UserUpdateProfile.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String fullname = request.getParameter("txtFullname");
            String DOB = (String) request.getParameter("txtDOB");
            String sex = request.getParameter("txtGender");
            String email = request.getParameter("txtEmail");
            String phoneNumber = request.getParameter("txtPhoneNumber");
            String address = request.getParameter("txtAddress");

            RegistrationDAO registrationDAO = new RegistrationDAO();
            int userId = registrationDAO.findUserId((String) session.getAttribute("USER"));
            ProfileDAO profileDAO = new ProfileDAO();

            RegistrationErrorObject errorObj = new RegistrationErrorObject();
            boolean valid = true;

            if (!fullname.matches("[A-Za-z[ ]]{3,50}")) {
                valid = false;
                errorObj.setFullnameError("Tên phải từ 3->50 kí tự!");
            }

            if (!phoneNumber.matches("[0-9]{10}")) {
                valid = false;
                errorObj.setPhoneNumberError("Số điện thoại bao gồm 10 chữ số.");
            }

             if (email.length() == 0) {
                valid = false;
                errorObj.setEmailError("email không được trống");
            }
             
            if (address.length() == 0) {
                valid = false;
                errorObj.setAddressError("địa chỉ không được trống");
            }

            if (valid) {
                ProfileDTO dto = new ProfileDTO(fullname, sex, DOB, email, phoneNumber, address, userId);
                if (profileDAO.update(dto)) {
                    url = SUCCESS;
                    session.setAttribute("fullname", fullname);
                    session.setAttribute("DOB", DOB);
                    session.setAttribute("sex", sex);
                    session.setAttribute("email", email);
                    session.setAttribute("phoneNumber", phoneNumber);
                    session.setAttribute("address", address);
                } else {
                    request.setAttribute("ERROR", "Cập nhật thông tin cá nhân thất bại!");
                }
            } else {
                request.setAttribute("INVALID", errorObj);
                url = INVALID;
            }

        } catch (Exception e) {
            log("ERROR at UserUpdateProfileController: " + e.getMessage());
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
