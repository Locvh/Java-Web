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
import locVH.dto.OrderDetailDTO;

/**
 *
 * @author LocVH
 */
public class OrderDetailDAO implements Serializable{
     private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    
    public OrderDetailDAO(){}
    
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
    public boolean insert(OrderDetailDTO dto) throws Exception{
        boolean result = false;
        
        try {
            String sql = "INSERT INTO Order_detail(ProductId,OrderId,AmountOfMoney,TotalQuantity,ProductName) VALUES(?,?,?,?,?)";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1,dto.getProductId());
            preStm.setString(2,dto.getOrderId());
            preStm.setFloat(3,dto.getAmountOfMoney());
            preStm.setInt(4,dto.getTotalQuantity());
            preStm.setString(5,dto.getProductName());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<OrderDetailDTO> findByOrderId(String orderId) throws Exception{
        List<OrderDetailDTO> result = null;
        OrderDetailDTO dto = null;
        String productId,productName;
        int quantity;
        float amountOfMonney;
        try {
            String sql = "SELECT ProductId, AmountOfMoney,TotalQuantity,ProductName FROM Order_detail WHERE OrderId = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, orderId);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()){
                productId = rs.getString("ProductId");
                quantity=rs.getInt("TotalQuantity");
                amountOfMonney = rs.getFloat("AmountOfMoney");
                productName = rs.getString("ProductName");
                dto = new OrderDetailDTO(productId,productName, quantity, amountOfMonney);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
