package com.icss.main;

import com.icss.bp.control.BPBusinessControlImpl;
import com.icss.intfc.IACBusinessControl;
import com.icss.pp.control.PPBusinessControlImpl;
import com.icss.util.globle.BaseConfig;
import com.icss.util.log.MyLogger;

public class StartAC {

	public static void main(String[] args) {
		// 加载日志配置
		System.out.println("====== 加载日志配置信息 开始 ======");
		MyLogger.doLoadLoggerInfo();
		System.out.println("====== 加载日志配置信息 结束 ======\r\n");

		// 配置信息获取
		if (args != null && args.length > 0) {
			System.out.println("====== 加载基础配置信息加载 开始 ======");
			// 解析配置内容
			BaseConfig.doHandleConfigInfo(args);
			// 赋值配置信息
			BaseConfig.doAssignmentConfigInfo();
			System.out.println("====== 加载基础配置信息加载 完成 ======\r\n");
		}

		// 工程启动开始
		IACBusinessControl ac = null;

		// 根据业务类型加载模块
		switch (BaseConfig.ACType) {
		case 1:
			ac = new BPBusinessControlImpl();
			break;
		case 2:
			ac = new PPBusinessControlImpl();
			break;
		default:
			System.out.println("请配置有效参数");
			break;
		}
		ac.doOpenThreadPool();
	}

}
