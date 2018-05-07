/**
 * 
 */
package com.thon.task.post;

import com.thon.entity.post.Comment;
import com.thon.task.Task;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author THON
 * @email thon.ju@gmail.com
 * @date 2014-08-31 下午04:36:02
 * @description:
 */
@Aspect
@Component
public class PostAspect {
	
	@Autowired
	private Task commentAddTask;
	
	/**
	 * 评论数量统计
	 * @param jp
	 */
    @Before("execution(*  com.thon.service.post.PostService.saveComment(..))")
	public void doUpdateBookingStatus(JoinPoint jp) {
		Map<Object, Object> context =new HashMap<Object, Object>();
		Comment comment = (Comment)jp.getArgs()[0];

        if (comment.getId() == null && comment.getPost() != null){
            context.put("postId", comment.getPost().getId());
            commentAddTask.doAsyncTask(context);
        }
	}

}
