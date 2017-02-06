package com.mall.common.geo.distance;

/**
 * 经纬度计算工具
 * @author ty
 *
 */
public class LatLonUtil {
	
	private static final double PI = 3.14159265;
	private static final double EARTH_RADIUS = 6378137;
	private static final double RAD = Math.PI/180.0;
	
	
	/**
	 * 根据当前位置坐标和半径获取最大或最小经纬度
	 * @param lat 当前位置纬度
	 * @param lon 当前位置经度
	 * @param radius 半径
	 * @return 最大或者最小经纬度数组
	 */
	public static double[] getAround(double lat, double lon, int radius){
		Double latitude = lat;
		Double longitude = lon;
		
		//地球周长24901英里，换算成单位米
		Double degree = (24901*1609)/360.0;
		double radiusMile = radius;
		
		Double dpmLat = 1/degree;
		Double radiusLat = dpmLat*radiusMile;
		Double minLat = latitude - radiusLat;
		Double maxLat = latitude + radiusLat;
		
		Double mpdLng = degree*Math.cos(latitude*(PI/180));
		Double dpmLng = 1/mpdLng;
		Double radiusLng = dpmLng*radiusMile;
		Double minLng = longitude - radiusLng;
		Double maxLng = longitude + radiusLng;
		
		return new double[]{minLat,minLng,maxLat,maxLng};
	}
	
	/**
	 * 根据两点之间坐标计算距离
	 * @param lat1 坐标1纬度
	 * @param lng1 坐标1经度
	 * @param lat2 坐标2纬度
	 * @param lng2 作弊2经度
	 * @return 两个坐标之间的距离
	 */
	public static double getDistance(double lat1, double lng1, double lat2, double lng2){
		double radLat1 = lat1 * RAD;
		double radLat2 = lat2 * RAD;
		double a = radLat1 - radLat2;
		double b = (lng1 - lng2) * RAD;
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
}
