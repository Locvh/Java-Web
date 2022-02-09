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
import locVH.dao.ProductDAO;
import locVH.dto.ProductDTO;
import locVH.dto.ProductErrorObject;
import locVH.models.Cart;

/**
 *
 * @author LocVH
 */
public class UserUpdateProductController extends HttpServlet {

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
        try {
            HttpSession session = request.getSession();
            Cart shoppingCart = (Cart) session.getAttribute("cart");
            String[] listId = request.getParameterValues("txtId");
            String[] listQuantity = request.getParameterValues("txtQuantity");
            ProductDAO productDAO = new ProductDAO();
            ProductErrorObject errorObj = new ProductErrorObject();
           

            for (int i = 0; i < listId.length; i++) {
                for (ProductDTO dto : shoppingCart.getShoppingCart().values()) {
                    String productId = dto.getProductId();
                    int QuantityInWarehouse = productDAO.Quantity(productId);
                    int quantity = Integer.parseInt(listQuantity[i]);                
                        if (QuantityInWarehouse >= quantity) {
                            shoppingCart.updateCart(listId[i], quantity);
                            session.setAttribute("cart", shoppingCart);
                        } else {
                            errorObj.setQuantityError("Số lượng bạn mua đã vượt quá kho");
                            request.setAttribute("INVALID", errorObj);
                        }
                }
            }
        } catch (Exception e) {
            log("ERROR at UserUpdateProductCController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("ShoppingCart.jsp").forward(request, response);
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
