/**
 * 
 */
package com.thon.service.system;

import com.thon.commons.persistence.Page;
import com.thon.commons.security.utils.Digests;
import com.thon.commons.utils.Encodes;
import com.thon.commons.utils.StringUtils;
import com.thon.dao.system.RoleDao;
import com.thon.dao.system.UserDao;
import com.thon.entity.system.Role;
import com.thon.entity.system.User;
import com.thon.service.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author THON
 * @mail thon.ju@meet-future.com
 * @date 2012-2-13 下午09:00:06
 * @description
 */
@Service
@Transactional
public class SystemService{
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	
	public void saveUser(User user) {

        if(StringUtils.isBlank(user.getName())){
            user.setName(user.getLoginName());
        }

		userDao.clear();
		entryptPassword(user);
		userDao.save(user);

        // 清理缓存
        UserUtils.removeCache(UserUtils.CACHE_USER);
	}

	public User findUser(String account, Integer delFlag) {
		User user= userDao.findByLoginName(account, delFlag);
		return user;
	}

    public User findUserByEmail(String email, Integer delFlag) {
        User user= userDao.findByEmail(email, delFlag);
        return user;
    }

    public User findUserByPhone(String phone, Integer delFlag) {
        User user= userDao.findByPhone(phone, delFlag);
        return user;
    }

	public User getUser(int id) {
		User user= userDao.get(id);
		return user;
	}

    public Page<User> findUsers(Page<User> page, User user) {
        DetachedCriteria dc = userDao.createDetachedCriteria();
        if (StringUtils.isNotEmpty(user.getLoginName())){
            dc.add(Restrictions.like("loginName", "%"+user.getLoginName()+"%"));
        }
        if (StringUtils.isNotEmpty(user.getEmail())){
            dc.add(Restrictions.like("email", "%"+user.getEmail()+"%"));
        }
        if (user.getDelFlag() != null){
            dc.add(Restrictions.eq("delFlag",user.getDelFlag()));
        }
        dc.addOrder(Order.desc("id"));
        return userDao.find(page, dc);
    }

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		if (StringUtils.isBlank(user.getPlainPassword())) {
			return;
		}
		
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	public String entryptPassword(String password, String salt) {
		byte[] decodeSalt = Encodes.decodeHex(salt);
		byte[] hashPassword = Digests.sha1(password.getBytes(), decodeSalt, HASH_INTERATIONS);
		return Encodes.encodeHex(hashPassword);
	}

	public List<User> listAllUsers() {
		return userDao.listAllUsers();
	}

	public void forgetPassword(User user) {
		
	}

	public void resetPassword(User user) {
		entryptPassword(user);
		userDao.save(user);
	}

    public void deleteUser(User user) {
        userDao.delete(user);
    }

	public void deleteUser(int id) {
		userDao.deleteById(id);
	}
	
	public void updateUserLoginInfo(int id){
		userDao.updateLoginInfo(SecurityUtils.getSubject().getSession().getHost(), new Date(), id);
	}

	//-- Role Service --//
	public Role getRole(int id) {
		return roleDao.get(id);
	}

	public Role findRoleByName(String name) {
		return roleDao.findByName(name);
	}
	
	public List<Role> findAllRole(){
		DetachedCriteria dc = roleDao.createDetachedCriteria();
		dc.add(Restrictions.eq(Role.DEL_FLAG, Role.DEL_FLAG_NORMAL));
		dc.addOrder(Order.asc("name"));
		return roleDao.find(dc);
	}
	
	@Transactional(readOnly = false)
	public void saveRole(Role role) {
		roleDao.clear();
		roleDao.save(role);
	}

	@Transactional(readOnly = false)
	public void deleteRole(int id) {
		roleDao.deleteById(id);
	}

}