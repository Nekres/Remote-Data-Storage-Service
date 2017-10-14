/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.dao.impl;

import com.nekres.rm.dao.UserProfileDao;
import com.nekres.rm.pojo.UserProfile;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nekres
 */
public class UserProfileDaoImpl implements UserProfileDao{
    private static final Logger logger = Logger.getLogger(UserProfileDaoImpl.class.getName());
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void add(UserProfile profile) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(profile);
        logger.info("Profile saved successfully. Profile description: " + profile.toString());
    }
    
    
}
