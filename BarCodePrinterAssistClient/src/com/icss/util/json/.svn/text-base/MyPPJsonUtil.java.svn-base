package com.icss.util.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icss.pp.agreement.MsgAgreement;

public class MyPPJsonUtil {
	// JSON对象
	private static ObjectMapper objectMapper = null;
	// 写入流
	private static Writer strWriter = null;
	// json字符串
	private static String strJson = "";
	// MsgAgreement
	private static MsgAgreement msgAgreement = null;

	private MyPPJsonUtil() {
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
	static public synchronized String entity2Json(MsgAgreement msg) {

		try {
			strWriter = new StringWriter();
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			objectMapper.writeValue(strWriter, msg);
			strJson = strWriter.toString();
			strWriter.flush();
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
			// 设置null字段不序列化剔除
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
			objectMapper.configure(
					DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
			// 设置null字段不序列化剔除
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
			objectMapper.configure(
					DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			msgAgreement = objectMapper.readValue(jsonStr.toString(),
					MsgAgreement.class);
		} catch (Exception e) {
			e.printStackTrace();
			msgAgreement = null;
		}

		return msgAgreement;
	}
}
