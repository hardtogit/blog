/**
 * 
 */
package com.thon.dao.system.impl;

import com.thon.commons.persistence.BaseDaoImpl;
import com.thon.commons.persistence.Page;
import com.thon.dao.system.UserDao;
import com.thon.entity.system.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author thon
 * @email thon.ju@meet-future.com
 * @date 2011-11-10 14:18:53
 * @description:
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
	
	public UserDaoImpl(){
		super(User.class);
	}
	
	@Override
	public void deleteById(int id) {
		String queryString = "update User set delFlag=? where id = ?";
		update(queryString, User.DEL_FLAG_DELETE, id);
	}

	@Override
	public User findByLoginName(String loginName, Integer delFlag) {
		String queryString = "FROM User u where u.loginName = ? and u.delFlag = ?";
		
		if (delFlag == null) {
			queryString = "FROM User u where u.loginName =?";
			return findOne(queryString, loginName);
		}
		
		return findOne(queryString, loginName, delFlag);
	}

    @Override
    public User findByEmail(String email, Integer delFlag) {
        String queryString = "FROM User u where u.email = ? and u.delFlag = ?";

        if (delFlag == null) {
            queryString = "FROM User u where u.email =?";
            return findOne(queryString, email);
        }

        return findOne(queryString, email, delFlag);
    }

    @Override
    public User findByPhone(String phone, Integer delFlag) {
        String queryString = "FROM User u where u.phone = ? and u.delFlag = ?";

        if (delFlag == null) {
            queryString = "FROM User u where u.phone =?";
            return findOne(queryString, phone);
        }

        return findOne(queryString, phone, delFlag);
    }

	@Override
	public List<User> listAllUsers() {
		String queryString = "FROM User";
		return findList(queryString);
	}

	@Override
	public Page<User> findUserPage(Page<User> page, String role) {
		String queryString = "SELECT u.* FROM sys_user u join sys_user_role r on u.id=r.user_id join sys_role sr on r.role_id=sr.id and sr.enname=?";
		return findPageBySql(page, queryString, User.class, role);
	}

	@Override
	public Page<User> findUserActorPage(Page<User> page, int courseId, int flag) {
		String queryString ="FROM User u WHERE u.id in (SELECT ca.userId FROM CourseActor ca WHERE ca.courseId = ? AND ca.delFlag = ?)";
		return findPage(page, queryString, courseId, flag);
	}

	@Override
	public Page<User> findUserActorAvailablePage(Page<User> page, int courseId) {
		String queryString ="FROM User u WHERE u.delFlag = ? "
				+ "AND u.id in (SELECT ca.userId FROM CourseActor ca WHERE ca.courseId = ?) "
				+ "AND u.id not in (SELECT tm.user.id FROM TeamMember tm WHERE tm.team.courseId = ?)";
		return findPage(page, queryString, User.DEL_FLAG_NORMAL, courseId, courseId);
	}

	@Override
	public void updateLoginInfo(String host, Date loginDate, int id) {
		String queryString = "update User set host=?, loginDate=? where id = ?";
		update(queryString, host, loginDate, id);
	}

	@Override
	public Page<User> findUserPage(Page<User> page) {
		String queryString="FROM User";
		return findPage(page, queryString);
	}
	
	@Override
	public List<User> listUser(int did) {
		String queryString="FROM User u WHERE u.company.id=? or u.office.id=?";
		return findList(queryString, did,did);
	}

}
