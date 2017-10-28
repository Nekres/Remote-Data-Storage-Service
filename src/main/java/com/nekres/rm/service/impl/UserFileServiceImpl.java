/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.service.impl;

import com.nekres.rm.dao.UserFileDao;
import com.nekres.rm.entity.UserFile;
import com.nekres.rm.service.UserFileService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author nekres
 */
public class UserFileServiceImpl implements UserFileService{
    @Autowired
    private UserFileDao userFileDao;
    
    @Override
    public void save(UserFile userFile) {
        userFileDao.save(userFile);
    }

    @Override
    public void update(UserFile userFile) {
    }
    
}
