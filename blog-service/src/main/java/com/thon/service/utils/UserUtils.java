
package com.thon.service.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thon.commons.security.utils.ShiroUser;
import com.thon.commons.utils.CacheUtils;
import com.thon.commons.utils.SpringContextHolder;
import com.thon.commons.utils.StringUtils;
import com.thon.dao.system.OfficeDao;
import com.thon.dao.system.UserDao;
import com.thon.entity.system.Office;
import com.thon.entity.system.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.subject.Subject;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * 用户工具类
 * @author thon
 * @date Dec 31, 2013 7:00:11 PM
 */
public class UserUtils extends BaseService{
	
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static OfficeDao officeDao = SpringContextHolder.getBean(OfficeDao.class);

	public static final String CACHE_USER = "user";
	public static final String CACHE_USER_OFFICE = "userOffice";
	public static final String CACHE_PAGE_SIZE = "userPageSize";
	public static final String CACHE_MENU_LIST = "menuList";
	public static final String CACHE_AREA_LIST = "areaList";
	public static final String CACHE_OFFICE_LIST = "officeList";
	public static final String CACHE_OFFICE_LIST_CODE = "officeListCode";
	
	public static User getUser(){
		User user = (User)getCache(CACHE_USER);
		if (user == null){
			ShiroUser principal = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
			if (principal!=null){
				user = userDao.get(principal.getId());
				putCache(CACHE_USER, user);
			}
		}
		if (user == null){
			user = new User();
			SecurityUtils.getSubject().logout();
		}
		return user;
	}
	
	public static User getUser(boolean isRefresh){
		if (isRefresh){
			removeCache(CACHE_USER);
		}
		return getUser();
	}

	public static Office getOffice(){
		Office office = (Office)getCache(CACHE_USER_OFFICE);
		if (office == null){
			office = getUser().getOffice();
			putCache(CACHE_USER_OFFICE, office);
		}

		return office;
	}

	public static Office getOffice(boolean isRefresh){
		if (isRefresh){
			removeCache(CACHE_USER);
		}
		return getOffice();
	}

	public static void cacheOffice(Office office){
		putCache(CACHE_USER_OFFICE, office);
		removeCache(CACHE_OFFICE_LIST_CODE);
	}

	public static List<String> getOfficeCodes(){
		@SuppressWarnings("unchecked")
		List<String> officeCodes = (List<String>)getCache(CACHE_OFFICE_LIST_CODE);
		if (officeCodes == null){
			officeCodes = Lists.newArrayList();
			Office office = UserUtils.getOffice();
			if(office != null){

				if(StringUtils.isNotEmpty(office.getCode())) officeCodes.add(office.getCode());

				List<Office> offices = officeDao.findByParentIdsLike(office.getId()+",");
				for (Office office1 : offices) {
					if(StringUtils.isNotEmpty(office1.getCode())) officeCodes.add(office1.getCode());
				}

				putCache(CACHE_OFFICE_LIST_CODE, officeCodes);
			}
		}
		return officeCodes;
	}

	public static String getOfficeCode(){
		String code="";
		List<String> officeCodes = getOfficeCodes();
		if (officeCodes == null) return  code;

		for (String officeCode : officeCodes) {
			if(StringUtils.isEmpty(code)){
				code = "'"+officeCode+"'";
			}else{
				code += ",'"+officeCode+"'";
			}
		}
		return code;
	}

	public static List<Office> getOfficeList(){
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>)getCache(CACHE_OFFICE_LIST);
		if (officeList == null){
			User user = getUser();
			DetachedCriteria dc = officeDao.createDetachedCriteria();
			dc.add(dataScopeFilter(user, dc.getAlias(), ""));
			dc.add(Restrictions.eq("delFlag", Office.DEL_FLAG_NORMAL));
			officeList = officeDao.find(dc);
			putCache(CACHE_OFFICE_LIST, officeList);
		}
		return officeList;
	}
	
	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
		Object obj = getCacheMap().get(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
		getCacheMap().put(key, value);
	}

	public static void removeCache(String key) {
		getCacheMap().remove(key);
	}
	
	public static Map<String, Object> getCacheMap(){
		Map<String, Object> map = Maps.newHashMap();
		try{
			Subject subject = SecurityUtils.getSubject();
			ShiroUser principal = (ShiroUser)subject.getPrincipal();
			return principal!=null?principal.getCacheMap():map;
		}catch (UnavailableSecurityManagerException e) {
			return map;
		}
	}
	
	public static String getPageSize(){
		String pageSize = (String)CacheUtils.get(CACHE_PAGE_SIZE);
		if (StringUtils.isBlank(pageSize)){

			CacheUtils.put(CACHE_PAGE_SIZE, "50");
		}
		return pageSize;
	}
}
