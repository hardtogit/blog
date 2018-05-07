package com.thon.controller.util;

import org.apache.commons.lang3.SystemUtils;

public class ParamSystemTest {

	public static void main(String[] args) {
		System.out.println(SystemUtils.getJavaIoTmpDir().getAbsolutePath());
	}
}
