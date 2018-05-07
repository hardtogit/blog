/**
 * 
 */
package com.thon.controller.system;

import com.thon.controller.util.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 
 * @author thon
 * @date Apr 11, 2014 7:40:10 PM
 */
@Controller
public class LoginController extends BaseController{
	
	@RequestMapping(value={"/","/login"}, method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, Map<String, Object> map) {
		
		Subject subject = SecurityUtils.getSubject();
		
		if (subject.isAuthenticated()) {
			if (subject.hasRole("admin")) {
				return new ModelAndView(new RedirectView("/admin/bill/manage", true));
			}else {
				return new ModelAndView(new RedirectView("/bill/sum", true));
			}
		}else {
			return new ModelAndView("login");
		}
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ResponseEntity<?> fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		//return "login";

        return new ResponseEntity("false",HttpStatus.OK);
	}
}
