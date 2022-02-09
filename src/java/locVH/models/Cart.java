package locVH.models;

import java.io.Serializable;
import java.util.HashMap;
import locVH.dto.ProductDTO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LocVH
 */
public class Cart implements Serializable{
    private String customerName;
    private HashMap<String,ProductDTO> shoppingCart;
    
    public Cart(){
        this.customerName = "GUEST";
        this.shoppingCart = new HashMap<>();
    }
    
    public Cart(String customerName){
        this.customerName = customerName;
        this.shoppingCart = new HashMap<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HashMap<String, ProductDTO> getShoppingCart() {
        return shoppingCart;
    }
    
    
     public void addToCart(ProductDTO dto) throws Exception{
        if(this.shoppingCart.containsKey(dto.getProductId())){
            int quantity = this.shoppingCart.get(dto.getProductId()).getQuantity() + 1;
            dto.setQuantity(quantity);
        }
        this.shoppingCart.put(dto.getProductId(), dto);
    }
    
      public void addToCategory(ProductDTO dto) throws Exception{
        this.shoppingCart.put(dto.getProductId(), dto);
    }
      
    public void updateCart(String id, int quantity) throws Exception{
        if(this.shoppingCart.containsKey(id)){
            this.shoppingCart.get(id).setQuantity(quantity);
        }
    }
    
    public float getTotal() throws Exception{
        float result = 0;
        for (ProductDTO dto : this.shoppingCart.values()) {
            result += dto.getPrice() * dto.getQuantity();
        }       
        return result;
    }
    
     public int getTotalQuantity() throws Exception{
        int result = 0;
        for (ProductDTO dto : this.shoppingCart.values()) {
            result += dto.getQuantity();
        }       
        return result;
    }
     
    public void removeCart(String id) throws Exception{
        if (this.shoppingCart.containsKey(id)){
            this.shoppingCart.remove(id);
        }
    }
    
    public boolean checkProductExist(String id) throws Exception{
        if (this.shoppingCart.containsKey(id)){
            return true;
        }
        return false;
    }

}
