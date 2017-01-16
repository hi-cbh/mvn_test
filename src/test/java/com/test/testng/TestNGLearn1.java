package com.test.testng;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestNGLearn1 {



    @BeforeMethod
    public void beforeClass() {
        System.out.println("this is before class");
    }

    @Test
    public void TestNgLearn() {
    	Assert.assertTrue(false, "测试失败");
        System.out.println("this is TestNG test case");
    }

    @AfterMethod
    public void afterClass() {
        System.out.println("this is after class");
    }
}