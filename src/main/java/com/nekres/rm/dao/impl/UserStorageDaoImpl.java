/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.dao.impl;

import com.nekres.rm.dao.UserStorageDao;
import com.nekres.rm.entity.UserStorage;
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
public class UserStorageDaoImpl implements UserStorageDao{
    
    private static final Logger logger = Logger.getLogger(UserStorageDaoImpl.class.getName());
    
    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void save(UserStorage storage) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(storage);
        logger.info("Saving " + storage.toString());
    }

    @Override
    public UserStorage getById(int id) {
     return null;   
    }

    @Override
    public void update(UserStorage storage) {
        Session session = sessionFactory.getCurrentSession();
        session.update(storage);
        logger.info("Updating " + storage.toString());
    }

    @Override
    public void delete(UserStorage storage) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(storage);
        logger.info("Deleting " + storage.toString());
    }
    
    
}
