package com.thon.controller.admin;

import com.thon.controller.util.BaseController;
import com.thon.entity.post.Post;
import com.thon.entity.system.Dict;
import com.thon.entity.system.Office;
import com.thon.entity.system.User;
import com.thon.service.post.PostService;
import com.thon.service.system.DictService;
import com.thon.service.system.OfficeService;
import com.thon.service.system.SystemService;
import com.thon.service.utils.UserUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @file AdminController.java
 * @author thon
 * @email thon.ju@gmail.com
 * @date Aug 24, 2014 2:20:50 PM
 * @description 系统管理相关控制
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {
	private static final Log log = LogFactory.getLog(AdminController.class);

	@Autowired
	private SystemService systemService;
	@Autowired
	private DictService dictService;
    @Autowired
    private PostService postService;
    @Autowired
    private OfficeService officeService;

	@RequestMapping(method = RequestMethod.GET)
	public String admin() {
		if (!SecurityUtils.getSubject().isAuthenticated()){
			return "login";
		}
		
		return "admin";
	}

    @RequestMapping(value="/bill/manage", method = RequestMethod.GET)
    public String billManage() {
        if (!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }

        return "admin-bill-manage";
    }

    @RequestMapping(value="/post/manage", method = RequestMethod.GET)
    public String postManage() {
        if (!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }

        return "admin-post-manage";
    }

    @RequestMapping(value="/post/form", method = RequestMethod.GET)
    public String postForm(@RequestParam(value="id",required=false) Integer id,Model model) {
        if (!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }

        Post post = new Post();
        post.setAuthor(UserUtils.getUser());
        post.setPostType(Post.TYPE_POST);
        post.setPostStatus(Post.STATUS_PUBLISH);
        if (id != null) {
            post = postService.getPost(id);
        }

        model.addAttribute("post", post);
        return "admin-post-form";
    }

    @RequestMapping(value="/post/category", method = RequestMethod.GET)
    public String postCategory() {
        if (!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }

        return "admin-post-category";
    }

    @RequestMapping(value="/post/tags", method = RequestMethod.GET)
    public String postTags() {
        if (!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }

        return "admin-post-tags";
    }
	
	@RequestMapping(value="/user/manage", method = RequestMethod.GET)
	public String userManage(@RequestParam(value="p", defaultValue="1") Integer pageNo,
			@RequestParam(value="s", defaultValue="20") Integer pageSize,Model model) {
		if (!SecurityUtils.getSubject().isAuthenticated()){
			return "login";
		}

		return "admin-user-manage";
	}
	
	@RequestMapping(value="/user/form", method = RequestMethod.GET)
	public String userForm(@RequestParam(value="id",required=false) Integer id,Model model) {
		if (!SecurityUtils.getSubject().isAuthenticated()){
			return "login";
		}

		User user = new User();
		if (id != null) {
			user = systemService.getUser(id);
		}
		
		model.addAttribute("user", user);
		model.addAttribute("allRoles", systemService.findAllRole());
		return "admin-user-form";
	}

    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public String adminProfile(Model model) {
        if (!SecurityUtils.getSubject().isAuthenticated()){
            return "login";
        }

        int userId = getCurrentUserId();
        model.addAttribute("user", systemService.getUser(userId));
        return "admin-profile";
    }

    @RequestMapping(value="/office/manage", method = RequestMethod.GET)
    public String officeManage(Model model) {

        List<Office> offices = officeService.listAllOffices();

        model.addAttribute("offices", offices);
        return "admin-office-manage";
    }

    @RequestMapping(value="/office/form", method = RequestMethod.GET)
    public String officeForm(@RequestParam(value="id",required=false) Integer id, Model model) {

        Office office = new Office();
        if (id != null){
            office = officeService.getOffice(id);
        }

        model.addAttribute("office", office);
        return "admin-office-form";
    }
	
	@RequestMapping(value="/dict/manage", method = RequestMethod.GET)
	public String dictManage(Model model) {

		List<String> dictTypes = dictService.findTypeList();
		
        model.addAttribute("dictTypes", dictTypes);
		return "admin-dict-manage";
	}
	
	@RequestMapping(value="/dict/form", method = RequestMethod.GET)
	public String dictForm(@RequestParam(value="id",required=false) Integer id, Model model) {
		
		Dict dict = new Dict();
		if (id != null){
			dict = dictService.getDict(id);
		}
		
		model.addAttribute("dict", dict);
		return "admin-dict-form";
	}

}
