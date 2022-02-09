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
import locVH.dto.AddNewProductErrorObject;
import locVH.dto.ProductDTO;
import locVH.models.Cart;

/**
 *
 * @author LocVH
 */
public class UserAddProductController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession();
            Cart shoppingCart = (Cart) session.getAttribute("cart");

            if (shoppingCart == null) {
                String username = (String) session.getAttribute("USER");
                if (username == null) {
                    username = "GUEST";
                }
                shoppingCart = new Cart(username);
            }
            String productId = request.getParameter("txtProductId");
            String productName = request.getParameter("txtProductName");
            float price = Float.parseFloat(request.getParameter("txtPrice"));
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));

            boolean valid = true;
            AddNewProductErrorObject error = new AddNewProductErrorObject();
            if (quantity == 0) {
                valid = false;
                error.setQuantityError("Chúng tôi xin lỗi vì đã bán hết sản phẩm này rùi");
            }
            
            if (valid) {
                if (!shoppingCart.checkProductExist(productId)) {
                    ProductDTO dto = new ProductDTO();
                    dto.setProductId(productId);
                    dto.setProductName(productName);
                    dto.setPrice(price);
                    dto.setQuantity(1);
                    shoppingCart.addToCart(dto);
                }
                session.setAttribute("cart", shoppingCart);
            } else {
                request.setAttribute("INVALID", error);
            }
        } catch (Exception e) {
            log("ERROR at UserAddHController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("ProductController").forward(request, response);
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
