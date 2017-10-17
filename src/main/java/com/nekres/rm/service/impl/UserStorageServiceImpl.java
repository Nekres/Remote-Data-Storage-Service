/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.service.impl;

import com.nekres.rm.dao.UserStorageDao;
import com.nekres.rm.exceptions.FileAlreadyExistException;
import com.nekres.rm.exceptions.NoSuchFileException;
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
    public boolean createStorage(String key) {
        File file = new File(ROOT +key);
        return file.mkdirs();
    }
    @Override
    public boolean mkdir(final String directory, final String key) {
        StringBuilder builder = new StringBuilder(ROOT);
        builder.append(key);
        builder.append("/" + directory);
        File file = new File(builder.toString());
        if(file.exists())
            throw new FileAlreadyExistException();
        file.mkdir();
        return true;
    }
    
    @Override
    public String toString() {
        return "UserStorageServiceImpl{" + '}';
    }

    @Override
    public boolean rename(String key, String oldFilePath, String newFilePath) {
        String fileName = getPath(oldFilePath, key);
        String newFileName = getPath(newFilePath, key);
        File file = new File(fileName);
        if(!file.exists())
            throw new NoSuchFileException("File \"" + fileName + "\" does not exist.");
        return file.renameTo(new File(newFileName));
    }
    
    private static final String getPath(final String filepath, String key){
        StringBuilder builder = new StringBuilder(ROOT);
        builder.append(key).append("/").append(filepath);
        return builder.toString();
    }


}
