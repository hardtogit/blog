package com.thon.controller.system;

import com.thon.commons.utils.Encodes;
import com.thon.entity.system.User;
import com.thon.service.system.SystemService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * @file ForgetController.java
 * @author thon
 * @email thon.ju@gmail.com
 * @date Aug 16, 2013 10:04:08 AM
 * @description 忘记密码相关操作
 */
@Controller
@RequestMapping(value = "/forget")
public class ForgetController {

	@Autowired
	private SystemService systemService;

	@RequestMapping(method = RequestMethod.GET)
	public String forget() {
		return "forget";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String forgetPost(HttpServletRequest request) {
		String email = request.getParameter("email");
		User user = systemService.findUser(email, null);
		if (user != null) {
			systemService.forgetPassword(user);
		}
		
		return "forget-send";
	}
	
	@RequestMapping(value="/reset/{code}",method = RequestMethod.GET)
	public String forgetReset(@PathVariable("code") String code, Model model) throws IOException {
		
		@SuppressWarnings("deprecation")
		String activeCode = IOUtils.toString(Encodes.decodeBase64(code));
		String[] strings = StringUtils.split(activeCode, "&");
		String email = strings[0];
		String time = strings[1];
		if (StringUtils.isBlank(email) || StringUtils.isBlank(time)) {
			model.addAttribute("error", "Illegal request.Please copy from email again. ");
		}
		Date nowDate = new Date();
		Date requestDate = new Date(Long.valueOf(time));
		
		if (nowDate.compareTo(requestDate) > 0) {
			model.addAttribute("error", "This link is outdate.Please regist again, then active. ");
		}
		
		User user = systemService.findUser(email, null);
		if (user == null) {
			model.addAttribute("error", "This account not exist.");
		}
		
		model.addAttribute("user", user);
		
		return "forget-reset";
	}
	
	@RequestMapping(value="/reset",method = RequestMethod.POST)
	public String forgetResetPost(HttpServletRequest request) {
		int userId = Integer.valueOf(request.getParameter("uid"));
		String password = request.getParameter("password");
		User user = systemService.getUser(userId);
		user.setPlainPassword(password);
		systemService.resetPassword(user);
		return "login";
	}
}
