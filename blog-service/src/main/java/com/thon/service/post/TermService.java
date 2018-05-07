/**
 * 
 */
package com.thon.service.post;

import com.thon.commons.utils.CacheUtils;
import com.thon.dao.post.TermDao;
import com.thon.entity.post.Term;
import com.thon.service.utils.TermUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author THON
 * @date 2014-9-5 下午09:00:06
 * @description
 */
@Service
@Transactional
public class TermService {
	
	@Autowired
	private TermDao termDao;
	
	public Term getTerm(int id) {
		return termDao.get(id);
	}

    public Term getTerm(String name, String type) {
        return termDao.findByNameType(name, type);
    }

	public void saveTerm(Term term) {

        termDao.clear();
        termDao.save(term);
		
		CacheUtils.remove(TermUtils.CACHE_TERM_MAP);
	}

	public void deleteTerm(int id) {
        termDao.deleteById(id, "%," + id + ",%");
		CacheUtils.remove(TermUtils.CACHE_TERM_MAP);
	}

    public List<Map<String,Object>> sumHotTags(int num){
        return termDao.sumHotTags(num);
    }
	
}