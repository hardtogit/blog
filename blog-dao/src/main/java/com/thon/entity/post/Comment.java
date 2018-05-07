package com.thon.entity.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import com.thon.commons.persistence.IdEntity;
import com.thon.entity.system.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Created by thon on 8/24/14.
 */
@Entity
@Table(name = "bi_comment")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment extends IdEntity{

    private String commentAuthor;
    private String commentAuthorEmail;
    private String commentAuthorUrl;
    private String commentAuthorIp;
    private Date commentDate;
    private String commentContent;
    private String commentAgent;
    private String commentType;

    private Post post;
    private User author;
    private Comment parent;

    private List<Comment> childList = Lists.newArrayList(); // 拥有子评论

    public String getCommentAuthor() {
        if (author != null && author.getName() != null){
            return author.getName();
        }

        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public String getCommentAuthorEmail() {
        return commentAuthorEmail;
    }

    public void setCommentAuthorEmail(String commentAuthorEmail) {
        this.commentAuthorEmail = commentAuthorEmail;
    }

    public String getCommentAuthorUrl() {
        return commentAuthorUrl;
    }

    public void setCommentAuthorUrl(String commentAuthorUrl) {
        this.commentAuthorUrl = commentAuthorUrl;
    }

    public String getCommentAuthorIp() {
        return commentAuthorIp;
    }

    public void setCommentAuthorIp(String commentAuthorIp) {
        this.commentAuthorIp = commentAuthorIp;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentAgent() {
        return commentAgent;
    }

    public void setCommentAgent(String commentAgent) {
        this.commentAgent = commentAgent;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentParent")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    @OneToMany(cascade = {CascadeType.ALL},fetch=FetchType.LAZY,mappedBy="parent")
    @Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
    @NotFound(action = NotFoundAction.IGNORE)
    public List<Comment> getChildList() {
        return childList;
    }

    public void setChildList(List<Comment> childList) {
        this.childList = childList;
    }

    @Transient
    public static void sortList(List<Comment> list, List<Comment> sourcelist, Integer parentId){
        for (int i=0; i<sourcelist.size(); i++){
            Comment e = sourcelist.get(i);
            if (e.getParent()!=null && e.getParent().getId()!=null
                    && e.getParent().getId().equals(parentId)){
                list.add(e);
                // 判断是否还有子节点, 有则继续获取子节点
                for (int j=0; j<sourcelist.size(); j++){
                    Comment child = sourcelist.get(j);
                    if (child.getParent()!=null && child.getParent().getId()!=null
                            && child.getParent().getId().equals(e.getId())){
                        sortList(list, sourcelist, e.getId());
                        break;
                    }
                }
            }
        }
    }

    @Transient
    public int getParentId(){
        if (parent != null){
            return parent.getId();
        }

        return 0;
    }
}
