/**
 * 
 */
package com.thon.controller.rest;

import com.thon.commons.persistence.Page;
import com.thon.controller.util.BaseController;
import com.thon.entity.system.User;
import com.thon.service.system.SystemService;
import com.thon.service.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author thon
 * @email thon.ju@gmail.com
 * @date Aug 19, 2014 7:46:21 PM
 * @description
 */
@Controller
@RequestMapping("/api/user")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserRestController extends BaseController{
	
	@Autowired
	private SystemService systemService;
	
	private static final Log log = LogFactory.getLog(UserRestController.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> list(@RequestParam(value = "p", defaultValue = "1") int pageNo,
                                  @RequestParam(value = "s", defaultValue = PAGE_SIZE) int pageSize,
                                  User user) {

        Page<User> page = new Page<User>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        page = systemService.findUsers(page, user);

        return new ResponseEntity(page, HttpStatus.OK);
    }

	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseEntity<?> apiUserAdd(@Valid User user) {
		systemService.saveUser(user);
		return new ResponseEntity(true, HttpStatus.OK);
	}

    @RequestMapping(value="/del", method = RequestMethod.POST)
    public ResponseEntity<?> apiUserDel(@RequestParam("id") int id) {
        if(UserUtils.getUser().isAdmin()){
            User user = systemService.getUser(id);
            systemService.deleteUser(user);

            return new ResponseEntity("true", HttpStatus.OK);
        }else {
            return new ResponseEntity("false", HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value="/profile/update", method = RequestMethod.POST)
    public ResponseEntity<?> apiProfileUpdate(@Valid User user) {

        User oldUser = systemService.getUser(user.getId());
        oldUser.setName(user.getName());
        oldUser.setPhone(user.getPhone());
        oldUser.setEmail(user.getEmail());
        systemService.saveUser(oldUser);

        return new ResponseEntity(true, HttpStatus.OK);
    }

    @RequestMapping(value="/profile/avatar/update", method = RequestMethod.POST)
    public ResponseEntity<?> apiProfileAvatarUpdate(HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getParameter("id"));
        Integer avatar = Integer.valueOf(request.getParameter("avatar"));

        User user = systemService.getUser(userId);
        user.setAvatar(avatar);
        systemService.saveUser(user);

        return new ResponseEntity(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/account/check", method = RequestMethod.GET)
    public ResponseEntity<?> apiUserAccountCheck(@RequestParam("loginName") String loginName,
                                                 @RequestParam(value = "uid", defaultValue = "0") int userId) {
        User user = systemService.findUser(loginName, null);
        if ( user== null || user.getId().equals(userId)) {
            return new ResponseEntity("true", HttpStatus.OK);
        } else {
            return new ResponseEntity("false", HttpStatus.OK);
        }
    }
	
	@RequestMapping(value = "/email/check", method = RequestMethod.GET)
	public ResponseEntity<?> apiUserEmailCheck(@RequestParam("email") String email,
                                               @RequestParam(value = "uid", defaultValue = "0") int userId) {

        User user = systemService.findUserByEmail(email, null);
        if ( user== null || user.getId().equals(userId)) {
            return new ResponseEntity("true", HttpStatus.OK);
		} else {
            return new ResponseEntity("false", HttpStatus.OK);
		}
	}

    @RequestMapping(value = "/phone/check", method = RequestMethod.GET)
    public ResponseEntity<?> apiUserPhoneCheck(@RequestParam("phone") String phone,
                                               @RequestParam(value = "uid", defaultValue = "0") int userId) {

        User user = systemService.findUserByPhone(phone, null);
        if ( user== null || user.getId().equals(userId)) {
            return new ResponseEntity("true", HttpStatus.OK);
        } else {
            return new ResponseEntity("false", HttpStatus.OK);
        }
    }
	
	@RequestMapping(value = "/pwd/check", method = RequestMethod.GET)
	public ResponseEntity<?> apiUserPasswordCheck(@RequestParam("uid") int userId,
			@RequestParam("pwd") String password) {
		User user = systemService.getUser(userId);
		String hashPwd = systemService.entryptPassword(password, user.getSalt());
		if (StringUtils.equals(hashPwd, user.getPassword())) {
            return new ResponseEntity("true", HttpStatus.OK);
		}else {
            return new ResponseEntity("false", HttpStatus.OK);
		}	
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/pwd/update", method = RequestMethod.POST)
	public ResponseEntity<?> apiProfilePwdUpdate(HttpServletRequest request) {
		
		Integer userId = Integer.valueOf(request.getParameter("uid"));
		String password = request.getParameter("plainPassword");
		
		User user = systemService.getUser(userId);
		String hashPwd = systemService.entryptPassword(password, user.getSalt());
		user.setPassword(hashPwd);
		systemService.saveUser(user);

		return new ResponseEntity(true, HttpStatus.CREATED);
	}

}
