
package locVH.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import locVH.db.DBUtils;
import locVH.dto.RegistrationDTO;


/**
 *
 * @author LocVH
 */
public class RegistrationDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    public RegistrationDAO() {
    }

    private void closeConnection() throws Exception{

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
    
    public String checkLogin(String username, String password) throws Exception{
        String result = "failed";
        
        try {
            String sql = "SELECT Role FROM Registration WHERE Username = ? AND Password = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            
            if (rs.next()){
                result = rs.getString("Role");
            }
        } finally {
            closeConnection();
        }
        return result;
    }
     
   
    
    public int findUserId(String username) throws Exception{
        int result = -1;
        try {
            String sql = "SELECT UserId FROM Registration WHERE Username = ?";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1,username);
            rs = preStm.executeQuery();
            if (rs.next()){
                result = rs.getInt("UserId");
            }
        } finally {
            closeConnection();
        }
        
        return result;
    }


    
    public boolean delete(int userId) throws Exception{
        boolean result = false;
        
        try {
            String sql = "UPDATE Registration SET Status = ? WHERE UserId = ?";
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
    
    public boolean insert(RegistrationDTO dto) throws Exception{
        boolean result = false;
        
        try {
            String sql = "INSERT INTO Registration(Username, Password, Role, Status) VALUES(?,?,?,?)";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUsername());
            preStm.setString(2, dto.getPassword());
            preStm.setString(3, dto.getRole());
            preStm.setBoolean(4, true);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        
        
        return result;
    }
}
