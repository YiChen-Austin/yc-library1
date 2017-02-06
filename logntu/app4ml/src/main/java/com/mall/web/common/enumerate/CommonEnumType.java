package com.mall.web.common.enumerate;

public class CommonEnumType {
	
	/**
	 * 图片类型
	 * @author caokw
     */
	public enum PictureType {
		ARTICLE("文章", 1), GOODS("商品", 2), STORE("店铺", 3), ORDER("订单", 4);

		// 定义私有变量
		private String name;
		private int index;

		// 构造函数，枚举类型只能为私有
		private PictureType(String name, int index) {
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

	/**
	 * 处理状态
	 * @author caokw
     */
	public enum OperatType {
		NOTOPERAT("未处理", 1), OPERATED("已处理", 2);
		// 定义私有变量
		private String name;
		private int index;

		// 构造函数，枚举类型只能为私有
		private OperatType(String name, int index) {
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
	
	/**
	 * 受理状态
	 * @author caokw
     */
	public enum AcceptType {
		NOTACCEPT("未受理", 0), ACCEPTED("已受理", 1),DEL("删除",9);
		// 定义私有变量
		private String name;
		private int index;

		// 构造函数，枚举类型只能为私有
		private AcceptType(String name, int index) {
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
