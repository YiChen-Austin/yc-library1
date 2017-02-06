package com.mall.common.util;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.util.ResourceUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.HybridBinarizer;

public class QRcodeUtil {
	public static Logger logger = Logger.getLogger(QRcodeUtil.class);

	private static final int BLACK = 0xFF000000;
	private static final int SELF_COLOR = 0xFFB81D21;
	private static final int WHITE = 0xFFFFFFFF;

	/**
	 * 解析二维码
	 * 
	 * @param file
	 *            二维码文件路径
	 * @return 二维码内容
	 */
	public static String decode(String file) {
		File f = new File(file);
		return decode(f);
	}

	/**
	 * 解析二维码
	 * 
	 * @param file
	 *            二维码文件
	 * @return 二维码内容
	 */
	public static String decode(File file) {
		return decode(file, "UTF-8");
	}

	/**
	 * 解析二维码
	 * 
	 * @param file
	 *            二维码文件
	 * @param charsetName
	 *            二维码字符集
	 * @return 二维码内容
	 */
	public static String decode(File file, String charsetName) {
		String value = null;
		try {
			Result result = null;
			BufferedImage image = null;

			image = ImageIO.read(file);
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, charsetName);

			result = new MultiFormatReader().decode(bitmap, hints);
			value = result.getText();
		} catch (Exception ex) {
			logger.error("解析二维码错误：");
			ex.printStackTrace();
		}
		return value;
	}

	/**
	 * @功能说明：纯二维码
	 * @param content
	 *            二维码内容
	 * @param width
	 *            宽度
	 * @param height
	 *            高流
	 * 
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void encode(String content, int width, int height,
			OutputStream output) throws WriterException, IOException {
		BitMatrix qrBm = content2Qr(content, width, height);
		BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(qrBm);
		ImageIO.write(qrImage, "jpeg", output);
	}

	/**
	 * @功能说明：推广二维码（带背景、二维码、顶层图片）
	 * @param content
	 *            二维码内容
	 * @param topFile
	 *            顶层图片
	 * @param output
	 *            输出流
	 * 
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void encodeSpread(String content, File topFile, String name,
			OutputStream output) throws WriterException, IOException {
		BitMatrix qrBm = content2Qr(content, 220, 220, 0);
		BufferedImage qrImage = toBufferedImage(qrBm, BLACK);
		URL bgUrl = ClassLoader.getSystemClassLoader().getResource(
				"/mall/logo/bgSpreadImg.jpg");

		File bgFile = null;
		if (bgUrl != null) {
			bgFile = new File(bgUrl.getFile());
		} else {
			bgFile = ResourceUtils
					.getFile("classpath:mall/logo/bgSpreadImg.jpg");
		}

		if (topFile == null || !topFile.exists()) {
			URL topUrl = ClassLoader.getSystemClassLoader().getResource(
					"/mall/logo/logo0.png");
			if (topUrl != null) {
				topFile = new File(topUrl.getFile());
			} else {
				topFile = ResourceUtils
						.getFile("classpath:mall/logo/logo0.png");
			}
		}
		composedImages(bgFile, qrImage, 424, 284, topFile, 100, 70,name,120, 210, output);
	}
	/**
	 * @功能说明：推广二维码（带背景、二维码、顶层图片）
	 * @param content
	 *            二维码内容
	 * @param topFile
	 *            顶层图片
	 * @param output
	 *            输出流
	 * 
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void encodeSpread(BufferedImage qrImage,String name,
			OutputStream output) throws WriterException, IOException {
		URL bgUrl = ClassLoader.getSystemClassLoader().getResource(
				"/mall/logo/bgSpreadImg.jpg");

		File bgFile = null;
		if (bgUrl != null) {
			bgFile = new File(bgUrl.getFile());
		} else {
			bgFile = ResourceUtils
					.getFile("classpath:mall/logo/bgSpreadImg.jpg");
		}
		composedImages(bgFile, qrImage, 424, 284, null, 100, 70,name,120, 210, output);
	}
	/**
	 * @功能说明：推广二维码（带背景、二维码、顶层图片）
	 * @param content
	 *            二维码内容
	 * @param topFile
	 *            顶层图片
	 * @param output
	 *            输出流
	 * 
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void encodeMyCode(String content, File topFile,
			OutputStream output) throws WriterException, IOException {
		BitMatrix qrBm = content2Qr(content, 300, 300);
		BufferedImage qrImage = toBufferedImage(qrBm, SELF_COLOR);
		URL bgUrl = ClassLoader.getSystemClassLoader().getResource(
				"/mall/logo/bgImage.jpg");

		File bgFile = null;
		if (bgUrl != null) {
			bgFile = new File(bgUrl.getFile());
		} else {
			bgFile = ResourceUtils.getFile("classpath:mall/logo/bgImage.jpg");
		}

		if (topFile == null || !topFile.exists()) {
			URL topUrl = ClassLoader.getSystemClassLoader().getResource(
					"/mall/logo/logo.png");
			if (topUrl != null) {
				topFile = new File(topUrl.getFile());
			} else {
				topFile = ResourceUtils.getFile("classpath:mall/logo/logo.png");
			}
		}
		composedImages(bgFile, qrImage, 126, 120, topFile, 266, 260, output);
	}

	/**
	 * @功能说明：我的二维码（带二维码、顶层图片）
	 * @param content
	 *            二维码内容
	 * @param topFile
	 *            顶层图片
	 * @param output
	 *            输出流
	 * 
	 * @throws WriterException
	 * @throws IOException
	 */
	// public static void encodeMyCode(String content, File topFile,
	// OutputStream output) throws WriterException, IOException {
	// BitMatrix qrBm = content2Qr(content, 300, 300);
	// BufferedImage qrImage = toBufferedImage(qrBm, SELF_COLOR);
	// composedImages(qrImage,topFile, 140, 140, output);
	// }

	/**
	 * @功能说明：我的二维码（带二维码、顶层图片）
	 * @param content
	 *            二维码内容
	 * @param topFile
	 *            顶层图片
	 * @param output
	 *            输出流
	 * 
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void encodeMyCode(String content, OutputStream output)
			throws WriterException, IOException {
		BitMatrix qrBm = content2Qr(content, 300, 300);
		BufferedImage qrImage = toBufferedImage(qrBm, SELF_COLOR);
		URL topUrl = ClassLoader.getSystemClassLoader().getResource(
				"/mall/logo/logo.png");
		File topFile = null;
		if (topUrl != null) {
			topFile = new File(topUrl.getFile());
		} else {
			topFile = ResourceUtils.getFile("classpath:mall/logo/logo.png");
		}
		composedImages(qrImage, topFile, 140, 140, output);
	}

	/**
	 * 
	 * @功能说明：二維碼、背景、logo三图片合,输出合成图片
	 * @param bgFile
	 *            背景文件
	 * @param qrImage
	 *            二维码图片
	 * @param xQr
	 *            二维码图片位置x
	 * @param yQr
	 *            二维码图片位置y
	 * @param topFile
	 *            顶层logo文件
	 * @param xTop
	 *            顶层图片位置x
	 * @param yTop
	 *            顶层图片位置y
	 * 
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void composedImages(BufferedImage qrImage, File topFile,
			int xTop, int yTop, OutputStream output) throws WriterException,
			IOException {
		Graphics2D bgQr = qrImage.createGraphics();
		Image topImage = null;
		if (topFile != null && topFile.exists()) {
			topImage = ImageIO.read(topFile);
			bgQr.drawImage(topImage, xTop, yTop, null);
		}
		bgQr.dispose();
		if (topFile != null && topFile.exists()) {
			topImage.flush();
		}
		qrImage.flush();
		ImageIO.write(qrImage, "jpeg", output);
	}

	/**
	 * 
	 * @功能说明：二維碼、背景、logo三图片合,输出合成图片
	 * @param bgFile
	 *            背景文件
	 * @param qrImage
	 *            二维码图片
	 * @param xQr
	 *            二维码图片位置x
	 * @param yQr
	 *            二维码图片位置y
	 * @param topFile
	 *            顶层logo文件
	 * @param xTop
	 *            顶层图片位置x
	 * @param yTop
	 *            顶层图片位置y
	 * 
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void composedImages(File bgFile, BufferedImage qrImage,
			int xQr, int yQr, File topFile, int xTop, int yTop,
			OutputStream output) throws WriterException, IOException {
		composedImages(bgFile, qrImage, xQr, yQr, topFile, xTop, yTop, null, 0,
				0, output);
	}

	/**
	 * 
	 * @功能说明：二維碼、背景、logo三图片合,输出合成图片
	 * @param bgFile
	 *            背景文件
	 * @param qrImage
	 *            二维码图片
	 * @param xQr
	 *            二维码图片位置x
	 * @param yQr
	 *            二维码图片位置y
	 * @param topFile
	 *            顶层logo文件
	 * @param xTop
	 *            顶层图片位置x
	 * @param yTop
	 *            顶层图片位置y
	 * 
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void composedImages(File bgFile, BufferedImage qrImage,
			int xQr, int yQr, File topFile, int xTop, int yTop, String name,
			int nx, int ny, OutputStream output) throws WriterException,
			IOException {
		BufferedImage bgImage = ImageIO.read(bgFile);
		Graphics2D bgGs = bgImage.createGraphics();
		bgGs.drawImage(qrImage, xQr, yQr, null);
		Image topImage = null;
		if (topFile != null && topFile.exists()) {
			topImage = ImageIO.read(topFile);
			bgGs.drawImage(topImage, xTop, yTop, null);
		}
		if (name != null && name.length() > 0) {
			Font f = new Font("MSYH",Font.BOLD ,25);
			bgGs.setFont(f);
			bgGs.drawString(name, nx, ny);
		}
		bgGs.dispose();
		if (topFile != null && topFile.exists()) {
			topImage.flush();
		}
		qrImage.flush();
		bgImage.flush();
		ImageIO.write(bgImage, "jpeg", output);
	}

	/**
	 * 内容生产二维码点阵
	 * 
	 * @throws WriterException
	 */
	public static BitMatrix content2Qr(String content, int width, int height)
			throws WriterException {
		return content2Qr(content, width, height, 2);
	}

	/**
	 * 内容生产二维码点阵
	 * 
	 * @throws WriterException
	 */
	public static BitMatrix content2Qr(String content, int width, int height,
			int margin) throws WriterException {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.MARGIN, margin);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.QR_CODE, width, height, hints);
		return bitMatrix;
	}

	/**
	 * 点阵转换成图片
	 */
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		return toBufferedImage(matrix, BLACK);
	}

	/**
	 * 点阵转换成图片
	 */
	public static BufferedImage toBufferedImage(BitMatrix matrix, int color) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? color : WHITE);
			}
		}
		return image;
	}

	// public static void main(String[] args) {
	// String val = QRcodeUtil.decode("d:/test_qrcode11.png");
	// // 解析二维码
	// System.out.println(val);
	// }
	public static void main(String[] args) {
		try {
			// get2CodeImage("www.baidu.com",
			// "C://Users//Administrator//desktop//xxxx.png",
			// "C://Users//Administrator//desktop//96.png");
			// encode2FileLogo("www.baidu.com",
			// "C://Users//Administrator//desktop//xxxx.png", "png");
			// encode2File("www.baidu.com",
			// "C://Users//Administrator//desktop//xxxx.png", "png");

			URL topUrl = ClassLoader.getSystemClassLoader().getResource(
					"/mall/logo/touxiang.png");

			File topFile = null;
			if (topUrl != null) {
				topFile = new File(topUrl.getFile());
			} else {
				topFile = ResourceUtils
						.getFile("classpath:mall/logo/touxiang.png");
			}
			FileOutputStream output = new FileOutputStream(
					"C:/Users/Administrator/desktop/qr.png");
			// encode("http://m.qqgo.cc", 300, 300, output);
			encodeSpread("http://m.qqgo.cc", topFile,"老白", output);
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}