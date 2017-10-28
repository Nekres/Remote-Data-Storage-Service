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
@Table(name = "user_file")
public class UserFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_file_id", unique=true, nullable = false)
    private int userFileId;
    @Column(name = "name")
    private String name;
    @Column(name = "path")
    private String path;
    @OneToMany(mappedBy = "userFile")
    private Set<AccessRights> storages = new HashSet<AccessRights>(0);

    public UserFile() {
    }
    
    public int getUserFileId() {
        return userFileId;
    }

    public void setUserFileId(int userFileId) {
        this.userFileId = userFileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<AccessRights> getStorages() {
        return storages;
    }

    public void setStorages(Set<AccessRights> storages) {
        this.storages = storages;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.userFileId;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.path);
        hash = 53 * hash + Objects.hashCode(this.storages);
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
        final UserFile other = (UserFile) obj;
        if (this.userFileId != other.userFileId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (!Objects.equals(this.storages, other.storages)) {
            return false;
        }
        return true;
    }

    
    
    
}
