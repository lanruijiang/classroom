package org.soft.base.ctrl.dao;

import org.soft.base.model.Admin;

/**
 *	 管理员操作
 * @author Administrator
 *
 */
public interface AdminDao {

	/**
	 *	 超级管理员分配管理员账户
	 *	默认密码为 ： admin admin
	 * @param admin
	 * @return
	 */
	public boolean adminCreate(Admin admin);
	
	/**
	 * 	管理员登录
	 * @param admin
	 * @return
	 */
	public Admin adminLogin(Admin admin);
}
