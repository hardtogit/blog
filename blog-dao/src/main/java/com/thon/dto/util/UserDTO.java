/**
 * 
 */
package com.thon.dto.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.thon.commons.mapper.BeanMapper;
import com.thon.entity.system.User;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @file MemberDTO.java
 * @author thon
 * @email thon.ju@gmail.com
 * @date Sep 12, 2013 5:59:36 PM
 * @description TODO
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserDTO {

	private Integer id;
	private String avatarUrl;
	private String loginName;
	private String name;
	private String loginDate;
	private int courseStatus;
	private Integer delFlag;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public String getLoginDate() {
		return loginDate;
	}
	
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	
	public Integer getDelFlag() {
		return delFlag;
	}
	
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public static UserDTO convert(UserDTO dto, User user){
		dto = BeanMapper.map(user, UserDTO.class);
		return dto;
	}
	public int getCourseStatus() {
		return courseStatus;
	}
	public void setCourseStatus(int courseStatus) {
		this.courseStatus = courseStatus;
	}
	
}
