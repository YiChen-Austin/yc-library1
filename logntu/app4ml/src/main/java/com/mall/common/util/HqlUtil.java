package com.mall.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

/**
 * 
 * @功能：产生HQL条件语句工具类
 * @作者：印鲜刚
 * @创建日期：2004-4-24
 * 
 */
public class HqlUtil {
	private static Logger logger = Logger.getLogger(HqlUtil.class);

/**
	 * 根据业务条件产生安全的HQL的条件语句
	 * 
	 * @param condition
	 * 		-业务查询条件Map<String,Object>参数对象(
	 *      -key=method__property__operator或者实体的property属性名
	 *      -method注解: AND,OR两种方式之一
	 *      -operator注解：
	 *      			 e-->'='  b -->'>'  s-->'<' l-->'LIKE' i-->'IN' 
	 *                   be-->'>=' se-->'<=' ne-->'!=' n->'NULL' nn->'NOT NULL' 
	 *		-property注解：实体的property属性名)
	 * @return Map<String, Map<String, Object>>
	 *         -String:产生的HQL条件语句,Map<String,Object>传入的有效条件值
	 * @throws HibernateException
	 */
	public static Map<String, Map<String, Object>> generateSecurityHql(
			Map<String, Object> condition) throws HibernateException {
		StringBuffer sb = new StringBuffer(" WHERE");
		if (!BaseUtil.isEmpty(condition)) {
			Map<String, Map<String, Object>> generatorMap = new HashMap<String, Map<String, Object>>();
			Map<String, Object> buildParams = new HashMap<String, Object>();
			Set<String> keys = condition.keySet();
			for (String key : keys) {
				if (key.indexOf("__") < 0) {
					if (sb.length() == 6)
						sb.append(" " + key + " = :" + key);
					else
						sb.append(" AND " + key + " = :" + key);
					Object value = condition.get(key);
					if (BaseUtil.isEmpty(value))
						buildParams.put(key, "");
					else
						buildParams.put(key, value);
				} else {
					String[] list = key.split("__");
					if (list.length != 3)
						throw new HibernateException("分割查询字段不满足约定条件!");
					if (list[2].equalsIgnoreCase("e"))
						list[2] = "=";
					else if (list[2].equalsIgnoreCase("b"))
						list[2] = ">";
					else if (list[2].equalsIgnoreCase("s"))
						list[2] = "<";
					else if (list[2].equalsIgnoreCase("be"))
						list[2] = ">=";
					else if (list[2].equalsIgnoreCase("se"))
						list[2] = "<=";
					else if (list[2].equalsIgnoreCase("ne"))
						list[2] = "!=";
					else if (list[2].equalsIgnoreCase("n"))
						list[2] = "IS NULL";
					else if (list[2].equalsIgnoreCase("nn"))
						list[2] = "IS NOT NULL";
					else if (list[2].equalsIgnoreCase("l"))
						list[2] = "LIKE";
					else if (list[2].equalsIgnoreCase("i"))
						list[2] = "IN";
					else
						throw new HibernateException("HQL条件操作符不满足约定条件!");
					if (sb.length() == 6)
						sb.append(" " + list[1] + " " + list[2]);
					else
						sb.append(" " + list[0].toUpperCase() + " " + list[1]
								+ " " + list[2]);
					if (!list[2].equalsIgnoreCase("IS NULL")
							&& !list[2].equalsIgnoreCase("IS NOT NULL")) {
						if (list[2].equalsIgnoreCase("IN")) {
							sb.append(" (");
							if (condition.get(key).getClass().getSimpleName()
									.indexOf("[]") < 0)
								throw new HibernateException(
										"Map该Key对应的Value值必须为数组类型!");
							Object[] obj = (Object[]) condition.get(key);
							int len = obj.length;
							for (int i = 0; i < len; i++) {
								if (i == 0)
									sb.append(":inParam" + i);
								else
									sb.append(",:inParam" + i);
								buildParams.put("inParam" + i, obj[i]);
							}
							sb.append(" )");
						} else {
							sb.append(" :" + list[1]);
							Object value = condition.get(key);
							buildParams.put(list[1], value);
						}
					}
				}
			}
			generatorMap.put(sb.toString(), buildParams);
			return generatorMap;
		}
		return null;
	}

	/**
	 * 将VO对象参照domain对象转换成HQL条件TreeMap<String,Object>对象
	 * -转换的属性类型不包括集合和数组类型(数组和集合通过手工方式构造) -VO对象与domain对象对应属性的类型必须相同，否则必须手工方式构造
	 * 
	 * @param bean
	 *            -VO对象
	 * @param poCls
	 *            -domain对象Class类型
	 * @return -返回HQL条件参数(TreeMap<String,Object>)
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> beanToMap(Object bean, Class<?> poCls) {
		if (BaseUtil.isEmpty(bean))
			return null;
		Class beanCls = bean.getClass();
		Map<String, Object> params = new TreeMap<String, Object>();
		Field[] poFields = poCls.getDeclaredFields();
		for (Field poField : poFields) {
			String poPropertyType = poField.getType().getSimpleName();
			if (poPropertyType.indexOf("List") < 0
					&& poPropertyType.indexOf("Set") < 0
					&& poPropertyType.indexOf("Map") < 0
					&& poPropertyType.indexOf("Collection") < 0
					&& poPropertyType.indexOf("[]") < 0) {
				String poPropertyName = poField.getName();
				if (!"serialVersionUID".equalsIgnoreCase(poPropertyName)) {
					String poGetMethodName = "get"
							+ poPropertyName.substring(0, 1).toUpperCase()
							+ poPropertyName.substring(1, poPropertyName
									.length());
					// 这里发生的异常不需要框架异常来集中处理，如发生异常忽略不计，继续往下执行
					try {
						Method getMethod = beanCls.getMethod(poGetMethodName,
								new Class[] {});
						Object value = getMethod.invoke(bean, new Object[] {});
						if (!BaseUtil.isEmpty(value)) {
							params.put(poPropertyName, value);
						}
					} catch (Exception e) {
					}
				}
			}
		}
		return params;
	}

	@SuppressWarnings("unchecked")
	public static Object map2Bean(Map<String, Object> map, Class<?> poCls) {
		if (BaseUtil.isEmpty(map))
			return null;
		Object bean = null;
		try {
			bean = poCls.newInstance();
		} catch (Exception e) {
			return null;
		}
		Method[] poMethods = poCls.getDeclaredMethods();
		for (Method poMethod : poMethods) {
			String poMethodName = poMethod.getName();
			poMethodName = poMethodName
					.substring(poMethodName.indexOf("get") + 3);
			String poMethodUName = poMethodName.toUpperCase();
			Object pram = map.get(poMethodUName);
			if (pram != null) {
				poMethodName = "set" + poMethodName;
				Method setMethod;
				try {
					if ("java.math.BigDecimal".equalsIgnoreCase(pram.getClass()
							.getName())) {
						java.math.BigDecimal bd = (java.math.BigDecimal) pram;
						pram = new Float(bd.floatValue());
					} else if ("java.sql.Timestamp".equalsIgnoreCase(pram
							.getClass().getName())) {
						java.sql.Timestamp tt = (java.sql.Timestamp) pram;
						Calendar c = Calendar.getInstance();
						c.setTimeInMillis(tt.getTime());
						pram = c.getTime();
					}
					setMethod = poCls.getMethod(poMethodName,
							new Class[] { pram.getClass() });
					setMethod.invoke(bean, new Object[] { pram });
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				poMethodUName = poMethodUName + "_ID";
				pram = map.get(poMethodUName);
				// 对象
				if (pram != null) {

				}
			}
		}
		Field[] poFields = poCls.getDeclaredFields();
		String poMethodName = null;
		for (Field poField : poFields) {
			String poPropertyType = poField.getType().getSimpleName();
			if (poPropertyType.indexOf("List") < 0
					&& poPropertyType.indexOf("Set") < 0
					&& poPropertyType.indexOf("Map") < 0
					&& poPropertyType.indexOf("Collection") < 0
					&& poPropertyType.indexOf("[]") < 0) {
				String poPropertyName = poField.getName();
				if (!"serialVersionUID".equalsIgnoreCase(poPropertyName)) {
					String poPropertyUName = poPropertyName.toUpperCase();
					Object pram = map.get(poPropertyUName);
					Method setMethod;
					if (pram != null) {
						poMethodName = "set"
								+ poPropertyName.substring(0, 1).toUpperCase()
								+ poPropertyName.substring(1);
						try {
							if ("java.math.BigDecimal".equalsIgnoreCase(pram
									.getClass().getName())) {
								java.math.BigDecimal bd = (java.math.BigDecimal) pram;
								pram = new Float(bd.floatValue());
							} else if ("java.sql.Timestamp"
									.equalsIgnoreCase(pram.getClass().getName())) {
								java.sql.Timestamp tt = (java.sql.Timestamp) pram;
								Calendar c = Calendar.getInstance();
								c.setTimeInMillis(tt.getTime());
								pram = c.getTime();
							}
							setMethod = poCls.getMethod(poMethodName,
									new Class[] { pram.getClass() });
							setMethod.invoke(bean, new Object[] { pram });
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					// 适用于对于对象性域赋值
					else {
						poPropertyUName = poPropertyUName + "_ID";
						pram = map.get(poPropertyUName);
						try {
							// 
							if (pram != null) {
								// 子对象
								Object sub = null;
								try {
									sub = poField.getType().newInstance();
									setMethod = poField.getType().getMethod(
											"setId",
											new Class[] { String.class });
									setMethod
											.invoke(sub, new Object[] { pram });
								} catch (Exception e) {
									return null;
								}
								poMethodName = "set"
										+ poPropertyName.substring(0, 1)
												.toUpperCase()
										+ poPropertyName.substring(1);
								// 赋值
								setMethod =poCls.getMethod(
										poMethodName,
										new Class[] { poField.getType() });
								setMethod.invoke(bean, new Object[] { sub });
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return bean;
	}
}
