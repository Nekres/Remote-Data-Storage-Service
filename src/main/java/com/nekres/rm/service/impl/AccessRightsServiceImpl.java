/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.service.impl;

import com.nekres.rm.dao.AccessRightsDao;
import com.nekres.rm.entity.AccessRights;
import com.nekres.rm.service.AccessRightsService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nekres
 */
@Service
public class AccessRightsServiceImpl implements AccessRightsService{
    @Autowired private AccessRightsDao accessRightsDao;
    @Transactional
    @Override
    public void save(AccessRights accessRights) {
        accessRightsDao.save(accessRights);
    }
    @Transactional
    @Override
    public void update(AccessRights accessRights) {
        accessRightsDao.update(accessRights);
    }

    @Override
    public void setRW(int read, int write) {
        
    }
    
}
