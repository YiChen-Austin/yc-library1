package com.mall.common.util;

import java.util.Random;

public class OrderSnUtil {

	public static String createOrderSn(int userId, int storeId){
		long timeFrontTemp = System.currentTimeMillis()/1000000000;
		long timeEndTemp = System.currentTimeMillis()%10000;
		String idTemp = "";
		if(new Random().nextInt(2) > 0){
			idTemp += userId;
			idTemp += storeId;
		}else{
			idTemp += storeId;
			idTemp += userId;
		}
		return String.valueOf(timeFrontTemp) + idTemp + new Random().nextInt(1000) + timeEndTemp;
	}
}
