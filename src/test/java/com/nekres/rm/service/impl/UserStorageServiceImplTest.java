/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nekres.rm.service.impl;

import com.nekres.rm.config.AppConfiguration;
import com.nekres.rm.service.UserStorageService;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author nekres
 */
@WebAppConfiguration
@RunWith(BlockJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class UserStorageServiceImplTest {
    private static final Logger logger = Logger.getLogger(UserStorageServiceImplTest.class.getName());
    
    @Test
    public void testGetPath() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Method method = UserStorageServiceImpl.class.getDeclaredMethod("getPath",String.class, String.class);
        method.setAccessible(true);
        String expectedResult = "root/test2/test1";
        Object[] params = {new String("test1"), new String("test2")};
        String result = (String)method.invoke(null,params);
        Assert.assertTrue(expectedResult.equals(result));
    }
    @Test
    public void testIsSame() throws NoSuchMethodException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Method method = UserStorageServiceImpl.class.getDeclaredMethod("isSame", String.class, MultipartFile.class);
        method.setAccessible(true);
        MultipartFile mockito = mock(MultipartFile.class);
        byte bytes[] = {127};
        when(mockito.getBytes()).thenReturn(bytes);
        Object[] params = {"testfile", mockito};
        Boolean result = (Boolean)method.invoke(new UserStorageServiceImpl(),params);
        Assert.assertTrue(result == false);
    }
    @Before
    public void setUp() throws IOException{
        File file = new File("testfile");
        file.createNewFile();
    }
    @After
    public void after(){
        File file = new File("testfile");
        file.delete();
    }
    
}
