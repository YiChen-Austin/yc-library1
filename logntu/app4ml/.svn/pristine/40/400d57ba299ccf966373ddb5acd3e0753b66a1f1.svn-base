package com.mall.common.util;

public class ShopIdUtil {
	/**
	 * 将店铺id进行伪码处理,运行伪码提供给界面显示
	 * 
	 * @param long shopId 店铺真实id
	 * @return 经过处理的店铺id
	 */
	public static String encodeShopId(long shopId) throws Exception {
		byte[] v = appendLong(shopId);
		// 去除前面0
		int start = 0;
		for (byte b : v) {
			if (b == 0)
				start++;
			else
				break;
		}
		byte[] sdata = new byte[8 - start + 1];
		sdata[0] = (byte) (8 - start);
		System.arraycopy(v, start, sdata, 1, sdata.length - 1);
		// 计算mac
		byte[] mac = smac(sdata);
		byte[] data = new byte[sdata.length + mac.length];
		System.arraycopy(sdata, 0, data, 0, sdata.length);
		System.arraycopy(mac, 0, data, sdata.length, mac.length);
		// 将结果处理为16进制串
		return DESUtil.getInstance().byteArr2HexStr(data);
	}

	/**
	 * 将店铺id进行伪码处理,运行伪码提供给界面显示
	 * 
	 * @param String
	 *            shopId 店铺id伪码
	 * @return 经过转换的店铺真实id
	 * @exception 提供的伪码格式不合法
	 *                ，则抛出异常
	 */
	public static long decodeShopId(String shopI) throws Exception {
		byte[] v = DESUtil.getInstance().hexStr2ByteArr(shopI);
		if (v.length <= 3)
			throw new Exception("the length is invalid.");

		byte[] sdata = new byte[v.length - 2];
		System.arraycopy(v, 0, sdata, 0, sdata.length);
		byte[] macs = new byte[2];
		System.arraycopy(v, v.length - 2, macs, 0, 2);
		byte[] macd = smac(sdata);
		if (!equip(macs[0], macd[0]) || !equip(macs[1], macd[1]))
			throw new Exception("the mac is invalid.");

		if (v[0] != sdata.length - 1)
			throw new Exception("the length is invalid.");

		byte[] data = new byte[8];
		System.arraycopy(sdata, 1, data, 8 - v[0], v[0]);
		return readLong(data);
	}

	public static byte[] appendLong(long data) {
		byte[] abyte0 = new byte[8];
		abyte0[7] = (byte) (data & 0xff);
		abyte0[6] = (byte) ((data >>> 8) & 0xff);
		abyte0[5] = (byte) ((data >>> 16) & 0xff);
		abyte0[4] = (byte) ((data >>> 24) & 0xff);
		abyte0[3] = (byte) ((data >>> 32) & 0xff);
		abyte0[2] = (byte) ((data >>> 40) & 0xff);
		abyte0[1] = (byte) ((data >>> 48) & 0xff);
		abyte0[0] = (byte) ((data >>> 56) & 0xff);
		return abyte0;
	}

	public static long readLong(byte[] buffer) {
		long result = 0;
		result |= buffer[0] & 0xff;
		result <<= 8;
		result |= buffer[1] & 0xff;
		result <<= 8;
		result |= buffer[2] & 0xff;
		result <<= 8;
		result |= buffer[3] & 0xff;
		result <<= 8;
		result |= buffer[4] & 0xff;
		result <<= 8;
		result |= buffer[5] & 0xff;
		result <<= 8;
		result |= buffer[6] & 0xff;
		result <<= 8;
		result |= buffer[7] & 0xff;
		return result;
	}

	/**
	 * 
	 * 计算MAC(hex)
	 * 
	 * @return mac
	 * @throws Exception
	 */

	private static byte[] smac(byte[] data) {
		byte[] v = null;
		try {
			v = DESUtil.getInstance().encrypt(data);
		} catch (Exception e) {
			return new byte[] { 10, 10 };
		}
		byte[] mac = new byte[2];
		if (v.length <= 2) {
			System.arraycopy(v, 0, mac, 0, v.length);
		} else {
			System.arraycopy(v, 0, mac, 0, mac.length);
		}
		return mac;
	}
    /**
     *判断两字节是否相等 
     */
	private static boolean equip(byte s, byte d) {
		int si = s;
		int di = d;
		if (si < 0)
			si = si + 256;
		if (di < 0)
			di = di + 256;
		return di == si;
	}

	public static void main(String[] args) throws Exception {
		String ddr="0386d650c4b2,03a380d92c04,020c38cd7d,037ca89b4e08,020c371e2d";
		String[] ddrArray=ddr.split(",");
		for(String str: ddrArray){
			System.out.println(str + " --> " + decodeShopId(str));
		}
		
		/*String shopId = encodeShopId(100001L);
		System.out.println(shopId + "," + decodeShopId(shopId));

		shopId = encodeShopId(100002L);
		System.out.println(shopId + "," + decodeShopId(shopId));*/
	}
}
