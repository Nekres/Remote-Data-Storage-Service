/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.service.impl;

import com.nekres.rm.dao.UserStorageDao;
import com.nekres.rm.pojo.UserStorage;
import com.nekres.rm.service.UserStorageService;
import java.io.File;
import java.util.logging.Logger;
import javax.transaction.Transactional;
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
    @Transactional
    @Override
    public void save(UserStorage storage) {
        userStorageDao.save(storage);
    }
    @Transactional
    @Override
    public void update(UserStorage storage) {
        userStorageDao.update(storage);
    }
    @Transactional
    @Override
    public void delete(UserStorage storage) {
        userStorageDao.delete(storage);
    }
    @Transactional
    @Override
    public UserStorage findById(int id) {
        return null;
    }

    @Override
    public String toString() {
        return "UserStorageServiceImpl{" + '}';
    }

    @Override
    public boolean createStorage(String key) {
        File file = new File(ROOT +key);
        return file.mkdirs();
    }
    
}
