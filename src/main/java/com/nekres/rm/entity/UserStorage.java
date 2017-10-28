/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author nekres
 */
@Entity
@Table(name = "user_storage")
public class UserStorage {
    @Id
    @Column(name = "user_storage_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storageId;
    
    private String mountPoint;
    @OneToMany(mappedBy = "userStorage")
    private Set<AccessRights> files = new HashSet<>(0);
    
    public int getStorageId() {
        return storageId;
    }
    
    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public String getMountPoint() {
        return mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }

    public Set<AccessRights> getFiles() {
        return files;
    }

    public void setFiles(Set<AccessRights> files) {
        this.files = files;
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.storageId;
        hash = 79 * hash + Objects.hashCode(this.mountPoint);
        hash = 79 * hash + Objects.hashCode(this.files);
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
        final UserStorage other = (UserStorage) obj;
        if (this.storageId != other.storageId) {
            return false;
        }
        if (!Objects.equals(this.mountPoint, other.mountPoint)) {
            return false;
        }
        if (!Objects.equals(this.files, other.files)) {
            return false;
        }
        return true;
    }

    

    
    
}
