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
public class ProductDTO implements Serializable{
    private String productName,description,image,productId;
    private int quantity, cateID;
    private float price;

    public ProductDTO() {
    }

    
    public ProductDTO(String productId, String productName, String description, String image, float price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;       
    }

    public ProductDTO(String productName, String productId, float price) {
        this.productName = productName;
        this.productId = productId;
        this.price = price;
    }

    public ProductDTO(String productId,String productName, String description, int quantity, float price) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;        
        this.quantity = quantity;
        this.price = price;
    }

    public ProductDTO(String productId, int cateID) {
        this.productId = productId;
        this.cateID = cateID;
    }


    public ProductDTO(String productName, String description, int quantity, float price) {
        this.productName = productName;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public ProductDTO(String productId,String productName, String description, String image,  float price, int quantity, int cateID) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.image = image; 
        this.quantity = quantity;
        this.cateID = cateID;
        this.price = price;
    }
    
    

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }
      
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

 

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
    
    
   
}
