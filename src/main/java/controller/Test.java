package controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Test {
	
	String test;

	
	public String getTest() {
		return test;
	}
	//@Value("3338")
	public void upTest(String test) {
		this.test = test;
		System.out.println(test);
	}

}
