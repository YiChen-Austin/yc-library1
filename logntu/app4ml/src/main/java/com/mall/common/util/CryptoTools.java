package com.mall.common.util;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CryptoTools {
	private static CryptoTools cryptoTools;
	// DES加密的私钥，必须是8位长的字符串
	private static final byte[] DESkey = "mlltpwok".getBytes();// 设置密钥

	private static final byte[] DESIV = "mlltpwok".getBytes();// 设置向量

	static AlgorithmParameterSpec iv = null;// 加密算法的参数接口，IvParameterSpec是它的一个实现
	private static Key key = null;

	public static CryptoTools getInstance() {
		if (cryptoTools == null)
			try {
				cryptoTools = new CryptoTools();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return cryptoTools;
	}

	public CryptoTools() throws Exception {
		DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
		iv = new IvParameterSpec(DESIV);// 设置向量
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
		key = keyFactory.generateSecret(keySpec);// 得到密钥对象
	}

	public String encode2HexStrDelimi_(String data) throws Exception {
		Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
		enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
		byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
		return byteArr2HexStr(pasByte, "-");
	}

	public String encode2HexStrDelimi(String data, String salt)
			throws Exception {
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		// false 表示：生成32位的Hex版, 这也是encodeHashAsBase64的, Acegi 默认配置; true
		// 表示：生成24位的Base64版
		md5.setEncodeHashAsBase64(false);
		String pwd = md5.encodePassword(data, new String(DESkey));
		return md5.encodePassword(pwd.toUpperCase() + salt, salt)
				.toUpperCase();
	}

	public String decodeHexStrDelimi(String data) throws Exception {
		Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		deCipher.init(Cipher.DECRYPT_MODE, key, iv);
		String[] datas = data.split("-");
		byte[] bData = new byte[datas.length];
		for (int i = 0; i < datas.length; i++) {
			bData[i] = (byte) Integer.parseInt(datas[i], 16);
		}
		byte[] pasByte = deCipher.doFinal(bData);
		return new String(pasByte, "UTF-8");
	}

	public String encode2HexStr(String data) throws Exception {
		Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
		enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
		byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
		return byteArr2HexStr(pasByte);
	}

	public String encode2Base64(String data) throws Exception {
		Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
		enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
		byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
		BASE64Encoder base64Encoder = new BASE64Encoder();
		return base64Encoder.encode(pasByte);
	}

	public String decodeBase64(String data) throws Exception {
		Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		deCipher.init(Cipher.DECRYPT_MODE, key, iv);
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
		return new String(pasByte, "UTF-8");
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
	 * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和private byte[]
	 * hexStr2ByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB
	 *            -需要转换的byte数组
	 * @return String -转换后的字符串
	 * @throws Exception
	 */
	public String byteArr2HexStr(byte[] arrB, String delimit) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 加分隔符号
			if (sb.length() > 0) {
				sb.append(delimit);
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16).toUpperCase());
		}
		return sb.toString();
	}

	// 测试
	public static void main(String[] args) throws Exception {

//		CryptoTools tools = new CryptoTools();
//		System.out.println("加密:" + tools.encode2HexStrDelimi("123456"));
//		System.out.println("解密:"
//				+ tools.decodeHexStrDelimi("C5-14-A8-94-8A-21-16-CB"));
	}
}
