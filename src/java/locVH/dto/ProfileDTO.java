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
public class ProfileDTO implements Serializable{
    private String customerName, sex, DOB, email, phoneNumber,address;
    int  userId;


    public ProfileDTO(String customerName, String phoneNumber, String address) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public ProfileDTO(String customerName, String sex, String DOB, String email, String phoneNumber, String address, int userId) {
        this.customerName = customerName;
        this.sex = sex;
        this.DOB = DOB;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userId = userId;
    }
    
    public ProfileDTO(String customerName, String sex, String DOB, String phoneNumber, int userId) {
        this.customerName = customerName;
        this.sex = sex;
        this.DOB = DOB;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }
    
    
    public ProfileDTO(String customerName, String sex, String DOB, String email, String phoneNumber, String address) {
        this.customerName = customerName;
        this.sex = sex;
        this.DOB = DOB;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

  

    
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
}
