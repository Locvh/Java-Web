/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locVH.dto;

import java.io.Serializable;

/**
 *
 * @author LocVH
 */
public class CategoriesDTO implements Serializable {

    private String categoryName;
    private int categoryID;

    public CategoriesDTO() {
    }

    public CategoriesDTO( int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;        
    }



    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
