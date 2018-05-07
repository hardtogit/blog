package com.thon.service.post;

import com.thon.commons.persistence.Page;
import com.thon.commons.utils.DateUtils;
import com.thon.commons.utils.StringUtils;
import com.thon.dao.post.CommentDao;
import com.thon.dao.post.PostDao;
import com.thon.entity.post.Comment;
import com.thon.entity.post.Post;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by thon on 8/24/14.
 */
@Service
@Transactional
public class PostService {

    @Autowired
    private PostDao postDao;
    @Autowired
    private CommentDao commentDao;

    public Post getPost(int id){
        return postDao.get(id);
    }

    public void savePost(Post post){
        postDao.save(post);
    }

    public void delPost(Post post){
        postDao.delete(post);
    }

    public Comment getComment(int id){
        return commentDao.get(id);
    }

    public void saveComment(Comment comment){
        commentDao.save(comment);
    }

    public void delComment(Comment comment){
        commentDao.delete(comment);
    }

    public Page<Post> findPosts(Page<Post> page, Post post, String tags) {
        DetachedCriteria dc = postDao.createDetachedCriteria();

        if (post.getPostDate() != null){
            Date beginDate = DateUtils.truncate(post.getPostDate(), Calendar.MONTH);
            Date endDate = DateUtils.ceiling(post.getPostDate(), Calendar.MONTH);
            dc.add(Restrictions.between("postDate", beginDate, endDate));
        }
        if (StringUtils.isNotBlank(post.getPostStatus())){
            dc.add(Restrictions.eq("postStatus",post.getPostStatus()));
        }
        if (StringUtils.isNotBlank(post.getPostType())){
            dc.add(Restrictions.eq("postType",post.getPostType()));
        }
        if (StringUtils.isNotBlank(tags)) {
            dc.createAlias("termList","tm").add(Restrictions.in("tm.name", StringUtils.split(tags, ",")));
        }
        if (post.getDelFlag() != null){
            dc.add(Restrictions.eq("delFlag",post.getDelFlag()));
        }
        dc.addOrder(Order.desc("id"));
        return postDao.find(page, dc);
    }

    public Page<Comment> findComments(Page<Comment> page, Integer postId, Boolean exchild) {
        DetachedCriteria dc = commentDao.createDetachedCriteria();

        if (postId != null){
            dc.add(Restrictions.eq("post.id",postId));
        }
        if (exchild){
            dc.add(Restrictions.isNull("parent.id"));
        }
        dc.addOrder(Order.desc("commentDate"));

        return commentDao.find(page, dc);
    }

    public List<Map<String, Object>> sumMonth(){

        return postDao.sumMonth(Post.STATUS_PUBLISH,Post.TYPE_POST);
    };

}
