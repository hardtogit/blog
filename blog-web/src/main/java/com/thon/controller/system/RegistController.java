package com.thon.controller.system;

import com.google.common.collect.Lists;
import com.thon.commons.utils.Encodes;
import com.thon.controller.util.BaseController;
import com.thon.entity.system.Role;
import com.thon.entity.system.User;
import com.thon.service.system.SystemService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @file RegistController.java
 * @author thon
 * @email thon.ju@gmail.com
 * @date Aug 16, 2013 9:55:46 AM
 * @description 用户注册相关控制器
 */
@Controller
@RequestMapping(value = "/regist")
public class RegistController extends BaseController{
	private static final Log log = LogFactory.getLog(RegistController.class);

	@Autowired
	private SystemService systemService;

	@RequestMapping(method = RequestMethod.GET)
	public String register(Model model) {

        List<Role> allRoles = Lists.newArrayList();
        List<Role> roles = systemService.findAllRole();
        for (Role role : roles) {
            if (!role.getEnname().equals("admin")){
                allRoles.add(role);
            }
        }

        model.addAttribute("allRoles", allRoles);
		return "regist";
	}

	@RequestMapping(value="/active/{code}", method = RequestMethod.GET)
	public String registerActive(@PathVariable("code") String code, Model model) throws IOException {
		
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
		
		log.debug("active email : "+ email);
		log.debug("active time : "+ requestDate);
		
		if (nowDate.compareTo(requestDate) > 0) {
			model.addAttribute("error", "This link is outdate.Please regist again, then active. ");
		}
		
		User user = systemService.findUser(email, null);
		if (user != null && user.getDelFlag() == User.DEL_FLAG_AUDIT) {
			user.setDelFlag(User.DEL_FLAG_NORMAL);
			systemService.saveUser(user);
			model.addAttribute("success", "Active success.");
		}else if (user != null && user.getDelFlag() == User.DEL_FLAG_NORMAL){
			model.addAttribute("success", "This account has been actived already.");
		}else if (user != null && user.getDelFlag() == User.DEL_FLAG_DELETE){
			model.addAttribute("error", "This account is outdate.Please regist again, then active. ");
		}
		
		return "regist-active";
	}
}
