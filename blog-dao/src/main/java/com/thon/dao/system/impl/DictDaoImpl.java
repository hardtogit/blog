/**
 * 
 */
package com.thon.dao.system.impl;

import com.thon.commons.persistence.BaseDaoImpl;
import com.thon.dao.system.DictDao;
import com.thon.entity.system.Dict;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author thon
 * @date Jan 2, 2014 11:49:55 AM
 */
@Repository
public class DictDaoImpl extends BaseDaoImpl<Dict> implements DictDao {

	public DictDaoImpl() {
		super(Dict.class);
	}

	@Override
	public List<Dict> findAllList() {
		String queryString = "from Dict where delFlag=? order by sort";
		return findList(queryString, Dict.DEL_FLAG_NORMAL);
	}

    @Override
    public List<Dict> findByType(String type) {
        String queryString = "from Dict where delFlag=? AND type =? order by type,sort";
        return findList(queryString, Dict.DEL_FLAG_NORMAL, type);
    }

    @Override
	public void deleteById(int id) {
		String queryString = "update Dict set delFlag= ? where id = ?";
		update(queryString, Dict.DEL_FLAG_DELETE, id);
	}

	@Override
	public List<Object> findTypeList() {
		String sqlString = "select type from sys_dict where del_flag= ? group by type";
		return findListBySql(sqlString, Dict.DEL_FLAG_NORMAL);
	}


}
