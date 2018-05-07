/**
 * 
 */
package com.thon.dto.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @file MemberDTO.java
 * @author thon
 * @email thon.ju@gmail.com
 * @date Sep 12, 2013 5:59:36 PM
 * @description TODO
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class SessionDTO {

	private Serializable id;
	private Object name;
	private Date startTimestamp;
	private String host;
	private Object agent;
	private Object userId;
	private Object username;

	public Serializable getId() {
		return id;
	}
	public void setId(Serializable id) {
		this.id = id;
	}
	
	public Object getName() {
		return name;
	}
	public void setName(Object name) {
		this.name = name;
	}
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getStartTimestamp() {
		return startTimestamp;
	}
	public void setStartTimestamp(Date startTimestamp) {
		this.startTimestamp = startTimestamp;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Object getAgent() {
		return agent;
	}
	public void setAgent(Object agent) {
		this.agent = agent;
	}
	public Object getUserId() {
		return userId;
	}
	public void setUserId(Object userId) {
		this.userId = userId;
	}
	public Object getUsername() {
		return username;
	}
	public void setUsername(Object username) {
		this.username = username;
	}

}
