package com.mall.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.mall.common.constant.CommonConstant;

/**
 * 
 * @功能：验证码工具类
 * @作者：印鲜刚
 * @创建日期：2004-4-26
 * 
 */
public class VerificationUtil {
	private static Logger logger = Logger.getLogger(VerificationUtil.class);
	private static VerificationUtil instance;
	private static final String defaultVerificationCheckCode = CommonConstant.VERIFICATION_CHECK_CODE_DEFAULT;

	/**
	 * 利用单利模式获取一个VerificationCode对象
	 * 
	 * @return VerificationCode -VerificationCode对象
	 * @throws Exception
	 */
	public static synchronized VerificationUtil getInstance() throws Exception {
		if (BaseUtil.isEmpty(instance))
			instance = new VerificationUtil();
		return instance;
	}

	/**
	 * 产生默认字符串的图形验证码
	 * 
	 * @param show
	 *            -条形码的长度
	 * @param output
	 *            -图形验证码图片输出流
	 * @return String -生成的验证码
	 * @throws Exception
	 */
	public String getCheckCodeImage(int show, OutputStream output) throws Exception {
		String defaultCheckCode = null;
		if (BaseUtil.isEmpty(defaultCheckCode))
			defaultCheckCode = defaultVerificationCheckCode;
		return getCheckCodeImage(defaultCheckCode, show, output);
	}

	/**
	 * 产生给定字符串图形验证码
	 * 
	 * @param str -给定字符串
	 * @param show -条形码的长度
	 * @param output -图形验证码图片输出流
	 * @return String -生成的验证码
	 * @throws Exception
	 */
	public String getCheckCodeImage(String str, int show, OutputStream output) throws Exception {
		Random random = new Random();
		BufferedImage image = new BufferedImage(76, 20,BufferedImage.TYPE_3BYTE_BGR);
		Font font = new Font("Arial", Font.PLAIN, 18);
		int distance = 15;
		Graphics d = image.getGraphics();
		d.setColor(Color.WHITE);
		d.fillRect(0, 0, image.getWidth(), image.getHeight());
		d.setColor(new Color(random.nextInt(100) + 100,random.nextInt(100) + 100, random.nextInt(100) + 100));
		for (int i = 0; i < 10; i++) {
			d.drawLine(random.nextInt(image.getWidth()),random.nextInt(image.getHeight()),random.nextInt(image.getWidth()),random.nextInt(image.getHeight()));
		}
		d.setColor(Color.BLACK);
		d.setFont(font);
		String checkCode = "";
		char tmp = 0;
		int x = -distance;
		for (int i = 0; i < show; i++) {
			tmp = str.charAt(random.nextInt(str.length() - 1));
			checkCode = checkCode + tmp;
			x = x + distance;
			d.setColor(new Color(random.nextInt(100) + 50,random.nextInt(100) + 50, random.nextInt(100) + 50));
			d.drawString(" " + tmp + " ", x,random.nextInt(image.getHeight() - (font.getSize())) + (font.getSize()));
		}
		d.dispose();
		ImageIO.write(image, "jpeg", output);
		return checkCode;
	}

	/**
	 * 产生给定字符串图形验证码
	 * 
	 * @param str
	 *            -给定字符串
	 * @param show
	 *            -条形码的长度
	 * @param output
	 *            -图形验证码图片输出流
	 * @return String -生成的验证码
	 * @throws Exception
	 */
	public void writeCheckCodeImage(String checkCode, OutputStream output)
			throws Exception {
		Random random = new Random();
		BufferedImage image = new BufferedImage(76, 20,BufferedImage.TYPE_3BYTE_BGR);
		Font font = new Font("Arial", Font.PLAIN, 18);
		int distance = 15;
		Graphics d = image.getGraphics();
		d.setColor(Color.WHITE);
		d.fillRect(0, 0, image.getWidth(), image.getHeight());
		d.setColor(new Color(random.nextInt(100) + 100,random.nextInt(100) + 100, random.nextInt(100) + 100));
		for (int i = 0; i < 10; i++) {
			d.drawLine(random.nextInt(image.getWidth()),random.nextInt(image.getHeight()),random.nextInt(image.getWidth()),random.nextInt(image.getHeight()));
		}
		d.setColor(Color.BLACK);
		d.setFont(font);

		char tmp = 0;
		int x = -distance;
		for (int i = 0; i < checkCode.length(); i++) {
			tmp = checkCode.charAt(i);
			x = x + distance;
			d.setColor(new Color(random.nextInt(100) + 50,random.nextInt(100) + 50, random.nextInt(100) + 50));
			d.drawString(" " + tmp + " ", x,random.nextInt(image.getHeight() - (font.getSize())) + (font.getSize()));
		}
		d.dispose();
		ImageIO.write(image, "jpeg", output);
	}

	/**
	 * 产生给定字符串图形验证码
	 * 
	 * @param show
	 *            -条形码的长度
	 * @return String -生成的验证码
	 * @throws Exception
	 */
	public String getCheckCodeImage(int show) throws Exception {
		return this.getCheckCodeImage(null, show);
	}

	/**
	 * 产生给定字符串图形验证码
	 * 
	 * @param str
	 *            -给定字符串
	 * @param show
	 *            -条形码的长度
	 * @return String -生成的验证码
	 * @throws Exception
	 */
	public String getCheckCodeImage(String str, int show) throws Exception {
		if (BaseUtil.isEmpty(str)) {
			str = defaultVerificationCheckCode;
		}
		Random random = new Random();
		String checkCode = "";
		char tmp = 0;
		for (int i = 0; i < show; i++) {
			tmp = str.charAt(random.nextInt(str.length() - 1));
			checkCode = checkCode + tmp;
		}
		return checkCode;
	}

	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream(new File("e:\\test.jpeg"));
		logger.debug("returnCode=" + VerificationUtil.getInstance().getCheckCodeImage(4, fos));
	}

}
