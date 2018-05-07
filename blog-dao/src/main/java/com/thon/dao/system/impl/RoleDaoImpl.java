/**
 * 
 */
package com.thon.dao.system.impl;

import com.thon.commons.persistence.BaseDaoImpl;
import com.thon.dao.system.RoleDao;
import com.thon.entity.system.Role;
import org.springframework.stereotype.Repository;

/**
 * @author thon
 * @date Jan 2, 2014 11:54:27 AM
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	public RoleDaoImpl() {
		super(Role.class);
	}

	@Override
	public void deleteById(int id) {
		String queryString = "update Role set delFlag=? where id = ?";
		update(queryString, Role.DEL_FLAG_DELETE, id);
	}

	@Override
	public Role findByName(String name) {
		String queryString = "FROM Role where name = ? AND delFlag = ?";
		return findOne(queryString, name, Role.DEL_FLAG_NORMAL);
	}

}
