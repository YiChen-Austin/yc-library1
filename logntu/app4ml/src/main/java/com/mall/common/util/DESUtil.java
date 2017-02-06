package com.mall.common.util;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import com.mall.common.constant.CommonConstant;

/**
 * 
 * @功能：基于DES方式的加密、解密工具类
 * @作者：印鲜刚
 * @创建日期：2004-4-26
 * 
 */
public class DESUtil {
	private static Logger logger = Logger.getLogger(DESUtil.class);
	private static DESUtil instance;
	// DES密钥字符串
	private static final String strDefaultKey = CommonConstant.DES_KEY_DEFAULT;
	// 加密对象
	private Cipher encryptCipher = null;
	// 解密对象
	private Cipher decryptCipher = null;

	/**
	 * DES构造方法
	 * 
	 * @throws Exception
	 */
	private DESUtil() throws Exception {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		String strKey = null;// SystemResourceUtil.getInstance().getResourceAsString("DES_KEY");
		if (BaseUtil.isEmpty(strKey))
			strKey = strDefaultKey;
		logger.debug("strKey=" + strKey);
		Key key = getKey(strKey.getBytes());
		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	/**
	 * 利用单利模式获取一个DESPlus对象
	 * 
	 * @return DESPlus -DESPlus对象
	 * @throws Exception
	 */
	public static synchronized DESUtil getInstance() throws Exception {
		if (BaseUtil.isEmpty(instance))
			instance = new DESUtil();
		return instance;
	}

	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 * 
	 * @param arrBTmp
	 *            -构成该字符串的字节数组
	 * @return Key -生成的密钥
	 * @throws Exception
	 */
	private Key getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];
		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
		return key;
	}

	/**
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和private byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB
	 *            -需要转换的byte数组
	 * @return String -转换后的字符串
	 * @throws Exception
	 */
	public String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * 将表示16进制值的字符串转换为byte数组， 和private String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn
	 *            -需要转换的字符串
	 * @return byte[] -转换后的byte数组
	 * @throws Exception
	 */
	public byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * 加密字节数组
	 * 
	 * @param arrB
	 *            -需加密的字节数组
	 * @return byte[] -加密后的字节数组
	 * @throws Exception
	 */
	public byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}

	/**
	 * 加密字符串
	 * 
	 * @param strIn
	 *            -需加密的字符串
	 * @return String -加密后的字符串
	 * @throws Exception
	 */
	public String encrypt(String strIn) throws Exception {
		if (BaseUtil.isEmpty(strIn))
			return null;
		return byteArr2HexStr(encrypt(strIn.trim().getBytes()));
	}

	/**
	 * 解密字节数组
	 * 
	 * @param arrB
	 *            -需解密的字节数组
	 * @return byte[] -解密后的字节数组
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}

	/**
	 * 解密字符串
	 * 
	 * @param strIn
	 *            -需解密的字符串
	 * @return String -解密后的字符串
	 * @throws Exception
	 */
	public String decrypt(String strIn) throws Exception {
		if (BaseUtil.isEmpty(strIn))
			return null;
		return new String(decrypt(hexStr2ByteArr(strIn.trim())));
	}

	/************************************************************************/
	// 以下3DES
	private final String Algorithm = "DESede"; // 定义 加密算法,可用
												// DES,DESede,Blowfish
	private final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88,
			0x10, 0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
			0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
			(byte) 0xE2 }; // 24字节的密钥

	public String encryptMode(byte[] src) throws Exception {
		return byteArr2HexStr(encryptMode(keyBytes, src));
	}

	// keybyte为加密密钥，长度为24字节
	// src为被加密的数据缓冲区（源）
	public byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public byte[] decryptMode(String src) throws Exception {
		return decryptMode(keyBytes, hexStr2ByteArr(src));
	}

	// keybyte为加密密钥，长度为24字节
	// src为加密后的缓冲区
	public byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		// logger.debug(DESUtil.getInstance().encrypt("xgyin"));
		// logger.debug(DESUtil.getInstance().decrypt(
		// DESUtil.getInstance().encrypt("xgyin")));

		// 添加新安全算法,如果用JCE就要把它添加进去
		Security.addProvider(new com.sun.crypto.provider.SunJCE());

		final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10,
				0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
				0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
				(byte) 0xE2 }; // 24字节的密钥
		String szSrc = "This is a 3DES test. 测试";

		System.out.println("加密前的字符串:" + szSrc);

		byte[] encoded = DESUtil.getInstance().encryptMode(keyBytes,
				szSrc.getBytes());
//		System.out.println("加密后的字符串:" + byte2hex(encoded) + "\r\n"
//				+ DESUtil.getInstance().encryptMode(szSrc.getBytes()));
//
//		byte[] srcBytes = DESUtil.getInstance().decryptMode(keyBytes, encoded);
//		System.out
//				.println("解密后的字符串:"
//						+ (new String(srcBytes))
//						+ ","
//						+ (new String(
//								DESUtil.getInstance()
//										.decryptMode(
//												"46872e6c5eda9142d7c73208d2acd0cb72b8b36842ed5edaf2aef4e1171595c4"))));
	}

}