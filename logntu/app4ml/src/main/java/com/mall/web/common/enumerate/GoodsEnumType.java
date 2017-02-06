package com.mall.web.common.enumerate;

/**
 * 商品枚举类型
 * @author Caokw
 *
 */
public class GoodsEnumType {

	/**
	 * 上架状态
	 * @author caokw
	 */
	public enum DownUpType {
		DOWN("下架", 0), UP("上架", 1);

		private String name;
		private int index;

		private DownUpType(String name, int index) {
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
	 * 统计排序条件(销量|收藏)
	 * @author caokw
	 */
	public enum TaxisType {
		SALES("销量", 0), COLLECTS("收藏", 1);

		private String name;
		private int index;

		private TaxisType(String name, int index) {
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
	 * 禁售状态
	 * @author caokw
	 */
	public enum ClosedType {
		OPEN("可售", 0), CLOSE("禁售", 1);

		private String name;
		private int index;

		private ClosedType(String name, int index) {
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
	 * 推荐状态
	 * @author caokw
	 */
	public enum RecommendType {
		NOT("不推荐", 0), YES("推荐", 1);
		private String name;
		private int index;

		private RecommendType(String name, int index) {
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
