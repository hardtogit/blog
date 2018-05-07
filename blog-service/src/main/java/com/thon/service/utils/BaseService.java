package com.thon.service.utils;

import com.google.common.collect.Lists;
import com.thon.dao.system.UserDao;
import com.thon.entity.system.Role;
import com.thon.entity.system.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Service基类
 * @author thon
 * @date Jan 2, 2014 12:07:49 PM
 */
public abstract class BaseService {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private static UserDao userDao;
	/**
	 * 数据范围过滤
	 * @param user 当前用户对象，通过“UserUtils.getUser()”获取
	 * @param officeAlias 机构表别名，例如：dc.createAlias("office", "office");
	 * @param userAlias 用户表别名，传递空，忽略此参数
	 * @return 标准连接条件对象
	 */
	protected static Junction dataScopeFilter(User user, String officeAlias, String userAlias) {

		// 进行权限过滤，多个角色权限范围之间为或者关系。
		List<String> dataScope = Lists.newArrayList();
		Junction junction = Restrictions.disjunction();

		// 超级管理员，跳过权限过滤
		if (!user.isAdmin()){
			Role r = user.getRole();

			if (!dataScope.contains(r.getDataScope()) && StringUtils.isNotBlank(officeAlias)){
				boolean isDataScopeAll = false;
				if (Role.DATA_SCOPE_ALL.equals(r.getDataScope())){
					isDataScopeAll = true;
				}
				else if (Role.DATA_SCOPE_COMPANY_AND_CHILD.equals(r.getDataScope())){
					junction.add(Restrictions.eq(officeAlias+".id", user.getCompany().getId()));
					junction.add(Restrictions.like(officeAlias+".parentIds", user.getCompany().getParentIds()+user.getCompany().getId()+",%"));
				}
				else if (Role.DATA_SCOPE_COMPANY.equals(r.getDataScope())){
					junction.add(Restrictions.eq(officeAlias+".id", user.getCompany().getId()));
					junction.add(Restrictions.and(Restrictions.eq(officeAlias+".parent.id", user.getCompany().getId()),
							Restrictions.eq(officeAlias+".type", "2"))); // 包括本公司下的部门
				}
				else if (Role.DATA_SCOPE_OFFICE_AND_CHILD.equals(r.getDataScope())){
					junction.add(Restrictions.eq(officeAlias+".id", user.getOffice().getId()));
					junction.add(Restrictions.like(officeAlias+".parentIds", user.getOffice().getParentIds()+user.getOffice().getId()+",%"));
				}
				else if (Role.DATA_SCOPE_OFFICE.equals(r.getDataScope())){
					junction.add(Restrictions.eq(officeAlias+".id", user.getOffice().getId()));
				}
//					else if (Role.DATA_SCOPE_CUSTOM.equals(r.getDataScope())){
//						junction.add(Restrictions.in(officeAlias+".id", r.getOfficeIdList()));
//					}

				if (!isDataScopeAll){
					if (StringUtils.isNotBlank(userAlias)){
						junction.add(Restrictions.eq(userAlias+".id", user.getId()));
					}else {
						junction.add(Restrictions.isNull(officeAlias+".id"));
					}
				}else{
					// 如果包含全部权限，则去掉之前添加的所有条件，并跳出循环。
					junction = Restrictions.disjunction();
				}
				dataScope.add(r.getDataScope());
			}
		}
		return junction;
	}
}
