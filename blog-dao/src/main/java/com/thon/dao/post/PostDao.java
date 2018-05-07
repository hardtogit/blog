/**
 * 
 */
package com.thon.dao.post;

import com.thon.commons.persistence.BaseDao;
import com.thon.entity.post.Post;

import java.util.List;
import java.util.Map;

/**
 * @author thon
 * @date Aug 24, 2014 11:43:28 AM
 */
public interface PostDao extends BaseDao<Post> {

    public List<Map<String, Object>> sumMonth(String status, String type);

}
