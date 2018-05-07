/**
 * 
 */
package com.thon.service.system;

import com.thon.entity.system.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

/**
 * @author thon
 * @date 2011-11-10 上午11:31:24
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testContent-service.xml"})
@TransactionConfiguration(defaultRollback = false)
public class SystemServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private SystemService systemService;

	@Test
	public void testListAllUser(){
		List<User> list = systemService.listAllUsers();
	}
	
}
