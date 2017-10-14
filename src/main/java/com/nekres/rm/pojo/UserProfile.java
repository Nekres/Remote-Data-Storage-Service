/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author nekres
 */
@Entity
@Table
public class UserProfile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    
    private String email;
    
    private String password;
    
    private String storageId;
    @Column(unique = true)
    private String storageKey; //used to get access to user storage
    
    public UserProfile() {
        
    }

    public UserProfile(String email, String password, String storageId, String storageKey) {
        this.email = email;
        this.password = password;
        this.storageId = storageId;
        this.storageKey = storageKey;
    }
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getStorageKey() {
        return storageKey;
    }

    public void setStorageKey(String storageKey) {
        this.storageKey = storageKey;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserProfile{" + "userId=" + userId + ", email=" + email + ", password=" + password + ", storageId=" + storageId + ", storageKey=" + storageKey + '}';
    }

    
    
}
