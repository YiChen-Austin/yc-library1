package com.mall.chinapay;

import org.junit.Test;

import junit.framework.TestCase;

public class ChinaPayTest extends TestCase {
	@Test
	public void testPay() {
		chinapay.PrivateKey key = new chinapay.PrivateKey();
		chinapay.SecureLink sl;
		String MerId = "808080070894073";
		String OrdId = "0000000000000001";
		String TransAmt = "000000001234";
		String CuryId = "156";
		String TransDate = "20070801";
		String TransType = "0001";

		boolean flag = key
				.buildKey(
						MerId,
						0,
						"D:/develop/java/wzmall/test/main/resources/MerPrK_808080070894073_20140710091810.key");
		if (flag == false) {
			System.out.println("build key error!");
			return;
		}
		sl = new chinapay.SecureLink(key);
		String CheckValue1 = sl.signOrder(MerId, OrdId, TransAmt, CuryId,
				TransDate, TransType);
		System.out.println(CheckValue1);
		System.out.println(sl.Sign(MerId + OrdId + TransAmt + CuryId
				+ TransDate + TransType));
	}
}
