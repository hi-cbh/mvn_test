package com.mail.run;

import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.email.util.elementManager;
import com.mail.browsers.Browser;
import com.mail.common.CommonUtil;
import com.mail.common.Location;
import com.mail.common.Log;
import com.mail.common.User;

public class Components {
	public int timeout = 60;// 设置超时时间


	/**
	 * 退出账号
	 * 
	 * @param driver
	 */
	public void quit(AndroidDriver driver) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// 点击【主菜单】
		Log.info("点击【主菜单】");
		elementManager.waitForElement(driver,
				By.id("cn.cj.pe:id/hjl_headicon"), 5).click();
		
		CommonUtil.sleep(1000);
		driver.swipe(width / 3, height * (4 / 5), width / 3, height / 5, 500);
		
		// 点击【设置】
		Log.info("点击【设置】");
		elementManager.waitForElement(driver, By.name("设置"), 10).click();
		CommonUtil.sleep(500);
		Log.info("屏幕上滑");
		driver.swipe(width / 3, height - 100, width / 3, height / 2, 200);// 上拉
		CommonUtil.sleep(500);
		Log.info("屏幕上滑");
		driver.swipe(width / 3, height - 100, width / 3, height / 2, 200);// 上拉
		CommonUtil.sleep(500);
		Log.info("屏幕上滑");
		driver.swipe(width / 3, height - 100, width / 3, height / 2, 200);// 上拉
		// 点击【退出】。
		Log.info("点击【退出】");
		elementManager.waitForElement(driver, By.id("cn.cj.pe:id/log_off"), 5)
				.click();
		// 确认退出
		Log.info("确认退出");
		elementManager.waitForElement(driver, By.id("cn.cj.pe:id/right"), 5)
				.click();
		
		driver.closeApp();
	}

	/**
	 * 登录
	 * 
	 * @param driver
	 * @param networkType
	 * @param user
	 * @param i
	 * @return
	 */
	public void testLogin(AndroidDriver driver, String networkType,
			User user) {
		// User firUser = new User("18718879146","18879146");
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;

		Log.info("判断网络环境，当前网络环境为：" + networkType);
		// 如果是4G网络则，首次登陆后退出，换另外一个账号登陆
		if (networkType.equals("4G")) {

			Log.info("切换到飞行模式");
			// 修改网络设置；0 (什么都没有) 1 (飞行模式) 2 (只有Wifi) 4 (只有数据连接) 6 (开启所有网络)
			NetworkConnectionSetting connection = new NetworkConnectionSetting(
					1);
			driver.setNetworkConnection(connection);// 开启飞行模式，防止软件自动登录

			Log.info("reset App");
			driver.resetApp();

			// System.out.println("-->点击权限：允许");
			// //点击权限按钮
			// CommonUtil.sleep(2*1000);
			// driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
			// CommonUtil.sleep(2*1000);
			// System.out.println("-->点击权限：允许");
			// driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
			// CommonUtil.sleep(2*1000);

			Log.info("判断当前页面是否为账号登录页面");
			WebElement el4g = elementManager.waitForElement(driver,
					By.id("cn.cj.pe:id/login_name"), 10);
			
			Assert.assertTrue(el4g != null, "进入登录页面异常");

			Log.info("切换到只有数据连接");
			connection = new NetworkConnectionSetting(4);
			driver.setNetworkConnection(connection);
			
			Log.info("等待5秒");
			CommonUtil.sleep(5000);
			
			
		} else {
			
			Log.info("切换到飞行模式");
			// 修改网络设置；0 (什么都没有) 1 (飞行模式) 2 (只有Wifi) 4 (只有数据连接) 6 (开启所有网络)
			NetworkConnectionSetting connection = new NetworkConnectionSetting(
					1);
			driver.setNetworkConnection(connection);// 开启飞行模式，防止软件自动登录

			Log.info("reset App");
			driver.resetApp();
			
//			 System.out.println("-->点击权限：允许");
//			 //点击权限按钮
//			 driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
//			 CommonUtil.sleep(2*1000);
//			 System.out.println("-->点击权限：允许");
//			 driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
//			 CommonUtil.sleep(2*1000);
			
			Log.info("判断当前页面是否为账号登录页面");
			WebElement elwifi = elementManager.waitForElement(driver,
					By.id("cn.cj.pe:id/login_name"), 10);
			
			Assert.assertTrue(elwifi != null, "进入登录页面异常");
			
			Log.info("切换到Wifi模式");
			connection = new NetworkConnectionSetting(2);
			driver.setNetworkConnection(connection);

		}

		Log.info("输入用户名：" + user.getUser());
		// 登录时延
		elementManager.waitForElement(driver, By.id("cn.cj.pe:id/login_name"),
				10).sendKeys(user.getUser());

		Log.info("输入密码：" + "xxxxxxxxxxxxxxx");
		driver.findElement(By.id("cn.cj.pe:id/login_password")).click();
		driver.findElement(By.id("cn.cj.pe:id/login_password")).sendKeys(
				user.getPassword());

		Log.info("等待5秒");
		CommonUtil.sleep(5 * 1000);

		Log.info("点击登录按钮");
		driver.findElement(By.id("cn.cj.pe:id/login")).click();

		Log.info("等待出现订阅推荐页出现，等待："+ timeout);
		WebElement e = null;
		e = elementManager.waitForElement(driver, By.id("cn.cj.pe:id/submit"),
				timeout);

		Assert.assertTrue(e != null, "登录失败");
		
//		if (e != null) {
//			Log.info("登录成功");
//			//Reporter.log("账号登录成功", true);
//		} else {
//			Log.info("登录失败");
//			//Reporter.log("账号登录失败", false);
//			Assert.fail("登录失败");
//		}
		
		Log.info("去掉订阅的默认2个选项");
		// 去掉订阅的默认2个选项
		elementManager.waitForElement(driver, By.id("cn.cj.pe:id/check"),
				timeout);
		List<WebElement> list = driver.findElements(By.id("cn.cj.pe:id/check"));
		list.get(0).click();
		CommonUtil.sleep(1000);
		list.get(1).click();
		CommonUtil.sleep(1000);
		
		Log.info("点击【开始体验】");
		// 点击开始体验
		elementManager.waitForElement(driver, By.id("cn.cj.pe:id/submit"),
				timeout).click();

		CommonUtil.sleep(2000);

		Log.info("点击【主菜单】");
		// 点击【主菜单】
		elementManager
				.waitForElement(driver, By.id("cn.cj.pe:id/hjl_headicon"))
				.click();
		
		CommonUtil.sleep(1000);
		Log.info("屏幕上滑");
		driver.swipe(width / 3, height * (4 / 5), width / 3, height / 5, 500);
		CommonUtil.sleep(1000);
		Log.info("屏幕上滑");
		driver.swipe(width / 3, height * (4 / 5), width / 3, height / 5, 500);


		Log.info("点击设置");
		// 点击【设置】
		CommonUtil.sleep(1000);
		elementManager.waitForElement(driver, By.name("设置"), 10).click();
		// 设置删除邮件同步到云端
		Log.info("邮件删除不同步服务器");
		// driver.swipe(width/3, height*(4/5), width/3, height/5, 500);
		CommonUtil.sleep(2000);
		// elementManager.waitForElement(driver,
		// By.xpath("//android.widget.TextView[@text='邮件删除不同步服务器']"),timeout).click();
		elementManager.waitForElement(driver, By.name("邮件删除不同步服务器"), timeout)
				.click();
		Log.info("点击返回");
		// CommonUtil.sleep(1000);
		// driver.swipe(width/3, height/5, width/3, height*(4/5), 500);
		CommonUtil.sleep(1000);
		elementManager.waitForElement(driver,
				By.id("cn.cj.pe:id/hjl_headicon"), 5).click();

		// CommonUtil.sleep(1000);
		// driver.swipe(width/3, height/5, width/3, height*(4/5), 500);
		CommonUtil.sleep(1000);
		Log.info("屏幕下滑");
		driver.swipe(width / 3, height / 5, width / 3, height * (4 / 5), 500);
		
		Log.info("点击收件箱");
		elementManager.waitForElement(driver, By.name("收件箱"), timeout).click();
	}


	/**
	 * 登陆 
	 * 
	 * @param driver
	 * @param networkType
	 * @param user
	 * @param bl  true为 一键登录
	 * @return
	 */
	public void testLogin(AndroidDriver driver, String networkType,
			User user,boolean bl) {
		// User firUser = new User("18718879146","18879146");
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;

		Log.info("判断网络环境，当前网络环境为：" + networkType);
		// 如果是4G网络则，首次登陆后退出，换另外一个账号登陆
		if (networkType.equals("4G")) {

			Log.info("切换到飞行模式");
			// 修改网络设置；0 (什么都没有) 1 (飞行模式) 2 (只有Wifi) 4 (只有数据连接) 6 (开启所有网络)
			NetworkConnectionSetting connection = new NetworkConnectionSetting(
					1);
			driver.setNetworkConnection(connection);// 开启飞行模式，防止软件自动登录

			Log.info("reset App");
			driver.resetApp();

			// System.out.println("-->点击权限：允许");
			// //点击权限按钮
			// CommonUtil.sleep(2*1000);
			// driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
			// CommonUtil.sleep(2*1000);
			// System.out.println("-->点击权限：允许");
			// driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
			// CommonUtil.sleep(2*1000);

			Log.info("判断当前页面是否为账号登录页面");
			WebElement el4g = elementManager.waitForElement(driver,
					By.id("cn.cj.pe:id/login_name"), 10);
			
			Assert.assertTrue(el4g != null, "进入登录页面异常");

			Log.info("切换到只有数据连接");
			connection = new NetworkConnectionSetting(4);
			driver.setNetworkConnection(connection);
			
			Log.info("等待5秒");
			CommonUtil.sleep(5000);
			
			
		} else {
			
			Log.info("切换到飞行模式");
			// 修改网络设置；0 (什么都没有) 1 (飞行模式) 2 (只有Wifi) 4 (只有数据连接) 6 (开启所有网络)
			NetworkConnectionSetting connection = new NetworkConnectionSetting(
					1);
			driver.setNetworkConnection(connection);// 开启飞行模式，防止软件自动登录

			Log.info("reset App");
			driver.resetApp();
			
//			 System.out.println("-->点击权限：允许");
//			 //点击权限按钮
//			 driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
//			 CommonUtil.sleep(2*1000);
//			 System.out.println("-->点击权限：允许");
//			 driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
//			 CommonUtil.sleep(2*1000);
			
			Log.info("判断当前页面是否为账号登录页面");
			WebElement elwifi = elementManager.waitForElement(driver,
					By.id("cn.cj.pe:id/login_name"), 10);
			
			Assert.assertTrue(elwifi != null, "进入登录页面异常");
			
			Log.info("切换到Wifi模式");
			connection = new NetworkConnectionSetting(2);
			driver.setNetworkConnection(connection);

		}

		
		//非一键登录
		if(!bl){

			Log.info("输入用户名：" + user.getUser());
			// 登录时延
			elementManager.waitForElement(driver, By.id("cn.cj.pe:id/login_name"),
					10).sendKeys(user.getUser());

			Log.info("输入密码：" + user.getPassword());
			driver.findElement(By.id("cn.cj.pe:id/login_password")).click();
			driver.findElement(By.id("cn.cj.pe:id/login_password")).sendKeys(
					user.getPassword());

			Log.info("等待5秒");
			CommonUtil.sleep(5 * 1000);

			Log.info("点击登录按钮");
			driver.findElement(By.id("cn.cj.pe:id/login")).click();

		}
		else{
			Log.info("等待5秒");
			CommonUtil.sleep(5 * 1000);
			
			Log.info("点击一键登录按钮");
			elementManager.waitForElement(driver, By.id("cn.cj.pe:id/sm_login"), 20).click();
		}
		
		
		
		Log.info("等待出现订阅推荐页出现，等待："+ timeout);
		WebElement e = null;
		e = elementManager.waitForElement(driver, By.id("cn.cj.pe:id/submit"),
				timeout);

		Assert.assertTrue(e != null, "登录失败");
		
//		if (e != null) {
//			Log.info("登录成功");
//			//Reporter.log("账号登录成功", true);
//		} else {
//			Log.info("登录失败");
//			//Reporter.log("账号登录失败", false);
//			Assert.fail("登录失败");
//		}
		
		Log.info("去掉订阅的默认2个选项");
		// 去掉订阅的默认2个选项
		elementManager.waitForElement(driver, By.id("cn.cj.pe:id/check"),
				timeout);
		List<WebElement> list = driver.findElements(By.id("cn.cj.pe:id/check"));
		list.get(0).click();
		CommonUtil.sleep(1000);
		list.get(1).click();
		CommonUtil.sleep(1000);
		
		Log.info("点击【开始体验】");
		// 点击开始体验
		elementManager.waitForElement(driver, By.id("cn.cj.pe:id/submit"),
				timeout).click();

		CommonUtil.sleep(2000);

		Log.info("点击【主菜单】");
		// 点击【主菜单】
		elementManager
				.waitForElement(driver, By.id("cn.cj.pe:id/hjl_headicon"))
				.click();
		
		CommonUtil.sleep(1000);
		Log.info("屏幕上滑");
		driver.swipe(width / 3, height * (4 / 5), width / 3, height / 5, 500);
		CommonUtil.sleep(1000);
		Log.info("屏幕上滑");
		driver.swipe(width / 3, height * (4 / 5), width / 3, height / 5, 500);


		Log.info("点击设置");
		// 点击【设置】
		CommonUtil.sleep(1000);
		elementManager.waitForElement(driver, By.name("设置"), 10).click();
		// 设置删除邮件同步到云端
		Log.info("邮件删除不同步服务器");
		// driver.swipe(width/3, height*(4/5), width/3, height/5, 500);
		CommonUtil.sleep(2000);
		// elementManager.waitForElement(driver,
		// By.xpath("//android.widget.TextView[@text='邮件删除不同步服务器']"),timeout).click();
		elementManager.waitForElement(driver, By.name("邮件删除不同步服务器"), timeout)
				.click();
		Log.info("点击返回");
		// CommonUtil.sleep(1000);
		// driver.swipe(width/3, height/5, width/3, height*(4/5), 500);
		CommonUtil.sleep(1000);
		elementManager.waitForElement(driver,
				By.id("cn.cj.pe:id/hjl_headicon"), 5).click();

		// CommonUtil.sleep(1000);
		// driver.swipe(width/3, height/5, width/3, height*(4/5), 500);
		CommonUtil.sleep(1000);
		Log.info("屏幕下滑");
		driver.swipe(width / 3, height / 5, width / 3, height * (4 / 5), 500);
		
		Log.info("点击收件箱");
		elementManager.waitForElement(driver, By.name("收件箱"), timeout).click();
	}

	
	/**
	 * 打开未读邮件时延
	 * 
	 * @param driver
	 * @return
	 */
	public void testUnReadMail(AndroidDriver driver) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;

		// 点击【收件箱】，选择【未读】邮件按钮
		Log.info("等待10秒");
		CommonUtil.sleep(10000);
		
		Log.info("下滑邮箱列表");
		driver.swipe(width / 3, height / 5, width / 3, height * (4 / 5), 500);
		
		Log.info("等待10秒");
		CommonUtil.sleep(10000);
		
		Log.info("判断页面是否为邮箱列表页");
		WebElement list = elementManager.waitForElement(driver,
				By.id("android:id/list"), timeout);
		
		Log.info("判断邮箱列表，是否存在列表");
		List<WebElement> mailList = list.findElements(By
				.className("android.widget.LinearLayout"));
		
		CommonUtil.sleep(3000);
		
		Log.info("打开第一封未读邮件");
		mailList.get(0).click();

		Log.info("打开邮件，检测邮件中的关键字段");
		WebElement e = elementManager.waitForElement(driver,
				By.name("123456789012345678901234567890"), timeout);
		
		Assert.assertTrue(e != null, "打开未读邮件失败");
//		if (e != null) {
//			Log.info("打开邮箱成功");
//			Reporter.log("打开未读邮件成功", true);
//		} else {
//			Log.info("打开邮箱失败");
//			Reporter.log("打开未读邮件失败", false);
//			Assert.fail("打开未读邮件失败");
//		}
		
	}

	/**
	 * 下载附件
	 * 
	 * @param driver
	 * @param path
	 *            路径
	 * @param filename
	 *            文件名
	 * @return
	 */
	public void testDownLoad(AndroidDriver driver, String path,
			String fileName) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		
		Log.info("判断附件是否存在，存在是清除附件");
		if (CommonUtil.adbFindFile(path, fileName)) {
			CommonUtil.adbDeleteFile(path);
		}
		CommonUtil.sleep(3000);
		Location location = CommonUtil.getLocation(1440, 2560, width, height,
				120, 800);
		CommonUtil.sleep(1000);
		
		Log.info("获取下载坐标，点击下载");
		CommonUtil.adbClickElement(String.valueOf(location.getX()),
				String.valueOf(location.getY()));

		Log.info("等待下载，等待2分钟");
		boolean isexists = CommonUtil.adbwaitforfile(path, fileName, 120000);
		
		Assert.assertTrue(isexists, "下载失败");
		
//		if (isexists) {
//			Log.info("下载成功");
//			Reporter.log("下载成功", true);
//		} else {
//			Log.info("下载失败");
//			Reporter.log("下载失败", false);
//			Assert.fail("下载失败");
//		}

		CommonUtil.sleep(2000);
		// //返回
		// driver.sendKeyEvent(4);
		// CommonUtil.sleep(1000);

		Log.info("返回收件箱列表");
		elementManager.waitForElement(driver,
				By.id("cn.cj.pe:id/actionbar_back"), timeout).click();// 
		CommonUtil.sleep(2000);

	}

	/**
	 * 发送邮件时延
	 * 
	 * @param driver
	 * @param recipient
	 *            收件人
	 * @return
	 */
	public void testWriterMail(AndroidDriver driver, String recipient) {
		// 写邮件，点击【写邮件】按钮
		CommonUtil.sleep(1000);
		Log.info("点击写邮件按钮");
		driver.findElement(By.id("cn.cj.pe:id/actionbar_right_view")).click();
		
		Log.info("输入接收人信息");
		elementManager.waitForElement(driver, By.id("cn.cj.pe:id/to_wrapper"),
				10).sendKeys(recipient);

		CommonUtil.sleep(3000);
		Log.info("点击空白地方");
		driver.findElement(By.id("cn.cj.pe:id/actionbar_title_sub")).click();
		

		CommonUtil.sleep(3000);
		
		Log.info("输入主题");
		driver.findElement(By.id("cn.cj.pe:id/subject")).click();
		driver.findElement(By.id("cn.cj.pe:id/subject")).sendKeys("test");
		
		// 设定正文长度
		String textField = "123456789012345678901234567890";
//		WebElement body = driver
//				.findElement(By.id("cn.cj.pe:id/editTextField"));
//		body.sendKeys(textField);
		Log.info("输入正文");	
		elementManager.waitForElement(driver, By.id("cn.cj.pe:id/editTextField")).sendKeys(textField);
		
		Log.info("点击添加附件");
		driver.findElement(By.id("cn.cj.pe:id/add_attachment")).click();
		
		Log.info("点击文件夹图标");
		elementManager.waitForElement(driver,
				By.id("cn.cj.pe:id/attach_display_file"), 5).click();
		
		Log.info("打开本地文件/0/0./，选择文件");
		elementManager.waitForElement(driver, By.name("本地文件"), 5).click();
		elementManager.waitForElement(driver, By.name("0"), 5).click();
		elementManager.waitForElement(driver, By.name("0."), 5).click();
		elementManager.waitForElement(driver, By.name("test2K.rar"), 5).click();
		
		Log.info("点击添加按钮");
		elementManager.checkandClick(driver, By.id("cn.cj.pe:id/check_button"));
		
		Log.info("发送按钮");
		elementManager.waitForElement(driver, By.id("cn.cj.pe:id/txt_send"), 2)
				.click();
		
		Log.info("等待时间最大为分钟");
		WebElement e = elementManager.waitForElement(driver, By.name("已完成"),
				120);

		Assert.assertTrue(e != null, "邮件发送失败");
		
//		if (e != null) {
//			Log.info("邮件发送成功");
//			Reporter.log("邮件发送成功", true);
//		} else {
//			Log.info("邮件发送失败");
//			Reporter.log("邮件发送失败", false);
//			Assert.fail("邮件发送失败");
//		}
		
		// 返回邮件列表
		Log.info("返回邮件列表");
		driver.findElement(By.id("cn.cj.pe:id/hjl_headicon")).click();
		CommonUtil.sleep(5000);
	}

	/**
	 * 接收本域邮件
	 * 
	 * @param driver
	 * @return
	 */
	public void testReceiveMail(AndroidDriver driver, User user,
			String recipient) {
		int width = driver.manage().window().getSize().width;

		Re139 re139 = new Re139();

		Log.info("打开chrome浏览器");
		WebDriver wd = Browser.open_browser("chrome");

		Log.info("浏览器，发送邮件");
		// 获取开始时间
		re139.testRe139(wd, user, recipient);
		
		//Log.info("等待20秒，待邮件列表稳定");
		//CommonUtil.sleep(20*1000);
		wd.close();
		wd.quit();
		
		Log.info("在邮件列表，等待是否含有：testReceive");
		/**
		 * 列表中不能存在testReceive的邮件，否则发送异常
		 */
		boolean isReceived = waitUnReadMail(driver, By.name("testReceive"),
				15*1000);

		if (isReceived) {
			Log.info("接收邮件成功");
			//Reporter.log("接收邮件成功", true);
			Log.info("准备删除刚接收邮件");
			elementManager.waitForElement(driver, By.id("android:id/list"), 30);
			Log.info("等待20秒，待邮件列表稳定");
			CommonUtil.sleep(20*1000);
			
			Log.info("右滑第一条邮件");
			driver.swipe(width - 20, 400, 20, 400, 500);
			CommonUtil.sleep(1000);
			
			Log.info("点击删除按钮");
			elementManager.waitForElement(driver,
					By.id("cn.cj.pe:id/back_delete"), 30).click();
			CommonUtil.sleep(1000);
		} else {
			Assert.assertTrue(false, "接收邮件失败");
//			Log.info("接收邮件失败");
//			Reporter.log("接收邮件失败", false);
//			Assert.fail("接收邮件失败");
		}

		
	}

	/**
	 * 等待未读邮件
	 * 
	 * @param driver
	 * @param by
	 * @param timeoutMillis
	 *            超时时间
	 * @return
	 */
	public boolean waitUnReadMail(AndroidDriver driver, By by,
			long timeoutMillis) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		boolean isTimeOut = false;
		// 超时时间
		long timeout = System.currentTimeMillis() + timeoutMillis;
		Log.info("等待60秒，下拉刷新邮件列表");
		while (System.currentTimeMillis() < timeout) {
			try {
				if (driver.findElement(by).isDisplayed()) {
					return true;
				}
			} catch (Exception e) {
				driver.swipe(width / 5, height / 5, width / 5, height * 4 / 5,
						500);
			}
		}
		if (!isTimeOut) {
			Log.info("接收邮件失败，接收失败或超时");
			//Assert.fail("接收邮件失败");
			//System.out.println("wait for \" " + by + " \" error!");
		}
		return isTimeOut;

	}

	/**
	 * 点击取消 监听弹出的确认框，点击取消
	 * 
	 * @param driver
	 * @param by
	 * @return
	 */
	public static WebElement watcherForCancel(AndroidDriver driver, By by) {
		WebElement e = null;
		try {
			e = driver.findElement(by);
			e.click();
		} catch (Exception e1) {
		}
		return e;
	}

	/**
	 * 
	 * @param driver
	 * @param findText
	 *            元素名称
	 */
	public void clearCache(AndroidDriver driver, String findText) {
		CommonUtil.sleep(3000);
		// 按Home键
		driver.sendKeyEvent(3);
		CommonUtil.sleep(1000);
		driver.openNotifications();
		elementManager.waitForElement(driver,
				By.id("com.android.systemui:id/basic_settings_button"), 30)
				.click();
		driver.scrollTo("应用程序管理器");
		elementManager.waitForElement(driver, By.name("应用程序管理器"), 30).click();
		WebElement e = elementManager.waitForElement(driver, By.name(findText),
				2);
		if (e == null) {
			driver.scrollTo(findText);
			e = driver.findElement(By.name(findText));
		}
		e.click();
		elementManager.waitForElement(driver, By.name("强制停止"), 30).click();
		elementManager.waitForElement(driver, By.name("确定"), 30).click();
		CommonUtil.sleep(5000);
		elementManager.waitForElement(driver, By.name("清除数据"), 30).click();
		elementManager.waitForElement(driver, By.name("确定"), 30).click();
		driver.sendKeyEvent(3);
	}
}
