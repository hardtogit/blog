/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thon.service.utils;

import com.google.common.collect.Lists;
import com.thon.commons.utils.CacheUtils;
import com.thon.commons.utils.SpringContextHolder;
import com.thon.dao.post.TermDao;
import com.thon.entity.post.Term;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 词汇工具类
 * @author thon
 * @date Dec 31, 2013 6:59:12 PM
 */
public class TermUtils extends BaseService{
	
	private static TermDao termDao = SpringContextHolder.getBean(TermDao.class);

	public static final String CACHE_TERM_MAP = "termMap";
	
	public static Term getTerm(String name, String type){
		if (StringUtils.isNotBlank(name)){
			for (Term term : getTermList()){
				if (name.equals(term.getName()) && type.equals(term.getType())){
					return term;
				}
			}
		}
		return null;
	}
	
	public static List<Term> getTermList(String type){
		List<Term> list = Lists.newArrayList();
		
		if (StringUtils.isNotBlank(type)){
			for (Term term : getTermList()){
				if (!type.equals(term.getName()) && type.equals(term.getType())){
                    list.add(term);
				}
			}
		}
		return list;
	}
	
	public static List<Term> getTermList(){
		@SuppressWarnings("unchecked")
		List<Term> list = (List<Term>)CacheUtils.get(CACHE_TERM_MAP);
		if (list==null){
            list = termDao.findAllList();
			CacheUtils.put(CACHE_TERM_MAP, list);
		}
		return list;
	}
	
}
