/**
 * 
 */
package com.thon.dao.post;

import com.thon.commons.persistence.BaseDao;
import com.thon.entity.post.Term;

import java.util.List;
import java.util.Map;

/**
 * @author thon
 * @date Sep 5, 2014 11:42:55 AM
 */
public interface TermDao extends BaseDao<Term> {
	
	List<Term> findByParentIdsLike(String parentIds);
	
	void deleteById(int id, String likeParentIds);
	
	List<Term> findAllList();
	
	List<Term> findListByType(String type);

    Term findByNameType(String name, String type);

    List<Map<String, Object>> sumHotTags(int num);

}
