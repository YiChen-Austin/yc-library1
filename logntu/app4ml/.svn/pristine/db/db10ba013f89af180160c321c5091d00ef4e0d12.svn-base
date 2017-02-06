package com.mall.web.mall.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.DateUtil;
import com.mall.web.mall.domain.MemberSign;
import com.mall.web.mall.member.dao.WebMemberSigDao;
import com.mall.web.mall.member.vo.WebMemberSignVo;

@Service("webMemberSignService")
public class WebMemberSignService {
	@Resource(name = "webMemberSigDao")
	private WebMemberSigDao webMemberSigDao;

	/**
	 * 会员签到
	 * 
	 * @param userId
	 *            会员id
	 * @return 返回连续签到次数(已经签到，则返回0，其他返回有效连续签到次数)
	 * @throws FrameworkException
	 */
	@Transactional
	public WebMemberSignVo memberSign(int userId, int maxSigns)
			throws FrameworkException {
		MemberSign sign = webMemberSigDao.get(userId);

		if (sign == null) {
			sign = new MemberSign();
			sign.setUserId(userId);
			sign.setSigns(1);
			sign.setContinuous(1);
			sign.setCurrDate(DateUtil.dateToStringYYYYMMHH(DateUtil
					.getGMTDate()));
			sign.setNextDate(DateUtil.dateToStringYYYYMMHH(DateUtil
					.getDateAfterDays(DateUtil.getGMTDate(), 1)));
			webMemberSigDao.save(sign);

			WebMemberSignVo vo = new WebMemberSignVo();
			vo.setUserId(sign.getUserId());
			vo.setContinuous(sign.getContinuous());
			vo.setSigns(sign.getSigns());
			return vo;
		} else {
			// 判断是否已经签到
			if (DateUtil.dateToStringYYYYMMHH(DateUtil.getGMTDate())
					.equalsIgnoreCase(sign.getCurrDate())) {
				return null;
			}
			WebMemberSignVo vo = new WebMemberSignVo();
			// 是否为连续签到
			if (DateUtil.dateToStringYYYYMMHH(DateUtil.getGMTDate())
					.equalsIgnoreCase(sign.getNextDate())) {
				sign.setContinuous(sign.getContinuous() + 1);
			} else {
				sign.setContinuous(1);
			}
			vo.setContinuous(sign.getContinuous());

			sign.setSigns(sign.getSigns() + 1);
			sign.setCurrDate(DateUtil.dateToStringYYYYMMHH(DateUtil
					.getGMTDate()));
			sign.setNextDate(DateUtil.dateToStringYYYYMMHH(DateUtil
					.getDateAfterDays(DateUtil.getGMTDate(), 1)));

			// 连续天数>最大天使，连续天数清0
			if (sign.getContinuous() >= maxSigns) {
				sign.setContinuous(0);
			}
			webMemberSigDao.update(sign);

			vo.setUserId(sign.getUserId());
			vo.setSigns(sign.getSigns());
			return vo;
		}
	}

	/**
	 * 会员签到
	 * 
	 * @param userId
	 *            会员id
	 * @return 返回连续签信息
	 * @throws FrameworkException
	 */
	@Transactional(readOnly = true)
	public WebMemberSignVo findMemberSign(int userId) {
		MemberSign sign;
		try {
			sign = webMemberSigDao.get(userId);
			WebMemberSignVo vo = new WebMemberSignVo();
			if (sign != null) {
				vo.setUserId(sign.getUserId());
				vo.setContinuous(sign.getContinuous());
				vo.setSigns(sign.getSigns());
				vo.setCurrDate(sign.getCurrDate());
			}
			return vo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
