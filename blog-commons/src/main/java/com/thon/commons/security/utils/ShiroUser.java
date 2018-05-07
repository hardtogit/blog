/**
 * 
 */
package com.thon.commons.security.utils;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author thon
 * @date Dec 23, 2013 3:59:37 PM
 */
public class ShiroUser implements Serializable{
	private static final long serialVersionUID = -1373760761780840081L;
	public Integer id;
	public String loginName;
	public String name;
	public String avatarUrl;
	private Map<String, Object> cacheMap;

	public ShiroUser(Integer id, String loginName, String name, String avatarUrl) {
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.avatarUrl = avatarUrl;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public String getName() {
		return name;
	}
	
	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return loginName;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	/**
	 * 重载equals,只计算loginName;
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "loginName");
	}

	/**
	 * 重载equals,只比较loginName
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "loginName");
	}
	
	public Map<String, Object> getCacheMap() {
		if (cacheMap==null){
			cacheMap = new HashMap<String, Object>();
		}
		return cacheMap;
	}
}
