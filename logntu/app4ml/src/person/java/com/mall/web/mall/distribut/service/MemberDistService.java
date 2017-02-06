package com.mall.web.mall.distribut.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.BaseUtil;
import com.mall.common.util.DateUtil;
import com.mall.web.mall.distribut.dao.MemberDistRelationDao;
import com.mall.web.mall.distribut.vo.MemberDistRelationVo;
import com.mall.web.mall.domain.MemberDistRelation;

/**
 * @Description(描述) :会员分销业务层 @author(作者) : ventrue
 * @date (开发日期) : 2015年8月6日 下午2:01:23
 */
@Service("memberDistService")
public class MemberDistService {
	private static Logger logger = Logger.getLogger(MemberDistService.class);

	@Resource(name = "memberDistRelationDao")
	private MemberDistRelationDao memberDistRelationDao;

	/***********************************************/
	/**
	 * @Description(功能描述) : 用户注册时，建立和上线用户的关系 @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2016年10月18日 上午11:08:42
	 * @param userId
	 *            : 用户id
	 * @param supId
	 *            : 直接上线用户
	 * @return
	 */
	@Transactional
	public boolean doMemberDist(int userId, int supId, String channel, String remark) {
		try {
			// 判断当前用户的关系是否存在
			MemberDistRelation dist = memberDistRelationDao.findDistRelation(userId);
			// 已存在，则不处理
			if (!BaseUtil.isEmpty(dist))
				return false;
			channel = (channel == null) ? "0" : channel;
			// 直接上级为平台supId=0
			if (supId == 0) {
				dist = new MemberDistRelation();
				dist.setUserId(userId);
				dist.setChannel(channel);
				dist.setCreateTime(DateUtil.getGMTDate());
				dist.setRemark(remark);
				memberDistRelationDao.save(dist);
				return true;
			}
			// 判断直接上级用户关系是否存在
			dist = memberDistRelationDao.findDistRelation(supId);
			// 不存在,则保存上级的多用户关系
			if (BaseUtil.isEmpty(dist)) {
				dist = new MemberDistRelation();
				dist.setUserId(supId);
				dist.setSup01(0);
				dist.setdLowerNum(1);//已有有当前用户作为直接下级
				dist.setaLowerNum(1);//已有有当前用户作为所有下级
				dist.setChannel(channel);
				dist.setCreateTime(DateUtil.getGMTDate());
				memberDistRelationDao.save(dist);
			}
			MemberDistRelation cdist = getLowerDist(dist);
			cdist.setUserId(userId);
			cdist.setChannel(channel);
			cdist.setCreateTime(DateUtil.getGMTDate());
			cdist.setRemark(remark);
			memberDistRelationDao.save(cdist);
			// 调整所有上线的下线数量
			memberDistRelationDao.batchDistNum(cdist);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e);
			return false;
		}
	}

	private MemberDistRelation getLowerDist(MemberDistRelation dist) {
		MemberDistRelation lower = new MemberDistRelation();
		lower.setSup01(dist.getUserId());
		lower.setSup02(dist.getSup01());
		lower.setSup03(dist.getSup02());
		lower.setSup04(dist.getSup03());
		lower.setSup05(dist.getSup04());
		lower.setSup06(dist.getSup05());
		lower.setSup07(dist.getSup06());
		lower.setSup08(dist.getSup07());
		lower.setSup09(dist.getSup08());
		lower.setSup10(dist.getSup09());
		lower.setSup11(dist.getSup10());
		lower.setSup12(dist.getSup11());
		lower.setSup13(dist.getSup12());
		lower.setSup14(dist.getSup13());
		lower.setSup15(dist.getSup14());
		return lower;
	}

	/***********************************************/

	/**
	 * @Description(功能描述) : 查找上级分销 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月7日 上午11:08:42
	 * @param userId
	 *            : 用户id
	 * @return
	 */
	public List<MemberDistRelationVo> findSuperior(int userId) {
		return memberDistRelationDao.findSuperior(userId);
	}

	/**
	 * @Description(功能描述) : 一级分销列表 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月7日 下午2:51:24
	 * @param userId
	 *            : 用户id
	 * @return
	 */
	public List<MemberDistRelationVo> findOneDist(int userId) {
		return memberDistRelationDao.findOneDist(userId);
	}

	/**
	 * @Description(功能描述) : 一级分销中成为微商人数 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月7日 下午2:51:24
	 * @param userId
	 *            : 用户id
	 * @return
	 */
	public int getOneDistCount4Store(int userId) {
		return memberDistRelationDao.getOneDistCount4Store(userId);
	}

	/**
	 * @Description(功能描述) : 二级分销列表 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月8日 下午1:20:10
	 * @param userId
	 *            : 用户id
	 * @return
	 */
	public List<MemberDistRelationVo> findTwoDist(int userId) {
		return memberDistRelationDao.findTwoDist(userId);
	}

	/**
	 * @Description(功能描述) : 三级分销列表 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年10月8日 下午1:20:10
	 * @param userId
	 *            : 用户id
	 * @return
	 */
	public List<MemberDistRelationVo> findThreeDist(int userId) {
		return memberDistRelationDao.findThreeDist(userId);
	}

	/**
	 * @Description(功能描述) : 保存分销信息 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年12月24日 下午2:02:25
	 */
	@Transactional
	public void saveDistRelation(MemberDistRelation entity) {
		try {
			memberDistRelationDao.save(entity);
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description(功能描述) : 更新分销信息 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年12月24日 下午2:02:25
	 */
	@Transactional
	public void updateDistRelation(MemberDistRelation entity) {
		try {
			memberDistRelationDao.update(entity);
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description(功能描述) : 根据用户查找分销信息 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年12月24日 下午2:30:24
	 * @param userId
	 *            : 用户信息
	 */
	public MemberDistRelation findDistRelation(int userId) {
		return memberDistRelationDao.findDistRelation(userId);
	}

	/**
	 * @Description(功能描述) : 判断分销关系是否存在 @author(作者) : wangliyou
	 * 
	 * @date (开发日期) : 2015年12月24日 下午2:30:24
	 * @param userId
	 *            : 用户id
	 * @param supperId
	 *            : 上级id
	 */
	public MemberDistRelation findDistRelationByUS(int userId, int supperId) {
		return memberDistRelationDao.findDistRelationByUS(userId, supperId);
	}

	/**
	 * @Description(功能描述) : 查找上三级代理 @author(作者) : ventrue
	 * 
	 * @date (开发日期) : 2015年10月8日 下午1:20:10
	 * @param userId
	 *            : 用户id
	 * @return
	 */
	public List<MemberDistRelationVo> findSupersDist(int userId) {
		return memberDistRelationDao.findSuperDist(userId);
	}

}
