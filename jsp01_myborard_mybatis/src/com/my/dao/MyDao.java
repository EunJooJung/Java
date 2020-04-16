package com.my.dao;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;

import com.my.dto.MyDto;

public class MyDao {
	
	
	
	public List<MyDto> selectList(){
		String resource = "com/my/db/mybatis-config.xml";	//driver, 계정 정보
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource); //driver, 계정연결
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		List<MyDto> list = session.selectList("com.my.mapper.selectList");
		session.close();
		
		return list;
	}
	
	
	public MyDto selectOne(int myno) {
		String resource = "com/my/db/mybatis-config.xml";
		InputStream inputStream = null;
		
		try {
			 inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		MyDto dto = session.selectOne("com.my.mapper.selectOne",myno);
		session.close();
		
		return dto;
	}
	
	
	public int insert(MyDto dto) {
		String resource = "com/my/db/mybatis-config.xml";
		InputStream inputStream = null;

		try {
			 inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int res = sqlSession.insert("com.my.mapper.insert",dto);
		
		sqlSession.commit();
		sqlSession.close();
		
		return res;
	}
	
	
	

	public int update(MyDto dto) {
		
	String resource = "com/my/db/mybatis-config.xml";
	InputStream inputStream = null;

	try {
		 inputStream = Resources.getResourceAsStream(resource);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	SqlSessionFactory sqlSessionFactory = 
			new SqlSessionFactoryBuilder().build(inputStream);
	
	SqlSession sqlSession = sqlSessionFactory.openSession();
	int res = sqlSession.update("com.my.mapper.update",dto);
	
	sqlSession.close();
	
	return res;
	
	}
	
	
	

	public int delete(int myno) {
		String resource = "com/my/db/mybatis-config.xml";
		InputStream inputStream = null;
		
		try {
			 inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		
		int res = session.delete("com.my.mapper.delete",myno);
		session.commit();
		session.close();
		
		
		return res;
	}
	

}


