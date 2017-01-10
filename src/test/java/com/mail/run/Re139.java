package com.mail.run;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; 
import org.testng.Assert;

import com.email.util.elementManager;

import com.mail.browsers.Browser;

import com.mail.common.Log;
import com.mail.common.User;


public class Re139 {
//	static String userName = Contants.Mail139UserName3;// 139用户名 发送给测试账号
//	static String userPwd = Contants.Mail139Password3;// 邮箱密码
//	static String userName2 = Contants.Mail139UserName2+"@139.com";
	static String title = "testReceive"; // 邮件主题
	static long startime = 0;

	public static void main(String[] args) {
		for (int i = 1; i <= 19; i++) {
			
			Re139 re139 = new Re139();
			
			WebDriver driver = Browser.open_browser("chrome");
			
			//re139.testRe139(driver);
			driver.close();
			driver.quit();
			
		}
	}

//	public  long testRe139(WebDriver driver) {
//		
//		driver.get("http://mail.10086.cn/");
//		sleep(3000);
//		if(!elementManager.doesWebElementExist(driver, By.name("UserName"))){
//			sleep(3000);
//			elementManager.waitForElement(driver, By.id("logout"), 20).click();
//			elementManager.waitForElement(driver, By.id("relogin"), 20).click();
//		}
//		elementManager.waitForElement(driver, By.name("UserName"), 20).clear();
//		
//		sleep(3000);
//		elementManager.waitForElement(driver, By.name("UserName"), 20).sendKeys(userName);
//		elementManager.waitForElement(driver, By.id("txtPass"), 20).sendKeys(userPwd);
//		driver.findElement(By.id("loginBtn")).click();
//		sleep(3000);
//		
//		elementManager.waitForElement(driver, By.name("mailbox_1"), 20).click();
//		
//		while (!elementManager.doesElementDisplay(driver, By.xpath("//input[@id='tb_mailSearch']"))) {
//		}
//		driver.findElement(By.xpath("//a[@id='btn_compose']")).click();
//		sleep(3000);
//		driver.switchTo().frame(driver.findElement(By.id("compose_preload")));
//		//By.xpath("//input[@id='txtSubject']");
//		// 邮件主题
//		elementManager.waitForElement(driver, By.xpath("//input[@id='txtSubject']"), 30).sendKeys(title);
//		sleep(3000);
//		// 输入收件人
//		elementManager.waitForElement(driver,
//				By.xpath("//*[@id='toContainer']/div/div[2]/div[2]/input"), 30).sendKeys(userName2);;
//		sleep(1000);
//		
//		//点击发送
//		driver.findElement(By.id("bottomSend")).click();
//		elementManager.waitForElement(driver, By.xpath("//h1[@id='snedStatus']"), 15);
//		//返回时间
//
//		return startime = System.currentTimeMillis();
//		
//	}
	public  void testRe139(WebDriver driver, User user, String recipient) {
		
		Log.info("打开URL: http://mail.10086.cn/");
		driver.get("http://mail.10086.cn/");
		sleep(3000);
		Log.info("等待60秒，判断是否进入登录页面");
		if(!elementManager.doesWebElementExist(driver, By.name("UserName"))){
			sleep(3000);
			elementManager.waitForElement(driver, By.id("logout"), 20).click();
			elementManager.waitForElement(driver, By.id("relogin"), 20).click();
		}
		
		Log.info("清除用户名信息");
		elementManager.waitForElement(driver, By.name("UserName"), 20).clear();
		sleep(3000);
		
		Log.info("输入用户名：" + user.getUser());
		elementManager.waitForElement(driver, By.name("UserName"), 20).sendKeys(user.getUser());
		
		Log.info("输入密码：" + user.getUser());
		elementManager.waitForElement(driver, By.id("txtPass"), 20).sendKeys(user.getPassword());
		
		Log.info("点击登录按钮");
		driver.findElement(By.id("loginBtn")).click();
		
		Log.info("等待10秒");
		sleep(10 * 1000);
		
		Log.info("点击邮箱链接");
		elementManager.waitForElement(driver, By.name("mailbox_1"), 20).click();
		
		Log.info("等待页面缓冲完成");
		while (!elementManager.doesElementDisplay(driver, By.xpath("//input[@id='tb_mailSearch']"))) {
		}
		
		Log.info("点击写邮件链接");
		driver.findElement(By.xpath("//a[@id='btn_compose']")).click();
		sleep(3000);
		
		Log.info("切换Frame");
		driver.switchTo().frame(driver.findElement(By.id("compose_preload")));
		
		Log.info("输入主题"); 
		elementManager.waitForElement(driver, By.xpath("//input[@id='txtSubject']"), 30).sendKeys(title);
		sleep(3000);
		
		Log.info("输入收件人");
		elementManager.waitForElement(driver,
				By.xpath("//*[@id='toContainer']/div/div[2]/div[2]/input"), 30).sendKeys(recipient);;
		sleep(1000);
		
		Log.info("点击发送");
		driver.findElement(By.id("bottomSend")).click();
		
		Log.info("等待20秒");
		sleep(5*1000);
		
		if(elementManager.doesElementDisplay(driver, By.xpath("//h1[@id='snedStatus']"))){
			Log.info("浏览器邮件发送成功");
		}else{
			Log.info("浏览器邮件发送失败");
			Assert.fail("浏览器邮件发送失败");
		}
	}
	// 用于设置等待时间，不用修改
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
