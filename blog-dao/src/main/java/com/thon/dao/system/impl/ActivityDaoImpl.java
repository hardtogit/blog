/**
 * 
 */
package com.thon.dao.system.impl;

import com.thon.commons.persistence.BaseDaoImpl;
import com.thon.commons.persistence.Page;
import com.thon.dao.system.ActivityDao;
import com.thon.entity.system.Activity;
import org.springframework.stereotype.Repository;

/**
 * @file TrendDaoImpl.java
 * @author thon
 * @email thon.ju@gmail.com
 * @date Aug 20, 2014 9:18:57 AM
 * @description
 */
@Repository
public class ActivityDaoImpl extends BaseDaoImpl<Activity> implements ActivityDao {

	public ActivityDaoImpl() {
		super(Activity.class);
	}

	@Override
	public Page<Activity> findUserActivityPage(Page<Activity> page, int userId) {
		String queryString = "FROM Activity t WHERE t.toId =?  AND t.isRead = null ORDER BY t.createTime desc";
		return findPage(page, queryString, userId);
	}

	@Override
	public Page<Activity> findTeachActivityPage(Page<Activity> page, int userId) {
		String queryString = "FROM Activity t WHERE t.fromId = t.toId AND t.toId in (SELECT ca.userId FROM CourseActor ca WHERE ca.courseId in(SELECT c.id FROM Course c WHERE c.createBy = ?))  ORDER BY t.createTime desc";
		return findPage(page, queryString, userId);
	}

    @Override
    public Page<Activity> findActivityPage(Page<Activity> page, int courseId, int userId, String action){
        String queryString = "FROM Activity t WHERE t.courseId =? AND t.toId =? AND t.action = ? AND t.isRead = null ORDER BY t.createTime desc";
        return findPage(page, queryString, courseId, userId, action);
    }

	@Override
	public Activity getLastActivity(int fromId, String action) {
		String sqlString = "select t.* from sys_activity t where t.from_id = ? and t.action = ? order by t.id desc limit 1";
		return findOneBySql(sqlString, Activity.class, fromId, action);
	}

	@Override
	public long sumMessageCount(int userId) {
		String queryString = "select count(*) from Activity t WHERE t.isRead = 0 AND t.toId = ?";
		return count(queryString, userId);
	}
	
}
