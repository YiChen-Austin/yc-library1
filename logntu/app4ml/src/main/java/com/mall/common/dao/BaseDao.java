package com.mall.common.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.HqlUtil;

/**
 * 
 * @功能：采用泛型技术构建系统BaseDao
 * @作者：印鲜刚
 * @创建日期：2010-04-20
 * @泛型参数： 实体类Entity
 * 
 */
public abstract class BaseDao<T> {

	protected static Log log = LogFactory.getLog(BaseDao.class);
	private final String entityName;
	@Resource(name = "hibernateTemplate")
	protected HibernateTemplate hibernateTemplate;
	@Resource(name = "jdbcTemplate")
	protected JdbcTemplate jdbcTemplate;
	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	/**
	 * 无参构造方法，检测泛型参数T的合法性
	 */
	@SuppressWarnings("unchecked")
	public BaseDao() {
		Type type = this.getClass().getGenericSuperclass();
		if (!(type instanceof ParameterizedType)) {
			throw new RuntimeException("没有传递类型参数T");
		}
		ParameterizedType pt = (ParameterizedType) type;
		this.entityName = ((Class<T>) pt.getActualTypeArguments()[0]).getName();
	}

	/**
	 * 删除指定持久化实例
	 * 
	 * @param entity
	 *            -实体对象
	 * @throws FrameworkException
	 */
	public void delete(T entity) throws FrameworkException {
		sessionFactory.getCurrentSession().delete(entity);
	}

	/**
	 * 删除集合内全部持久化类实例
	 * 
	 * @param entitys
	 *            -持久化集合实例
	 * @throws FrameworkException
	 */
	public void deleteAll(Collection<T> entitys) throws FrameworkException {
		hibernateTemplate.deleteAll(entitys);
	}

	/**
	 * 删除指定持久化实例
	 * 
	 * @param id
	 *            -持久化实例ID
	 * @throws FrameworkException
	 */
	public void deleteById(Serializable id) throws FrameworkException {
		this.delete(this.get(id));
	}

	/**
	 * 保存实体
	 * 
	 * @param entity
	 *            实体对象
	 * @return 生成的主键ID
	 * @throws FrameworkException
	 */
	public Serializable save(T entity) throws FrameworkException {
		return sessionFactory.getCurrentSession().save(entityName, entity);
	}

	/**
	 * 保存实体 数据批量放入
	 * 
	 * @param dataList
	 * @param batchCount
	 * @return
	 */
	public T saveList(final List<T> dataList) throws FrameworkException{
		return this.hibernateTemplate.execute(new HibernateCallback<T>() {
					@Override
					public T doInHibernate(Session session)throws HibernateException {
						for (int i = 0; i < dataList.size(); i++) {
							session.save(dataList.get(i));
							
							session.flush();
							session.clear();
						}
						return null;
					}
				});
	}

	/**
	 * 调用save(T entity)方法之前调用的方法，主要解决OneToMany级联保存或修改 a different object with
	 * the same identifier value was already associated with the session
	 * 
	 * @param entity
	 * @return
	 * @throws FrameworkException
	 */
	@SuppressWarnings("unchecked")
	public T merge(T entity) throws FrameworkException {
		return (T) sessionFactory.getCurrentSession().merge(entityName, entity);
	}

	/**
	 * 修改实体
	 * 
	 * @param entity
	 *            -实体对象
	 * @throws FrameworkException
	 */

	public void update(T entity) throws FrameworkException {
		sessionFactory.getCurrentSession().update(entityName, entity);
	}

	/**
	 * 获取满足条件的多个实体
	 * 
	 * @param hql
	 *            -标准hql语句
	 * @param condition
	 *            -查询条件
	 * @return List<T>
	 * @throws FrameworkException
	 */
	public List<T> findAllEntitiesByCondition(final String hql,
			final Map<String, Object> condition) throws FrameworkException {
		return this.findAllEntitiesByCondition(hql, condition, -1, -1);
	}

	/**
	 * 获取满足条件的多个实体
	 * 
	 * @param condition
	 *            -查询条件
	 * @return List<T>
	 * @throws FrameworkException
	 */
	public List<T> findAllEntitiesByCondition(
			final Map<String, Object> condition) throws FrameworkException {
		return this.findAllEntitiesByCondition(condition, -1, -1);
	}

	/**
	 * 根据动态条件查询满足条件的实体
	 * 
	 * @param condition
	 *            -查询条件
	 * @param currentPage
	 *            -当前页数
	 * @param pageSize
	 *            -每页值的大小
	 * @return List<T>
	 * @throws FrameworkException
	 */

	public List<T> findAllEntitiesByCondition(
			final Map<String, Object> condition, final int currentPage,
			final int pageSize) throws FrameworkException {
		return this.findAllEntitiesByCondition(null, condition, currentPage,
				pageSize);

	}

	/**
	 * 根据动态条件查询满足条件的实体
	 * 
	 * @param hql
	 *            -标准hql
	 * @param condition
	 *            -查询条件
	 * @param currentPage
	 *            -当前页数
	 * @param pageSize
	 *            -每页值的大小
	 * @return List<T>
	 * @throws FrameworkException
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllEntitiesByCondition(final String hql,
			final Map<String, Object> condition, final int currentPage,
			final int pageSize) throws FrameworkException {
		Query query = null;
		Map<String, Object> tempCondition = condition;
		String tempHql = hql;
		if (!BaseUtil.isEmpty(tempCondition) && !condition.isEmpty()) {
			Set<String> keys = tempCondition.keySet();
			if (BaseUtil.isEmpty(tempHql)) {
				Map<String, Map<String, Object>> securityHqlCondition = HqlUtil
						.generateSecurityHql(tempCondition);
				String whereCondition = securityHqlCondition.keySet()
						.iterator().next();
				tempHql = "FROM " + entityName + whereCondition;
				tempCondition = securityHqlCondition.get(whereCondition);
				keys = tempCondition.keySet();
			}
			query = this.sessionFactory.getCurrentSession()
					.createQuery(tempHql);
			for (String key : keys) {
				query.setParameter(key, tempCondition.get(key));
			}

		} else {
			if (BaseUtil.isEmpty(tempHql))
				tempHql = "FROM " + entityName;
			query = this.sessionFactory.getCurrentSession()
					.createQuery(tempHql);
		}
		if (currentPage > 0 && pageSize > 0) {
			query.setFirstResult((currentPage - 1) * pageSize);
			query.setMaxResults(pageSize);
		}
		List<T> list = query.list();
		return list;
	}

	/**
	 * 根据动态条件查询满足条件的实体记录总数
	 * 
	 * @param condition
	 *            -查询条件
	 * @return int
	 * @throws FrameworkException
	 */
	public int getTotalRows(final Map<String, Object> condition)
			throws FrameworkException {
		return this.getTotalRows(null, condition);
	}

	/**
	 * 根据动态条件查询满足条件的实体记录总数
	 * 
	 * @param hql
	 *            -标准hql
	 * @param condition
	 *            -查询条件
	 * @return int
	 * @throws FrameworkException
	 */
	public int getTotalRows(final String hql,
			final Map<String, Object> condition) throws FrameworkException {
		Query query = null;
		Map<String, Object> tempCondition = condition;
		String tempHql = hql;
		if (!BaseUtil.isEmpty(tempCondition) && !condition.isEmpty()) {
			Set<String> keys = tempCondition.keySet();
			if (BaseUtil.isEmpty(tempHql)) {
				Map<String, Map<String, Object>> securityHqlCondition = HqlUtil
						.generateSecurityHql(tempCondition);
				String whereCondition = securityHqlCondition.keySet()
						.iterator().next();
				tempHql = "SELECT COUNT(*) FROM " + entityName + whereCondition;
				tempCondition = securityHqlCondition.get(whereCondition);
				keys = tempCondition.keySet();
			}
			query = this.sessionFactory.getCurrentSession()
					.createQuery(tempHql);
			for (String key : keys) {
				query.setParameter(key, tempCondition.get(key));
			}
		} else {
			if (BaseUtil.isEmpty(tempHql))
				tempHql = "SELECT COUNT(*) FROM " + entityName;
			query = this.sessionFactory.getCurrentSession()
					.createQuery(tempHql);
		}
		Object obj = query.list().get(0);
		return ((Long) obj).intValue();
	}

	/**
	 * 根据动态条件查询满足条件的实体记录总数
	 * 
	 * @param hql
	 *            -标准hql
	 * @param condition
	 *            -查询条件
	 * @return int
	 * @throws FrameworkException
	 */
	public String getSimpleValue(final String hql,
			final Map<String, Object> condition) throws FrameworkException {
		Query query = null;
		Map<String, Object> tempCondition = condition;
		String tempHql = hql;
		if (!BaseUtil.isEmpty(tempCondition) && !condition.isEmpty()) {
			Set<String> keys = tempCondition.keySet();
			query = this.sessionFactory.getCurrentSession()
					.createQuery(tempHql);
			for (String key : keys) {
				query.setParameter(key, tempCondition.get(key));
			}
		} else {
			query = this.sessionFactory.getCurrentSession()
					.createQuery(tempHql);
		}
		Object obj = query.list().get(0);
		return (String) obj;
	}

	/**
	 * 
	 * @param hql
	 * @param objects
	 * @return
	 */
	public Object getSimpleValue(final String hql, final Object... objects) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		Object obj = query.uniqueResult();
		return obj;
	}

	/**
	 * 根据动态hql语句和条件参数查询单一列值列表
	 * 
	 * @param hql
	 *            hql语句
	 * @param condition
	 *            条件参数
	 * @return 单一列值列表
	 * @throws FrameworkException
	 */
	@SuppressWarnings("unchecked")
	public List<String> getSimpleValueList(final String hql,
			final Map<String, Object> condition) throws FrameworkException {
		Query query = null;
		Map<String, Object> tempCondition = condition;
		String tempHql = hql;
		if (!BaseUtil.isEmpty(tempCondition) && !condition.isEmpty()) {
			Set<String> keys = tempCondition.keySet();
			query = this.sessionFactory.getCurrentSession()
					.createQuery(tempHql);
			for (String key : keys) {
				query.setParameter(key, tempCondition.get(key));
			}
		} else {
			query = this.sessionFactory.getCurrentSession()
					.createQuery(tempHql);
		}
		List<String> results = query.list();
		return results;
	}

	/**
	 * 根据动态hql语句和条件参数查询单一列值对象列表
	 * 
	 * @param hql
	 *            hql语句
	 * @param condition
	 *            条件参数
	 * @return
	 * @throws FrameworkException
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getSimpleObjectValueList(final String hql,
			final Map<String, Object> condition) throws FrameworkException {
		Query query = null;
		Map<String, Object> tempCondition = condition;
		String tempHql = hql;
		if (!BaseUtil.isEmpty(tempCondition) && !condition.isEmpty()) {
			Set<String> keys = tempCondition.keySet();
			query = this.sessionFactory.getCurrentSession()
					.createQuery(tempHql);
			for (String key : keys) {
				query.setParameter(key, tempCondition.get(key));
			}
		} else {
			query = this.sessionFactory.getCurrentSession()
					.createQuery(tempHql);
		}
		List<Object> results = query.list();
		return results;
	}

	/**
	 * 根据hql和条件查找一条唯一记录
	 * 
	 * @param hql
	 *            hql语句
	 * @param condition
	 *            条件参数
	 * @return 泛型对象
	 * @throws FrameworkException
	 */
	@SuppressWarnings("unchecked")
	public T getSingleEntity(final String hql,
			final Map<String, Object> condition) throws FrameworkException {
		Query query = null;
		Map<String, Object> tempCondition = condition;
		String tempHql = hql;
		if (!BaseUtil.isEmpty(tempCondition) && !BaseUtil.isEmpty(condition)) {
			Set<String> keys = tempCondition.keySet();
			query = this.sessionFactory.getCurrentSession()
					.createQuery(tempHql);
			for (String key : keys) {
				query.setParameter(key, tempCondition.get(key));
			}
		} else {
			query = this.sessionFactory.getCurrentSession()
					.createQuery(tempHql);
		}
		Object obj = query.uniqueResult();
		return (T) obj;
	}

	/**
	 * 获取单个实体
	 * 
	 * @param id
	 *            - 主键id
	 * @return T
	 * @throws FrameworkException
	 */
	@SuppressWarnings("unchecked")
	public T get(Serializable id) throws FrameworkException {
		return (T) hibernateTemplate.get(entityName, id);
	}

	/**
	 * 无条件获取所有实体
	 * 
	 * @return list<T>
	 * @throws FrameworkException
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() throws FrameworkException {
		return (List<T>) hibernateTemplate.find("FROM " + entityName);
	}

	@SuppressWarnings("unchecked")
	public T getSingleEntity(String hql, final Object... objects) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		return (T) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<T> list(String hql) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> list(String hql, final Object... objects) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> list(String hql, int startPos, int length,
			final Object... objects) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		query.setFirstResult(startPos);
		query.setMaxResults(length);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> list(String hql, int startPos, int length) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(startPos);
		query.setMaxResults(length);
		return query.list();
	}

	public int getTotal(String hql, Object... objects) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		Object obj = query.list().get(0);
		if (obj instanceof Integer) {
			return (Integer) obj;
		}
		if (obj instanceof Long) {
			return ((Long) obj).intValue();
		}
		return (Integer) obj;

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMultValueList(String hql,
			Object... objects) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		return query.setResultTransformer(
				CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
	}

	/**
	 * 适用于hibernate in查询
	 * 
	 * @param hql
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMultValueList(String hql,
			Map<String, Object> condition) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Map<String, Object> tempCondition = condition;
		Set<String> keys = condition.keySet();
		for (String key : keys) {
			Object obj = tempCondition.get(key);
			if (obj instanceof Collection<?>) {
				query.setParameterList(key, (Collection<?>) obj);
			} else if (obj instanceof Object[]) {
				query.setParameterList(key, (Object[]) obj);
			} else {
				query.setParameter(key, obj);
			}
		}
		return query.setResultTransformer(
				CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMultValueList(String hql) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.setResultTransformer(
				CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
	}

	public int excuteUpdate(String hql, Map<String, Object> condition) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Map<String, Object> tempCondition = condition;
		Set<String> keys = condition.keySet();
		for (String key : keys) {
			Object obj = tempCondition.get(key);
			if (obj instanceof Collection<?>) {
				query.setParameterList(key, (Collection<?>) obj);
			} else if (obj instanceof Object[]) {
				query.setParameterList(key, (Object[]) obj);
			} else {
				query.setParameter(key, obj);
			}
		}
		return query.executeUpdate();
	}

	/**
	 * 通过集合条件查询
	 * 
	 * @param hql
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllEntitiesByCollection(String hql,
			Map<String, Object> condition) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Map<String, Object> tempCondition = condition;
		Set<String> keys = condition.keySet();
		for (String key : keys) {
			Object obj = tempCondition.get(key);
			if (obj instanceof Collection<?>) {
				query.setParameterList(key, (Collection<?>) obj);
			} else if (obj instanceof Object[]) {
				query.setParameterList(key, (Object[]) obj);
			} else {
				query.setParameter(key, obj);
			}
		}
		return query.list();
	}

}
