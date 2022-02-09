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
import locVH.dto.CategoriesDTO;

/**
 *
 * @author LocVH
 */
public class CategoriesDAO implements Serializable{
     
     private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    
    public CategoriesDAO(){}
    
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
     
       public List<CategoriesDTO> chooseCategories() throws Exception {
        List<CategoriesDTO> result = null;
        CategoriesDTO dto = null;
        String CategoryName;
        int CategoryID;
        try {
            String sql = "SELECT TOP 3 CategoryID, CategoryName FROM Category";
            conn = DBUtils.getMyConnection();
            preStm = conn.prepareStatement(sql);
            result = new ArrayList<>();
            rs = preStm.executeQuery();
            while (rs.next()) {
               CategoryID = rs.getInt("CategoryID");
               CategoryName = rs.getString("CategoryName");
               dto = new CategoriesDTO(CategoryID,CategoryName);
               result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;

    }
                
       
}
