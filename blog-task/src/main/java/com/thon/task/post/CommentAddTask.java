/**
 * 
 */
package com.thon.task.post;

import com.thon.entity.post.Post;
import com.thon.service.post.PostService;
import com.thon.task.AbstractTask;
import com.thon.task.annotation.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Task
public class CommentAddTask extends AbstractTask {

	@Autowired
	private PostService postService;

	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {
        int postId = context.get("postId") != null ? (Integer)context.get("postId") : 0;
        Post post = postService.getPost(postId);
        post.setCommentCount(post.getCommentCount() + 1);
        postService.savePost(post);
	}

}
