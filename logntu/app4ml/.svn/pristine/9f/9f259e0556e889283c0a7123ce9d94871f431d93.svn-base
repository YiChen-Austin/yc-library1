package com.mall.web.mall.order.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mall.common.dao.BaseDao;
import com.mall.web.mall.domain.OrderTimeout;

/**
 * @Description(描述) : 订单超时日志
 * @author(作者) : ventrue
 * @date (开发日期) : 2015年9月1日 上午11:03:02
 */
@Repository("orderTimeoutDao")
public class OrderTimeoutDao extends BaseDao<OrderTimeout> {
	public OrderTimeout batchSaveData(final List<OrderTimeout> dataList,
			final int batchCount) {
		return this.hibernateTemplate
				.execute(new HibernateCallback<OrderTimeout>() {
					@Override
					public OrderTimeout doInHibernate(Session session)
							throws HibernateException {
						for (int i = 0; i < dataList.size(); i++) {
							session.save(dataList.get(i));
							// 如果想每1000条一次批处理，调用方法时batchCount传入1000
							if (i % batchCount == 0) {
								session.flush();
								session.clear();
							}
						}
						// 保存剩余的数据
						session.flush();
						session.clear();
						return null;
					}
				});
	}
}
