package com.mmall.dao.test;

import com.mmall.service.impl.TestServiceImpl;
import com.mmall.test.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class MyTest extends TestBase {

    @Autowired
    private TestServiceImpl testService;

    @Test
    public void testChildService() {
        File root = new File("/Users/yangjun/Documents/journalfile");

        File[] fs = root.listFiles();
        for (int i = 0; i < fs.length; i++) {
            System.out.println(fs[i].getAbsolutePath());
//            if (fs[i].isDirectory()) {
//                try {
//                    showAllFiles(fs[i]);
//                } catch (Exception e) {
//                }
//            }
        }
    }

    @Test
    public void test() {
        testService.call();
    }
}
