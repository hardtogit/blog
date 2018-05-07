/**
 * 
 */
package com.thon.service.system;

import com.thon.commons.persistence.Page;
import com.thon.dao.system.ActivityDao;
import com.thon.entity.system.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author thon
 * @email thon.ju@gmail.com
 * @date Aug 20, 2014 9:20:35 AM
 * @description
 */
@Service
@Transactional
public class ActivityService {
	@Autowired
	private ActivityDao activityDao;

    public void saveActivity(Activity trend) {
        activityDao.save(trend);
    }

	public Activity getTrend(int id) {
		return activityDao.get(id);
	}

    public void deleteActivity(Activity trend) {
        activityDao.delete(trend);
    }

	public Activity getLastActivity(int fromId, String action) {
		return activityDao.getLastActivity(fromId, action);
	}

	public Page<Activity> findUserActivityPage(int userId, int pageNo, int pageSize) {
		Page<Activity> page = new Page<Activity>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return activityDao.findUserActivityPage(page, userId);
	}

	public Page<Activity> findTeachActivityPage(int userId, int pageNo, int pageSize) {
		Page<Activity> page = new Page<Activity>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return activityDao.findTeachActivityPage(page, userId);
	}

    public Page<Activity> findActivityPage(int courseId, int userId, String action,int pageNo, int pageSize) {
        Page<Activity> page = new Page<Activity>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        return activityDao.findActivityPage(page, courseId, userId, action);
    }

	public long sumActivityCount(int userId) {
		return activityDao.sumMessageCount(userId);
	}



}
