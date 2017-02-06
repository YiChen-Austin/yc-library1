package com.mall.web.pay.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class Pkipair {

	public String signMsg(String signMsg) {

		String base64 = "";
		try {
			// 密钥仓库
			KeyStore ks = KeyStore.getInstance("PKCS12");

			// 读取密钥仓库（相对路径）
			// String file = Pkipair.class
			// .getResource("/keys/bill/tester-rsa.pfx").getPath()
			// .replaceAll("%20", " ");
			String file = Pkipair.class
					.getResource("/keys/bill/99bill.rsa.pfx").toURI().getPath()
					.replaceAll("%20", " ");

			FileInputStream ksfis = new FileInputStream(file);

			BufferedInputStream ksbufin = new BufferedInputStream(ksfis);

			// char[] keyPwd = "123456".toCharArray();//测试
			char[] keyPwd = "1qaz4rfv".toCharArray();// 生产
			ks.load(ksbufin, keyPwd);
			// 从密钥仓库得到私钥
			PrivateKey priK = (PrivateKey) ks.getKey("test-alias", keyPwd);
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initSign(priK);
			signature.update(signMsg.getBytes("utf-8"));
			sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
			base64 = encoder.encode(signature.sign());

		} catch (FileNotFoundException e) {
			System.out.println("文件找不到");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return base64;
	}

	public boolean enCodeByCer(String val, String msg) {
		boolean flag = false;
		try {
			// 获得文件(相对路径)
			String file = Pkipair.class
					.getResource("/keys/bill/99bill.cert.rsa.20340630.cer")
					.toURI().getPath();// 生产
			// String file =
			// Pkipair.class.getResource("/keys/bill/99bill[1].cert.rsa.20140803.cer").toURI().getPath();//测试
			FileInputStream inStream = new FileInputStream(file);

			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) cf
					.generateCertificate(inStream);
			// 获得公钥
			PublicKey pk = cert.getPublicKey();
			// 签名
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initVerify(pk);
			signature.update(val.getBytes());
			// 解码
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			System.out.println(new String(decoder.decodeBuffer(msg)));
			flag = signature.verify(decoder.decodeBuffer(msg));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}