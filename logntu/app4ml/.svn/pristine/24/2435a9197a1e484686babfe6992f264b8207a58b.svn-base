package com.mall.web.mall.common.utils;

import java.math.BigDecimal;

///* 订单类型 */
//define('ORDER_TYPE_BUYER',	0);		// 买家收货型
//define('ORDER_TYPE_SELLER',	1);		// 卖家确认收货型
///* 订单资金位置 */
//define('MONEY_CLIENT',		0);		// 客户
//define('MONEY_PLATFORM',	1);		// 平台担保
//define('MONEY_STORE',		2);	    // 商家
//
///* 订单状态 */
//define('ORDER_SUBMITTED',	10);		// 针对货到付款而言，他的下一个状态是卖家已发货
//define('ORDER_PENDING',		11);		// 等待买家付款
//define('ORDER_ACCEPTING',	12);		// （内部状态）买家已发起付款，系统正在确认中
//define('ORDER_ACCEPTED',	20);		// 买家已付款，等待卖家发货
//define('ORDER_SHIPPED',		30);		// 卖家已发货
//define('ORDER_FINISHED',	40);		// 交易成功
//define('ORDER_FROZEN',		50);		// 交易冻结（完结），该订单不可再更改
//define('ORDER_CANCELED',	0);			// 交易已取消
//
///* 订单退款状态 */
//define('ORDER_REFUND_NONE',		0);		// 无
//define('ORDER_REFUND_PENDING',	1);		// 等待卖家处理
//define('ORDER_REFUND_REJECTED',	2);		// 卖家已拒绝退款
//define('ORDER_REFUND_ACCEPTED',	3);		// 卖家已同意退款，等待买家返货
//define('ORDER_REFUND_SHIPPED',	4);		// 买家已返货（退款类型为【仅退款】时无此状态）
//define('ORDER_REFUND_FINISHED',	5);		// 退款成功
//define('ORDER_REFUND_CANCLE',	6);		// 取消
///* 订单介入状态*/
//define('APPEAL_NORMAL',	0);		//默认状态
//define('APPEAL_APPLY',	1);		//申请介入
//define('APPEAL_APPLYING',	2);		//申请介入中
//define('APPEAL_APPLYED',	3);		//申请介入完成
//
///* 退款类型 */
//define('REFUND_TYPE_NONE',			0);		// 无
//define('REFUND_TYPE_REFUND_ONLY',	1);		// 仅退款
//define('REFUND_TYPE_RETURN_REFUND',	2);		// 退款退

/**
 * @功能说明：商城相关枚举 @作者： ventrue @创建日期： 2015-5-20
 */
public class MallEnum {
	/**
	 * @功能说明:商铺经营者类型
	 */
	public enum SotreOwnerType {
		JXS("经销商", 0), PFS("批发商", 1), VS("V客", 2);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private SotreOwnerType(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (SotreOwnerType c : SotreOwnerType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static SotreOwnerType getValue(int index) {
			for (SotreOwnerType c : SotreOwnerType.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:合约机分类(移动合约机、电信合约机，联通合约机)
	 */
	public enum HyjCategoryType {
		Mobile("移动合约机", 3236139), Telcom("电信合约机", 138238560), Unicom("联通合约机", 137815084);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private HyjCategoryType(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (SotreOwnerType c : SotreOwnerType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static HyjCategoryType getValue(int index) {
			for (HyjCategoryType c : HyjCategoryType.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:套餐分类(移动合约套餐、电信合约套餐，联通合约套餐)
	 */
	public enum TcCategoryType {
		Mobile("移动合约套餐", 3236140), Telcom("电信合约套餐", 138238561), Unicom("联通合约套餐", 137815085);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private TcCategoryType(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (SotreOwnerType c : SotreOwnerType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static TcCategoryType getValue(int index) {
			for (TcCategoryType c : TcCategoryType.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:商品上架状态(上架、下架)
	 */
	public enum GoodsShowType {
		show("上架", 1), unshow("下架", 0);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private GoodsShowType(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (GoodsShowType c : GoodsShowType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static GoodsShowType getValue(int index) {
			for (GoodsShowType c : GoodsShowType.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:激活状态(未激活、已激活)
	 */
	public enum ActivatedStatus {
		Activated("已激活", "1"), UnActivated("未激活", "0");
		// 成员变量
		private String name;
		private String index;

		// 构造方法
		private ActivatedStatus(String name, String index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(String index) {
			for (ActivatedStatus c : ActivatedStatus.values()) {
				if (c.getIndex().equalsIgnoreCase(index)) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static ActivatedStatus getValue(String index) {
			for (ActivatedStatus c : ActivatedStatus.values()) {
				if (c.getIndex().equalsIgnoreCase(index)) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getIndex() {
			return index;
		}

		public void setIndex(String index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:防沉迷状态(无须防成迷、需要放沉迷)
	 */
	public enum HookedStatus {
		UnHooked("无须防成迷", "0"), Hooked("需要放沉迷", "1");
		// 成员变量
		private String name;
		private String index;

		// 构造方法
		private HookedStatus(String name, String index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(String index) {
			for (ActivatedStatus c : ActivatedStatus.values()) {
				if (c.getIndex().equalsIgnoreCase(index)) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static ActivatedStatus getValue(String index) {
			for (ActivatedStatus c : ActivatedStatus.values()) {
				if (c.getIndex().equalsIgnoreCase(index)) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getIndex() {
			return index;
		}

		public void setIndex(String index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:商品禁止销售状态(可售、禁售)
	 */
	public enum GoodsClosedType {
		closed("禁售", 1), unclosed("可售", 0);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private GoodsClosedType(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (GoodsClosedType c : GoodsClosedType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static GoodsClosedType getValue(int index) {
			for (GoodsClosedType c : GoodsClosedType.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/******************************************/
	// 订单相关
	/**
	 * @功能说明:订单类型
	 */
	public enum OrderType {
		ORDER_TYPE_BUYER("买家收货型", 0), ORDER_TYPE_SELLER("卖家确认收货型", 1);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private OrderType(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (OrderType c : OrderType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static OrderType getValue(int index) {
			for (OrderType c : OrderType.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:订单资金位置
	 */
	public enum OrderMoneyPosition {
		MONEY_CLIENT("客户", 0), MONEY_PLATFORM("平台担保", 1), MONEY_STORE("商家", 2);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private OrderMoneyPosition(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (OrderMoneyPosition c : OrderMoneyPosition.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static OrderMoneyPosition getValue(int index) {
			for (OrderMoneyPosition c : OrderMoneyPosition.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:订单状态
	 */
	public enum OrderStatus {
		ORDER_SUBMITTED("针对货到付款而言，他的下一个状态是卖家已发货", (byte) 10), ORDER_PENDING("等待买家付款", (byte) 11), ORDER_ACCEPTING(
				"（内部状态）买家已发起付款，系统正在确认中", (byte) 12), ORDER_ACCEPTED("买家已付款，等待卖家发货", (byte) 13), ORDER_PAYED("已支付",
						(byte) 20), ORDER_SHIPPED("卖家已发货", (byte) 30), ORDER_FINISHED("交易成功", (byte) 40), ORDER_FROZEN(
								"交易冻结（完结），该订单不可再更改", (byte) 50), ORDER_CANCELED("交易已取消", (byte) 0);
		// 成员变量
		private String name;
		private byte index;

		// 构造方法
		private OrderStatus(String name, byte index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(byte index) {
			for (OrderStatus c : OrderStatus.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static OrderStatus getValue(byte index) {
			for (OrderStatus c : OrderStatus.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public byte getIndex() {
			return index;
		}

		public void setIndex(byte index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:订单退款状态
	 */
	public enum OrderRefundStatus {
		ORDER_REFUND_NONE("无", 0), ORDER_REFUND_PENDING("等待卖家处理", 1), ORDER_REFUND_REJECTED("卖家已拒绝退款",
				2), ORDER_REFUND_ACCEPTED("卖家已同意退款，等待买家返货", 3), ORDER_REFUND_SHIPPED(" 买家已返货（退款类型为【仅退款】时无此状态）",
						4), ORDER_REFUND_FINISHED("退款成功", 5), ORDER_REFUND_CANCLE("取消", 6);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private OrderRefundStatus(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (OrderRefundStatus c : OrderRefundStatus.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static OrderRefundStatus getValue(int index) {
			for (OrderRefundStatus c : OrderRefundStatus.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:订单介入状态
	 */
	public enum OrderAppealStatus {
		APPEAL_NORMAL("默认状态", 0), APPEAL_APPLY("申请介入", 1), APPEAL_APPLYING("申请介入中", 2), APPEAL_APPLYED("申请介入完成", 3);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private OrderAppealStatus(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (OrderAppealStatus c : OrderAppealStatus.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static OrderAppealStatus getValue(int index) {
			for (OrderAppealStatus c : OrderAppealStatus.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:退款类型
	 */
	public enum RefundStatus {
		REFUND_TYPE_NONE("无", 0), REFUND_TYPE_REFUND_ONLY("仅退款", 1), REFUND_TYPE_RETURN_REFUND("退款退货", 2);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private RefundStatus(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (RefundStatus c : RefundStatus.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static RefundStatus getValue(int index) {
			for (RefundStatus c : RefundStatus.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:对话类型
	 */
	public enum DialogMsgType {
		DIALOG_MSG("留言", 0), DIALOG_APPLY("申请", 1), DIALOG_APPEAL("申诉", 2);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private DialogMsgType(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (DialogMsgType c : DialogMsgType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static DialogMsgType getValue(int index) {
			for (DialogMsgType c : DialogMsgType.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:倒计时事件状态
	 */
	public enum EventType {
		EVENT_NO("未处理", 0), EVENT_CANCLE("取消", 1), EVENT_ING("正在执行中", 2), EVENT_FINISH("完成", 3);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private EventType(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (EventType c : EventType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static EventType getValue(int index) {
			for (EventType c : EventType.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/****************************/
	/**
	 * @功能说明:商品类型
	 */
	public enum GoodsType {
		material("实体商品", "material"), card("卡类商品", "card"), consumption2game("购买游戏元宝",
				"c2game"), consumption2product("购买游戏商品", "c2product"), recharge2account("账户充值", "r2account");
		// 成员变量
		private String name;
		private String index;

		// 构造方法
		private GoodsType(String name, String index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(String index) {
			for (GoodsType c : GoodsType.values()) {
				if (c.getIndex().equalsIgnoreCase(index)) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static GoodsType getValue(String index) {
			for (GoodsType c : GoodsType.values()) {
				if (c.getIndex().equalsIgnoreCase(index)) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getIndex() {
			return index;
		}

		public void setIndex(String index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:订单扩展信息
	 */
	public enum OrderExtensionType {
		normal("普通订单", "normal");
		// 成员变量
		private String name;
		private String index;

		// 构造方法
		private OrderExtensionType(String name, String index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(String index) {
			for (GoodsType c : GoodsType.values()) {
				if (c.getIndex().equalsIgnoreCase(index)) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static OrderExtensionType getValue(String index) {
			for (OrderExtensionType c : OrderExtensionType.values()) {
				if (c.getIndex().equalsIgnoreCase(index)) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getIndex() {
			return index;
		}

		public void setIndex(String index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:是否匿名购买
	 */
	public enum BuyAnonymous {
		No("非匿名", (byte) 0), Yes("匿名", (byte) 1);
		// 成员变量
		private String name;
		private byte index;

		// 构造方法
		private BuyAnonymous(String name, byte index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(byte index) {
			for (BuyAnonymous c : BuyAnonymous.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static BuyAnonymous getValue(byte index) {
			for (BuyAnonymous c : BuyAnonymous.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public byte getIndex() {
			return index;
		}

		public void setIndex(byte index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:错误代码类型
	 */
	public enum ServiceCodeType {
		Success("成功", 0), OthSucc("其他方式成功", 1), UnLogin("未登录", 9), SysErr("系统错误", 8), DataErr("数据错误",
				7), OtherErr("其他错误", 6);
		public static final String ServiceCode = "serviceCode";
		// 成员变量
		private String name;
		private Integer index;

		// 构造方法
		private ServiceCodeType(String name, Integer index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(Integer index) {
			for (ServiceCodeType c : ServiceCodeType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static ServiceCodeType getValue(int index) {
			for (ServiceCodeType c : ServiceCodeType.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getIndex() {
			return index;
		}

		public void setIndex(Integer index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:余额支付
	 */
	public enum BalancePayType {
		YbalancePay("需要余额支付", "1"), NbalancePay("非余额支付", "0");
		// 成员变量
		private String name;
		private String index;

		// 构造方法
		private BalancePayType(String name, String index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(String index) {
			for (BalancePayType c : BalancePayType.values()) {
				if (c.getIndex().equalsIgnoreCase(index)) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static BalancePayType getValue(String index) {
			for (BalancePayType c : BalancePayType.values()) {
				if (c.getIndex().equalsIgnoreCase(index)) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getIndex() {
			return index;
		}

		public void setIndex(String index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:资金表动服务类型
	 */
	public enum PayRecordServiceType {
		BalancePay("余额支付", 0), OnlinePay("在线支付", 1), Commission("佣金发放", 2), RechargePlus("账户充值", 3), SettlementPlus(
				"销售结算", 4), Blance2Frozen("余额转冻结", 5), Frozen2Blance("冻结转余额", 6), OtherRec("其他记录", 9);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private PayRecordServiceType(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (PayRecordServiceType c : PayRecordServiceType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static PayRecordServiceType getValue(int index) {
			for (PayRecordServiceType c : PayRecordServiceType.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:资金表动服务类型
	 */
	public enum PayFundDirect {
		Increase("增加", 0), Reduce("减少", 1), Other("其他", 1);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private PayFundDirect(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (PayFundDirect c : PayFundDirect.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static PayFundDirect getValue(int index) {
			for (PayFundDirect c : PayFundDirect.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:资金表动支付结果类型
	 */
	public enum PayRecordType {
		PayIng("支付中", 1), Success("支付成功", 2), UnSuccess("支付失败", 3);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private PayRecordType(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (PayRecordType c : PayRecordType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static PayRecordType getValue(int index) {
			for (PayRecordType c : PayRecordType.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:工作状态类型
	 */
	public enum WorkStats {
		UnWork("待处理", 1), Working("处理中", 2), Worked("处理结束", 3);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private WorkStats(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (WorkStats c : WorkStats.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static WorkStats getValue(int index) {
			for (WorkStats c : WorkStats.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:资金冻结状态
	 */
	public enum PayFrozenType {
		Frozen("冻结", 1), UnFrozen("解冻", 2);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private PayFrozenType(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (PayFrozenType c : PayFrozenType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 获取方法
		public static PayFrozenType getValue(int index) {
			for (PayFrozenType c : PayFrozenType.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:账户预留金额
	 */
	public enum BalanceReserve {
		YUAN00(0.00, 1), YUAN01(1.00, 1), YUAN05(5.00, 2), YUAN10(10.00, 3), YUAN50(50.00, 4), YUAN100(100.00,
				5), YUAN500(500.00, 6), YUAN1000(1000.00, 7);
		// 成员变量
		private double name;
		private int index;

		// 构造方法
		private BalanceReserve(double name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static double getName(int index) {
			for (BalanceReserve c : BalanceReserve.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return YUAN1000.getName();
		}

		// 获取方法
		public static BalanceReserve getValue(int index) {
			for (BalanceReserve c : BalanceReserve.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public double getName() {
			return name;
		}

		public void setName(double name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:会员等级
	 */
	public enum MemberUgrade {
		TIEPAI("铁牌", 1), TONGPAI("铜牌", 2), YINGPAI("银牌", 3), JINGPAI("金牌", 4), ZHUANSHI("钻石", 5);
		// 成员变量
		private String name;
		private int index;

		// 构造方法
		private MemberUgrade(String name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static String getName(int index) {
			for (MemberUgrade c : MemberUgrade.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return TIEPAI.getName();
		}

		// 获取方法
		public static UgradeValue getValue(int index) {
			for (UgradeValue c : UgradeValue.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}

	/**
	 * @功能说明:升级阀值
	 */
	public enum UgradeValue {
		TIEPAI(BigDecimal.valueOf(0.00), 1), TONGPAI(BigDecimal.valueOf(2000), 2), YINGPAI(BigDecimal.valueOf(5000),
				3), JINGPAI(BigDecimal.valueOf(10000), 4), ZHUANSHI(BigDecimal.valueOf(20000), 5);
		// 成员变量
		private BigDecimal name;
		private int index;

		// 构造方法
		private UgradeValue(BigDecimal name, int index) {
			this.name = name;
			this.index = index;
		}

		// 普通方法
		public static BigDecimal getName(int index) {
			for (UgradeValue c : UgradeValue.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return TIEPAI.getName();
		}

		// 获取方法
		public static UgradeValue getValue(int index) {
			for (UgradeValue c : UgradeValue.values()) {
				if (c.getIndex() == index) {
					return c;
				}
			}
			return null;
		}

		// get set 方法
		public BigDecimal getName() {
			return name;
		}

		public void setName(BigDecimal name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}
}
