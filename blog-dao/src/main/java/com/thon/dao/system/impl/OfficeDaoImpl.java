/**
 * 
 */
package com.thon.dao.system.impl;

import com.thon.commons.persistence.BaseDaoImpl;
import com.thon.commons.persistence.Page;
import com.thon.dao.system.OfficeDao;
import com.thon.entity.system.Office;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author thon
 * @date Jan 2, 2014 11:53:07 AM
 */
@Repository
public class OfficeDaoImpl extends BaseDaoImpl<Office> implements OfficeDao {

	public OfficeDaoImpl() {
		super(Office.class);
	}
	
	@Override
	public List<Office> findByParentIdsLike(String parentIds) {
		String queryString = "FROM Office where parentIds like ?";
		return findList(queryString, parentIds);
	}
	
	@Override
	public void deleteById(int id, String likeParentIds) {
		String queryString = "update Office set delFlag=? where id = ? or parentIds like ?";
		update(queryString, Office.DEL_FLAG_DELETE, id, likeParentIds);
	}

	@Override
	public List<Office> listOffice() {
		String queryString = "FROM Office";
		return findList(queryString);
	}

	@Override
	public List<Office> listCompany(int parentId) {
		String queryString = "FROM Office o WHERE o.parent.id=?";
		return findList(queryString, parentId);
	}

	@Override
	public Page<Office> listOfficePage(Page<Office> page) {
		String queryString="FROM Office";
		return findPage(page, queryString);
	}

}