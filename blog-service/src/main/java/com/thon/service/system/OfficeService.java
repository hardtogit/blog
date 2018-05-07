/**
 * 
 */
package com.thon.service.system;

import com.thon.commons.persistence.Page;
import com.thon.commons.utils.CacheUtils;
import com.thon.commons.utils.StringUtils;
import com.thon.dao.system.OfficeDao;
import com.thon.entity.system.Office;
import com.thon.service.utils.DictUtils;
import com.thon.service.utils.UserUtils;
import org.hibernate.criterion.DetachedCriteria;
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
public class OfficeService {
	
	@Autowired
	private OfficeDao officeDao;
	
	public Office getOffice(int id) {
		return officeDao.get(id);
	}

	public  List<Office> listAllOffices(){
		return  officeDao.listOffice();
	}
	
	@Transactional(readOnly = false)
	public void saveOffice(Office office) {

		office.setParent(this.getOffice(office.getParent().getId()));
		String oldParentIds = office.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds
		office.setParentIds(office.getParent().getParentIds() + office.getParent().getId() + ",");
		officeDao.clear();

		officeDao.save(office);
		// 更新子节点 parentIds
		List<Office> list = officeDao.findByParentIdsLike("%," + office.getId() + ",%");
		for (Office e : list) {
			e.setParentIds(e.getParentIds().replace(oldParentIds, office.getParentIds()));
			officeDao.save(e);
		}

		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delOffice(Office office) {
		officeDao.delete(office);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	public Page<Office> findOffices(Page<Office> page, Office office) {
		DetachedCriteria dc = officeDao.createDetachedCriteria();

		if (StringUtils.isNotEmpty(office.getName())){
			dc.add(Restrictions.like("name", "%"+office.getName()+"%"));
		}

		return officeDao.find(page, dc);
	}

}