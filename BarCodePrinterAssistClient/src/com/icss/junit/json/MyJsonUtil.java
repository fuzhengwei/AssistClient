package com.icss.junit.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icss.junit.agreement.MsgAgreement;

public class MyJsonUtil {

	// JSON对象
	private static ObjectMapper objectMapper = null;
	// 写入流
	private static Writer strWriter = null;
	// json字符串
	private static String strJson = "";
	// MsgAgreement
	private static MsgAgreement msgAgreement = null;

	private MyJsonUtil() {
	}

	static {
		objectMapper = new ObjectMapper();
	}

	/**
	 * entity2Json
	 * 
	 * @param msg
	 * @return
	 */
	static public String entity2Json(MsgAgreement msg) {

		try {
			strWriter = new StringWriter();
			objectMapper.writeValue(strWriter, msg);
			strJson = strWriter.toString();
			strWriter.flush();
		} catch (Exception e) {
		} finally {
			if (null != strWriter) {
				try {
					strWriter.close();
				} catch (IOException e) {
				}
				strWriter = null;
			}
		}

		return strJson;
	}

	/**
	 * json2Entity
	 * 
	 * @param jsonStr
	 * @return
	 */
	static public MsgAgreement json2Entity(String jsonStr) {

		try {
			msgAgreement = objectMapper.readValue(jsonStr, MsgAgreement.class);
		} catch (Exception e) {
			msgAgreement = null;
		}

		return msgAgreement;
	}
	

	/**
	 * json2Entity
	 * 
	 * @param jsonStr
	 * @return
	 */
	static public MsgAgreement json2Entity(Object jsonStr) {

		try {
			msgAgreement = objectMapper.readValue(jsonStr.toString(),
					MsgAgreement.class);
		} catch (Exception e) {
			e.printStackTrace();
			msgAgreement = null;
		}

		return msgAgreement;
	}

}
