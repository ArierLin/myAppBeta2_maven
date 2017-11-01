package com.jr.utils;

public class Constants {

	/**
	 * 系统常量
	 * @author jql
	 *
	 */
	public static class SysConstants {

		public static final int SUCCESS = 10000;// 成功
		public static final int FAIL = 10010;// 失败
		
		public static final int ROLE_EXIST_USER_SUCCESS = 10001;//部分角色下存在用户，删除角色成功
		public static final int ROLE_EXIST_USER_FAIL = 10011;//角色下存在用户，删除角色删除失败
		
		
		public static final int ABSENT = 1002;// 不存在
		public static final int EXIST = 1003;// 存在

		// 用户状态
		public static final int ACCOUNT_STATE_ACTIVE = 10000; // 正常使用
		public static final int ACCOUNT_STATE_PAUSE = 10001;// 暂停
		public static final int ACCOUNT_STATE_DESTROY = 20000; // 注销
		
		public static final String LOCAL_UPGRADEFILE_PATH = "/usr/local/upgradefiles/"; //服务器本地保存的地址
	}

	/**
	 * 日志Log
	 * @author jql
	 *
	 */
	public static class LogStateConstants {
		public static final int SYSTEM_DEBUGGING_LOG = 16; // debugging调试
		public static final int SYSTEM_INFORMATION_LOG = 32;// information信息
		public static final int SYSTEM_WARNING_LOG = 48; // warning告警
		public static final int SYSTEM_ERROR_LOG = 64; // error错误
		public static final int SYSTEM_SUCCESS_LOG = 80; // success成功
		public static final int SYSTEM_FAIL_LOG = 96; // fail失败

	}


}
