package com.mail.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.openqa.selenium.By;

public class ObjectMap {

	Properties properties;

	public ObjectMap(String propFile) {
		properties = new Properties();

		try {
			FileInputStream in = new FileInputStream(propFile);
			properties.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("read file error!");
			e.printStackTrace();
		}
	}

	public String getLocator(String ElementNameInpropFile) {
		//
		String locator = properties.getProperty(ElementNameInpropFile);
		//
		String locatorType = locator.split(">")[0];
		String locatorValue = locator.split(">")[1];
		
		System.out.println("locatorType: " + locatorType);
		System.out.println("locatorValue1: " + locatorValue);
		//
		try {
			locatorValue = new String(locatorValue.getBytes("ISO8859-1"), "UTF-8");
			
			//System.out.println("locatorValue2: " + locatorValue);
			//
			if (locatorType.toLowerCase().equals("string")) {
				return locatorValue;
			} else {
				System.out.println("getLocator:  null");
				throw new Exception("lo" + locatorType);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

}
