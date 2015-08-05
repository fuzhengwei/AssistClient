package com.icss.util.log;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;

import com.icss.util.globle.BaseConfig;

public class MyLogger {

	public static void main(String[] args) {
		MyLogger.doLoadLoggerInfo();
	}
	
	/**
	 * 加载日志配置 
	 */
	static public void doLoadLoggerInfo() {
		boolean isExitLog4j = false;
		String log4jURL = "";
		try {
			// 校验文件位置
			File file = new File(BaseConfig.doGetProjectBaseURL_CH());
			
			File[] files = file.listFiles();
			
			for (File conFile : files) {
				if(conFile.getName().endsWith("log4j.properties")){
					isExitLog4j = true;
					break;
				}
			}
			
			// 获取日志配置文件路径
			if(isExitLog4j){
				log4jURL = BaseConfig.doGetProjectBaseURL_CH() + "/" + "log4j.properties";
			}else{
				log4jURL = BaseConfig.doGetProjectBaseURL_CH() + "/" + "src" + "/" + "log4j.properties";
			}
			
			// 加载配置文件
			PropertyConfigurator.configure(log4jURL);

			System.out.println("加载日志配置：");
			System.out.println(log4jURL);
			
		} catch (Exception e) {
			System.out.println("日志信息加载失败");
			e.printStackTrace();
		}

	}

}
