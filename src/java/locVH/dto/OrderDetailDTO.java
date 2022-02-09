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
public class OrderDetailDTO implements Serializable{
    String productId, orderId,productName;
    int totalQuantity;
    float amountOfMoney;


    public OrderDetailDTO(String productId, String orderId, String productName, float amountOfMoney, int totalQuantity) {
        this.productId = productId;
        this.orderId = orderId;
        this.productName = productName;
        this.amountOfMoney = amountOfMoney;
        this.totalQuantity = totalQuantity;
           }

    
    public OrderDetailDTO(String productId, String productName, int totalQuantity, float amountOfMoney) {
        this.productId = productId;
        this.productName = productName;
        this.totalQuantity = totalQuantity;
        this.amountOfMoney = amountOfMoney;
    }

    
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public float getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(float amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    
}
