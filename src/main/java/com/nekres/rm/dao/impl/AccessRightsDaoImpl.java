/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.dao.impl;

import com.nekres.rm.dao.AccessRightsDao;
import com.nekres.rm.entity.AccessRights;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nekres
 */
@Repository
public class AccessRightsDaoImpl implements AccessRightsDao{
    private static final Logger logger = Logger.getLogger(AccessRightsDaoImpl.class.getName());
    private SessionFactory sessionFactory;
    @Autowired
    public void setSession(SessionFactory session) {
        this.sessionFactory = session;
    }
    @Override
    public void save(AccessRights accessRights) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(accessRights);
        logger.info("Saving accessRights " + accessRights.toString());
    }

    @Override
    public void update(AccessRights accessRights) {
        Session session = sessionFactory.getCurrentSession();
        session.update(accessRights);
        logger.info("Updating accessRights" + accessRights.toString());
    }
    
}
