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

	@Test(invocationCount=10)
	public void successes(){
		Assert.assertTrue(true, "成功");
		System.out.println("hello");
	}
	
	@Test
	public void careInterfaceSmoke(){
	    Assert.assertEquals(1,2);
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
	    if (!result.isSuccess()) {
	        File file = new File("");
	        Reporter.setCurrentTestResult(result);
	        System.out.println(file.getAbsolutePath());
	        Reporter.log(file.getAbsolutePath());
	        String filePath = file.getAbsolutePath();
	        filePath  = filePath.replace("D:\\jenkins_home\\workspace","http://192.168.10.39:8080");
	        Reporter.log("<img src='"+filePath+"/"+result.getName()+".jpg' hight='100' width='100'/>");
	        int width = 100;
	        int height = 100;
	        String s = "这是一张测试图片";
	        File screenShotFile = new File(file.getAbsolutePath()+"/"+result.getName()+".jpg");

	        Font font = new Font("Serif", Font.BOLD, 10);
	        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        Graphics2D g2 = (Graphics2D)bi.getGraphics();
	        g2.setBackground(Color.BLACK);
	        g2.clearRect(0, 0, width, height);
	        g2.setPaint(Color.RED);

	        FontRenderContext context = g2.getFontRenderContext();
	        Rectangle2D bounds = font.getStringBounds(s, context);
	        double x = (width - bounds.getWidth()) / 2;
	        double y = (height - bounds.getHeight()) / 2;
	        double ascent = -bounds.getY();
	        double baseY = y + ascent;

	        g2.drawString(s, (int)x, (int)baseY);

	        try {
	            ImageIO.write(bi, "jpg", screenShotFile);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
}
