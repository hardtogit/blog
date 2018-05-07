/**
 * 
 */
package com.thon.dao.post.impl;

import com.thon.commons.persistence.BaseDaoImpl;
import com.thon.dao.post.TermDao;
import com.thon.entity.post.Post;
import com.thon.entity.post.Term;
import com.thon.entity.system.Dict;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author thon
 * @date Sep 5, 2014 11:49:55 AM
 */
@Repository
public class TermDaoImpl extends BaseDaoImpl<Term> implements TermDao {

	public TermDaoImpl() {
		super(Term.class);
	}
	
	@Override
	public List<Term> findByParentIdsLike(String parentIds) {
		String queryString = "FROM Term where parentIds like ?";
		return findList(queryString, parentIds);
	}
	
	@Override
	public List<Term> findListByType(String type) {
		String queryString = "FROM Term where delFlag=? and type = ?";
		return findList(queryString, Term.DEL_FLAG_NORMAL, type);
	}

    @Override
    public Term findByNameType(String name, String type) {
        String queryString = "FROM Term where delFlag=? and name = ? and type = ?";
        return findOne(queryString, Term.DEL_FLAG_NORMAL, name, type);
    }

    @Override
    public List<Map<String, Object>> sumHotTags(int num) {
        String sqlString ="SELECT bt.name, count(1) as total " +
                "FROM bi_post bp " +
                "JOIN bi_post_term bpt ON bp.id = bpt.post_id " +
                "JOIN bi_term bt ON bpt.term_id = bt.id " +
                "AND bt.del_flag = ? and bt.type = ?" +
                "WHERE bp.del_flag = ? and bp.post_status= ? and bp.post_type = ? " +
                "GROUP BY bt.name " +
                "ORDER BY total DESC " +
                "LIMIT ?";
        return findListBySql(sqlString, Map.class, Post.DEL_FLAG_NORMAL, Term.TYPE_POST_TAG, Post.DEL_FLAG_NORMAL, Post.STATUS_PUBLISH,Post.TYPE_POST, num);
    }

    @Override
	public void deleteById(int id, String likeParentIds) {
		String queryString = "update Term set delFlag=? where id = ? or parentIds like ?";
		update(queryString, Term.DEL_FLAG_DELETE, id, likeParentIds);
	}

	@Override
	public List<Term> findAllList() {
		String queryString = "from Term where delFlag=?";
		return findList(queryString, Dict.DEL_FLAG_NORMAL);
	}


}
