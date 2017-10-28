/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.entity;

import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author nekres
 */
@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    
    private String login;
    
    private String password;
    @OneToOne
    @JoinColumn(name = "storage_id")
    private UserStorage storage;
    @Column(unique = true)
    private String storageKey; //used to get access to user storage
    
    public UserProfile() {
        
    }

    public String getLogin() {
        return login;
    }

    public void setEmail(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public UserStorage getStorage() {
        return storage;
    }

    public void setStorage(UserStorage storage) {
        this.storage = storage;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.userId;
        hash = 37 * hash + Objects.hashCode(this.login);
        hash = 37 * hash + Objects.hashCode(this.password);
        hash = 37 * hash + Objects.hashCode(this.storage);
        hash = 37 * hash + Objects.hashCode(this.storageKey);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserProfile other = (UserProfile) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.storageKey, other.storageKey)) {
            return false;
        }
        if (!Objects.equals(this.storage, other.storage)) {
            return false;
        }
        return true;
    }
    
    

    
    
}
