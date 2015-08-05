package com.icss.util.date;

import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.log4j.Logger;

import com.icss.util.globle.BaseConfig;
import com.icss.util.log.AcInfoLogBean;

/**
 * 
 * 调用国家局时间服务器WebService修改本地时间
 * @author Fuzhengwei
 *
 */
public class SyncTime {

	// 日志
	private Logger log = Logger.getLogger("AC");
	// 基础日志对象
	private AcInfoLogBean logInfoBean = new AcInfoLogBean();
	
	/**
	 * 获得国家局标准时间
	 * 
	 * @return
	 */
	public String doGetGJJStandardTime() {
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();// 通过service创建call对象
			call.setTargetEndpointAddress(new URL(BaseConfig.WebServiceURL));
			call.setOperationName(new QName(BaseConfig.WebServiceURL,
					"getCurrentTime"));
			String result = (String) call.invoke(new Object[] {});

			return result;
		} catch (Exception e) {
			
			logInfoBean.setOperation("链接国家局标准时间服务器失败");
			logInfoBean.setImpClass("SyncTime");
			logInfoBean.setImpMethod("doGetGJJStandardTime");
			logInfoBean.setErrMsg(e.getMessage());
			log.error(logInfoBean);
			
			return "";
		}
	}

	/**
	 * 修改本地时间为国家局标准时间
	 * 
	 * @return
	 */
	public boolean doModifyLocalTimeByGJJVPN() {

		String GJJTimeStr = doGetGJJStandardTime();

		try {

			if (GJJTimeStr != "") {
				String cmdYear = "cmd /c date " + GJJTimeStr.substring(0,GJJTimeStr.indexOf(" ")).trim();  
	            Process processYear = Runtime.getRuntime().exec(cmdYear);  
	            processYear.waitFor();  
				
	            String cmdTime = "cmd  /c  time " + GJJTimeStr.substring(GJJTimeStr.indexOf(" ")+1,GJJTimeStr.indexOf(".")).trim();  
	            Process processTime = Runtime.getRuntime().exec(cmdTime);  
	            processTime.waitFor();  
			}

		} catch (Exception e) {
			
			logInfoBean.setOperation("从国家局获取标准时间，修改本地时间失败");
			logInfoBean.setImpClass("SyncTime");
			logInfoBean.setImpMethod("doGetGJJStandardTime");
			logInfoBean.setErrMsg(e.getMessage());
			log.error(logInfoBean);
			
			return false;
		}

		return true;
	}

}
