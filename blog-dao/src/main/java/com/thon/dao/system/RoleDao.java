/**
 * 
 */
package com.thon.dao.system;

import com.thon.commons.persistence.BaseDao;
import com.thon.entity.system.Role;

/**
 * @author thon
 * @date Jan 2, 2014 11:45:02 AM
 */
public interface RoleDao extends BaseDao<Role> {
	
	void deleteById(int id);
	
	Role findByName(String name);
}
