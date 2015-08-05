package com.icss.util.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.icss.util.globle.BaseConfig;

public class MybatisSqlSession {

	public static SqlSession sqlSession = null;
	public static SqlSessionFactory sqlSessionFactory = null;
	private static Reader reader = null;
	private static String resource = "";

	static {

		// 根据业务类型指定数据配置文件
		switch (BaseConfig.ACType) {
		case 1:
			resource = "mybatis-configuration-bp.xml";
			break;
		case 2:
			resource = "mybatis-configuration-pp.xml";
			break;
		default:
			break;
		}

		// 初始化SessionFactory
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	}

	/**
	 * Single
	 * 
	 * @return
	 */
	public static SqlSession getSqlSessionSingle() {
		sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}

	/**
	 * Prototype
	 * 
	 * @return
	 */
	public static SqlSession getSqlSessionPrototype() {
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new SqlSessionFactoryBuilder().build(reader).openSession();
	}

}
