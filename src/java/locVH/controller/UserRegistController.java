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
import locVH.dao.ProfileDAO;
import locVH.dao.RegistrationDAO;
import locVH.dto.ProfileDTO;
import locVH.dto.RegistrationDTO;
import locVH.dto.RegistrationErrorObject;

/**
 *
 * @author LocVH
 */
public class UserRegistController extends HttpServlet {

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
    private static final String SUCCESS = "Login.jsp";
    private static final String INVALID = "UserRegister.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String confirm = request.getParameter("txtConfirm");
            String fullname = request.getParameter("txtFullname");
            String gmail = request.getParameter("txtMail");
            String phoneNumber = request.getParameter("txtPhoneNumber");
            String gender = request.getParameter("txtGender");
            String DOB = request.getParameter("txtDOB");
            String address = request.getParameter("txtAddress");
            
            ProfileDAO profileDAO = new ProfileDAO();
            
            RegistrationErrorObject errorObj = new RegistrationErrorObject();
            boolean valid = true;

            if (!username.matches("[A-Za-z0-9]{3,30}")) {
                valid = false;
                errorObj.setUsernameError("Tên tài khoản phải từ 3->30 kí tự.");
            }

            if (password.length() == 0 || password.length() > 30) {
                valid = false;
                errorObj.setPasswordError("Mật khẩu không thể để trống hoặc dài hơn 30 ký tự.");
            }

            if (!confirm.equals(password)) {
                valid = false;
                errorObj.setConfirmError("Xác nhận mật khẩu phải khớp với mật khẩu");
            }

            if (!fullname.matches("[A-Za-z[ ]]{1,50}")) {
                valid = false;
                errorObj.setFullnameError("Tên không được để trống hoặc lớn hơn 50 ký tự.");
            }     
                  
            if (!phoneNumber.matches("[0-9]{10}")){
                valid = false;
                errorObj.setPhoneNumberError("Số điện thoại bao gồm 10 chữ số.");
            } else if (profileDAO.checkPhoneNumber(phoneNumber)){
                  valid = false;
                  errorObj.setPhoneNumberError("Số điện thoại này đã được sử dụng.");
            }

             if (!gmail.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$")) {
                valid = false;
                errorObj.setEmailError("abcd@abcd.domain (các ký tự theo sau là dấu @, theo sau là các ký tự khác và sau đó là \".\". Sau dấu \".\", Bạn chỉ có thể viết 2 đến 3 chữ cái từ a đến z.");
            } 
             
            if (address.length()==0) {
                valid = false;
                errorObj.setAddressError("địa chỉ không được trống");
            }
             
              if (valid) {
                RegistrationDAO registrationDAO = new RegistrationDAO();
                RegistrationDTO registrationDTO = new RegistrationDTO(username, password, "user");
                if (registrationDAO.insert(registrationDTO)) {
                    int UserId = registrationDAO.findUserId(username);
                    ProfileDTO profileDTO = new ProfileDTO(fullname, gender, DOB, gmail ,phoneNumber, address , UserId);
                    if (profileDAO.insert(profileDTO)){
                         url = SUCCESS;
                    }  
                } else {
                    request.setAttribute("ERROR", "Đăng ký tài khoản thất bại!");
                }

            }
            else {
                request.setAttribute("INVALID", errorObj);
                url = INVALID;
            }

        } catch (Exception e) {
            log("ERROR at RegistOController: " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                RegistrationErrorObject errorObj = new RegistrationErrorObject();
                errorObj.setUsernameError("Username has already exist!");
                request.setAttribute("INVALID", errorObj);
                url = INVALID;
            }
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
