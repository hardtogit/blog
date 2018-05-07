/**
 * 
 */
package com.thon.security;

import com.thon.commons.utils.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author thon
 *
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	public String getCaptchaParam() {

		return captchaParam;

	}

	protected String getCaptcha(ServletRequest request) {

		return WebUtils.getCleanParam(request, getCaptchaParam());

	}

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		HttpServletRequest req = (HttpServletRequest) request;
		
		String username = getUsername(request);
		String password = getPassword(request);
		String captcha = getCaptcha(request);
		boolean rememberMe = isRememberMe(request);
		String host = StringUtils.getRemoteAddr(req);
		String agent = req.getHeader("user-agent");

		HttpSession session = req.getSession();
		session.setAttribute("USER_AGENT", agent);

		return new CaptchaUsernamePasswordToken(username,
				password.toCharArray(), rememberMe, host, captcha);

	}
}
