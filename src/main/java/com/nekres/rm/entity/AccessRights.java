/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 *
 * @author nekres
 */
@Entity
@Table(name = "access_rights")
@AssociationOverrides({
    @AssociationOverride(name = "pk.user_storage", 
            joinColumns = @JoinColumn(name = "storage_id")),
    @AssociationOverride(name = "pk.user_file",
            joinColumns = @JoinColumn(name = "user_file_id"))
})
public class AccessRights {
    @EmbeddedId
    private AccessRightsID pk = new AccessRightsID();
    
    @Column(name = "read")
    private int read;
    @Column(name = "write")
    private int write;

    public AccessRightsID getPk() {
        return pk;
    }

    public void setPk(AccessRightsID pk) {
        this.pk = pk;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getWrite() {
        return write;
    }

    public void setWrite(int write) {
        this.write = write;
    }
    
}
