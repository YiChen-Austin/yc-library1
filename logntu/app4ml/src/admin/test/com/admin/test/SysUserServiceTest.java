/**
 * 
 */
package com.admin.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mall.common.exception.FrameworkException;
import com.mall.common.util.DESUtil;
import com.mall.web.admin.security.service.SysMenuService;
import com.mall.web.admin.security.service.SysUserService;
import com.mall.web.common.util.IpSearchUtil;
import com.mall.web.pay.service.PayDataService;
import com.mall.web.pay.vo.BankPayVo;
import com.mall.web.pay.vo.BankThirdPayVo;

/**
 * @功能说明：系统人员业务层测试
 * @作者： 印鲜刚
 * @创建日期： 2010-5-6 @
 */

// @Transactional
@RunWith(SpringJUnit4ClassRunner.class)
// 使用junit4进行测试
@ContextConfiguration(locations = {
		"classpath:admin/spring/applicationContext.xml",
		"classpath:admin/spring/spring-hibernate.xml" })
public class SysUserServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	private static Logger logger = Logger.getLogger(SysUserServiceTest.class);

	@Autowired
	private SysUserService sysUserService;

	@Test
	public void findSysUserByName() throws FrameworkException {
		System.out.println(sysUserService.findSysUserByName("admin").getRealName());
	}
	@Test
	public void login() throws Exception {
		System.out.println(sysUserService.login("admin",DESUtil.getInstance().encrypt("123456")));
		System.out.println(DESUtil.getInstance().encrypt("123456"));
	}
}