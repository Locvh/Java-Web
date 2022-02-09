/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locVH.controller;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import locVH.dao.OrderDAO;
import locVH.dao.OrderDetailDAO;
import locVH.dao.ProductDAO;
import locVH.dao.ProfileDAO;
import locVH.dao.RegistrationDAO;
import locVH.dto.OrderDTO;
import locVH.dto.OrderDetailDTO;
import locVH.dto.ProductDTO;
import locVH.dto.ProfileDTO;
import locVH.models.Cart;

/**
 *
 * @author LocVH
 */
public class UserCompleteOrderController extends HttpServlet {

    private static final String ERROR = "Error.jsp";
    private static final String SUCCESS = "UserMain.jsp";
    private static final String LOGIN = "Login.jsp";
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

            if (session.getAttribute("ROLE") == null) {
                url = LOGIN;
                session.setAttribute("CONTINUE", "LoginToContinue");
            } else {
                Cart shoppingCart = (Cart) session.getAttribute("cart");
                if (shoppingCart.getTotal() != 0) {
                    String productId,productName;
                    float amountOfMoney;
                    int amountOfQuantity;
                    int RemainingAmount;

                    String username = (String) session.getAttribute("USER");
                    RegistrationDAO regDAO = new RegistrationDAO();
                    int userId = regDAO.findUserId(username);                   
                    ProfileDAO profileDAO = new ProfileDAO();
                    ProfileDTO profileDTO = profileDAO.findByUserId(userId);
                    String customerName = profileDTO.getCustomerName();
                    String address= profileDTO.getAddress();
                    String phoneNumber = profileDTO.getPhoneNumber();
                    float TotalPayment = shoppingCart.getTotal();
                    String orderId;
                    boolean check = true;
                    OrderDAO orderDAO = new OrderDAO();                    
                    do {
                        orderId = UUID.randomUUID().toString().substring(0,5).toUpperCase();
                        check = orderDAO.checkOrderId(orderId);
                    } while (check == true);
                    OrderDTO orderDTO = new OrderDTO(orderId, customerName, phoneNumber, address, userId,TotalPayment);
                    if (orderDAO.insert(orderDTO)) {
                        OrderDetailDTO orderDetailDTO = null;
                        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                        ProductDAO productDAO = new ProductDAO();
                        for (ProductDTO dto : shoppingCart.getShoppingCart().values()) {
                            productId = dto.getProductId(); 
                            productName=dto.getProductName();
                            amountOfMoney = dto.getQuantity()*dto.getPrice();
                            amountOfQuantity=dto.getQuantity();
                            int QuantityInWarehouse=productDAO.Quantity(productId);                          
                            RemainingAmount= QuantityInWarehouse-amountOfQuantity;                            
                            orderDetailDTO = new OrderDetailDTO(productId, orderId,productName, amountOfMoney, amountOfQuantity);
                            orderDetailDAO.insert(orderDetailDTO);                           
                            productDAO.updateProductQuantity(RemainingAmount,productId);
                        }
                    }
                    shoppingCart.getShoppingCart().clear();
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", " Bạn chưa mua gì cả nào cả!");
                }
            }
        } catch (Exception e) {
            log("ERROR at UserCompleteUOrderController: " + e.getMessage());
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
