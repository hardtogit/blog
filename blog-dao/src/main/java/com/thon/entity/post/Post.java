package com.thon.entity.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import com.thon.commons.persistence.IdEntity;
import com.thon.commons.utils.JsoupUtil;
import com.thon.commons.utils.StringUtils;
import com.thon.entity.system.User;
import org.apache.commons.lang3.StringEscapeUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Created by thon on 8/24/14.
 */
@Entity
@Table(name = "bi_post")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post extends IdEntity{

    public static final String STATUS_DRAFT = "draft";
    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_PUBLISH = "publish";
    public static final String TYPE_POST = "post";
    public static final String TYPE_ATTACHMENT= "attachment";

    private Date postDate;
    private String postContent;
    private String postTitle;
    private String postStatus;
    private String commentStatus = "open";
    private Date postModified;
    private String postType;
    private String postMimeType;
    private Integer commentCount;

    private User author;
    private Post parent;

    private List<Post> childList = Lists.newArrayList();
    private List<Term> termList = Lists.newArrayList();

    public Post(){
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getPostContent() {
        return StringEscapeUtils.unescapeHtml4(postContent);
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getPostModified() {
        return postModified;
    }

    public void setPostModified(Date postModified) {
        this.postModified = postModified;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostMimeType() {
        return postMimeType;
    }

    public void setPostMimeType(String postMimeType) {
        this.postMimeType = postMimeType;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postAuthor")
    @NotFound(action = NotFoundAction.IGNORE)
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postParent")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    public Post getParent() {
        return parent;
    }

    public void setParent(Post parent) {
        this.parent = parent;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="parent")
    @Where(clause="delFlag="+DEL_FLAG_NORMAL)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    public List<Post> getChildList() {
        return childList;
    }

    public void setChildList(List<Post> childList) {
        this.childList = childList;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "bi_post_term", joinColumns = { @JoinColumn(name = "post_id") }, inverseJoinColumns = { @JoinColumn(name = "term_id") })
    @Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
    @OrderBy("id") @Fetch(FetchMode.SUBSELECT)
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    public List<Term> getTermList() {
        return termList;
    }

    public void setTermList(List<Term> termList) {
        this.termList = termList;
    }

    @Transient
    public String getTermNames() {
        List<String> nameList = Lists.newArrayList();
        for (Term term : termList) {
            nameList.add(term.getName());
        }
        return StringUtils.join(nameList, ",");
    }

    @Transient
    public String getPostAbstract(){
        String content = StringEscapeUtils.unescapeHtml4(postContent);
        if (StringUtils.isNotBlank(content)){
            content = JsoupUtil.truncateHTML(content, 800);
        }

        return content;
    }

    @Transient
    public String getPostShareText(){
        String content = StringEscapeUtils.unescapeHtml4(postContent);
        if (StringUtils.isNotBlank(content)){
            content = JsoupUtil.truncatEscapeHTML(content, 200);
        }

        return content;
    }
}
