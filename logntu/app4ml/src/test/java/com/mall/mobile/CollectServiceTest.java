package com.mall.mobile;


import javax.annotation.Resource;

import junit.framework.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mall.web.mobile.member.dao.MobMemberDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/springHibernateTest.xml" })
public class CollectServiceTest extends TestCase{

	@Resource(name = "mobMemberDao")
	protected MobMemberDao mobMemberdao;
	
	@SuppressWarnings("deprecation")
	@Test
	public void test() {
	   boolean d=mobMemberdao.updateMobile(302, "18166317750", "444");
	   Assert.assertEquals(false, d);
	    
	}
   public static void main(String[] args){
	   String pw="222112333333";
	   byte[] bpw=pw.getBytes();
	   for(byte b:bpw){
		   System.out.println((int)b);
	   }
   }
}