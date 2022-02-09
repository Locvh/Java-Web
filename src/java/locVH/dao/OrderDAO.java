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
import locVH.dto.OrderDTO;

/**
 *
 * @author LocVH
 */
public class OrderDAO implements Serializable{
    
     private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    
    public OrderDAO(){}
    
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
    
     public boolean insert(OrderDTO dto) throws Exception{
        boolean result = false;
        
        try {
            String sql = "INSERT INTO Orders(OrderId,CustomerName,PhoneNumber,UserId,Address,TotalPayment,Status) VALUES(?,?,?,?,?,?,?)";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getOderId());
            preStm.setString(2,dto.getCustomerName());
            preStm.setString(3,dto.getPhoneNumber());
            preStm.setInt(4,dto.getUserId());
            preStm.setString(5,dto.getAddress());
            preStm.setFloat(6, dto.getTotalPayment());
            preStm.setBoolean(7, true);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
     
    public boolean checkOrderId(String orderId) throws Exception{
        boolean result = false;
        
        try {
            String sql = "SELECT OrderId FROM Orders WHERE OrderId = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1,orderId);
            rs = preStm.executeQuery();
            if (rs.next()){
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }
  
    public List<OrderDTO> findByUserId(int userId) throws Exception{
         List<OrderDTO> result = null;
        OrderDTO dto = null;
        String orderId, customerName,  address, phoneNumber;
        float totalPayment;       
        try {
            String sql = "SELECT OrderId,CustomerName,PhoneNumber,Address,TotalPayment FROM Orders WHERE UserId = ? AND Status = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, userId);
            preStm.setBoolean(2, true);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()){
                orderId = rs.getString("OrderId");
                customerName = rs.getString("CustomerName");
                phoneNumber= rs.getString("PhoneNumber");
                address = rs.getString("Address");
                totalPayment = rs.getFloat("TotalPayment");                
                dto = new OrderDTO(orderId, customerName, phoneNumber, address, totalPayment);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
     public boolean deleteProductHistory(String orderId) throws Exception {
        boolean result = false;

        try {
            String sql = "UPDATE Orders SET Status = ? WHERE OrderId = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, false);
            preStm.setString(2, orderId);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return result;
    }
     public List<OrderDTO> loadAll() throws Exception{
        List<OrderDTO> result = null;
        OrderDTO dto = null;
        String orderId, customerName,  address, phoneNumber;
        float totalPayment;       
        try {
            String sql = "SELECT OrderId, CustomerName, PhoneNumber, Address, TotalPayment FROM Orders Where Status= ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, true);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()){
                orderId = rs.getString("OrderId");
                customerName = rs.getString("CustomerName");
                phoneNumber= rs.getString("PhoneNumber");
                address = rs.getString("Address");
                totalPayment = rs.getFloat("TotalPayment");                
                dto = new OrderDTO(orderId, customerName, phoneNumber, address, totalPayment);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
     
    
    
}
