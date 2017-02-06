package com.mall.common.dns;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.xbill.DNS.ARecord;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.MXRecord;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;


public class DNSServer {
	private static Logger logger = Logger.getLogger(DNSServer.class.getName());

	private static String getFixedMXTarget(String rawMXTarget) {
		if (rawMXTarget.lastIndexOf(" ") == -1) {
			return rawMXTarget;
		} else {
			int pos = rawMXTarget.lastIndexOf(" ");
			return rawMXTarget.substring(pos).trim();
		}
	}

	public static Vector<String> findMXServers(String hostname) {
		Vector<String> cols = new Vector<String>(1);
		try {
			Record[] records = new Lookup(hostname, Type.MX).run();
			if (records == null)
				return cols;
			for (int i = 0; i < records.length; i++) {
				MXRecord mx = (MXRecord) records[i];

				String rawMXTarget = mx.getTarget().toString();
				String fixedMXTarget = getFixedMXTarget(rawMXTarget);

				if (!cols.contains(fixedMXTarget))
					cols.add(fixedMXTarget);
			}
			return cols;
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return cols;
		}
	}

	private static boolean patternValid(String str, String pattern) {
		if (StringUtils.isEmpty(str))
			return false;
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public static Vector<String> findAServers(String hostname) {

		Vector<String> cols = new Vector<String>(1);

		if (hostname.endsWith(".")) {
			hostname = hostname.substring(0, hostname.length() - 1);
		}

		if (patternValid(hostname, "(\\d{1,3}\\.){3}\\d{1,3}")) {
			cols.add(hostname);
			return cols;
		}

		try {
			Record[] records = new Lookup(hostname, Type.A).run();
			if (records == null)
				return cols;
			for (int i = 0; i < records.length; i++) {
				ARecord a = (ARecord) records[i];
				if (!cols.contains(a.getAddress().getHostAddress()))
					cols.add(a.getAddress().getHostAddress());
			}
			return cols;
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return cols;
		}
	}

	public static Vector<String> findMXServer(String hostname) {
		Vector<String> cols = new Vector<String>(1);
		try {
			Record[] records = new Lookup(hostname, Type.MX).run();
			if (records == null)
				return cols;
			if (0 < records.length) {
				MXRecord mx = (MXRecord) records[(int) Math.round(Math.random()
						* (records.length - 1))];
				cols.add(mx.getTarget().toString());
			}
			return cols;
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return cols;
		}
	}

	public static String findAServer(String hostname) {
		String cols = null;
		try {
			Record[] records = new Lookup(hostname, Type.A).run();
			if (records == null)
				return cols;
			if (0 < records.length) {
				ARecord a = (ARecord) records[(int) Math.round(Math.random()
						* (records.length - 1))];
				cols = a.getAddress().getHostAddress();
			}
			return cols;
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return cols;
		}
	}

	public static void main(String[] args) {
		Vector<String> v = findMXServers("139.com");
		for (String s : v) {
			System.out.println(s+","+findAServer(s));
		}
	}
}