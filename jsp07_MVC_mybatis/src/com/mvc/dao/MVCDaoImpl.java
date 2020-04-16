package com.mvc.dao;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mvc.dto.MVCDto;


public class MVCDaoImpl implements MVCDao {

	@Override
	public List<MVCDto> selectList() {
		String resource = "com/mvc/db/config.xml";
		InputStream inputStream = null;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		List<MVCDto> list = session.selectList("com.mvc.mapper.selectList");
		session.close();
		
		return list;
	}

	@Override
	public MVCDto selectOne(int seq) {
		
		String resource = "com/mvc/db/config.xml";
		InputStream inputStream = null;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		MVCDto dto = session.selectOne("com.mvc.mapper.selectOne",seq);
		session.close();
		
		return dto;
	}

	@Override
	public int insert(MVCDto dto) {
		
		String resource = "com/mvc/db/config.xml";
		InputStream inputStream = null;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		int res = session.insert("com.mvc.mapper.insert",dto);
		session.commit();
		session.close();
	
		return res;
	}

	@Override
	public int update(MVCDto dto) {
		String resource = "com/mvc/db/config.xml";
		InputStream inputStream = null;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		int res = session.update("com.mvc.mapper.update",dto);
		session.commit();
		session.close();
		
		return res;
	}

	@Override
	public int delete(int seq) {
		String resource = "com/mvc/db/config.xml";
		InputStream inputStream = null;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		int res = session.delete("com.mvc.mapper.delete",seq);
		session.commit();
		session.close();
		
		
		return res;
	}

}
