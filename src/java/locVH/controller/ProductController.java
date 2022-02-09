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
import locVH.dao.CategoriesDAO;
import locVH.dao.ProductDAO;
import locVH.dto.CategoriesDTO;
import locVH.dto.ProductDTO;

/**
 *
 * @author LocVH
 */
public class ProductController extends HttpServlet {

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
            String indexPage=request.getParameter("index");
            
            if(indexPage == null){
                indexPage="1";
            }
            int index= Integer.parseInt(indexPage);
            ProductDAO productdao = new ProductDAO();
            int count = productdao.getTotalProduct();
            int endPage=count/3;
            if(count % 3!=0)
            {
                endPage++;
            }
            
            List<ProductDTO> list = productdao.chooseProductListPage(index);
                    
            CategoriesDAO category = new CategoriesDAO();                      
            List<CategoriesDTO> resultCate = category.chooseCategories();
            
            request.setAttribute("INFO", list);
            request.setAttribute("ENDPAGE", endPage);
            request.setAttribute("CATEGORY", resultCate);
        } catch (Exception e) {
            log("ERROR at Products: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("Shopping.jsp").forward(request, response);
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
