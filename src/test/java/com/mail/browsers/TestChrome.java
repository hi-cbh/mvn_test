package com.mail.browsers;

import org.openqa.selenium.WebDriver;
import com.mail.run.Re139;

public class TestChrome {
	
	public static void main(String[] args) {
		for (int i = 0; i <= 1; i++) {
			Re139 re139 = new Re139();
			WebDriver driver = 	Browser.open_browser("chrome");
			driver.manage().window().maximize();
			//re139.testRe139(driver);

			driver.close();
			driver.quit();
		}
	}

}
