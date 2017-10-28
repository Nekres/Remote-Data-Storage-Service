/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.entity;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author nekres
 */
@Entity
@Table(name = "access_rights")
public class AccessRights {
    @Id
    @GeneratedValue
    @Column(name = "access_rights_id")
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_storage_id")
    private UserStorage userStorage;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_file_id")
    private UserFile userFile;
    private int r;
    private int w;


    public int getRead() {
        return r;
    }

    public void setRead(int r) {
        this.r = r;
    }

    public int getWrite() {
        return w;
    }

    public void setWrite(int w) {
        this.w = w;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.userStorage);
        hash = 11 * hash + Objects.hashCode(this.userFile);
        hash = 11 * hash + this.r;
        hash = 11 * hash + this.w;
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
        final AccessRights other = (AccessRights) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.r != other.r) {
            return false;
        }
        if (this.w != other.w) {
            return false;
        }
        if (!Objects.equals(this.userStorage, other.userStorage)) {
            return false;
        }
        if (!Objects.equals(this.userFile, other.userFile)) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserStorage getUserStorage() {
        return userStorage;
    }

    public void setUserStorage(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public UserFile getUserFile() {
        return userFile;
    }

    public void setUserFile(UserFile userFile) {
        this.userFile = userFile;
    }

    
    
}
