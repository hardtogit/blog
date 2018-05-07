package com.thon.entity.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thon.commons.persistence.IdEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="sys_activity")
@DynamicInsert @DynamicUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
public class Activity extends IdEntity{

    public static final String ACT_TEAM_UPDATE = "team_update";
    public static final String ACT_TEAM_JOIN = "team_join";
    public static final String ACT_COURSE_UPDATE = "course_update";
    public static final String ACT_COURSE_JOIN = "course_join";
    public static final String ACT_DECISION_UPDATE = "decision_update";
    public static final String ACT_PLAN_UPDATE = "plan_update";

	private static final long serialVersionUID = 1904246394671635563L;
	private String action;
	private String content;
	private Integer toId;
	private Integer courseId;
	private Integer isRead;
	private Date createTime;

    private User fromUser;
	
	public String getAction() {
		return action;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getToId() {
		return toId;
	}
	public void setToId(Integer toId) {
		this.toId = toId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fromId")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }
}
