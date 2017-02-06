package com.mall.web.mall.member.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mall.web.mall.domain.FeedBack;
import com.mall.web.mall.member.dao.FeedBackDao;
import com.mall.web.mall.member.vo.FeedBackVo;

@Service("feedBackService")
public class FeedBackService {

	@Autowired
	private FeedBackDao feedBackDao;
	
	@Transactional
	public void save(FeedBackVo vo) throws Exception{
		FeedBack feedback = vo2Bean(vo);
		feedBackDao.save(feedback);
	}
	
	private FeedBack vo2Bean(FeedBackVo vo){
		FeedBack bean = new FeedBack();
		if(vo!=null){
			bean.setId(vo.getId());
			bean.setUserId(vo.getUserId());
			bean.setFeedBacktext(vo.getFeedBacktext());
			bean.setFeedBackimg(vo.getFeedBackimg());
			bean.setFeedBackdate(vo.getFeedBackdate());
		}
		return bean;
	}
}
