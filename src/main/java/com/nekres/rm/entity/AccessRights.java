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
import javax.persistence.GenerationType;
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
    @Column(name = "r")
    private int read;
    @Column(name = "w")
    private int write;


    public int getRead() {
        return read;
    }

    public void setRead(int r) {
        this.read = r;
    }

    public int getWrite() {
        return write;
    }

    public void setWrite(int w) {
        this.write = w;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.id;
        hash = 11 * hash + Objects.hashCode(this.userStorage.getStorageId());
        hash = 11 * hash + Objects.hashCode(this.userFile.getUserFileId());
        hash = 11 * hash + this.read;
        hash = 11 * hash + this.write;
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
        if (this.read != other.read) {
            return false;
        }
        if (this.write != other.write) {
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
