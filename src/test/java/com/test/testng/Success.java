package com.test.testng;

import org.testng.annotations.Test;

public class Success {

	@Test(invocationCount=10)
	public void successes(){
		System.out.println("hello");
	}
	
	
	
}
