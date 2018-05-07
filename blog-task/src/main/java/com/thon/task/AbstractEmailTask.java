package com.thon.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

/**
 * @author THON
 * @email thon.ju@meet-future.com
 * @date 2011-11-27 下午09:29:33
 * @description:
 */
public abstract class AbstractEmailTask extends AbstractTask {

	@Autowired
	private JavaMailSender javaMailSender;

	public AbstractEmailTask() {
	}

	protected void doTask(final Map<Object, Object> context) {
		JavaMailSenderImpl senderImpl  = ((JavaMailSenderImpl)javaMailSender);
		
		Properties prop  =   new  Properties() ;
		prop.put( "mail.smtp.auth" ,  " true" ) ;  //  将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确 
		prop.put( "mail.smtp.timeout" ,  "25000" ) ; 
		senderImpl.setJavaMailProperties(prop);  
		
		final String from = senderImpl.getUsername();
		MimeMessagePreparator preparator = new MimeMessagePreparator(){
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				// default from use @code JavaMailSender, @code AbstractEmailTask Implementer can override it
				helper.setFrom(from);
				prepareMessage(helper, context);
			}
		};
		
		javaMailSender.send(preparator);
	}

	protected abstract void prepareMessage(MimeMessageHelper helper, Map<Object, Object> context) throws Exception;
	
	
}
