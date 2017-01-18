package com.testng;

import io.appium.java_client.android.AndroidDriver;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mail.common.CommonUtil;
import com.mail.common.Message;
import com.mail.common.Log;
import com.mail.common.ObjectMap;
import com.mail.common.Report;
import com.mail.common.User;
import com.mail.run.Components;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;


public class Run139Email5 {
	public AndroidDriver driver;

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

//	@BeforeTest
//	public void beforeSuite() {
//		DOMConfigurator.configure("Log4j.xml");
//	}

	@BeforeClass
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

	@AfterClass
	public void tearDown() throws Exception {

		driver.quit();
	}


	@Test(description = "一键登录")
	public void testCasePhoneLogin() {
		
		boolean dataEnabled = driver.getNetworkConnection().dataEnabled();
		boolean wifiEnabled = driver.getNetworkConnection().wifiEnabled();

		String networkType = "";
		if ((!wifiEnabled) && dataEnabled) {
			networkType = "4G";
		} else {
			networkType = "CMCC";
		}

		try {
			Assert.fail("测试失败");
			Log.info("----------运行：账号登录-------------");
			components.testLogin(driver, networkType, user,true);
			
			//Assert.fail("测试失败");
			Log.info("----------运行：退出账号----------");
			components.quit(driver);
			
		} catch (Exception e) {
			Assert.fail("测试运行失败，错误信息为：" + e.getMessage());
//			System.out.println("测试运行失败，截图");
//			// 截图
//			CommonUtil.screenShot(driver, picPath, screenshotName);
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
	    if (!result.isSuccess())
	    	Report.catchExceptions(result, driver);
	}


}