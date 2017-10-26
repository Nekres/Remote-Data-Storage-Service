/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author nekres
 */
@Embeddable
public class AccessRightsID implements java.io.Serializable{
    @ManyToOne
    private UserFile file;
    @ManyToOne
    private UserStorage storage;

    public UserFile getFile() {
        return file;
    }

    public void setFile(UserFile file) {
        this.file = file;
    }

    public UserStorage getStorage() {
        return storage;
    }

    public void setStorage(UserStorage storage) {
        this.storage = storage;
    }
    
    
    
}
