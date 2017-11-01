package com.jr.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jr.entity.User;
import com.jr.service.UserService;
import com.jr.utils.MD5Util;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 登陆认证
 * 
 * 授权
 * 
 * @author jql
 *
 */
@SuppressWarnings(value="all")
public class MyRealm extends AuthorizingRealm {
	
//	private AuthorityService authorityService;
//	
//	public AuthorityService getAuthorityService() {
//		return authorityService;
//	}
//
//	public void setAuthorityService(AuthorityService authorityService) {
//		this.authorityService = authorityService;
//	}
	@Autowired
	private UserService userService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//利用token传入的信息查询数据库，得到其对应的记录
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		String username = usernamePasswordToken.getUsername();
		System.out.println("利用用户名：" + username + "查询数据库。");
		if (username == null||"".equals(username)) {
			throw new UnknownAccountException("用户名：" + username + "不存在！");
		}
		User user = userService.getOneUserByName(username);
		String password = user.getPassword();
		//现在相当于是查出来密码，然后自己算了一下。之后应该在数据库中就是加密过的密码
		String saltSource = "我是盐";
		int hashIterations = 1024;
		Object hashedCredentials = MD5Util.hashedCredentials(password, saltSource, hashIterations);

		//若查询的有结果，则返回AuthenticationInfo接口的实现类对象
		//返回的认证信息
		String principal = username;
		//从数据库表中查到的密码
//		Object hashedCredentials = "15dd51655246194bc1e75a150655e1db";
		//当前realm的name，通常通过调用getName()方法得到
		String realm = getName();
		
		String salt = "我是盐";
		
		ByteSource credentialsSalt = ByteSource.Util.bytes(salt.getBytes());
		
		//修改为MD5盐值加密
//		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,realm);	
		/*
		 * principal:			用户名
		 * hashedCredentials:	从数据库中查询的密码（准备被比对）
		 * credentialsSalt:		盐值
		 * realm:				当前realm的name
		 */
		SimpleAuthenticationInfo info = 
				new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realm);
		return info;
	}

	/**
	 * 生成MD5盐值加密密码
	 * @author jql
	 * @param args
	 */
	public static void main(String[] args) {
		//MD5盐值加密算法
		String hashAlgoritmName = "MD5";
		String credentialsSalt = "123456";
		String saltSource = "我是盐";
		ByteSource salt = ByteSource.Util.bytes(saltSource.getBytes());
		int hashIterations = 1024;
		/*
		 * hashAlgoritmName:加密算法
		 * credentialsSalt:加密之前的密码
		 * salt:盐值
		 * hashIterations:加密次数
		 */
		Object result = new SimpleHash(hashAlgoritmName, credentialsSalt, salt,hashIterations);
		System.out.println(result.toString().equals("15dd51655246194bc1e75a150655e1db"));
	}

	/**
	 * 授权
	 * 授权的方式，即若需要访问受保护的资源，检查用户是否具有对应的权限，则调用该方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		//从PrincipalCollection里获取用户的登录信息
		Object principal = principals.getPrimaryPrincipal();
		
		//再根据principal从数据库中获取其对应的权限
		System.out.println("利用principal:"+principal+"查询对应的权限！");
		List<String> authorities = userService.getAuthoritiesByUserName(principal.toString());
		List<String> roles = userService.getRolesByUserName(principal.toString());
		if (authorities!=null&&authorities.size()>0&&roles!=null&&roles.size()>0) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			//将角色信息添加到用户信息中  
			info.addRoles(roles);
			//将权限资源添加到用户信息中  
			info.addStringPermissions(authorities);
			return info;
		}
		return null;
	}
	
	
}





