package com.mall.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * @Description(描述) : 价格工具类
 * @author(作者) : wangliyou
 * @date (开发日期) : 2015年9月11日 下午1:38:47
 */

public class PriceUtil {

	/**
	 * @Description(功能描述) : 计算商品价格
	 * @author(作者) : wangliyou
	 * @date (开发日期) : 2015年9月12日 上午10:10:17
	 * @param goods
	 *            : 商品对象
	 * @param quantity
	 *            : 购买数量
	 * @return
	 */
	public static Double getGoodsPrice(BigDecimal price, Integer quantity) {
		double result = 0;
		result += price.doubleValue() * quantity;
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(result));
	}

	

	/**
	 * @Description(功能描述) : 价格转字符串（单位元-元）
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月12日 下午2:25:59
	 * @return
	 */
	public static String yunPrice2yunString(double price) {
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		currency.setGroupingUsed(false);
		currency.setMaximumFractionDigits(2);
		String sum_amount = currency.format(price).substring(1);
		return sum_amount;
	}

	/**
	 * @Description(功能描述) : 价格转字符串（单位元-分）
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月12日 下午2:25:59
	 * @return
	 */
	public static String yunPrice2fenString(double price) {
		return Long.toString((long) (price * 100));
	}

	/**
	 * @Description(功能描述) : 价格转字符串（单位分-分）
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月12日 下午2:25:59
	 * @return
	 */
	public static String fenPrice2YunString(long price) {
		return yunPrice2yunString(((double) price) / 100);
	}

	/**
	 * @Description(功能描述) : 价格转字符串（单位分-分）
	 * @author(作者) : ventrue
	 * @date (开发日期) : 2015年9月12日 下午2:25:59
	 * @return
	 */
	public static String fenPrice2fenString(long price) {
		return Long.toString(price);
	}

	public static void main(String[] arges) {
		System.out.println(yunPrice2yunString(10023465));
		System.out.println(yunPrice2fenString(10023465));
		System.out.println(fenPrice2YunString(10023465));
		System.out.println(fenPrice2fenString(10023465));
	}
}
