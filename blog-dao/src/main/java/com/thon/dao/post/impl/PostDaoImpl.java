/**
 * 
 */
package com.thon.dao.post.impl;

import com.thon.commons.persistence.BaseDaoImpl;
import com.thon.dao.post.PostDao;
import com.thon.entity.post.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author thon
 * @date Aug 24, 2014 11:51:04 AM
 */
@Repository
public class PostDaoImpl extends BaseDaoImpl<Post> implements PostDao {

	public PostDaoImpl() {
		super(Post.class);
	}

    @Override
    public List<Map<String, Object>> sumMonth(String status, String type) {
        String sqlString = "SELECT YEAR (post_date) as year, LPAD(MONTH (post_date),2,'0') as month, COUNT(1) as num " +
                "FROM bi_post " +
                "WHERE post_status = ? AND post_type = ? " +
                "GROUP BY YEAR (post_date), MONTH (post_date) " +
                "ORDER BY YEAR (post_date) DESC, MONTH (post_date) DESC";
        return findListBySql(sqlString, Map.class, status, type);
    }
}
