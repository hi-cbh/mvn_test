package com.email.util;

import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.android.AndroidDriver;

public class NetworkManager {
	/**
	 * 读取当前网络连接类型
	 * @since 2016.8.30
	 * @param driver
	 *          AndroidDriver
	 */
	public static String getNetwork(AndroidDriver driver){
		NetworkConnectionSetting connection = driver.getNetworkConnection();
		if(connection.toString().contains("Wifi: false")){
			return "4G";
		}
		return "Wifi";	
	}
}


