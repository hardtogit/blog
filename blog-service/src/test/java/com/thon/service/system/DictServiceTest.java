/**
 * 
 */
package com.thon.service.system;

import com.thon.commons.persistence.Page;
import com.thon.entity.system.Dict;
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
public class DictServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private DictService dictService;

	@Test
	public void find(){
		Dict dict = new Dict();
		dict.setType("check_type,project_info_type");
		
		Page<Dict> page = new Page<Dict>(100);
		page = dictService.find(page, dict);
		for (Dict dict2 : page.getResult()) {
			System.out.println(dict2.getType());
		}
	}
	
	//@Test
	public void findTypeList(){
		List<String> types = dictService.findTypeList();
		for (String string : types) {
			System.out.println(string);
		}
	}
	
}
