/**
 * 
 */
package com.thon.security;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author thon
 *
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {
	private static final long serialVersionUID = 1L;

	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public CaptchaUsernamePasswordToken() {
		super();

	}

	public CaptchaUsernamePasswordToken(String username, char[] password,
			Boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}
}
