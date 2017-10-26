/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.entity;

import java.util.HashSet;
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
    @Column(name = "file_owner")
    private UserStorage fileOwner;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "pk.user_file", cascade = CascadeType.ALL)
    private Set<UserStorage> readAccess = new HashSet<UserStorage>(0);
    private Set<UserStorage> writeAccess = new HashSet<UserStorage>(0);

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

    public Set<UserStorage> getReadAccess() {
        return readAccess;
    }

    public void setReadAccess(Set<UserStorage> readAccess) {
        this.readAccess = readAccess;
    }
    
    
}
