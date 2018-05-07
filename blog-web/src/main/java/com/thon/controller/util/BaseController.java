/**
 * 
 */
package com.thon.controller.util;

import com.thon.commons.config.Global;
import com.thon.commons.security.utils.ShiroUser;
import com.thon.commons.utils.DateUtils;
import com.thon.commons.utils.JsoupUtil;
import com.thon.service.utils.UserUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * @author THON
 * @email thon.ju@meet-future.com
 * @date 2011-11-23 下午03:20:23
 * @description:
 */
public abstract class BaseController {
	public static final String PAGE_SIZE = "50";
	private static final Log log = LogFactory.getLog(BaseController.class);
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		//防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : JsoupUtil.clean(text));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : null;
			}
		});
				
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
		});
	}
	
	@ModelAttribute
	public void init(HttpServletRequest request, Model model) {
		
		// 系统全局信息
		model.addAttribute("env", Global.getConfig("env"));
		model.addAttribute("ver", Global.getConfig("ver"));
		
		// 登录用户信息
		if (!SecurityUtils.getSubject().isAuthenticated()){
			return;
		}
		model.addAttribute("curUser", UserUtils.getUser());
		model.addAttribute("curOffice", UserUtils.getOffice());
	}
	
	/**
	 * 取出Shiro中的当前用户Id.
	 */
	protected Integer getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if (user != null && user.id != null) {
			return user.id;
		}else {
			return 0;
		}
		
	}

    /**
     * 格式化进度输出
     * @param progress
     * @return
     */
    protected String fmtProgress(Double progress){
        String result = "待建";
        if (progress >= 1){
            result = "完工";
        }else if(progress > 0 && progress < 1){
            result = "进行中";
        }

        return result;
    }
}
