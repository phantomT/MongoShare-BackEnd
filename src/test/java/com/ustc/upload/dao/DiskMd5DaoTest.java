package com.ustc.upload.dao;


import com.ustc.Application;
import com.ustc.entity.DiskMd5;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DiskMd5DaoTest {

    @Autowired
    private DiskMd5Dao diskMd5Dao;

    @Test
    public void insertTest() {
        DiskMd5 diskMd5 = new DiskMd5();
        diskMd5.setFileName("test");
        diskMd5.setFileMd5("test-test-test-test-md5");
        diskMd5.setFileNum(10);
        diskMd5.setFileSize(10000L);
        diskMd5.setFileSuffix("png");
        diskMd5.setTypeCode("1");

        diskMd5Dao.insertOne(diskMd5);
    }

    @Test
    public void existTest() {
        Assert.assertTrue(diskMd5Dao.findMd5IsExist("test-test-test-test-md5"));
    }
}
