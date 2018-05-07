/**
 * 
 */
package com.thon.dao.post.impl;

import com.thon.commons.persistence.BaseDaoImpl;
import com.thon.dao.post.CommentDao;
import com.thon.entity.post.Comment;
import org.springframework.stereotype.Repository;

/**
 * @author thon
 * @date Aug 24, 2014 11:51:04 AM
 */
@Repository
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao {

	public CommentDaoImpl() {
		super(Comment.class);
	}

}
