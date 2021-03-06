/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.service.impl;

import com.nekres.rm.dao.UserProfileDao;
import com.nekres.rm.entity.UserProfile;
import com.nekres.rm.entity.UserStorage;
import com.nekres.rm.service.UserProfileService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nekres
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileDao profileDao;

    @Transactional
    @Override
    public void save(UserProfile profile) {
        this.profileDao.save(profile);
    }

    @Transactional
    @Override
    public boolean isKeyExist(String key) {
        return profileDao.isKeyExist(key);
    }
    @Transactional
    @Override
    public boolean isLoginBusy(String login) {
        return profileDao.isLoginBusy(login);
    }
    
    @Transactional
    @Override
    public UserProfile get(String login, String password) {
        return profileDao.get(login, password);
    }
    @Transactional
    @Override
    public int getStorageByKey(String key) {
        return profileDao.getStorageByKey(key);
    }

}
