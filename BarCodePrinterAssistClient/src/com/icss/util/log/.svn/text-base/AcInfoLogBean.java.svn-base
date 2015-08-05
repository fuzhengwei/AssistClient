package com.icss.util.log;

/**
 * 基础日志Bean
 * @author FUZHENGWEI
 *
 */
public class AcInfoLogBean {

	//操作叙述
	private String Operation;
	//执行某类
	private String impClass;
	//执行方法
	private String impMethod;
	//执行结果
	private String operationResults;
	//错误信息
	private String errMsg;

	public String getOperation() {
		return Operation;
	}

	/**
	 * 操作叙述
	 * @param operation
	 */
	public void setOperation(String operation) {
		Operation = operation;
	}

	public String getImpClass() {
		return impClass;
	}

	/**
	 * 执行某类
	 * @param impClass
	 */
	public void setImpClass(String impClass) {
		this.impClass = impClass;
	}

	public String getImpMethod() {
		return impMethod;
	}

	/**
	 * 执行方法
	 * @param impMethod
	 */
	public void setImpMethod(String impMethod) {
		this.impMethod = impMethod;
	}

	public String getOperationResults() {
		return operationResults == null ? "":operationResults;
	}

	/**
	 * 执行结果
	 * @param operationResults
	 */
	public void setOperationResults(String operationResults) {
		this.operationResults = operationResults;
	}

	public String getErrMsg() {
		return errMsg == null ? "":errMsg;
	}

	/**
	 * 错误信息
	 * @param errMsg
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		return "操作叙述："+Operation+"\r\n"+
			   "执行某类："+impClass+"\r\n"+
			   "执行方法："+impMethod+"\r\n"+
			   "执行结果："+operationResults+"\r\n"+
			   "错误信息："+errMsg+"\r\n";
	}
	
}
