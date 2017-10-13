/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.pojo;

/**
 *
 * @author nekres
 */
public class Client {
    
    private String email;
    
    private String password;
    
    private String storageId;
    
    private String mountPoint;

    public Client() {
    }
    
    
    public Client(String email, String password, String storageId, String mountPoint) {
        this.email = email;
        this.password = password;
        this.storageId = storageId;
        this.mountPoint = mountPoint;
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

    public String getMountPoint() {
        return mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }
    
    
}
