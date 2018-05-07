/**
 * 
 */
package com.thon.task.system;

import com.thon.task.Task;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author THON
 * @email thon.ju@meet-future.com
 * @date 2011-11-27 下午04:36:02
 * @description:
 */
@Aspect
@Component
public class SystemAspect {
	
	@Autowired
	private Task registEmailTask;
	@Autowired
	private Task forgetEmailTask;
	
	/**
//	 * 激活用户
//	 * @param jp
//	 */
//	@AfterReturning("execution(*  com.thon.service.system.SystemService.saveUser(..))")
//	public void doUpdateBookingStatus(JoinPoint jp) {
//		Map<Object, Object> context =new HashMap<Object, Object>();
//		User user = (User)jp.getArgs()[0];
//		context.put("user", user);
//
//		registEmailTask.doAsyncTask(context);
//	}
//
//	/**
//	 * 忘记密码
//	 * @param jp
//	 */
//	@AfterReturning("execution(*  com.thon.service.system.SystemService.forgetPassword(..))")
//	public void doForgetPassword(JoinPoint jp) {
//		Map<Object, Object> context =new HashMap<Object, Object>();
//		User user = (User)jp.getArgs()[0];
//		context.put("user", user);
//
//		forgetEmailTask.doAsyncTask(context);
//	}
}
