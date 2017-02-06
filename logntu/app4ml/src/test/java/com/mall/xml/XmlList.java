package com.mall.xml;

import java.io.File;
import java.io.FileFilter;

public class XmlList {
	public void myListFiles(String dir) {

		File directory = new File(dir);

		if (!directory.isDirectory()) {
			System.out.println("No directory provided");
			return;
		}

		File[] files = directory.listFiles(filefilter);

		for (File f : files) {
			System.out.println("<value>com/mall/domain/mall/" + f.getName()
					+ "</value>");
		}
	}

	// create a FileFilter and override its accept-method
	FileFilter filefilter = new FileFilter() {

		public boolean accept(File file) {
			// if the file extension is .txt return true, else false
			if (file.getName().endsWith(".hbm.xml")) {
				return true;
			}
			return false;
		}
	};

	public static void main(String[] args) {
		XmlList fileutil = new XmlList();
		fileutil.myListFiles("D:\\develop\\java\\wzmall\\src\\main\\java\\com\\mall\\domain\\mall");
	}
}
