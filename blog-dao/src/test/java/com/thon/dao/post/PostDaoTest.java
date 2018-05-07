package com.thon.dao.post;

import com.thon.entity.post.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;
import java.util.Map;

/**
 * @author thon
 * @date Aug 30, 2014 3:03:17 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testContent-dao.xml"})
@TransactionConfiguration(defaultRollback = false)
public class PostDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private PostDao postDao;

    @Test
    public void sumMonth(){
        List<Map<String, Object>> list = postDao.sumMonth(Post.STATUS_PUBLISH,Post.TYPE_POST);
        for (Map<String, Object> map : list) {
            System.out.print(map.get("year") + "    ");
            System.out.print(map.get("month") + "    ");
            System.out.print(map.get("num") + "    ");
            System.out.println();
        }
    }

}
