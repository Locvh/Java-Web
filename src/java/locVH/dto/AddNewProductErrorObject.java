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
public class AddNewProductErrorObject implements Serializable{
    private String productNameError,descriptionError,imageError,productIdError,quantityError,priceError;

    public AddNewProductErrorObject() {
    }

    public String getProductIdError() {
        return productIdError;
    }

    public void setProductIdError(String productIdError) {
        this.productIdError = productIdError;
    }

    
    public String getProductNameError() {
        return productNameError;
    }

    public void setProductNameError(String productNameError) {
        this.productNameError = productNameError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getImageError() {
        return imageError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    
    
}
