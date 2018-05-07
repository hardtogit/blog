/**
 * 
 */
package com.thon.dao.system;

import com.thon.commons.persistence.BaseDao;
import com.thon.entity.system.Dict;

import java.util.List;

/**
 * @author thon
 * @date Jan 2, 2014 11:42:55 AM
 */
public interface DictDao extends BaseDao<Dict> {
	
	List<Dict> findAllList();

    List<Dict> findByType(String type);
	
	public void deleteById(int id);

	public List<Object> findTypeList();
}
