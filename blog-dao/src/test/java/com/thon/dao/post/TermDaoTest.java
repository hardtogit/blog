package com.thon.dao.post;

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
 * @date Sep 05, 2014 3:03:17 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:testContent-dao.xml"})
@TransactionConfiguration(defaultRollback = false)
public class TermDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private TermDao termDao;

    @Test
    public void sumHotTags(){
        List<Map<String, Object>> list = termDao.sumHotTags(10);
        for (Map<String, Object> map : list) {
            System.out.print(map.get("name") + "    ");
            System.out.print(map.get("total") + "    ");
            System.out.println();
        }
    }

}
