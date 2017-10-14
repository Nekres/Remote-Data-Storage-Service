/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.dao.impl;

import com.nekres.rm.dao.UserStorageDao;
import com.nekres.rm.pojo.UserStorage;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nekres
 */
@Repository
public class UserStorageDaoImpl implements UserStorageDao{
    
    private static final Logger logger = Logger.getLogger(UserStorageDaoImpl.class.getName());
    
    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void add(UserStorage storage) {
        
    }

    @Override
    public UserStorage getById(int id) {
     return null;   
    }
    
    
}
