/**
 * 
 */
package com.thon.service.post;

import com.thon.commons.persistence.Page;
import com.thon.entity.post.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @author thon
 * @date 2014-09-05 上午11:31:24
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testContent-service.xml"})
@TransactionConfiguration(defaultRollback = false)
public class PostServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private PostService postService;
	
	@Test
	public void getPost(){
        Post post = postService.getPost(4);
        System.out.println(post.getPostShareText());
        System.out.println(post.getPostAbstract());
        System.out.println("why ========" + post.getTermNames());
	}

    @Test
    public void findPosts(){
        Post post = new Post();
        Page<Post> page = new Page<Post>();
        page.setPageNo(1);
        page.setPageSize(10);

        page = postService.findPosts(page,post,"nih");
        for (Post result : page.getResult()) {
            System.out.println(result.getPostTitle());
        }
    }
	
}
