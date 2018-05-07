/**
 * 
 */
package com.thon.dto.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @file MemberDTO.java
 * @author thon
 * @email thon.ju@gmail.com
 * @date Sep 12, 2013 5:59:36 PM
 * @description TODO
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ActivityDTO {

	private Integer id;
	private String action;
	private String content;
	private Integer fromId;
	private String fromUserAvatarUrl;
	private String fromUserName;
	private Integer toId;
	private Date createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAction() {
		return action;
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
	public Integer getFromId() {
		return fromId;
	}
	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}
	public Integer getToId() {
		return toId;
	}
	public void setToId(Integer toId) {
		this.toId = toId;
	}
	public String getFromUserAvatarUrl() {
		return fromUserAvatarUrl;
	}
	public void setFromUserAvatarUrl(String fromUserAvatarUrl) {
		this.fromUserAvatarUrl = fromUserAvatarUrl;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
