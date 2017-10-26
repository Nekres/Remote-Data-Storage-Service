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
import com.nekres.rm.entity.UserStorage;
import com.nekres.rm.pojo.response.Tuple;
import com.nekres.rm.service.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
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
        return null; //will be implemented later
    }

    @Override
    public boolean createStorage(String key) {
        String filepath = ROOT + key;
        File file = new File(filepath);
        File versions = new File(filepath + VERSIONS);
        File trash = new File(filepath + TRASH);
        return file.mkdirs() && versions.mkdirs() && trash.mkdirs();
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
        if (!userProfileService.isKeyExist(key)) {
            throw new NoSuchStorageException("No storage with key " + key);
        }
        StringBuilder builder = new StringBuilder(getPath(targetDirectory, key));
        builder.append("/");
        builder.append(file.getOriginalFilename());
        String filepath = builder.toString();
        try {
            File f = new File(filepath);
            if(f.exists()){
                if(isSame(filepath, file)){
                    throw new FileAlreadyExistException("File already has actual version");
                }
                else{
                    replaceFileVersion(targetDirectory+"/" + file.getOriginalFilename(), f.getName(), key);
                }
            }
            checkSecurity(new File(builder.toString()).getCanonicalPath(), key);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        writeFile(builder.toString(), file);
    }
    private void replaceFileVersion(String filepath, String filename, String key){
        String targetDirectory = getPath(VERSIONS, key) + "/" + filename;
        File targetDir = new File(targetDirectory);
        if(!targetDir.exists())
            targetDir.mkdir();
        move(filepath,Long.toBinaryString(new Date().getTime()), VERSIONS + "/" + filename, key);
    }
    private boolean isSame(String existingFile, MultipartFile actualFile) throws IOException{
        byte first[] = Files.readAllBytes(Paths.get(existingFile));
        byte second[] = actualFile.getBytes();
        return Arrays.equals(first, second);
    }
    private final void writeFile(String filepath, MultipartFile file){
        try{
            Path path = Paths.get(filepath);
            Files.write(path, file.getBytes());
        }catch(IOException iox){
            iox.printStackTrace();
            throw new NoSuchDirectoryException("Failed to upload file to " + filepath);
        }
    }
    private void checkSecurity(String directory, String key){
        if(!directory.contains(key)){
            throw new NoSuchDirectoryException("Path is not correct.");
        }
    }
    
    @Override
    public void move(String sourceFile, String newFileName, String destinationFolder, String key) {
        if(!userProfileService.isKeyExist(key))
            throw new NoSuchStorageException("No storage with key " + key);
        try {
            File f = new File((getPath(sourceFile, key)));
            if(!f.exists()){
                throw new NoSuchFileException("No such file");
            }
            checkSecurity(f.getCanonicalPath(), key);
            Files.move(Paths.get(getPath(sourceFile, key)), Paths.get(getPath(destinationFolder,key) + "/" + newFileName),StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new NoSuchDirectoryException("Directory does not exist.");
        }
    }

    @Override
    public Tuple search(String file, String key) {
        if(!userProfileService.isKeyExist(key))
            throw new NoSuchStorageException("No storage with key " + key);
        ArrayList<String> files = new ArrayList<String>();
        ArrayList<String> directories = new ArrayList<>();
        checkDirectory(file, new File(ROOT+ "/"+ key), files, directories);
        return new Tuple(directories,files);
    }
    
    private void checkDirectory(String pattern, File file,ArrayList<String> files, ArrayList<String> directories){
        if(file.getName().contains(pattern) && !file.isDirectory())
            files.add(file.getPath());
        if(file.isDirectory()){
            if(file.getName().contains(pattern))
                directories.add(file.getPath());
            for(File f : file.listFiles()){
                checkDirectory(pattern, f, files, directories);
            }
        }
    }
    @Override
    public boolean remove(String file, String key) {
        if(!userProfileService.isKeyExist(key))
            throw new NoSuchStorageException("No storage with key " + key);
        File f= new File(getPath(file, key));
        if(!f.exists())
            throw new NoSuchFileException("No such file");
        try {
            move(f.getCanonicalPath(), f.getName(), TRASH, key);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return f.delete();
    }

    @Override
    public boolean restore(String file, String key) {
        File fileToRestore = new File(getPath(file, key));
        if(!fileToRestore.exists())
            throw new NoSuchFileException("No such file.");
        File f = getLatestVersion(fileToRestore.getName(), key);
        fileToRestore.delete();
       // move(f.getPath(), file, VERSIONS, key);
        return false; //fileToRestore.delete() && 
    }
    
    private final File getLatestVersion(String filename, String key){
        String path = getPath(VERSIONS, key) + "/" + filename;
        logger.info(path);
        File file = new File(path);
        File[] files = file.listFiles();
        if(files.length == 0)
            return null;
        long max = Long.parseLong(files[0].getName());
        int imax = 0;
        for(int i = 0; i < files.length-1; i++){
            if(max < Long.parseLong(files[i+1].getName())){
                max = Long.parseLong(files[i+1].getName());
                imax = i;
            }
        }
        return files[imax];
    }


}
