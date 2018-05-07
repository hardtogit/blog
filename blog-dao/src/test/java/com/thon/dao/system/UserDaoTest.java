package com.thon.dao.system;

import com.thon.commons.persistence.Page;
import com.thon.entity.system.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @author thon
 * @date Dec 20, 2013 3:03:17 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testContent-dao.xml"})
@TransactionConfiguration(defaultRollback = false)
public class UserDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private UserDao userDao;
	
	
    //@Test
	public void testFindUserPage(){
		Page<User> page = new Page<User>(10);
		page = userDao.findUserPage(page, "user");
		
		//List<User> users = userDao.findUserPage("user");
		for (User u : page.getResult()) {
			System.out.println(u.getName());
		}
	}

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setName("鞠学见");
        user.setLoginName("ju");
        user.setEmail("thon@gmail.com");
        user.setPassword("12");
        user.setSalt("12");
        userDao.save(user);
    }

}
