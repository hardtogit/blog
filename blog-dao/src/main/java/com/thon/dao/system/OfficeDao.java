/**
 * 
 */
package com.thon.dao.system;

import com.thon.commons.persistence.BaseDao;
import com.thon.commons.persistence.Page;
import com.thon.entity.system.Office;

import java.util.List;

/**
 * @author thon
 * @date Jan 2, 2014 11:44:29 AM
 */
public interface OfficeDao extends BaseDao<Office> {
	
	void deleteById(int id, String likeParentIds);
	
	List<Office> findByParentIdsLike(String parentIds);
	
	List<Office> listOffice();

	List<Office> listCompany(int parentId);
	
	Page<Office> listOfficePage(Page<Office> page);
}