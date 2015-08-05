package com.icss.util.xor;

/**
 *  
 * 工业打码条码密文解析
 * @author FUZHENGWEI
 *
 */
public class IcssXor {

	// 密文密钥，根据vb语言中随即种子固定[ICSS],得到的固定数值
	static private int[] rd = { 2, 42, 5, 57, 3, -4, 16, 54, 36 };
	
	/**
	 * 工业条码序列号解密
	 * @param str
	 * @return
	 */
	static public String DecodeString(String str) {

		String plain_text = "";
		try {
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				int ch = (int) c - 32;
				ch = (ch - rd[i]) % 95;
				if (ch < 0) {
					ch = ch + 95;
				} else {
					ch = ch + 32;
				}
				plain_text = plain_text + (char) ch;
			}
		} catch (Exception e) {
			plain_text = "157370000";
		}
		return plain_text;
	}
	
}
