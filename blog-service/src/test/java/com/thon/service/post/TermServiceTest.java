/**
 * 
 */
package com.thon.service.post;

import com.thon.entity.post.Term;
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
public class TermServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private TermService termService;
	
	@Test
	public void getTerm(){
        Term term = termService.getTerm("etst","post_tag");
        System.out.println(term);
	}
	
}
