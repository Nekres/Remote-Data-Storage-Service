/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.service.impl;

import com.nekres.rm.dao.UserStorageDao;
import com.nekres.rm.exceptions.FileAlreadyExistException;
import com.nekres.rm.exceptions.NoSuchDirectoryException;
import com.nekres.rm.exceptions.NoSuchFileException;
import com.nekres.rm.exceptions.NoSuchStorageException;
import com.nekres.rm.pojo.UserStorage;
import com.nekres.rm.service.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author nekres
 */
@Service
public class UserStorageServiceImpl implements UserStorageService{
    private Logger logger = Logger.getLogger(UserStorageServiceImpl.class.getName());
    @Autowired
    private UserStorageDao userStorageDao;
    @Autowired
    private UserProfileService userProfileService;
    
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
        try {
            logger.info(file.getCanonicalPath());
            checkSecurity(file.getCanonicalPath(), key);
        } catch (IOException ex) {
            throw new NoSuchDirectoryException(ex.getMessage());
        }
        if(file.exists())
            throw new FileAlreadyExistException("Directory " + builder.toString() + " already exists");
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

    @Override
    public void uploadFile(MultipartFile file, String key, String targetDirectory) {
        if(!userProfileService.isKeyExist(key))
            throw new NoSuchStorageException("No storage with key " + key);
        StringBuilder builder = new StringBuilder(getPath(targetDirectory, key));
        try{
            byte bytes[] = file.getBytes();
            builder.append("/");
            builder.append(file.getOriginalFilename());
            Path path = Paths.get(builder.toString());
            checkSecurity(new File(builder.toString()).getCanonicalPath(), key);
            Files.write(path, bytes);
        }catch(IOException iox){
            iox.printStackTrace();
            throw new NoSuchDirectoryException("Failed to upload file to " + builder.toString());
        }
    }
    
    private void checkSecurity(String directory, String key){
        if(!directory.contains(key)){
            throw new NoSuchDirectoryException("Path is not correct.");
        }
    }

    @Override
    public Collection<String> explore(String key) {
        if(!userProfileService.isKeyExist(key))
            throw new NoSuchStorageException("No storage with key" + key);
        File file = new File(ROOT+key);
        Collection<String> list = new ArrayList<>();
        Collection<File> files = FileUtils.listFiles(file, null, true);
        for(File f : files){
            if(f.isDirectory()){
            list.add(f.getName() + " *dir*");
            }
            list.add(f.getName());
        }
        return list;
    }
    
    private final void tree(File[] array, ArrayList<ArrayList<String>> list){
    }

    @Override
    public void move(String sourceFile, String newFileName, String destinationFolder, String key) {
        if(!userProfileService.isKeyExist(key))
            throw new NoSuchStorageException("No storage with key " + key);
        try {
            File f = new File((getPath(sourceFile, key)));
            if(!f.exists())
                throw new NoSuchFileException("No such file");
            checkSecurity(f.getCanonicalPath(), key);
            Files.move(Paths.get(getPath(sourceFile, key)), Paths.get(getPath(destinationFolder,key) + "/" + newFileName),StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new NoSuchDirectoryException("Directory does not exist.");
        }
    }

    @Override
    public ArrayList<String> search(String file, String key) {
        if(!userProfileService.isKeyExist(key))
            throw new NoSuchStorageException("No storage with key " + key);
        ArrayList<String> list = new ArrayList<String>();
        checkDirectory(file, new File(ROOT+ "/"+ key), list);
        return list;
    }
    
    private void checkDirectory(String pattern, File file,ArrayList<String> files){
        if(file.getName().contains(pattern))
            files.add(file.getPath());
        
        if(file.isDirectory()){
            for(File f : file.listFiles()){
                checkDirectory(pattern, f, files);
                logger.info(f.getName());
            }
        }
    }


}
