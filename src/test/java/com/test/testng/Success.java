package com.test.testng;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Success {

//	@Test(invocationCount=10)
//	public void successes(){
//		Assert.assertTrue(true, "成功");
//		System.out.println("hello");
//	}
//	
	@Test
	public void careInterfaceSmoke(){
		System.out.println("1");
		System.out.println("2");
		System.out.println("3");
		System.out.println("4");
		System.out.println("5");
	    Assert.assertEquals(3,2);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
	    if (!result.isSuccess())
	        catchExceptions(result);
	}

	public void catchExceptions(ITestResult result) {
	    System.out.println("result" + result);
	    String methodName = result.getName();
	    System.out.println(methodName);
	    
	    File file = new File("");
//        Reporter.setCurrentTestResult(result);
//        System.out.println(file.getAbsolutePath());
        Reporter.log(file.getAbsolutePath()+"\\"+result.getName()+".jpg");
        String filePath = file.getAbsolutePath();
//        //filePath  = filePath.replace("D://jenkins_home//workspace","http://192.168.10.39:8080");
        Reporter.log("<img src='"+filePath+"\\"+result.getName()+".jpg'/>");
//
//        new File(file.getAbsolutePath()+"\\"+result.getName()+"1.jpg");

	    
	    Reporter.log("<img src='123.jpg'/>", false);;
	    Reporter.log("<img src='"+filePath+"/"+result.getName()+".jpg' hight='100' width='100'/>");
	}
}
