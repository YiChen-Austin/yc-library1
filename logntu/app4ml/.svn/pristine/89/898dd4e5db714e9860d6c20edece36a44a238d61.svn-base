package com.mall.web.common.dynamicds;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/**
 * 业务层切面类
 * 
 * @author ty
 * 
 */
public class DataSourceAdvice implements MethodBeforeAdvice,
		AfterReturningAdvice, ThrowsAdvice {
	private static Logger logger = Logger.getLogger(DataSourceAdvice.class);

	/**
	 * 业务层方法执行之前,以add,create,save,edit,update,set,delete,remove开头的方法切换到从数据库，写数据库
	 * ， 否则切换到主数据库，读数据库
	 */
	@Override
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		System.out.println("切入点: " + target.getClass().getName() + "类中"
				+ method.getName() + "方法");

		if (method.isAnnotationPresent(DataSource.class)) {
			DataSource dataSource = method.getAnnotation(DataSource.class);
			System.out.println("切换到: " + dataSource.value());
			DataSourceSwitcher.setDataSource(dataSource.value());
		}// 查询、获取走从库
		else if (method.getName().startsWith("find")
				|| method.getName().startsWith("get")
				|| method.getName().startsWith("count")
				|| method.getName().startsWith("slave")) {
			System.out.println("切换到: slave");
			DataSourceSwitcher.setSlave();
		} else if (method.getName().startsWith("add")
				|| method.getName().startsWith("create")
				|| method.getName().startsWith("save")
				|| method.getName().startsWith("edit")
				|| method.getName().startsWith("update")
				|| method.getName().startsWith("set")
				|| method.getName().startsWith("delete")
				|| method.getName().startsWith("remove")) {
			System.out.println("切换到: master");
			DataSourceSwitcher.setMaster();
		} else {
			System.out.println("默认切换到: master");
			DataSourceSwitcher.setMaster();
		}
	}

	/**
	 * 业务层方法执行完之后
	 */
	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2,
			Object arg3) throws Throwable {

	}

	/**
	 * 抛出异常后执行，数据库切换到主数据库（读数据库）
	 * 
	 * @param method
	 * @param args
	 * @param target
	 * @param ex
	 * @throws Throwable
	 */
	public void afterThrowing(Method method, Object[] args, Object target,
			Exception ex) throws Throwable {
		DataSourceSwitcher.clearDataSource();
		logger.warn(ex);ex.printStackTrace();
		System.out.println("出现异常,切换到: master");
	}
}
