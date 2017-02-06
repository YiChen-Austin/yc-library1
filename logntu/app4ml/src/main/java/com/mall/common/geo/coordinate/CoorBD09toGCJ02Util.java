package com.mall.common.geo.coordinate;

public class CoorBD09toGCJ02Util {
	private final static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

	// / <summary>
	// / 中国正常坐标系GCJ02协议的坐标，转到 百度地图对应的 BD09 协议坐标
	// / </summary>
	// / <param name="lat">维度</param>
	// / <param name="lng">经度</param>
	public static void Convert_GCJ02_To_BD09(double lat, double lng) {
		double x = lng, y = lat;
		double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
		lng = z * Math.cos(theta) + 0.0065;
		lat = z * Math.sin(theta) + 0.006;
	}

	public static double[] Convert_GCJ02_To_BD09(double[] latLng) {
		double x = latLng[1], y = latLng[0];
		double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
		latLng[1] = z * Math.cos(theta) + 0.0065;
		latLng[0] = z * Math.sin(theta) + 0.006;
		return latLng;
	}

	// / <summary>
	// / 百度地图对应的 BD09 协议坐标，转到 中国正常坐标系GCJ02协议的坐标
	// / </summary>
	// / <param name="lat">维度</param>
	// / <param name="lng">经度</param>
	public static void Convert_BD09_To_GCJ02(double lat, double lng) {
		double x = lng - 0.0065, y = lat - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
		lng = z * Math.cos(theta);
		lat = z * Math.sin(theta);
	}

	public static void main(String[] args) {
		double[] d = new double[] {29.587237,106.526888};
		d = Convert_GCJ02_To_BD09(d);
		System.out.println(d[1] + "," + d[0]);
	}
}
