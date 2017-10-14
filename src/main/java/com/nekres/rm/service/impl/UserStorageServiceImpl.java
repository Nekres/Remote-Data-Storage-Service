/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.service.impl;

import com.nekres.rm.dao.UserStorageDao;
import com.nekres.rm.pojo.UserStorage;
import com.nekres.rm.service.UserStorageService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author nekres
 */
@Service
public class UserStorageServiceImpl implements UserStorageService{
    private Logger logger = Logger.getLogger(UserStorageServiceImpl.class.getName());
    @Autowired
    private UserStorageDao userStorageDao;
    @Override
    public void save(UserStorage storage) {
        
    }

    @Override
    public void update(UserStorage storage) {
        
    }

    @Override
    public void delete(UserStorage storage) {
        
    }

    @Override
    public UserStorage findById(int id) {
        return null;
    }

    @Override
    public String toString() {
        return "UserStorageServiceImpl{" + '}';
    }
    
}
