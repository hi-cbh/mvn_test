package com.mail.run;

import io.appium.java_client.android.AndroidDriver;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mail.common.CommonUtil;
import com.mail.common.Message;
import com.mail.common.Log;
import com.mail.common.ObjectMap;
import com.mail.common.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

public class Run139Email {
	public AndroidDriver driver;
	public SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 工程根目录
	String projectRootPath = new File(System.getProperty("user.dir")).getPath()
			.concat("/");
	String picPath = projectRootPath + "pic/";
	File picFile = new File(picPath);
	String screenshotName = "mail139"; // 截图的名字前缀

	Components components = new Components();

	private static ObjectMap objectMap = new ObjectMap(
			Message.Path_ConfigurationFile);

	// 客户端登录用户，主测账号
	User user = new User(objectMap.getLocator("login.username0"),
			objectMap.getLocator("login.password0"));
	// Web端登录用户，辅助账号
	User user2 = new User(objectMap.getLocator("login.username2"),
			objectMap.getLocator("login.password2"));

	// 发送邮件时，邮件接收者，为主账号
	String recipient = user.getUser() + "@139.com";

	@BeforeClass
	public void beforeClass() {
		DOMConfigurator.configure("Log4j.xml");
	}

	@BeforeMethod
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");// 手机操作系统
		capabilities.setCapability("deviceName", "Android");// 真机时输入Android
		capabilities.setCapability("platformVersion", "5.1.1");// 手机操作系统版本，可不更改
		capabilities.setCapability("appPackage", "cn.cj.pe");// 运行的Android应用的包名
		capabilities.setCapability("appActivity",
				"com.mail139.about.LaunchActivity");// 启动的Activity
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);// 若测试多台手机需为开启多个appium，且修改4725（一台对应一个）
		Log.info("setUp");
	}

	@AfterMethod
	public void tearDown() throws Exception {
		Log.info("----------运行：退出账号----------");
		//components.quit(driver);
		driver.quit();
	}

	//@Test(description = "整合")
	public void test() throws Exception {

		

		String path = "sdcard/139PushEmail/download/" + user.getUser()
				+ "@139.com/*test2K.rar";
		String filename = "test2K";
		
		String networkType = "";
		boolean dataEnabled = driver.getNetworkConnection().dataEnabled();
		boolean wifiEnabled = driver.getNetworkConnection().wifiEnabled();

		if ((!wifiEnabled) && dataEnabled) {
			networkType = "4G";
		} else {
			networkType = "CMCC";
		}

		try {

			Log.info("----------运行：账号登录-------------");
			components.testLogin(driver, networkType, user);

			Log.info("----------运行：接收本域邮件----------");
			components.testReceiveMail(driver, user2, recipient);

			Log.info("----------运行：发送邮件----------");
			components.testWriterMail(driver, recipient);

			Log.info("----------运行：打开未读邮件----------");
			components.testUnReadMail(driver);

			Log.info("----------运行：下载附件----------");
			components.testDownLoad(driver, path, filename);

		} catch (Exception e) {

			System.out.println("测试运行失败，截图");
			// 截图
			CommonUtil.screenShot(driver, picPath, screenshotName);
			e.printStackTrace();
			driver.resetApp();
		}

	}

//	@Test(description = "账号登录")
//	public void testCaseLogin() {
//		Reporter.log("账号登录");
//		
//		boolean dataEnabled = driver.getNetworkConnection().dataEnabled();
//		boolean wifiEnabled = driver.getNetworkConnection().wifiEnabled();
//
//		String networkType = "";
//		if ((!wifiEnabled) && dataEnabled) {
//			networkType = "4G";
//		} else {
//			networkType = "CMCC";
//		}
//
//		try {
//
//			Log.info("----------运行：账号登录-------------");
//
//			components.testLogin(driver, networkType, user);
//
//		} catch (Exception e) {
//
//			System.out.println("测试运行失败，截图");
//			// 截图
//			CommonUtil.screenShot(driver, picPath, screenshotName);
//			e.printStackTrace();
//			driver.resetApp();
//		}
//	}
//
//	@Test(description = "接收本域邮件")
//	public void testCaseReceiveMail() {
//		Reporter.log("接收本域邮件");
//		String networkType = "";
//		boolean dataEnabled = driver.getNetworkConnection().dataEnabled();
//		boolean wifiEnabled = driver.getNetworkConnection().wifiEnabled();
//
//		if ((!wifiEnabled) && dataEnabled) {
//			networkType = "4G";
//		} else {
//			networkType = "CMCC";
//		}
//		try {
//			Log.info("----------运行：账号登录-------------");
//			components.testLogin(driver, networkType, user);
//
//			Log.info("----------运行：接收本域邮件----------");
//			components.testReceiveMail(driver, user2, recipient);
//
//		} catch (Exception e) {
//
//			System.out.println("测试运行失败，截图");
//			// 截图
//			CommonUtil.screenShot(driver, picPath, screenshotName);
//			e.printStackTrace();
//			driver.resetApp();
//		}
//	}
//
//	@Test(description = "发送邮件")
//	public void testCaseSendMail() {
//		Reporter.log("发送邮件");
//		String networkType = "";
//		boolean dataEnabled = driver.getNetworkConnection().dataEnabled();
//		boolean wifiEnabled = driver.getNetworkConnection().wifiEnabled();
//
//		if ((!wifiEnabled) && dataEnabled) {
//			networkType = "4G";
//		} else {
//			networkType = "CMCC";
//		}
//		try {
//			Log.info("----------运行：账号登录-------------");
//			components.testLogin(driver, networkType, user);
//
//			Log.info("----------运行：发送邮件----------");
//			components.testWriterMail(driver, recipient);
//
//		} catch (Exception e) {
//
//			System.out.println("测试运行失败，截图");
//			// 截图
//			CommonUtil.screenShot(driver, picPath, screenshotName);
//			e.printStackTrace();
//			driver.resetApp();
//		}
//	}
//
//	@Test(description = "打开未读邮件和下载附件")
//	public void testCaseOpenAndDownload() {
//		Reporter.log("打开未读邮件和下载附件");
//		String path = "sdcard/139PushEmail/download/" + user.getUser()
//				+ "@139.com/*test2K.rar";
//		String filename = "test2K";
//		String networkType = "";
//		boolean dataEnabled = driver.getNetworkConnection().dataEnabled();
//		boolean wifiEnabled = driver.getNetworkConnection().wifiEnabled();
//
//		if ((!wifiEnabled) && dataEnabled) {
//			networkType = "4G";
//		} else {
//			networkType = "CMCC";
//		}
//		try {
//			Log.info("----------运行：账号登录-------------");
//			components.testLogin(driver, networkType, user);
//
//			Log.info("----------运行：发送邮件----------");
//			components.testWriterMail(driver, recipient);
//			
//			Log.info("----------运行：打开未读邮件----------");
//			components.testUnReadMail(driver);
//
//			Log.info("----------运行：下载附件----------");
//			components.testDownLoad(driver, path, filename);
//
//		} catch (Exception e) {
//
//			System.out.println("测试运行失败，截图");
//			// 截图
//			CommonUtil.screenShot(driver, picPath, screenshotName);
//			e.printStackTrace();
//			driver.resetApp();
//		}
//	}
	
	
	@Test(description="hellow")
	public void careInterfaceSmoke(){
		File file = new File("");
		String filePath = file.getAbsolutePath();
		  
		Reporter.log("<img src='"+filePath+"\\"+"123.png' hight='100' width='100'/>");
	    Assert.assertEquals(1,2);
	}



}