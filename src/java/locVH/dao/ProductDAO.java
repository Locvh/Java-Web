/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locVH.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import locVH.db.DBUtils;
import locVH.dto.ProductDTO;

/**
 *
 * @author LocVH
 */
public class ProductDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public ProductDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    
    public List<ProductDTO> chooseProductListPage(int index) throws Exception {
        List<ProductDTO> result = null;
        ProductDTO dto = null;
        String ProductName, Description, Image, ProductId;
        float Price;
        int Quantity;
        try {
            String sql = "SELECT ProductId, ProductName, Description, Image, Price, Quantity FROM Product WHERE Status = ?\n"
                    + "ORDER BY Price\n"
                    + "OFFSET ? ROW FETCH NEXT 3 ROW ONLY";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, true);
            preStm.setInt(2, (index-1)*3);
            result = new ArrayList<>();
            rs = preStm.executeQuery();
            while (rs.next()) {
                ProductId = rs.getString("ProductId");
                ProductName = rs.getString("ProductName");
                Description = rs.getString("Description");
                Image = rs.getString("Image");
                Price = rs.getFloat("Price");
                Quantity = rs.getInt("Quantity");
                dto = new ProductDTO(ProductId, ProductName, Description, Image, Price, Quantity);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;

    }

    public int getTotalProduct() throws Exception {
        try {
            String sql = "SELECT count(ProductId) AS counts FROM Product WHERE Status = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
             preStm.setBoolean(1, true);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int ProductId = rs.getInt("counts");
                return ProductId;
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

    public List<ProductDTO> chooseProductCategory(int CategoryID) throws Exception {
        List<ProductDTO> result = null;
        ProductDTO dto = null;
        String ProductName, Description, Image, ProductId;
        float Price;
        int Quantity;
        try {
            String sql = "SELECT  ProductId,ProductName, Description, Image, Price, Quantity FROM Product WHERE  CategoryID = ? AND Status = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, CategoryID);
            preStm.setBoolean(2, true);
            result = new ArrayList<>();
            rs = preStm.executeQuery();
            while (rs.next()) {
                ProductId = rs.getString("ProductId");
                ProductName = rs.getString("ProductName");
                Description = rs.getString("Description");
                Image = rs.getString("Image");
                Price = rs.getFloat("Price");
                Quantity = rs.getInt("Quantity");
                dto = new ProductDTO(ProductId, ProductName, Description, Image, Price, Quantity);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;

    }

    public List<ProductDTO> chooseProductCategoryAdmin(int index) throws Exception {
        List<ProductDTO> result = null;
        ProductDTO dto = null;
        String ProductName, Description, Image, ProductId;
        float Price;
        int Quantity, CategoryID;
        try {
            String sql = "SELECT ProductId, ProductName, Description, Image, Price, Quantity,CategoryID FROM Product WHERE Status = ?\n"
                    + "ORDER BY Price\n"
                    + "OFFSET ? ROW FETCH NEXT 4 ROW ONLY";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, true);
            preStm.setInt(2, (index-1)*4);
            result = new ArrayList<>();
            rs = preStm.executeQuery();
            while (rs.next()) {
                ProductId = rs.getString("ProductId");
                ProductName = rs.getString("ProductName");
                Description = rs.getString("Description");
                Image = rs.getString("Image");
                Price = rs.getFloat("Price");
                Quantity = rs.getInt("Quantity");
                CategoryID = rs.getInt("CategoryID");
                dto = new ProductDTO(ProductId, ProductName, Description, Image, Price, Quantity, CategoryID);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;

    }

    public int Quantity(String ProductId) throws Exception {
        int result = 0;

        try {
            String sql = "SELECT Quantity FROM Product WHERE ProductId = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, ProductId);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("Quantity");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateProductQuantity(int quantity, String productId) throws Exception {
        boolean result = false;

        try {
            String sql = "Update Product SET Quantity = ? Where ProductId = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, quantity);
            preStm.setString(2, productId);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertProduct(ProductDTO dto) throws Exception {
        boolean result = false;

        try {
            String sql = "INSERT INTO Product(ProductId,ProductName,Description,Image,Price,Quantity,Status,CategoryID) "
                    + "VALUES (?,?,?,?,?,?,?,?)";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getProductId());
            preStm.setString(2, dto.getProductName());
            preStm.setString(3, dto.getDescription());
            preStm.setString(4, dto.getImage());
            preStm.setFloat(5, dto.getPrice());
            preStm.setInt(6, dto.getQuantity());
            preStm.setBoolean(7, true);
            preStm.setInt(8, 4);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean CategoryProduct(int CategoryID, String productId) throws Exception {
        boolean result = false;
        try {
            String sql = "UPDATE Product SET CategoryID= ? WHERE ProductId= ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, CategoryID);
            preStm.setString(2, productId);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return result;
    }

    public boolean deleteProduct(String productId) throws Exception {
        boolean result = false;

        try {
            String sql = "UPDATE Product SET Status = ? WHERE ProductId = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, false);
            preStm.setString(2, productId);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return result;
    }

    public ProductDTO findByPrimaryKey(String productId) throws Exception {
        ProductDTO dto = null;
        String productName, description, imageLink;
        int quantity;
        float price;
        try {
            String sql = "SELECT ProductId, ProductName, Description, Image, Price, Quantity FROM Product WHERE ProductId LIKE ? And Status = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, productId);
            preStm.setBoolean(2, true);
            rs = preStm.executeQuery();
            while (rs.next()) {
                productId = rs.getString("ProductId");
                productName = rs.getString("ProductName");
                description = rs.getString("Description");
                imageLink = rs.getString("Image");
                price = rs.getFloat("Price");
                quantity = rs.getInt("Quantity");
                dto = new ProductDTO(productId, productName, description, imageLink, price, quantity);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<ProductDTO> findByLikeNameeProduct(String search) throws Exception {
        List<ProductDTO> result = null;
        ProductDTO dto = null;
        String productName, description, imageLink, productId;
        int quantity;
        float price;
        try {
            String sql = "SELECT ProductId, ProductName, Description, Image, Price, Quantity FROM Product WHERE ProductName Like ?  And Status = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            preStm.setBoolean(2, true);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                productId = rs.getString("ProductId");
                productName = rs.getString("ProductName");
                description = rs.getString("Description");
                imageLink = rs.getString("Image");
                price = rs.getFloat("Price");
                quantity = rs.getInt("Quantity");
                dto = new ProductDTO(productId, productName, description, imageLink, price, quantity);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateProduct(ProductDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "UPDATE Product SET ProductName = ?, Description = ?, Quantity = ?, Price = ? WHERE ProductId = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getProductName());
            preStm.setString(2, dto.getDescription());
            preStm.setInt(3, dto.getQuantity());
            preStm.setFloat(4, dto.getPrice());
            preStm.setString(5, dto.getProductId());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return result;

    }
}
