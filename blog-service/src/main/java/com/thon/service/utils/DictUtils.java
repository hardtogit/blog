/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thon.service.utils;

import com.google.common.collect.Lists;
import com.thon.commons.utils.CacheUtils;
import com.thon.commons.utils.SpringContextHolder;
import com.thon.dao.system.DictDao;
import com.thon.entity.system.Dict;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 字典工具类
 * @author thon
 * @date Dec 31, 2013 6:59:12 PM
 */
public class DictUtils extends BaseService{
	
	private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);

	public static final String CACHE_DICT_MAP = "dictMap";
	
	public static Dict getDict(String value, String type){
		if (StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList()){
				if (value.equals(dict.getValue()) && type.equals(dict.getType())){
					return dict;
				}
			}
		}
		return null;
	}
	
	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList()){
				if (value.equals(dict.getValue()) && type.equals(dict.getType())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(label)){
			for (Dict dict : getDictList()){
				if (label.equals(dict.getLabel()) && type.equals(dict.getType())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static List<Dict> getDictList(String type){
		List<Dict> dicts = Lists.newArrayList();
		
		if (StringUtils.isNotBlank(type)){
			for (Dict dict : getDictList()){
				if (!type.equals(dict.getValue()) && type.equals(dict.getType())){
					dicts.add(dict);
				}
			}
		}
		return dicts;
	}
	
	public static List<Dict> getDictList(){
		@SuppressWarnings("unchecked")
		List<Dict> dictList = (List<Dict>)CacheUtils.get(CACHE_DICT_MAP);
		if (dictList==null){
			dictList = dictDao.findAllList();
			CacheUtils.put(CACHE_DICT_MAP, dictList);
		}
		return dictList;
	}
	
}
