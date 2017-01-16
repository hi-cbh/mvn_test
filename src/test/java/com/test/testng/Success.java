package com.test.testng;



import org.testng.Assert;
import org.testng.annotations.Test;

public class Success {

	@Test(invocationCount=10)
	public void successes(){
		Assert.assertTrue(true, "成功");
		System.out.println("hello");
	}
	
	
	
}
