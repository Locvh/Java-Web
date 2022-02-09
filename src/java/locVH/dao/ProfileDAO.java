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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import locVH.db.DBUtils;
import locVH.dto.ProfileDTO;

/**
 *
 * @author LocVH
 */
public class ProfileDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public ProfileDAO() {
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

    public ProfileDTO findByUserId(int userId) throws Exception {
        ProfileDTO dto = null;
        String customerName, phoneNumber,address;
        try {
            String sql = "SELECT CustomerName, PhoneNumber,Address FROM Profile WHERE UserId = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, userId);
            rs = preStm.executeQuery();
            if (rs.next()) {
                customerName = rs.getString("CustomerName");
                phoneNumber = rs.getString("PhoneNumber");
                address = rs.getString("Address");
                dto = new ProfileDTO(customerName, phoneNumber,address);
            }
        } finally {
            closeConnection();
        }

        return dto;
    }
    
    public ProfileDTO findInfo(int userId) throws Exception{
        ProfileDTO dto = null;
        String customerName, sex, DOB, email, phoneNumber,address;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sql = "SELECT CustomerName, Sex, DOB, Email, PhoneNumber,Address FROM Profile WHERE UserId = ? AND Status = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, userId);
            preStm.setBoolean(2, true);
            rs = preStm.executeQuery();
            if (rs.next()) {
                customerName = rs.getString("CustomerName");
                sex = rs.getString("Sex");
                DOB = rs.getDate("DOB").toString();
                Date date = df.parse(DOB);
                java.util.Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(GregorianCalendar.DATE, 2);
                String DOBStr = df.format(cal.getTime());
                email = rs.getString("Email");
                phoneNumber = rs.getString("PhoneNumber");
                address = rs.getString("Address");
                dto = new ProfileDTO(customerName, sex, DOBStr, email, phoneNumber,address);
            }
        } finally {
            closeConnection();
        }
        
        
        return dto;
    }

    public boolean update(ProfileDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "UPDATE Profile SET CustomerName = ?, Sex = ?, DOB = ?, "
                    + "Email = ?, PhoneNumber = ? WHERE UserId = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getCustomerName());
            preStm.setString(2, dto.getSex());
            preStm.setString(3, dto.getDOB());
            preStm.setString(4, dto.getEmail());
            preStm.setString(5, dto.getPhoneNumber());
            preStm.setInt(6, dto.getUserId());
            result = preStm.executeUpdate() > 0;

        } finally {
            closeConnection();
        }
        return result;
    }

    public List<ProfileDTO> findByLikeName(String search) throws Exception {
        List<ProfileDTO> result = null;
        ProfileDTO dto = null;
        String customerName, sex, DOB, email, phoneNumber,address;
        int  userId;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sql = "SELECT CustomerName, Sex, DOB, Email, PhoneNumber, UserId, Address FROM Profile WHERE CustomerName Like ? AND Status = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            preStm.setBoolean(2, true);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                customerName = rs.getString("CustomerName");
                sex = rs.getString("Sex");
                DOB = rs.getDate("DOB").toString();
                Date date = df.parse(DOB);
                java.util.Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(GregorianCalendar.DATE, 2);
                String DOBStr = df.format(cal.getTime());
                email = rs.getString("Email");
                phoneNumber = rs.getString("PhoneNumber");
                userId = rs.getInt("UserId");
                address = rs.getString("Address");
                dto = new ProfileDTO(customerName, sex, DOBStr, email, phoneNumber, address, userId);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean delete(int userId) throws Exception {
        boolean result = false;

        try {
            String sql = "UPDATE Profile SET Status = ? WHERE UserId = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, false);
            preStm.setInt(2, userId);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insert(ProfileDTO dto) throws Exception {
        boolean result = false;

        try {
            String sql = "INSERT INTO Profile(CustomerName, Sex, DOB, Email,PhoneNumber, UserId, Status,Address) VALUES(?,?,?,?,?,?,?,?)";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getCustomerName());
            preStm.setString(2, dto.getSex());
            preStm.setString(3, dto.getDOB());
            preStm.setString(4, dto.getEmail());            
            preStm.setString(5, dto.getPhoneNumber());
            preStm.setInt(6, dto.getUserId());
            preStm.setBoolean(7, true);
            preStm.setString(8, dto.getAddress());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean checkPhoneNumber(String phoneNumber) throws Exception {
        boolean result = false;

        try {
            String sql = "SELECT UserId FROM Profile WHERE PhoneNumber = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, phoneNumber);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

   public boolean updateInfo(ProfileDTO dto) throws Exception {
        boolean result = false;

        try {
            String sql = "UPDATE Profile SET CustomerName = ?, Sex = ?, DOB = ?, Email = ?, PhoneNumber = ?, Address = ? WHERE UserId = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getCustomerName());
            preStm.setString(2, dto.getSex());
            preStm.setString(3, dto.getDOB());
            preStm.setString(4, dto.getEmail());
            preStm.setString(5, dto.getPhoneNumber());
            preStm.setString(6, dto.getAddress());
            preStm.setInt(7, dto.getUserId());
            result = preStm.executeUpdate() > 0;

        } finally {
            closeConnection();
        }
        return result;
    }
}
