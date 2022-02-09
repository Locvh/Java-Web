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
public class ProductErrorObject implements Serializable{
    String quantityError;

    public ProductErrorObject() {
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }
 
}
