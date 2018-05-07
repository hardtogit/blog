package com.thon.controller.util;

import java.util.ResourceBundle;

public class ResourceReadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResourceBundle rb = ResourceBundle.getBundle("config"); 
		System.out.println(rb.getString("error.code")); 
	}

}
