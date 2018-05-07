/**
 * 
 */
package com.thon.security;

import com.thon.commons.security.utils.ShiroUser;
import com.thon.commons.utils.Encodes;
import com.thon.entity.system.User;
import com.thon.service.system.SystemService;
import com.thon.service.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @file ShiroDbRealm.java
 * @author thon
 * @email thon.ju@gmail.com
 * @date Aug 16, 2013 10:43:25 AM
 * @description TODO
 */
@Component
public class ShiroDbRealm extends AuthorizingRealm{
	
	@Autowired
	private SystemService systemService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
		User user = systemService.findUser(token.getUsername(), User.DEL_FLAG_NORMAL);
		if (user != null) {
			SecurityUtils.getSubject().getSession().setTimeout(-1000l);
            Session session = SecurityUtils.getSubject().getSession();
//            String captcha = token.getCaptcha();
//            String exitCode = (String) session.getAttribute(CaptchaServlet.KEY_CAPTCHA);
//            if (null == captcha || !captcha.equalsIgnoreCase(exitCode)) {
//                throw new CaptchaException();
//            }
			
			byte[] salt = Encodes.decodeHex(user.getSalt());
			return new SimpleAuthenticationInfo(new ShiroUser(user.getId(), user.getLoginName(), user.getName(), user.getAvatarUrl()),
					user.getPassword(), ByteSource.Util.bytes(salt), getName());
		} else {
			user = systemService.findUser(token.getUsername(), User.DEL_FLAG_AUDIT);
			if (user != null) {
				throw new LockedAccountException();
			}else {
				throw new UnknownAccountException();
			}
		}
	}
	
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {  
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		User user = systemService.findUser(shiroUser.loginName, User.DEL_FLAG_NORMAL);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if (user != null || user.getRole() == null) {
			UserUtils.putCache("user", user);
			
			// 管理员具有菜单编辑权限
			if (user.isAdmin()) {
				info.addStringPermission("sys:menu:edit");
			}

            info.addRole(user.getRole().getEnname());
			
			// 更新登录IP和时间
			systemService.updateUserLoginInfo(user.getId());
			return info;
		} else {
			return null;
		}
	} 
	
	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(SystemService.HASH_ALGORITHM);
		matcher.setHashIterations(SystemService.HASH_INTERATIONS);

		setCredentialsMatcher(matcher);
	}
	
	/**
	 * 清空用户关联权限认证，待下次使用时重新加载
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清空所有关联认证
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

}
