package com.test.testng;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestNGLearn1 {

	@BeforeSuite
	public void beforeSuite(){
		System.out.println("this is before Suite");
	}
	
	@AfterSuite
	public void afterSuite(){
		System.out.println("this is after Suite");
	}
    @BeforeClass
    public void beforeClass() {
        System.out.println("this is before class");
    }

    @Test
    public void TestNgLearn() {
    	Assert.assertTrue(false, "测试失败");
        System.out.println("this is TestNG test case");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("this is after class");
    }
}