package com.mall.common.util;

import java.util.Random;

/**
 * 随机字符串/数生成。
 * @author sol
 */
public class RandomUtil
{
	/**
	 * <p>获取长度为 15 的随机字符串，字符范围为 [A-Z], [a-z], [0-9]。</p>
	 * <p>如果希望指定字符串长度，可调用 getRandomString(int)。</p>
	 * <p>如果希望指定字符范围，可调用 getRandomString(int, char[])</p>
	 * 
	 * @return 随机字符串
	 * @see getRandomString(int), getRandomString(int, char[])
	 */
	public static String getRandomString()
	{
		return getRandomString(RAND_STR_LEN);
	}
	
	/**
	 * <p>获取长度为 {@code len} 的随机字符串，字符范围为 [A-Z], [a-z], [0-9]。</p>
	 * <p>如果希望指定字符范围，可调用 getRandomString(int, char[])</p>
	 * 
	 * @param len	返回值长度
	 * @return 随机字符串
	 * @see getRandomString(), getRandomString(int, char[])
	 */
	public static String getRandomString(int len)
	{
		return getRandomString(len, RAND_STR_DICT);
	}
	
	/**
	 * <p>获取长度为 {@code len} 的随机字符串，字符范围为 {@code dict}。</p>
	 * 
	 * @param len		返回值长度
	 * @param dict		字符范围
	 * @return 随机字符串
	 * @see getRandomString(), getRandomString(int)
	 */
	public static String getRandomString(int len, char[] dict)
	{
		if (len == -1) {
			len = RAND_STR_LEN;
		}
		if (dict == null || dict.length == 0) {
			dict = RAND_STR_DICT;
		}
		
		StringBuffer b = new StringBuffer();
		Random r;
		int k;
		for (int i = 0; i < len; i++) {
			r = new java.util.Random();
			k = r.nextInt();
			b.append(dict[Math.abs(k % dict.length)]);
		}

		return b.toString();
	}
	
	private static final int RAND_STR_LEN = 15;
	private static final char[] RAND_STR_DICT = new char[] {
//		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
//		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
		// 备用：'~', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '|', '`', '.'
	};
	
	public static void main(String[] args)
	{
			System.out.println(getRandomString(3));
	}
}
