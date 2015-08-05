package com.icss.junit.json;

import static org.junit.Assert.*;

import org.junit.Test;

import com.icss.bp.agreement.MsgAgreement;
import com.icss.util.json.MyBPJsonUtil;

public class JsonAgreement {

	@Test
	public void test() {
		
		
		
	}
	
	@Test
	public void entity2json(){
		
		MsgAgreement msgBP = new MsgAgreement();
		
		String str = MyBPJsonUtil.entity2Json(msgBP);
		
		System.out.println(str);
		
		com.icss.junit.agreement.MsgAgreement msg = com.icss.junit.json.MyJsonUtil.json2Entity(str);
		
		System.out.println(msg);
		
		String jsonStr = com.icss.junit.json.MyJsonUtil.entity2Json(msg);
		
		System.out.println(jsonStr);
		
	}

}
