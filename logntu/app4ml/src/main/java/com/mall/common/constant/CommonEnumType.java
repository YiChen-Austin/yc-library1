/**
 * 
 */
package com.mall.common.constant;

/**
 * @功能说明：操作类型枚举类型
 * @作者： xgyin
 * @创建日期： 2011-3-22
 */

public class CommonEnumType {
	/**
	 * 业务的操作类型
	 * 
	 * @author 潘瑞峥
	 * @date 2010-11-26
	 */
	public enum OperateType {
		新增("add"), 修改("modify"), 删除("delete"), 查看("query"), 登录("login"), 退出("logout");

		private String key;

		private OperateType(String key) {
			this.key = key;
		}

		public String getKey() {
			return this.key;
		}
	}

	public enum LogState {
		正常("1"), 删除("0");

		private String key;

		private LogState(String key) {
			this.key = key;
		}

		public String getKey() {
			return this.key;
		}
	}

}
