/**
 * 
 */
package com.thon.dao.system.impl;

import com.thon.commons.persistence.BaseDaoImpl;
import com.thon.dao.system.LogDao;
import com.thon.entity.system.Log;
import org.springframework.stereotype.Repository;

/**
 * @author thon
 * @date Jan 2, 2014 11:51:04 AM
 */
@Repository
public class LogDaoImpl extends BaseDaoImpl<Log> implements LogDao {

	public LogDaoImpl() {
		super(Log.class);
	}

}
