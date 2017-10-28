/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.dao.impl;

import com.nekres.rm.dao.UserFileDao;
import com.nekres.rm.entity.UserFile;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nekres
 */
@Repository
public class UserFileDaoImpl implements UserFileDao{
    private static final Logger logger = Logger.getLogger(UserFileDaoImpl.class.getName());
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void save(UserFile userFile) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(userFile);
        logger.info("Persisting file " + userFile.toString() + " to db.");
    }

    @Override
    public void update(UserFile userFile) {
        Session session = sessionFactory.getCurrentSession();
        session.update(userFile);
        logger.info("Updating file " + userFile.toString());
    }
    
}
