package com.thon.task.system;

import com.thon.commons.config.Global;
import com.thon.commons.utils.Encodes;
import com.thon.commons.utils.Validation;
import com.thon.entity.system.User;
import com.thon.task.AbstractEmailTask;
import com.thon.task.annotation.Task;
import freemarker.template.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @file RegistEmailTask.java
 * @author thon
 * @email thon.ju@gmail.com
 * @date Jul 10, 2013 9:55:31 AM
 * @description TODO
 */
@Task
public class RegistEmailTask extends AbstractEmailTask {
	private static final Log log = LogFactory.getLog(RegistEmailTask.class);

	private static final String FROM = "email/from.ftl";
	private static final String TITLE = "email/title.ftl";
	private static final String CONTENT = "email/regist_content.ftl";
	
	@Autowired
	private Configuration configuration;
	
	@Override
	protected void prepareMessage(MimeMessageHelper helper, Map<Object, Object> context) throws Exception {
		
		User user = (User)context.get("user");
		String mailTo = user.getLoginName();
		String userFullName = user.getName();
		String activeUrl = activeUrl(mailTo);
		if (Validation.checkEmail(mailTo)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("from", Global.getConfig("mail.from"));
			map.put("title", "[成都道简科技有限公司]会员注册成功，请激活账号！");
			map.put("user", user);
			map.put("userFullName", userFullName);
			map.put("activeUrl", Global.getConfig("mail.url")+"/regist/active/"+activeUrl);
			
			String from = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(FROM), map);
			String title = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(TITLE), map);
			String content = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(CONTENT), map);
			
			helper.setFrom(from);
			helper.setSubject(title);
			helper.setText(content, true);
			helper.addTo(mailTo);
		}
		
	}
	
	public static String activeUrl(String email) {
		Date date  = new Date();
		Long time = date.getTime() + 3*24*3600*1000;
		
		String url = email+"&"+time;
		return Encodes.encodeUrlSafeBase64(url.getBytes());
	}

}
