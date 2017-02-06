/**
 * 
 */
package com.mall.web.admin.common.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mall.common.constant.CommonConstant;
import com.mall.common.dao.BaseDao;
import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.common.vo.PageBean;
import com.mall.web.admin.common.domain.SysDesignatedDate;

/**
 * @功能说明：特定日期处理
 * @作者： 郭明军
 * @创建日期：2010年6月7日
 * 
 */
@Repository(value = "sysDesignatedDateDao")
public class SysDesignatedDateDao extends BaseDao<SysDesignatedDate> {

	/**
	 * 按条件查询角色总数
	 * 
	 * @param time4
	 * @param time5
	 * @param identifer
	 * @param pagevo
	 * @return
	 * @throws FrameworkException
	 */
	public int getTotalRows(String time4, String time5, String identifer)
			throws FrameworkException {
		Criteria criteria = hibernateTemplate.getSessionFactory()
				.getCurrentSession().createCriteria(SysDesignatedDate.class);
		if (!BaseUtil.isEmpty(time4) && !BaseUtil.isEmpty(time5)) {
			criteria.add(Restrictions.between("edate", DateUtil.stringToDate(
					time4, CommonConstant.DATE_SHORT_FORMAT), DateUtil
					.stringToDate(time5, CommonConstant.DATE_SHORT_FORMAT)));
		} else {
			if (BaseUtil.isEmpty(time4) && !BaseUtil.isEmpty(time5)) {
				criteria.add(Restrictions.le("edate", DateUtil.stringToDate(
						time5, CommonConstant.DATE_SHORT_FORMAT)));
			} else if (BaseUtil.isEmpty(time5) && !BaseUtil.isEmpty(time4)) {
				criteria.add(Restrictions.ge("edate", DateUtil.stringToDate(
						time4, CommonConstant.DATE_SHORT_FORMAT)));
			}
		}
		if (!BaseUtil.isEmpty(identifer)) {
			if (!"a".equals(identifer)) {
				criteria.add(Restrictions.eq("identifer", identifer));
			}
		}
		criteria.setProjection(Projections.countDistinct("id"));
		List<Integer> count = criteria.list();
		return count.get(0).intValue();
	}

	/**
	 * 按条件查询角色
	 * 
	 * @param time4
	 * @param time5
	 * @param identifer
	 * @param pagevo
	 * @return
	 * @throws FrameworkException
	 */
	@SuppressWarnings("unchecked")
	public List<SysDesignatedDate> sysDesignatedDateFind(final Date time4,
			final Date time5, final String identifer, final PageBean pagevo)
			throws FrameworkException {

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(SysDesignatedDate.class);
		// 排序
		if (!BaseUtil.isEmpty(pagevo.getOrder())) {
			criteria.addOrder(Order.asc(pagevo.getOrder()));
		} else {
			criteria.addOrder(Order.asc("edate"));
		}
		// 如果都为空
		if (BaseUtil.isEmpty(time4) && BaseUtil.isEmpty(time5)) {
		} else {
			// 如果开始时间与结束时间都不为空，则查询开始时间与结束时间之间的
			if (!BaseUtil.isEmpty(time4) && !BaseUtil.isEmpty(time5)) {
				criteria.add(Restrictions.between("edate", time4, time5));
			}
			// 如果起始时间为空，则查询小于等于结束时间
			if (BaseUtil.isEmpty(time4) && !BaseUtil.isEmpty(time5)) {
				criteria.add(Restrictions.le("edate", time5));
			}
			// 如果结束时间为空，则查询大于等于起始时间
			if (BaseUtil.isEmpty(time5) && !BaseUtil.isEmpty(time4)) {
				criteria.add(Restrictions.ge("edate", time4));
			}

		}
		// 设置查询类型
		if (!"a".equals(identifer) && !BaseUtil.isEmpty(identifer)) {
			criteria.add(Restrictions.eq("identifer", identifer));
		}

		// 分页查询
		if (!BaseUtil.isEmpty(pagevo.getCurPage())
				&& !BaseUtil.isEmpty(pagevo.getPageSize())) {
			criteria.setFirstResult(
					pagevo.getPageSize() * (pagevo.getCurPage() - 1))
					.setMaxResults(pagevo.getCurPage() * pagevo.getPageSize());
		}
		return (List<SysDesignatedDate>) criteria.list();

	}

	/**
	 * 根据日期查询
	 * 
	 * @param edate
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysDesignatedDate> findByDate(Date date)
			throws FrameworkException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("edate", date);
		List<SysDesignatedDate> list = this.findAllEntitiesByCondition(map);
		return list;
	}

	/**
	 * 查询指定日期内的节假日
	 * 
	 * @throws FrameworkException
	 */
	public int countPwork(String time1, String time2) throws FrameworkException {
		Criteria criteria = hibernateTemplate.getSessionFactory()
				.getCurrentSession().createCriteria(SysDesignatedDate.class);
		criteria.add(Restrictions.eq("identifer", "w"));
		if (!BaseUtil.isEmpty(time1) && !BaseUtil.isEmpty(time2)) {
			criteria.add(Restrictions.between("edate", DateUtil.stringToDate(
					time1, CommonConstant.DATE_SHORT_FORMAT), DateUtil
					.stringToDate(time2, CommonConstant.DATE_SHORT_FORMAT)));
		}
		criteria.setProjection(Projections.countDistinct("id"));
		List<Long> count = criteria.list();
		return count.get(0).intValue();
	}

	/**
	 * 计算日期
	 * 
	 * @param time3
	 * @param days
	 * @return
	 * @throws ParseException
	 * @throws FrameworkException
	 */
	public SysDesignatedDate sysDesignatedDateDay(String time3, int days)
			throws ParseException, FrameworkException {
		Criteria criteria = hibernateTemplate.getSessionFactory()
				.getCurrentSession().createCriteria(SysDesignatedDate.class);
		criteria.add(Restrictions.in("identifer", new String[] { "w", "pw" }));
		criteria.add(Restrictions.ge("edate",
				DateUtil.stringToDate(time3, CommonConstant.DATE_SHORT_FORMAT)));
		criteria.addOrder(Order.asc("edate"));
		criteria.setFirstResult(0).setMaxResults(days);
		List<SysDesignatedDate> dates = criteria.list();
		if (!BaseUtil.isEmpty(dates) && dates.size() >= days) {
			SysDesignatedDate sdd = dates.get(days - 1);
			return sdd;
		}
		return null;
	}

	/**
	 * 查询指定日期内的工作日
	 * 
	 * @throws FrameworkException
	 */
	public int countWork(String time1, String time2) throws FrameworkException {
		Criteria criteria = hibernateTemplate.getSessionFactory()
				.getCurrentSession().createCriteria(SysDesignatedDate.class);
		criteria.add(Restrictions.eq("identifer", "pw"));
		if (!BaseUtil.isEmpty(time1) && !BaseUtil.isEmpty(time2)) {
			criteria.add(Restrictions.between("edate", DateUtil.stringToDate(
					time1, CommonConstant.DATE_SHORT_FORMAT), DateUtil
					.stringToDate(time2, CommonConstant.DATE_SHORT_FORMAT)));
		}
		criteria.setProjection(Projections.countDistinct("id"));
		List<Long> count = criteria.list();
		return count.get(0).intValue();
	}

	/**
	 * 查询指定的时间段的集合
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	public List<SysDesignatedDate> findTergetDate(Date beforDate, Date afterDate)
			throws FrameworkException {

		String hql = "FROM SysDesignatedDate sd WHERE sd.edate BETWEEN :beforDate AND :afterDate";
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("beforDate", beforDate);
		condition.put("afterDate", afterDate);
		return this.findAllEntitiesByCondition(hql, condition);

	}

	/**
	 * 调整节假日
	 * 
	 * @param time1
	 * @param time2
	 * @param identifer
	 * @throws FrameworkException
	 */
	public void adjustSysDesignatedDate(String time1, String time2,
			String identifer) throws FrameworkException {
		String hql = "UPDATE SysDesignatedDate sd SET sd.identifer = :identifer WHERE sd.edate BETWEEN :time1 AND :time2";
		hibernateTemplate
				.getSessionFactory()
				.getCurrentSession()
				.createQuery(hql)
				.setDate(
						"time1",
						DateUtil.stringToDate(time1,
								CommonConstant.DATE_SHORT_FORMAT))
				.setDate(
						"time2",
						DateUtil.stringToDate(time2,
								CommonConstant.DATE_SHORT_FORMAT))
				.setString("identifer", identifer).executeUpdate();

	}
}
