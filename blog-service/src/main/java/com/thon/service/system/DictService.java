/**
 * 
 */
package com.thon.service.system;

import com.google.common.collect.Lists;
import com.thon.commons.persistence.Page;
import com.thon.commons.utils.CacheUtils;
import com.thon.commons.utils.StringUtils;
import com.thon.dao.system.DictDao;
import com.thon.entity.system.Dict;
import com.thon.service.utils.DictUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author THON
 * @mail thon.ju@meet-future.com
 * @date 2012-2-13 下午09:00:06
 * @description
 */
@Service
@Transactional
public class DictService{
	
	@Autowired
	private DictDao dictDao;
	
	public Dict getDict(int id) {
		return dictDao.get(id);
	}
	
	public Page<Dict> find(Page<Dict> page, Dict dict) {
		DetachedCriteria dc = dictDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(dict.getType())){
			String[] params = dict.getType().split(",");
			dc.add(Restrictions.in("type", params));
		}
		if (StringUtils.isNotEmpty(dict.getDescription())){
			dc.add(Restrictions.like("description", "%"+dict.getDescription()+"%"));
		}
		dc.add(Restrictions.eq(Dict.DEL_FLAG, Dict.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("type")).addOrder(Order.asc("sort")).addOrder(Order.desc("id"));
		return dictDao.find(page, dc);
	}
	
	public List<String> findTypeList(){
		List<String> distTypes = Lists.newArrayList();
		List<Object> types = dictDao.findTypeList();
		for (Object object : types) {
			distTypes.add(object.toString());
		}
		return distTypes;
	}
	
	@Transactional(readOnly = false)
	public void saveDict(Dict dict) {
		
		dictDao.clear();
		dictDao.save(dict);
		
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}
	
	@Transactional(readOnly = false)
	public void deleteDict(int id) {
		dictDao.deleteById(id);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}
	
}