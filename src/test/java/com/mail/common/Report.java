package com.mail.common;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.email.util.formatUtil;

public class Report {
	
	public static void catchExceptions(ITestResult result, AndroidDriver driver) {
	    System.out.println("result" + result);
	    String methodName = result.getName();
	    
	    File file = new File("");
	    
        Reporter.setCurrentTestResult(result);
       
        String filePath = file.getAbsolutePath();
        System.out.println("filePath: " + filePath);
        //在jenkins中使用
        //filePath  = filePath.replace("D://jenkins_home//workspace","http://localhost:8000");
        

		String picName = methodName+ formatUtil.getSystemTime("_MM.dd-HH.mm.ss") + ".png";
		String picPath = filePath+"\\pic"+"\\"+ picName;
		
		//截图
		//screenShot(driver,picPath);
		//显示路径
		//Reporter.log("图片位置："+picPath);
		//显示图片
		//DisplayPic(picPath,200,200); jenkins显示图片有问题
	}
	
	
	
	
	/**
	 *  图片显示在报告上
	 * @param filePath 文件路径，绝对值
	 * @param hight    图片的高
	 * @param width    图片的宽
	 */
	public static void DisplayPic(String filePath, int hight, int width){
		Reporter.log("<img src='"+filePath+"' hight='"+hight+"' width='"+width+"'/>");
	}
	
	/**
	 * 截图
	 * @param driver
	 * @param path
	 */
	public static void screenShot(AndroidDriver driver, String filepath) {
			File screenshot = driver.getScreenshotAs(OutputType.FILE);// 截取当前图片
			try {
				FileUtils.copyFile(screenshot,new File(filepath));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
    }


}
