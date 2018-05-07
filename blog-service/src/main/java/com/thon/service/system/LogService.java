/**
 * 
 */
package com.thon.service.system;

import com.thon.commons.persistence.Page;
import com.thon.commons.utils.DateUtils;
import com.thon.commons.utils.StringUtils;
import com.thon.dao.system.LogDao;
import com.thon.entity.system.Log;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @author thon
 * @date Dec 23, 2013 4:13:59 PM
 */
@Service
@Transactional
public class LogService{
	
	@Autowired
	private LogDao logDao;

	public void addLog(Log log) {
		logDao.save(log);
	}

	public Page<Log> find(Page<Log> page, Map<String, Object> paramMap) {
		DetachedCriteria dc = logDao.createDetachedCriteria();

		Long createById = StringUtils.toLong(paramMap.get("createById"));
		if (createById > 0){
			dc.add(Restrictions.eq("createBy.id", createById));
		}
		
		String requestUri = ObjectUtils.toString(paramMap.get("requestUri"));
		if (StringUtils.isNotBlank(requestUri)){
			dc.add(Restrictions.like("requestUri", "%"+requestUri+"%"));
		}

		String exception = ObjectUtils.toString(paramMap.get("exception"));
		if (StringUtils.isNotBlank(exception)){
			dc.add(Restrictions.eq("type", Log.TYPE_EXCEPTION));
		}
		
		Date beginDate = DateUtils.parseDate(paramMap.get("beginDate"));
		if (beginDate == null){
			beginDate = DateUtils.setDays(new Date(), 1);
			paramMap.put("beginDate", DateUtils.formatDate(beginDate, "yyyy-MM-dd"));
		}
		Date endDate = DateUtils.parseDate(paramMap.get("endDate"));
		if (endDate == null){
			endDate = DateUtils.addDays(DateUtils.addMonths(beginDate, 1), -1);
			paramMap.put("endDate", DateUtils.formatDate(endDate, "yyyy-MM-dd"));
		}
		dc.add(Restrictions.between("createTime", beginDate, endDate));
		
		dc.addOrder(Order.desc("id"));
		return logDao.find(page, dc);
	}

}
