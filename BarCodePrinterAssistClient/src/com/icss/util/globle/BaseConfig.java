package com.icss.util.globle;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class BaseConfig {

	// 日志
	static private Logger log = Logger.getLogger("AC");
	
	// 服务端IP
	static public String inetHost = "192.168.30.218";

	// 服务端端口
	static public int inetPort = 53073;

	// 业务类型 1.商业打码 2.工业打码
	static public int ACType = 1;

	// 国家局时间服务器WEBSERVICE地址
	static public String WebServiceURL = "http://10.1.0.119:9080/sjsd_stma/services/STMA_TIME_LOCK_INFO_SERVICE";

	// 配置内容键值对集合
	static public Map<String, String> configMap = new HashMap<String, String>();

	/**
	 * 处理配置信息
	 * 
	 * @param args
	 */
	static public void doHandleConfigInfo(String[] args) {

		try {

			for (String strConfig : args) {
				configMap.put(strConfig.substring(0, strConfig.indexOf(":")),
						strConfig.substring(strConfig.indexOf(":") + 1));
			}

		} catch (Exception e) {
			System.out.println("bat文件中配置信息有误");
		}
	}

	/**
	 * 赋值配置信息
	 */
	static public void doAssignmentConfigInfo() {
		try {
			// 赋值IP
			inetHost = configMap.get("inetHost");

			// 赋值端口
			inetPort = Integer.parseInt(configMap.get("inetPort"));

			// 赋值业务类型
			ACType = Integer.parseInt(configMap.get("ACType"));

			// 赋值WebService地址
			WebServiceURL = configMap.get("WebServiceURL");

			System.out.println("配置信息：");
			System.out.println("inetHost:" + inetHost);
			System.out.println("inetPort:" + inetPort);
			System.out.println("ACType:" + ACType);
			System.out.println("WebServiceURL:" + WebServiceURL);

			log.info("配置信息："+"\r\n"
					+ "inetHost:" + inetHost+"\r\n"
					+ "inetPort:" + inetPort+"\r\n"
					+ "ACType:" + ACType+"\r\n"
					+ "WebServiceURL:" + WebServiceURL+"\r\n");
			
		} catch (Exception e) {
		}
	}

	/**
	 * 获取工程基础路径 EN
	 * 
	 * @return
	 */
	static public String doGetProjectBaseURL_EN() {

		try {

			String projectURL = BaseConfig.class.getClassLoader()
					.getResource("").getPath();
			
			if (projectURL.lastIndexOf("bin") > 0) {
				projectURL = projectURL.substring(1,
						projectURL.lastIndexOf("bin"));
			}

			return projectURL;
			
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 获取工程基础路径 CH
	 * 
	 * @return
	 */
	static public String doGetProjectBaseURL_CH() {
		try {
			return java.net.URLDecoder.decode(doGetProjectBaseURL_EN(), "utf-8");
		} catch (Exception e) {
			return doGetProjectBaseURL_EN();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(doGetProjectBaseURL_CH());
	}

}
