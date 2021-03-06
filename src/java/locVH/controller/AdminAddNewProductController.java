/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locVH.controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import locVH.dao.ProductDAO;
import locVH.dto.AddNewProductErrorObject;
import locVH.dto.ProductDTO;

/**
 *
 * @author LocVH
 */
@MultipartConfig
public class AdminAddNewProductController extends HttpServlet {

    private static final String ERROR = "Error.jsp";
    private static final String SUCCESS = "AdminMain.jsp";
    private static final String INVALID = "AdminAddNewProduct.jsp";

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
            String productId = request.getParameter("txtProductId");
            String productName = request.getParameter("txtProductName");
            String description = request.getParameter("txtDescription");
            String priceStr = request.getParameter("txtPrice");
            float price = 0;
            String quantityStr = request.getParameter("txtQuantity");
            int quantity = 0;
            String imageLink = request.getParameter("txtImageLink");

            AddNewProductErrorObject errorObj = new AddNewProductErrorObject();
            boolean valid = true;

            if (!productId.matches("NT-[A-Z]{3}-[0-9]{3}")) {
                valid = false;
                errorObj.setProductIdError("M?? s???n ph???m NT-AAA-000 ( trong ???? AAA l?? 3 ch??? hoa b???t k?? v?? 000 l?? 3 s??? b???t k?? )");
            }

            if (productName.length() == 0) {
                valid = false;
                errorObj.setProductNameError("T??n s???n ph???m kh??ng ???????c ????? tr???ng!");
            }
            if (description.length() == 0) {
                valid = false;
                errorObj.setDescriptionError("M?? t??? kh??ng ???????c ????? tr???ng!");
            }
            try {
                price = Float.parseFloat(priceStr);
                if (price < 1) {
                    valid = false;
                    errorObj.setPriceError("Gi?? l???n h??n 1");
                }
            } catch (Exception e) {
                valid = false;
                errorObj.setPriceError("Gi?? s???n ph???m kh??ng ???????c ????? tr???ng v?? v?? ph???i l?? s???");
            }

            try {
                quantity = Integer.parseInt(quantityStr);
                if (quantity < 1) {
                    valid = false;
                    errorObj.setQuantityError("S??? l?????ng l???n h??n 1");
                }
            } catch (Exception e) {
                valid = false;
                errorObj.setQuantityError("S??? l?????ng  s???n ph???m kh??ng ???????c ????? tr???ng v?? v?? ph???i l?? s???");
            }

            if (!imageLink.contains(".jpg") && !imageLink.contains(".png")) {
                valid = false;
                errorObj.setImageError("Ch??? h??? tr??? .jpg & .png");
            }

            if (valid) {
                ProductDAO roomDAO = new ProductDAO();
                ProductDTO dto = new ProductDTO(productId, productName, description, imageLink, price, quantity);
                if (roomDAO.insertProduct(dto)) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Th??m ph??ng th???t b???i!");
                }
            } else {
                request.setAttribute("INVALID", errorObj);
                url = INVALID;
            }

        } catch (Exception e) {
            log("ERROR at AdminAddNewProductsController: " + e.getMessage());
            if (e.getMessage().contains("duplicate")) {
                AddNewProductErrorObject errorObj = new AddNewProductErrorObject();
                errorObj.setProductIdError("M?? s???n ph???m n??y ???? t???n t???i!");
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
