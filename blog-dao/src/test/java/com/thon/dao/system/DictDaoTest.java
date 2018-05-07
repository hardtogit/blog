package com.thon.dao.system;

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
 * @date Dec 20, 2013 3:03:17 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testContent-dao.xml"})
@TransactionConfiguration(defaultRollback = false)
public class DictDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private DictDao dictDao;
	
	
	@Test
	public void testFindType(){
		List<Object> types = dictDao.findTypeList();
		for (Object type : types) {
			System.out.println(type);
		}
	}

}
