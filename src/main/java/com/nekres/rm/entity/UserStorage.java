/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user_storage", cascade = CascadeType.ALL)
    private Set<UserFile> files = new HashSet<>(0);
    
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

    public Set<UserFile> getFiles() {
        return files;
    }

    public void setFiles(Set<UserFile> files) {
        this.files = files;
    }
    
    
    
}
