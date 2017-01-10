package com.mail.browsers;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browser {
	//打开浏览器
	public static WebDriver open_browser(String browserName){
		WebDriver driver;
		
		if(browserName.equals("ie")){
		    System.setProperty("webdriver.ie.driver","C:\\Users\\Administrator\\workspace_work\\testapp\\run\\IEDriverServer1.exe");
		    DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		    ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		    driver = new InternetExplorerDriver(ieCapabilities);
		   	//Log.info("IE 浏览器实例已经被声明");
		}
		else if(browserName.equals("chrome")){
			
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
			DesiredCapabilities capabilites = DesiredCapabilities.chrome();
			capabilites.setCapability("chrome.switches", Arrays.asList("--incognito"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--test-type");
			capabilites.setCapability("chrome.binary", "run/chromedriver.exe");
			capabilites.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilites);
			//Log.info("chrome 浏览器实例已经被声明");
		}
		
		else{
		    System.setProperty("webdriver.firefox.bin","C:\\Program Files\\Mozilla Firefox\\firefox.exe");
		    driver = new FirefoxDriver();
		    //Log.info("firefox 浏览器实例已经被声明");
		}
		driver.manage().window().maximize();
		
		return driver;
	}
}
