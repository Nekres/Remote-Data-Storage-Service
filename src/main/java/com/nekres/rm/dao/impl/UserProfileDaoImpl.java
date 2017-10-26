/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.dao.impl;

import com.nekres.rm.dao.UserProfileDao;
import com.nekres.rm.exceptions.NoSuchStorageException;
import com.nekres.rm.entity.UserProfile;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nekres
 */
@Repository
public class UserProfileDaoImpl implements UserProfileDao {

    private static final Logger logger = Logger.getLogger(UserProfileDaoImpl.class.getName());
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(UserProfile profile) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(profile);
        logger.info("Profile saved successfully. Profile description: " + profile.toString());
    }

    @Override
    public boolean isKeyExist(String key) {
        return !queryBuilder("storageKey", key).isEmpty();
    }

    @Override
    public boolean isLoginBusy(String login) {
        return !queryBuilder("login", login).isEmpty();
    }

    private List<UserProfile> queryBuilder(String columm, String value) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UserProfile> criteria = criteriaBuilder.createQuery(UserProfile.class);
        Root<UserProfile> userRoot = criteria.from(UserProfile.class);

        criteria.select(userRoot);
        criteria.where(criteriaBuilder.equal(userRoot.get(columm), value));

        List<UserProfile> result = session.createQuery(criteria).getResultList();
        return result;
    }

    @Override
    public UserProfile get(String login, String password) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserProfile> criteriaQuery = builder.createQuery(UserProfile.class);
        Root<UserProfile> userRoot = criteriaQuery.from(UserProfile.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(userRoot.get("login"), login));
        predicates.add(builder.equal(userRoot.get("password"), password));

        criteriaQuery.select(userRoot).where(predicates.toArray(new Predicate[]{}));
        UserProfile profile = null;
        try {
            profile = session.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException nre) {
            throw new NoSuchStorageException("No such user");
        }
        return profile;

    }

}
