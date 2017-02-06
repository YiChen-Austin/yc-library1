package com.mall.web.common.enumerate;

public class StoreEnumType {
	/**
	 * 店铺状态
	 * @author caokw
	 */
	public enum StatusType {
		APPLYING("申请中", 0), OPEN("开启", 1), CLOSED("关闭", 2);

		private String name;
		private int index;

		private StatusType(String name, int index) {
			this.name = name;
			this.index = index;
		}

		public String getName() {
			return this.name;
		}

		public int getIndex() {
			return this.index;
		}
	}

}
